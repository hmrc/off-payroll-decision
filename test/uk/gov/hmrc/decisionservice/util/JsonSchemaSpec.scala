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

package uk.gov.hmrc.decisionservice.util

import cats.data.Xor
import play.api.libs.json.Json
import uk.gov.hmrc.decisionservice.Versions
import uk.gov.hmrc.decisionservice.testutil.RequestAndDecision
import uk.gov.hmrc.play.test.UnitSpec

import scala.util.Try

class JsonSchemaSpec extends UnitSpec {
  val TEST_CASE_PATH = "/schema/1.0.1-beta/schema-checking-testcase.csv"

  " A Json Schema" should {
    s"validate correctly full example request json with the loose schema for version ${Versions.VERSION101_BETA}" in {
      validateRequestWithSchema(Versions.VERSION101_BETA)
    }
  }

  it should {
    s"validate correctly full example request json with the loose schema for version ${Versions.VERSION100_FINAL}" in {
      validateRequestWithSchema(Versions.VERSION100_FINAL)
    }
  }

  it should {
    s"validate a request with the Strict Schema for version ${Versions.VERSION101_BETA}" in {
      validateRequestWithStrictSchema(Versions.VERSION101_BETA)
    }
  }

  it should {
    s"validate a request with the Strict Schema for version ${Versions.VERSION100_FINAL}" in {
      validateRequestWithStrictSchema(Versions.VERSION101_BETA)
    }
  }

  it should {
    s"validate a full response with the lose schema for version ${Versions.VERSION101_BETA}" in {
      validateResponseWithSchema(Versions.VERSION101_BETA)
    }
  }

  it should {
    s"validate a full response with the lose schema for version ${Versions.VERSION100_FINAL}" in {
      validateResponseWithSchema(Versions.VERSION100_FINAL)
    }
  }

  it should {
    s"validate a full response with the strict Schema for version ${Versions.VERSION101_BETA}" in {
      validateResponseWithStrictSchema(Versions.VERSION101_BETA)
    }
  }

  it should {
    s"validate a full response with the strict Schema for version ${Versions.VERSION100_FINAL}" in {
      validateResponseWithStrictSchema(Versions.VERSION100_FINAL)
    }
  }

  it should {
    s"validate request created from a flattened test case for version ${Versions.VERSION101_BETA}" in {
      val testCasesTry = RequestAndDecision.readFlattenedTransposed(TEST_CASE_PATH, Versions.VERSION101_BETA)
      testCasesTry.isSuccess shouldBe true
      val testCase = testCasesTry.get
      val request = testCase.request
      val requestJson = Json.toJson(request)
      val requestJsonString = Json.prettyPrint(requestJson)
      val maybeValidator = JsonRequestValidatorFactory(Versions.VERSION101_BETA)
      maybeValidator.isDefined shouldBe true
      val validationResult = maybeValidator.get.validate(requestJsonString)
      printValidationResult(validationResult)
      validationResult.isRight shouldBe true
    }
  }

  private def readExampleRequestJson(version: String): String = {
    val exampleRequestPath = s"/schema/${version}/off-payroll-request-sample.json"
    val tryJson = FileReader.read(exampleRequestPath)
    tryJson.isSuccess shouldBe true
    tryJson.get
  }

  private def readExampleResponseJson(version: String): String = {
    val exampleResponsePath = s"/schema/${version}/off-payroll-response-sample.json"
    val tryJson = FileReader.read(exampleResponsePath)
    tryJson.isSuccess shouldBe true
    tryJson.get
  }

  private def printValidationResult(result: Xor[String, Unit]) = {
    result.leftMap { report => {
      info(report)
    }
    }
  }

  def validateRequestWithSchema(version: String): Unit = validateRequestWithSchema(version, JsonRequestValidatorFactory(version))

  def validateRequestWithStrictSchema(version: String): Unit = validateRequestWithSchema(version, JsonRequestStrictValidatorFactory(version))

  def validateRequestWithSchema(version: String, maybeValidator: Option[JsonSchemaValidator]): Unit = {
    val requestJsonString = readExampleRequestJson(version)
    maybeValidator.isDefined shouldBe true
    maybeValidator.map { validator =>
      val validationResult = validator.validate(requestJsonString)
      printValidationResult(validationResult)
      validationResult.isRight shouldBe true
    }
  }

  def validateResponseWithSchema(version: String): Unit = validateResponseWithSchema(version, JsonResponseValidatorFactory(version))

  def validateResponseWithStrictSchema(version: String): Unit = validateResponseWithSchema(version, JsonResponseStrictValidatorFactory(version))

  def validateResponseWithSchema(version: String, maybeValidator: Option[JsonSchemaValidator]): Unit = {
    val requestJsonString = readExampleResponseJson(version)
    maybeValidator.isDefined shouldBe true
    val validationResult = maybeValidator.get.validate(requestJsonString)
    printValidationResult(validationResult)
    validationResult.isRight shouldBe true
  }

}
