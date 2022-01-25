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
import uk.gov.hmrc.decisionservice.models.BusinessOnOwnAccount
import uk.gov.hmrc.decisionservice.models.enums.{DecisionServiceVersion, WeightedAnswerEnum}
import uk.gov.hmrc.decisionservice.ruleSets.BusinessOnOwnAccountRules

class BusinessOnOwnAccountRuleEngineSpec extends AnyWordSpecLike with Matchers {

  object TestBusinessOnOwnAccountRuleEngine extends BusinessOnOwnAccountRuleEngine

  "BusinessOnOwnAccountDecisionService" when {

    "decide is called with a BoOA section that triggers rules" should {

      DecisionServiceVersion.values.filterNot(_ == DecisionServiceVersion.v1_5_0).foreach { version =>

        s"for rule engine version $version" should {

          s"return the correct expected result" in {

            BusinessOnOwnAccountRules(version).ruleSet.foreach { ruleSet =>

              val actualAnswer = TestBusinessOnOwnAccountRuleEngine.decide(Some(ruleSet.rules.as[BusinessOnOwnAccount]))(version)
              val expectedAnswer = WeightedAnswerEnum.withName(ruleSet.result)

              await(actualAnswer) shouldBe Some(expectedAnswer)
            }
          }
        }
      }
    }

    "decide is called with no input values supplied" should {

      "return a default score of medium" in {

        val actualResult = TestBusinessOnOwnAccountRuleEngine.decide(Some(BusinessOnOwnAccount(None, None, None, None, None)))(DecisionServiceVersion.v2_4)
        val expectedResult = Some(WeightedAnswerEnum.MEDIUM)
        
        await(actualResult) shouldBe expectedResult
      }
    }
  }
}
