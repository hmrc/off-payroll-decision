/*
 * Copyright 2022 HM Revenue & Customs
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

package uk.gov.hmrc.decisionservice.ruleEngines

import javax.inject.Inject
import uk.gov.hmrc.decisionservice.models.Score
import uk.gov.hmrc.decisionservice.models.enums.{DecisionServiceVersion, ExitEnum, ResultEnum, WeightedAnswerEnum}
import uk.gov.hmrc.decisionservice.ruleSets.MatrixOfMatricesRules

import scala.concurrent.Future

class ResultRuleEngine @Inject()() extends RuleEngine {

  def decide(score: Score)(implicit version: DecisionServiceVersion.Value): Future[ResultEnum.Value] = {
    Future.successful(score match {
      case Score(None, None, None, None, None, None, None) => ResultEnum.NOT_MATCHED
      case _ => checkResults(score) match {
        case Some(result) => result
        case _ => checkMatrixOfMatrices(score, version)
      }
    })
  }

  def checkMatrixOfMatrices(score: Score, version: DecisionServiceVersion.Value): ResultEnum.Value = {
    val rules = MatrixOfMatricesRules(version)
    val result = checkRules(score, rules.ruleSet, ResultEnum.NOT_MATCHED)
    ResultEnum(result)
  }

  def checkResults(score: Score): Option[ResultEnum.Value] = {

    val sectionAnswers = Seq(score.personalService, score.control, score.financialRisk, score.partAndParcel, score.businessOnOwnAccount).flatten

    if (score.exit.contains(ExitEnum.INSIDE_IR35)) {
      Some(ResultEnum.INSIDE_IR35)
    } else if (sectionAnswers.contains(WeightedAnswerEnum.OUTSIDE_IR35)) {
      Some(ResultEnum.OUTSIDE_IR35)
    } else {
      None
    }
  }
}
