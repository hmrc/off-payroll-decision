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

package uk.gov.hmrc.decisionservice.config.ruleSets.v2_4

import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.libs.json.Json
import uk.gov.hmrc.decisionservice.config.ruleSets.BaseRuleSpec
import uk.gov.hmrc.decisionservice.models.Exit
import uk.gov.hmrc.decisionservice.models.enums.ExitEnum
import uk.gov.hmrc.decisionservice.ruleSets.EarlyExitRules_v24

class EarlyExitRulesSpec extends BaseRuleSpec with GuiceOneAppPerSuite {

  implicit val ruleSet = EarlyExitRules_v24.ruleSet

  "For the IN rules" should {

    val actual = getRules(ExitEnum.INSIDE_IR35)

    val expected = List(
      Json.obj(
        Exit.officeHolder -> true
      )
    )

    checkRules(expected, actual)
  }
}
