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

object WorkerMainIncome extends Enumeration with EnumFormats {

  val incomeCalendarPeriods: WorkerMainIncome.Value = Value("incomeCalendarPeriods")
  val incomeFixed: WorkerMainIncome.Value = Value("incomeFixed")
  val incomePieceRate: WorkerMainIncome.Value = Value("incomePieceRate")
  val incomeCommission: WorkerMainIncome.Value = Value("incomeCommission")
  val incomeProfitOrLosses: WorkerMainIncome.Value = Value("incomeProfitOrLosses")

  implicit val format: Format[WorkerMainIncome.Value] = enumFormat(WorkerMainIncome)
}
