/*
 * Copyright 2016 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.decisionservice.services

import cats.data.Xor
import uk.gov.hmrc.decisionservice.model.{DecisionServiceError, RulesFileError}
import uk.gov.hmrc.decisionservice.model.rules._
import uk.gov.hmrc.decisionservice.ruleengine._


trait DecisionService {
  val ruleEngine:RuleEngine = RuleEngineInstance

  val maybeSectionRules:Xor[DecisionServiceError,List[SectionRuleSet]]

  val csvSectionMetadata:List[RulesFileMetaData]

  def loadSectionRules():Xor[DecisionServiceError,List[SectionRuleSet]] = {
    val maybeRules = csvSectionMetadata.map(RulesLoaderInstance.load(_))
    val rulesErrors = maybeRules.collect {case Xor.Left(x) => x}
    val rules = maybeRules.collect{case Xor.Right(x) => x}
    rulesErrors match {
      case Nil => Xor.right(rules)
      case _ => Xor.left(rulesErrors.foldLeft(RulesFileError(""))(_ ++ _))
    }
  }

  def ==>:(facts:Facts):Xor[DecisionServiceError,RuleEngineDecision] = {
    maybeSectionRules match {
      case Xor.Right(sectionRules) =>
        ruleEngine.processRules(Rules(sectionRules),facts)
      case e@Xor.Left(_) => e
    }
  }
}


object DecisionServiceInstance extends DecisionService {
  lazy val maybeSectionRules = loadSectionRules()
  val csvSectionMetadata = List(
    (13, "/tables/control.csv", "control"),
    (24, "/tables/financial_risk.csv", "financial_risk"),
    (5,  "/tables/part_of_organisation.csv", "part_of_organisation"),
    (1,  "/tables/misc.csv", "miscellaneous"),
    (7,  "/tables/business_structure.csv", "business_structure"),
    (13, "/tables/personal_service.csv", "personal_service"),
    (6,  "/tables/matrix_of_matrices.csv", "matrix")
  ).collect{case (q,f,n) => RulesFileMetaData(q,f,n)}
}
