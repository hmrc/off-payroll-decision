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

package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format


object ResultEnum extends Enumeration with EnumFormats {

  val OUTSIDE_IR35: ResultEnum.Value = Value("Outside IR35")
  val SELF_EMPLOYED: ResultEnum.Value = Value("Self-Employed")
  val INSIDE_IR35: ResultEnum.Value = Value("Inside IR35")
  val EMPLOYED: ResultEnum.Value = Value("Employed")
  val UNKNOWN: ResultEnum.Value = Value("Unknown")
  val NOT_MATCHED: ResultEnum.Value = Value("Not Matched")

  implicit val format: Format[ResultEnum.Value] = enumFormat(ResultEnum)

  def apply(value: String): ResultEnum.Value = value match {
    case "INIR35" => ResultEnum.INSIDE_IR35
    case "OUTOFIR35" => ResultEnum.OUTSIDE_IR35
    case "UNKNOWN" => ResultEnum.UNKNOWN
    case _ => ResultEnum.NOT_MATCHED
  }
}
