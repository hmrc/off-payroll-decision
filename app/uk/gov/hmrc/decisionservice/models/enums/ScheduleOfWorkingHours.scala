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

object ScheduleOfWorkingHours extends Enumeration with EnumFormats {

  val scheduleDecidedForWorker: ScheduleOfWorkingHours.Value = Value("scheduleDecidedForWorker")
  val workerDecideSchedule: ScheduleOfWorkingHours.Value = Value("workerDecideSchedule")
  val workerAgreeSchedule: ScheduleOfWorkingHours.Value = Value("workerAgreeSchedule")
  val noScheduleRequiredOnlyDeadlines: ScheduleOfWorkingHours.Value = Value("noScheduleRequiredOnlyDeadlines")

  implicit val format: Format[ScheduleOfWorkingHours.Value] = enumFormat(ScheduleOfWorkingHours)
}
