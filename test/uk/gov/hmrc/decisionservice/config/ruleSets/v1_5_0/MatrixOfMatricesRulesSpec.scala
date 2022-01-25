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
import uk.gov.hmrc.decisionservice.models.enums.Section._
import uk.gov.hmrc.decisionservice.models.enums.WeightedAnswerEnum._
import uk.gov.hmrc.decisionservice.ruleSets.MatrixOfMatricesRules_v150
import scala.language.implicitConversions

class MatrixOfMatricesRulesSpec extends BaseRuleSpec with GuiceOneAppPerSuite with AnyWordSpecLike with Matchers {

  implicit def enumTuple[E <: Enumeration, B <: Enumeration](x: (E#Value, B#Value)): (String, Json.JsValueWrapper) = (x._1.toString, x._2)

  val ruleSet = MatrixOfMatricesRules_v150.ruleSet

  "For all the expected InIR35 rules" should {

    val actual = ruleSet.filter(_.result == "INIR35").map(_.rules).toList

    val expected = List(
      Json.obj(
        personalService -> MEDIUM,
        control -> MEDIUM,
        financialRisk -> LOW,
        partAndParcel -> HIGH
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> MEDIUM,
        financialRisk -> LOW,
        partAndParcel -> MEDIUM
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> HIGH,
        financialRisk -> LOW,
        partAndParcel -> LOW
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> HIGH,
        financialRisk -> LOW,
        partAndParcel -> MEDIUM
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> HIGH,
        financialRisk -> LOW,
        partAndParcel -> HIGH
      ),
      Json.obj(
        personalService -> HIGH,
        control -> MEDIUM,
        financialRisk -> LOW,
        partAndParcel -> LOW
      ),
      Json.obj(
        personalService -> HIGH,
        control -> MEDIUM,
        financialRisk -> LOW,
        partAndParcel -> MEDIUM
      ),
      Json.obj(
        personalService -> HIGH,
        control -> MEDIUM,
        financialRisk -> LOW,
        partAndParcel -> HIGH
      ),
      Json.obj(
        personalService -> HIGH,
        control -> HIGH,
        financialRisk -> LOW,
        partAndParcel -> LOW
      ),
      Json.obj(
        personalService -> HIGH,
        control -> HIGH,
        financialRisk -> LOW,
        partAndParcel -> MEDIUM
      ),
      Json.obj(
        personalService -> HIGH,
        control -> HIGH,
        financialRisk -> LOW,
        partAndParcel -> HIGH
      ),
      Json.obj(
        personalService -> HIGH,
        control -> HIGH,
        financialRisk -> MEDIUM,
        partAndParcel -> LOW
      ),
      Json.obj(
        personalService -> HIGH,
        control -> HIGH,
        financialRisk -> MEDIUM,
        partAndParcel -> MEDIUM
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> HIGH,
        financialRisk -> MEDIUM,
        partAndParcel -> LOW
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> HIGH,
        financialRisk -> MEDIUM,
        partAndParcel -> MEDIUM
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> HIGH,
        financialRisk -> MEDIUM,
        partAndParcel -> HIGH
      ),
      Json.obj(
        personalService -> HIGH,
        control -> MEDIUM,
        financialRisk -> MEDIUM,
        partAndParcel -> HIGH
      ),
      Json.obj(
        personalService -> HIGH,
        control -> HIGH,
        financialRisk -> MEDIUM,
        partAndParcel -> HIGH
      )
    )

    checkRules(expected, actual)
  }

  "For all the expected Indeterminate rules" should {

    val actual = ruleSet.filter(_.result == "UNKNOWN").map(_.rules).toList

    val expected = List(
      Json.obj(
        personalService -> MEDIUM,
        control -> MEDIUM,
        financialRisk -> LOW,
        partAndParcel -> LOW
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> MEDIUM,
        financialRisk -> MEDIUM,
        partAndParcel -> LOW
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> MEDIUM,
        financialRisk -> MEDIUM,
        partAndParcel -> MEDIUM
      ),
      Json.obj(
        personalService -> MEDIUM,
        control -> MEDIUM,
        financialRisk -> MEDIUM,
        partAndParcel -> HIGH
      ),
      Json.obj(
        personalService -> HIGH,
        control -> MEDIUM,
        financialRisk -> MEDIUM,
        partAndParcel -> LOW
      ),
      Json.obj(
        personalService -> HIGH,
        control -> MEDIUM,
        financialRisk -> MEDIUM,
        partAndParcel -> MEDIUM
      )
    )

    checkRules(expected, actual)
  }
}
