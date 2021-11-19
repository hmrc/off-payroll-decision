/*
 * Copyright 2021 HM Revenue & Customs
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

package uk.gov.hmrc.decisionservice.config.ruleSets

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import play.api.libs.json.JsObject
import uk.gov.hmrc.decisionservice.models.RuleSet
import uk.gov.hmrc.decisionservice.util.TestFixture

trait BaseRuleSpec extends TestFixture with AnyWordSpecLike with Matchers {

  def getRules[E <: Enumeration](weighting: E#Value)(implicit ruleSet: Seq[RuleSet]) =
    ruleSet.filter(_.result == weighting.toString).map(_.rules).toList

  def checkRules(expected: List[JsObject], actual: List[JsObject]): Unit = {
    expected.foreach { rule =>
      s"contain the rule: $rule" in {
        assert(actual.contains(rule))
      }
    }

    "Contain the correct number of rules" in {
      expected.length shouldBe actual.length
    }
  }
}
