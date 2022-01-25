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

object ArrangedSubstitute extends Enumeration with EnumFormats {

  val yesClientAgreed: ArrangedSubstitute.Value = Value("yesClientAgreed")
  val notAgreedWithClient: ArrangedSubstitute.Value = Value("notAgreedWithClient")
  val noSubstitutionHappened: ArrangedSubstitute.Value = Value("noSubstitutionHappened")

  implicit val format: Format[ArrangedSubstitute.Value] = enumFormat(ArrangedSubstitute)
}
