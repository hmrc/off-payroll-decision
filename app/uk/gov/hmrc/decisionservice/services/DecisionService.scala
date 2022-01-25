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

package uk.gov.hmrc.decisionservice.services

import com.google.inject.Inject
import uk.gov.hmrc.decisionservice.models._
import uk.gov.hmrc.decisionservice.models.enums.{DecisionServiceVersion, SetupEnum, WeightedAnswerEnum}
import uk.gov.hmrc.decisionservice.ruleEngines._

import scala.concurrent.{ExecutionContext, Future}

class DecisionService @Inject()(controlRuleEngine: ControlRuleEngine,
                                earlyExitRuleEngine: ExitRuleEngine,
                                financialRiskRuleEngine: FinancialRiskRuleEngine,
                                personalServiceRuleEngine: PersonalServiceRuleEngine,
                                partAndParcelRuleEngine: PartAndParcelRuleEngine,
                                resultRuleEngine: ResultRuleEngine,
                                businessOnOwnAccountRuleEngine: BusinessOnOwnAccountRuleEngine) {

  def calculateResult(request: DecisionRequest)(implicit ec: ExecutionContext): Future[DecisionResponse] = {

    val interview = request.interview
    implicit val version: DecisionServiceVersion.Value = request.version

    for {
      exit <- earlyExitRuleEngine.decide(interview.exit)
      personalService <- personalServiceRuleEngine.decide(interview.personalService)
      control <- controlRuleEngine.decide(interview.control)
      financialRisk <- financialRiskRuleEngine.decide(interview.financialRisk)
      partAndParcel <- partAndParcelRuleEngine.decide(interview.partAndParcel)
      businessOnOwnAccount <- businessOnOwnAccountRuleEngine.decide(interview.businessOnOwnAccount)
      score = Score(Some(SetupEnum.CONTINUE), exit, personalService, control, financialRisk, partAndParcel, businessOnOwnAccount)
      scoreWithoutBooa = Score(Some(SetupEnum.CONTINUE), exit, personalService, control, financialRisk, partAndParcel, Some(WeightedAnswerEnum.MEDIUM))
      result <- resultRuleEngine.decide(score)
      resultWithoutBooa <- resultRuleEngine.decide(scoreWithoutBooa)
    } yield DecisionResponse(request.version, request.correlationID, score, result, resultWithoutBooa)
  }
}
