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

package uk.gov.hmrc.decisionservice.model.rules

import cats.data.Xor
import uk.gov.hmrc.decisionservice.model.DecisionServiceError
import uk.gov.hmrc.decisionservice.ruleengine.FactMatcherInstance
import play.api.Logger

case class Facts(facts:Map[String,CarryOver]){

  def ==+>:(rules:SectionRuleSet):Xor[DecisionServiceError,Facts] = {
    val defaultFactName = rules.section
    Logger.debug(s"matching for section:\t'$defaultFactName'")
    Logger.debug(s"headings:\t${rules.headings.mkString("\t,")}")
    Logger.debug(s"facts:   \t${rules.headings.map(facts.getOrElse(_, >>>("")).value).mkString("\t,")}")
    FactMatcherInstance.matchFacts(facts,rules).map { carryOver =>
      val factName = carryOver.name.getOrElse(defaultFactName)
      val newFact = (factName -> carryOver)
      Logger.debug(s"new fact:\t$factName -> '${carryOver.value}' ${if (carryOver.exit) "EXIT" else ""}")
      Facts(facts + newFact)
    }
  }

}
