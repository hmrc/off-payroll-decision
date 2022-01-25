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

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import play.api.test.Helpers.{await, defaultAwaitTimeout}
import uk.gov.hmrc.decisionservice.models.Score
import uk.gov.hmrc.decisionservice.models.enums.{DecisionServiceVersion, ExitEnum, ResultEnum, WeightedAnswerEnum}
import uk.gov.hmrc.decisionservice.ruleSets.MatrixOfMatricesRules

class ResultRuleEngineSpec extends AnyWordSpecLike with Matchers {

  implicit val defaultDecisionServiceVersion: DecisionServiceVersion.Value = DecisionServiceVersion.v1_5_0

  object TestResultDecisionService extends ResultRuleEngine

  "ResultService" when {

    "decide is called with all sections populated for a determination" should {

      DecisionServiceVersion.values.foreach { version =>

        s"for rule engine version $version" should {

          s"return the correct expected result" in {

            MatrixOfMatricesRules(version).ruleSet.foreach { ruleSet =>

              val actualAnswer = TestResultDecisionService.decide(ruleSet.rules.as[Score])(version)
              val expectedAnswer = ResultEnum(ruleSet.result)

              await(actualAnswer) shouldBe expectedAnswer
            }
          }
        }
      }
    }

    "decide is called with None for every section" should {

      "return a result" in {

        val expectedAnswer = ResultEnum.NOT_MATCHED
        val actualAnswer = TestResultDecisionService.decide(Score())(DecisionServiceVersion.v1_5_0)

        await(actualAnswer) shouldBe expectedAnswer

      }
    }

    "decide is called with Exit section being an IN decision" should {

      "return an IN result" in {

        val score = Score(
          setup = None,
          exit = Some(ExitEnum.CONTINUE),
          personalService = Some(WeightedAnswerEnum.OUTSIDE_IR35),
          control = Some(WeightedAnswerEnum.OUTSIDE_IR35),
          financialRisk = Some(WeightedAnswerEnum.OUTSIDE_IR35),
          partAndParcel = Some(WeightedAnswerEnum.OUTSIDE_IR35)
        )

        val expectedAnswer = ResultEnum.INSIDE_IR35
        val actualAnswer = TestResultDecisionService.decide(score.copy(exit = Some(ExitEnum.INSIDE_IR35)))
        await(actualAnswer) shouldBe expectedAnswer
      }
    }

    "decide is called with many sections containing an IN decision and ignore any OUT decisions" should {

      "return an IN result" in {

        val expectedAnswer = ResultEnum.INSIDE_IR35
        val actualAnswer = TestResultDecisionService.decide(Score(None, Some(ExitEnum.INSIDE_IR35), Some(WeightedAnswerEnum.OUTSIDE_IR35),
          Some(WeightedAnswerEnum.OUTSIDE_IR35), Some(WeightedAnswerEnum.INSIDE_IR35), Some(WeightedAnswerEnum.OUTSIDE_IR35)))

        await(actualAnswer) shouldBe expectedAnswer

      }
    }

    "decide is called with a section containing an OUT decision with no IN decisions present" should {

      val score = Score(
        setup = None,
        exit = Some(ExitEnum.CONTINUE),
        personalService = Some(WeightedAnswerEnum.HIGH),
        control = Some(WeightedAnswerEnum.HIGH),
        financialRisk = Some(WeightedAnswerEnum.HIGH),
        partAndParcel = Some(WeightedAnswerEnum.HIGH)
      )

      val outside = Some(WeightedAnswerEnum.OUTSIDE_IR35)
      val expectedAnswer = ResultEnum.OUTSIDE_IR35

      "return an OUT result when personalService is OUT" in {
        val actualAnswer = TestResultDecisionService.decide(score.copy(personalService = outside))
        await(actualAnswer) shouldBe expectedAnswer
      }

      "return an OUT result when control is OUT" in {
        val actualAnswer = TestResultDecisionService.decide(score.copy(control = outside))
        await(actualAnswer) shouldBe expectedAnswer
      }

      "return an OUT result when financialRisk is OUT" in {
        val actualAnswer = TestResultDecisionService.decide(score.copy(financialRisk = outside))
        await(actualAnswer) shouldBe expectedAnswer
      }

      "return an OUT result when partAndParcel is OUT" in {
        val actualAnswer = TestResultDecisionService.decide(score.copy(partAndParcel = outside))
        await(actualAnswer) shouldBe expectedAnswer
      }
    }

    "decide is called with only one section supplied" should {

      "return a result" in {

        val expectedAnswer = ResultEnum.NOT_MATCHED
        val actualAnswer = TestResultDecisionService.decide(Score(financialRisk = Some(WeightedAnswerEnum.HIGH)))

        await(actualAnswer) shouldBe expectedAnswer

      }
    }
  }

}
