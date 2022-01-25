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

package uk.gov.hmrc.decisionservice.ruleSets

import play.api.libs.json._
import uk.gov.hmrc.decisionservice.models.RuleSet
import uk.gov.hmrc.decisionservice.models.enums.DecisionServiceVersion

import scala.io.Source

trait BaseRules {

  val ruleSet: Seq[RuleSet]

  def parseRules(section: String, version: DecisionServiceVersion.Value): Seq[RuleSet] = {

    lazy val csv = getClass.getResourceAsStream(s"/tables/$version/$section.csv")
    lazy val file = Source.fromInputStream(csv)

    lazy val csvRules: List[String] = file.getLines.toList
    lazy val headers: List[String] = csvRules.head.split(",").toList

    val resultSet = csvRules.tail.map { row =>

      val columns = row.split(",")
      val answers = columns.dropRight(1)
      val result = columns.last

      val rules: Map[String, JsValue] = headers.zip(answers).filterNot(_._2.isEmpty).map {
        case (key, "true") => key -> JsBoolean(true)
        case (key, "false") => key -> JsBoolean(false)
        case (key, value) => key -> JsString(value)
      }.toMap

      RuleSet(JsObject(rules), result)
    }

    file.close()
    resultSet
  }
}
