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

import org.mockito.ArgumentMatchers
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import org.mockito.MockitoSugar
import play.api.test.Helpers.{await, defaultAwaitTimeout}
import uk.gov.hmrc.decisionservice.models._
import uk.gov.hmrc.decisionservice.models.enums._
import uk.gov.hmrc.decisionservice.ruleEngines._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class DecisionServiceSpec extends AnyWordSpecLike with Matchers {

  private trait Setup extends MockitoSugar {
    val exit = mock[ExitRuleEngine]
    val control = mock[ControlRuleEngine]
    val personalService = mock[PersonalServiceRuleEngine]
    val financialRisk = mock[FinancialRiskRuleEngine]
    val partAndParcel = mock[PartAndParcelRuleEngine]
    val businessOnOwnAccount = mock[BusinessOnOwnAccountRuleEngine]
    val result = mock[ResultRuleEngine]

    val target = new DecisionService(control,exit,financialRisk,personalService,partAndParcel,result,businessOnOwnAccount)
  }

  "DecisionService" when {

    "provided a decision request" should {

      "calculate the result" in new Setup {

        val request = DecisionRequest(
          DecisionServiceVersion.v2_4, "coral", Interview(
            setup = Some(Setup(None, None, None)),
            exit = Some(Exit(None)),
            personalService = Some(PersonalService(None, None, None, None, None)),
            control = Some(Control(None, None, None, None)),
            financialRisk = Some(FinancialRisk(None, None, None, None, None, None, None)),
            partAndParcel = Some(PartAndParcel(None, None, None, None)),
            businessOnOwnAccount = Some(BusinessOnOwnAccount(None, None, None, None, None))

          )
        )

        when(exit.decide(ArgumentMatchers.any())(ArgumentMatchers.any())).thenReturn(Future.successful(Some(ExitEnum.CONTINUE)))
        when(control.decide(ArgumentMatchers.any())(ArgumentMatchers.any())).thenReturn(Future.successful(Some(WeightedAnswerEnum.HIGH)))
        when(personalService.decide(ArgumentMatchers.any())(ArgumentMatchers.any())).thenReturn(Future.successful(Some(WeightedAnswerEnum.HIGH)))
        when(financialRisk.decide(ArgumentMatchers.any())(ArgumentMatchers.any())).thenReturn(Future.successful(Some(WeightedAnswerEnum.HIGH)))
        when(partAndParcel.decide(ArgumentMatchers.any())(ArgumentMatchers.any())).thenReturn(Future.successful(Some(WeightedAnswerEnum.HIGH)))
        when(businessOnOwnAccount.decide(ArgumentMatchers.any())(ArgumentMatchers.any())).thenReturn(Future.successful(Some(WeightedAnswerEnum.HIGH)))


        when(result.decide(ArgumentMatchers.eq(Score(Some(SetupEnum.CONTINUE),Some(ExitEnum.CONTINUE),
          Some(WeightedAnswerEnum.HIGH),Some(WeightedAnswerEnum.HIGH),Some(WeightedAnswerEnum.HIGH),
          Some(WeightedAnswerEnum.HIGH),Some(WeightedAnswerEnum.HIGH))))(ArgumentMatchers.any()))
          .thenReturn(Future.successful(ResultEnum.INSIDE_IR35))

        when(result.decide(ArgumentMatchers.eq(Score(Some(SetupEnum.CONTINUE),Some(ExitEnum.CONTINUE),
          Some(WeightedAnswerEnum.HIGH),Some(WeightedAnswerEnum.HIGH),Some(WeightedAnswerEnum.HIGH),
          Some(WeightedAnswerEnum.HIGH),Some(WeightedAnswerEnum.MEDIUM))))(ArgumentMatchers.any()))
          .thenReturn(Future.successful(ResultEnum.INSIDE_IR35))

        await(target.calculateResult(request)) shouldBe DecisionResponse(
          version = DecisionServiceVersion.v2_4, correlationID = "coral", score = Score(
            setup = Some(SetupEnum.CONTINUE),
            exit = Some(ExitEnum.CONTINUE),
            personalService = Some(WeightedAnswerEnum.HIGH),
            control = Some(WeightedAnswerEnum.HIGH),
            financialRisk = Some(WeightedAnswerEnum.HIGH),
            partAndParcel = Some(WeightedAnswerEnum.HIGH),
            businessOnOwnAccount = Some(WeightedAnswerEnum.HIGH)
          ),
          result = ResultEnum.INSIDE_IR35,
          resultWithoutBooa = ResultEnum.INSIDE_IR35
        )
      }

      "not fail if compare result logs an error" in new Setup {

        val request = DecisionRequest(
          DecisionServiceVersion.v2_4, "coral", Interview(
            setup = Some(Setup(None, None, None)),
            exit = Some(Exit(None)),
            personalService = Some(PersonalService(None, None, None, None, None)),
            control = Some(Control(None, None, None, None)),
            financialRisk = Some(FinancialRisk(None, None, None, None, None, None, None)),
            partAndParcel = Some(PartAndParcel(None, None, None, None)),
            businessOnOwnAccount = Some(BusinessOnOwnAccount(None, None, None, None, None))
          )
        )

        when(exit.decide(ArgumentMatchers.any())(ArgumentMatchers.any())).thenReturn(Future.successful(Some(ExitEnum.CONTINUE)))
        when(control.decide(ArgumentMatchers.any())(ArgumentMatchers.any())).thenReturn(Future.successful(Some(WeightedAnswerEnum.HIGH)))
        when(personalService.decide(ArgumentMatchers.any())(ArgumentMatchers.any())).thenReturn(Future.successful(Some(WeightedAnswerEnum.HIGH)))
        when(financialRisk.decide(ArgumentMatchers.any())(ArgumentMatchers.any())).thenReturn(Future.successful(Some(WeightedAnswerEnum.HIGH)))
        when(partAndParcel.decide(ArgumentMatchers.any())(ArgumentMatchers.any())).thenReturn(Future.successful(Some(WeightedAnswerEnum.HIGH)))
        when(businessOnOwnAccount.decide(ArgumentMatchers.any())(ArgumentMatchers.any())).thenReturn(Future.successful(Some(WeightedAnswerEnum.HIGH)))

        when(result.decide(ArgumentMatchers.eq(Score(Some(SetupEnum.CONTINUE),Some(ExitEnum.CONTINUE),
          Some(WeightedAnswerEnum.HIGH),Some(WeightedAnswerEnum.HIGH),Some(WeightedAnswerEnum.HIGH),
          Some(WeightedAnswerEnum.HIGH),Some(WeightedAnswerEnum.HIGH))))(ArgumentMatchers.any()))
          .thenReturn(Future.successful(ResultEnum.INSIDE_IR35))

        when(result.decide(ArgumentMatchers.eq(Score(Some(SetupEnum.CONTINUE),Some(ExitEnum.CONTINUE),
          Some(WeightedAnswerEnum.HIGH),Some(WeightedAnswerEnum.HIGH),Some(WeightedAnswerEnum.HIGH),
          Some(WeightedAnswerEnum.HIGH),Some(WeightedAnswerEnum.MEDIUM))))(ArgumentMatchers.any()))
          .thenReturn(Future.successful(ResultEnum.OUTSIDE_IR35))

        await(target.calculateResult(request)) shouldBe DecisionResponse(
          version = DecisionServiceVersion.v2_4, correlationID = "coral", score = Score(
            setup = Some(SetupEnum.CONTINUE),
            exit = Some(ExitEnum.CONTINUE),
            personalService = Some(WeightedAnswerEnum.HIGH),
            control = Some(WeightedAnswerEnum.HIGH),
            financialRisk = Some(WeightedAnswerEnum.HIGH),
            partAndParcel = Some(WeightedAnswerEnum.HIGH),
            businessOnOwnAccount = Some(WeightedAnswerEnum.HIGH)
          ),
          result = ResultEnum.INSIDE_IR35,
          resultWithoutBooa = ResultEnum.OUTSIDE_IR35
        )
      }
    }
  }
}
