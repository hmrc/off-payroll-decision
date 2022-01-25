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

package uk.gov.hmrc.decisionservice.models

import play.api.libs.json.{Format, Json}
import uk.gov.hmrc.decisionservice.models.enums.{PaidForSubstandardWork, WorkerMainIncome}

case class FinancialRisk(workerProvidedMaterials: Option[Boolean],
                         workerProvidedEquipment: Option[Boolean],
                         workerUsedVehicle: Option[Boolean],
                         workerHadOtherExpenses: Option[Boolean],
                         expensesAreNotRelevantForRole: Option[Boolean],
                         workerMainIncome: Option[WorkerMainIncome.Value],
                         paidForSubstandardWork: Option[PaidForSubstandardWork.Value])

object FinancialRisk {
  implicit val format: Format[FinancialRisk] = Json.format[FinancialRisk]

  val workerProvidedMaterials = "workerProvidedMaterials"
  val workerProvidedEquipment = "workerProvidedEquipment"
  val workerUsedVehicle = "workerUsedVehicle"
  val workerHadOtherExpenses = "workerHadOtherExpenses"
  val expensesAreNotRelevantForRole = "expensesAreNotRelevantForRole"
  val workerMainIncome = "workerMainIncome"
  val paidForSubstandardWork = "paidForSubstandardWork"
}
