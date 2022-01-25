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

object WeightedAnswerEnum extends Enumeration with EnumFormats {

  val LOW: WeightedAnswerEnum.Value = Value("LOW")
  val MEDIUM: WeightedAnswerEnum.Value = Value("MEDIUM")
  val HIGH: WeightedAnswerEnum.Value = Value("HIGH")
  val NOT_VALID_USE_CASE: WeightedAnswerEnum.Value = Value("NotValidUseCase")
  val OUTSIDE_IR35: WeightedAnswerEnum.Value = Value("OUTOFIR35")
  val INSIDE_IR35: WeightedAnswerEnum.Value = Value("INIR35")
  val UNKNOWN: WeightedAnswerEnum.Value = Value("Unknown")

  implicit val format: Format[WeightedAnswerEnum.Value] = enumFormat(WeightedAnswerEnum)
}


