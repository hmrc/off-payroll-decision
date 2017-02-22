/*
 * Copyright 2017 HM Revenue & Customs
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

package uk.gov.hmrc.decisionservice.controllers

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import cats.data.Validated
import play.api.http.Status
import play.api.libs.json.Json._
import play.api.libs.json.{JsObject, JsString, JsValue, Json}
import play.api.test.{FakeRequest, Helpers}
import uk.gov.hmrc.decisionservice.{Validation, Versions}
import uk.gov.hmrc.decisionservice.model.FactError
import uk.gov.hmrc.decisionservice.model.api.ErrorCodes._
import uk.gov.hmrc.decisionservice.model.api.{DecisionRequest, ErrorCodes, Score}
import uk.gov.hmrc.decisionservice.model.rules.Facts
import uk.gov.hmrc.decisionservice.ruleengine.RuleEngineDecision
import uk.gov.hmrc.decisionservice.services.{DecisionService, DecisionServiceTestInstance, DecisionServiceTestInstance100final}
import uk.gov.hmrc.play.test.{UnitSpec, WithFakeApplication}

class DecisionControllerSpec extends UnitSpec with WithFakeApplication {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  private val CORRELATION_ID = "12345"
  private val TEST_ERROR_CODE = 15
  private val BAD_REQUEST_JSON = """{}"""

  object ErrorGeneratingDecisionService extends DecisionService {
    lazy val maybeSectionRules = loadSectionRules()
    lazy val csvSectionMetadata = DecisionServiceTestInstance.csvSectionMetadata
    override def ==>:(facts: Facts): Validation[RuleEngineDecision] = {
      Validated.invalid(List(FactError(TEST_ERROR_CODE, "fact error")))
    }
  }

  object DecisionTestController extends DecisionController {
    lazy val decisionServices = Map(
      Versions.VERSION1 -> DecisionServiceTestInstance,
      Versions.VERSION2 -> DecisionServiceTestInstance100final
    )
  }

  object DecisionTestControllerWithErrorGeneratingDecisionService extends DecisionController {
    lazy val decisionServices = Map(
      Versions.VERSION1 -> ErrorGeneratingDecisionService,
      Versions.VERSION2 -> ErrorGeneratingDecisionService
    )
  }

  def sampleInterviewForVersion(version:String) = {
    val iVersion1 = Map(
      "personalService" -> Map(
        "contractualObligationForSubstitute" -> "Yes",
        "contractualObligationInPractise" -> "Yes",
        "contractTermsWorkerPaysSubstitute" -> "Yes"
      ))
    val iVersion2 = Map(
      "personalService" -> Map(
        "workerSentActualSubstitute" -> "yesClientAgreed",
        "workerPayActualSubstitute" -> "Yes",
        "possibleSubstituteRejection" -> "Yes",
        "possibleSubstituteWorkerPay" -> "Yes",
        "wouldWorkerPayHelper" -> "Yes"
      ))
    Map(Versions.VERSION1 -> iVersion1, Versions.VERSION2 -> iVersion2).getOrElse(version, Map())
  }

  "POST /decide" should {
    s"return 200 and correct response when request is correct for version ${Versions.VERSION1}" in {
      runPostExpected200(Versions.VERSION1)
    }
    s"return 200 and correct response when request is correct for version ${Versions.VERSION2}" in {
      runPostExpected200(Versions.VERSION2)
    }
    "return 400 and error response when request does not conform to schema" in {
      val decisionController = DecisionTestController
      val fakeRequest = FakeRequest(Helpers.POST, "/decide").withBody(Json.parse(BAD_REQUEST_JSON))
      val result = decisionController.decide()(fakeRequest)
      status(result) shouldBe Status.BAD_REQUEST
      val errorResponse = jsonBodyOf(await(result))
      verifyErrorResponse(errorResponse, REQUEST_FORMAT)
    }
    s"return 400 and error response when there is error in decision service for version ${Versions.VERSION1}" in {
      runPostExpected400(Versions.VERSION1, TEST_ERROR_CODE)
    }
    s"return 400 and error response when there is error in decision service for version ${Versions.VERSION2}" in {
      runPostExpected400(Versions.VERSION2, TEST_ERROR_CODE)
    }
    "return 400 and error response when not supported version is passed in the request" in {
      runPostExpected400("NotSupportedVersion", INVALID_VERSION)
    }
  }

  def runPostExpected200(version:String) = {
    val decisionRequest = DecisionRequest(version, CORRELATION_ID, sampleInterviewForVersion(version))
    val EXPECTED_RESULT: String = "Outside IR35"
    val decisionController = DecisionTestController
    val fakeRequest = FakeRequest(Helpers.POST, "/decide").withBody(toJson(decisionRequest))
    val result = decisionController.decide()(fakeRequest)
    status(result) shouldBe Status.OK
    val response = jsonBodyOf(await(result))
    verifyResponse(response, EXPECTED_RESULT, version)
    verifyScore(response, version)
  }

  def runPostExpected400(version:String, expectedErrorCode:Int) = {
    val decisionRequest = DecisionRequest(version, CORRELATION_ID, sampleInterviewForVersion(version))
    val decisionController = DecisionTestControllerWithErrorGeneratingDecisionService
    val fakeRequest = FakeRequest(Helpers.POST, "/decide").withBody(toJson(decisionRequest))
    val result = decisionController.decide()(fakeRequest)
    status(result) shouldBe Status.BAD_REQUEST
    val errorResponse = jsonBodyOf(await(result))
    verifyErrorResponse(errorResponse, expectedErrorCode)
  }

  def verifyResponse(response: JsValue, expectedResult:String, version:String): Unit = {
    val versionJs = response \\ "version"
    versionJs should have size 1
    versionJs should contain theSameElementsAs Seq(JsString(version))
    val correlationID = response \\ "correlationID"
    correlationID should have size 1
    correlationID should contain theSameElementsAs Seq(JsString(CORRELATION_ID))
    val result = response \\ "result"
    result should have size 1
    result(0).as[String] shouldBe expectedResult
  }

  def verifyScore(response: JsValue, version:String): Unit = {
    val score = response \\ "score"
    score should have size 1
    for (scoreElement <- Score.elements(version)) {
      (score(0) \\ scoreElement) should have size 1
    }
    score(0).as[JsObject].fields should have size Score.elements(version).size
  }

  def verifyErrorResponse(response: JsValue, expectedErrorCode:Int): Unit = {
    val code = response \\ "code"
    code should have size 1
    code.head.as[Int] shouldBe expectedErrorCode
    val message = response \\ "message"
    message should have size 1
  }
}
