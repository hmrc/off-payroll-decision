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

object SeriesOfContracts extends Enumeration with EnumFormats {

  val contractIsPartOfASeries: SeriesOfContracts.Value = Value("contractIsPartOfASeries")
  val standAloneContract: SeriesOfContracts.Value = Value("standAloneContract")
  val contractCouldBeExtended: SeriesOfContracts.Value = Value("contractCouldBeExtended")

  implicit val format: Format[SeriesOfContracts.Value] = enumFormat(SeriesOfContracts)
}
