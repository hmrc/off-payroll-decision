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
import uk.gov.hmrc.decisionservice.models.enums.{ChooseWhereWork, HowWorkIsDone, MoveWorker, ScheduleOfWorkingHours}

case class Control(engagerMovingWorker: Option[MoveWorker.Value],
                   workerDecidingHowWorkIsDone: Option[HowWorkIsDone.Value],
                   whenWorkHasToBeDone: Option[ScheduleOfWorkingHours.Value],
                   workerDecideWhere: Option[ChooseWhereWork.Value])

object Control {
  implicit val format: Format[Control] = Json.format[Control]

  val engagerMovingWorker = "engagerMovingWorker"
  val workerDecidingHowWorkIsDone = "workerDecidingHowWorkIsDone"
  val whenWorkHasToBeDone = "whenWorkHasToBeDone"
  val workerDecideWhere = "workerDecideWhere"
}
