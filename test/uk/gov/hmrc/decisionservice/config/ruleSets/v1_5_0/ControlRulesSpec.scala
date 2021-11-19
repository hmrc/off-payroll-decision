/*
 * Copyright 2021 HM Revenue & Customs
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
import uk.gov.hmrc.decisionservice.models.Control._
import uk.gov.hmrc.decisionservice.models.enums.MoveWorker._
import uk.gov.hmrc.decisionservice.models.enums._
import uk.gov.hmrc.decisionservice.ruleSets.ControlRules_v150

class ControlRulesSpec extends BaseRuleSpec with GuiceOneAppPerSuite with AnyWordSpecLike with Matchers {

  implicit val ruleSet = ControlRules_v150.ruleSet

  "For the OUT rules" should {

    val actual = getRules(WeightedAnswerEnum.OUTSIDE_IR35)

    val expected = List(
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ))

    checkRules(expected, actual)
  }

  "For the HIGH rules" should {

    val actual = getRules(WeightedAnswerEnum.HIGH)

    val expected = List(
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      )
    )

    checkRules(expected, actual)
  }

  "For the MEDIUM rules" should {

    val actual = getRules(WeightedAnswerEnum.MEDIUM)

    val expected = List(
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> canMoveWorkerWithoutPermission,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerDecidesWithoutInput,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerAgreeWithOthers,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.noWorkerInputAllowed,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.scheduleDecidedForWorker,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerChooses
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.noLocationRequired
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerAgreeSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.workerDecideSchedule,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerCannotChoose
      ),
      Json.obj(
        engagerMovingWorker -> cannotMoveWorkerWithoutNewAgreement,
        workerDecidingHowWorkIsDone -> HowWorkIsDone.workerFollowStrictEmployeeProcedures,
        whenWorkHasToBeDone -> ScheduleOfWorkingHours.noScheduleRequiredOnlyDeadlines,
        workerDecideWhere -> ChooseWhereWork.workerAgreeWithOthers
      )
    )

    checkRules(expected, actual)
  }
}
