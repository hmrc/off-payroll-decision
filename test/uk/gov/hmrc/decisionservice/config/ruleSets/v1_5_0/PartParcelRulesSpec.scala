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

package uk.gov.hmrc.decisionservice.config.ruleSets.v1_5_0

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.libs.json.Json
import uk.gov.hmrc.decisionservice.config.ruleSets.BaseRuleSpec
import uk.gov.hmrc.decisionservice.models.PartAndParcel._
import uk.gov.hmrc.decisionservice.models.enums.{IdentifyToStakeholders, WeightedAnswerEnum}
import uk.gov.hmrc.decisionservice.ruleSets.PartAndParcelRules_v150

class PartParcelRulesSpec extends BaseRuleSpec with GuiceOneAppPerSuite with AnyWordSpecLike with Matchers {

  implicit val ruleSet = PartAndParcelRules_v150.ruleSet

  "For all the expected HIGH rules" should {

    val actual = getRules(WeightedAnswerEnum.HIGH)

    val expected = List(
      Json.obj(
        workerReceivesBenefits -> false,
        workerAsLineManager -> true
      ),
      Json.obj(
        workerReceivesBenefits -> true
      )
    )

    checkRules(expected, actual)
  }

  "For all the expected MEDIUM rules" should {

    val actual = getRules(WeightedAnswerEnum.MEDIUM)

    val expected = List(
      Json.obj(
        contactWithEngagerCustomer -> true,
        workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workForEndClient
      ),
      Json.obj(
        contactWithEngagerCustomer -> true,
        workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsBusiness
      )
    )

    checkRules(expected, actual)
  }

  "for the expected LOW rules" should {

    val expected = List(
      Json.obj(
        contactWithEngagerCustomer -> false
      ),
      Json.obj(
        workerRepresentsEngagerBusiness -> IdentifyToStakeholders.workAsIndependent
      )
    )

    val actual = ruleSet.filter(_.result == WeightedAnswerEnum.LOW.toString).map(_.rules).toList

    checkRules(expected, actual)
  }
}
