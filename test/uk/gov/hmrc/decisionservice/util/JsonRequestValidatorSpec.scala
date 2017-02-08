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

import uk.gov.hmrc.decisionservice.Versions
import uk.gov.hmrc.play.test.UnitSpec


class JsonRequestValidatorSpec extends UnitSpec {

  val jsonRequestValidator = JsonRequestValidatorFactory(Versions.VERSION1).get // TODO

  val valid_twoSections = """
   |{
   |  "version": "1.0.0-beta",
   |  "correlationID": "12345",
   |  "interview": {
   |    "personalService": {
   |      "contractualObligationForSubstitute": "No",
   |      "contractualObligationInPractise": "Yes",
   |      "contractualRightForSubstitute": "Yes",
   |      "actualRightToSendSubstitute": "Yes",
   |      "contractualRightReflectInPractise": "No",
   |      "engagerArrangeIfWorkerIsUnwillingOrUnable": "No",
   |      "possibleSubstituteRejection": "No",
   |      "contractTermsWorkerPaysSubstitute": "Yes",
   |      "workerSentActualSubstitute": "Yes",
   |      "actualSubstituteRejection": "Yes",
   |      "possibleHelper": "Yes",
   |      "wouldWorkerPayHelper": "Yes",
   |      "workerSentActualHelper": "No",
   |      "workerPayActualHelper": "Yes"
   |    },
   |    "control": {
   |      "toldWhatToDo": "Yes",
   |      "engagerMovingWorker": "Yes",
   |      "workerDecidingHowWorkIsDone": "WorkerCanGetInstructed",
   |      "whenWorkHasToBeDone": "workingPatternStipulated",
   |      "workerDecideWhere": "couldFixWorkerLocation"
   |    }
   |  }
   |}
   """.stripMargin

  val valid_withNewFinancialRiskAFields = """
   |{
   |  "version": "1.0.0-beta",
   |  "correlationID": "12345",
   |  "interview": {
   |    "personalService": {
   |      "contractualObligationForSubstitute": "No",
   |      "contractualObligationInPractise": "Yes",
   |      "contractualRightForSubstitute": "Yes",
   |      "actualRightToSendSubstitute": "Yes",
   |      "contractualRightReflectInPractise": "No",
   |      "engagerArrangeIfWorkerIsUnwillingOrUnable": "No",
   |      "possibleSubstituteRejection": "No",
   |      "contractTermsWorkerPaysSubstitute": "Yes",
   |      "workerSentActualSubstitute": "Yes",
   |      "actualSubstituteRejection": "Yes",
   |      "possibleHelper": "Yes",
   |      "wouldWorkerPayHelper": "Yes",
   |      "workerSentActualHelper": "No",
   |      "workerPayActualHelper": "Yes"
   |    },
   |    "control": {
   |      "toldWhatToDo": "Yes",
   |      "engagerMovingWorker": "Yes",
   |      "workerDecidingHowWorkIsDone": "WorkerCanGetInstructed",
   |      "whenWorkHasToBeDone": "workingPatternStipulated",
   |      "workerDecideWhere": "couldFixWorkerLocation"
   |    },
   |    "financialRiskA": {
   |      "engagerPayConsumables": "Yes",
   |      "engagerPayEquipment": "Yes"
   |    }
   |  }
   |}
   """.stripMargin

  val valid_noAnswers = """{
                              "version": "89.90.73C",
                              "correlationID": "adipisicing ullamco",
                              "interview": {
                              "personalService": {}
                              }
                            }"""

  val invalid_missingCorrelationID = """{
                                  "version": "89.90.73C",
                                  "personalService": {
                                    "workerSentActualSubstitiute": "No",
                                    "engagerArrangeWorker": "No",
                                    "contractualRightForSubstitute": "No",
                                    "workerPayActualHelper": "No",
                                    "workerSentActualHelper": "Yes",
                                    "contractrualObligationForSubstitute": "No",
                                    "contractTermsWorkerPaysSubstitute": "No"
                                  },
                                  "partOfOrganisation": {
                                    "workerAsLineManager": "No",
                                    "workerRepresentsEngagerBusiness": "No",
                                    "contactWithEngagerCustomer": "No"
                                  },
                                  "miscellaneous": {},
                                  "businessStructure": {}
                                }"""

  val invalid_missingVersion = """{
                                  "correlationID": "adipisicing ullamco",
                                  "personalService": {
                                    "workerSentActualSubstitiute": "No",
                                    "engagerArrangeWorker": "No",
                                    "contractualRightForSubstitute": "No",
                                    "workerPayActualHelper": "No",
                                    "workerSentActualHelper": "Yes",
                                    "contractrualObligationForSubstitute": "No",
                                    "contractTermsWorkerPaysSubstitute": "No"
                                  },
                                  "partOfOrganisation": {
                                    "workerAsLineManager": "No",
                                    "workerRepresentsEngagerBusiness": "No",
                                    "contactWithEngagerCustomer": "No"
                                  },
                                  "miscellaneous": {},
                                  "businessStructure": {}
                                }"""

  val invalid_invalidAnswerValue = """
 |{
 |  "version": "1.0.0-beta",
 |  "correlationID": "12345",
 |  "interview": {
 |    "personalService": {
 |      "contractualObligationForSubstitute": "No",
 |      "contractualObligationInPractise": true,
 |      "contractualRightForSubstitute": "Yes",
 |      "actualRightToSendSubstitute": "Yes",
 |      "contractualRightReflectInPractise": "No",
 |      "engagerArrangeIfWorkerIsUnwillingOrUnable": "No",
 |      "possibleSubstituteRejection": "No",
 |      "contractTermsWorkerPaysSubstitute": "Yes",
 |      "workerSentActualSubstitute": "Yes",
 |      "actualSubstituteRejection": "Yes",
 |      "possibleHelper": "Yes",
 |      "wouldWorkerPayHelper": "Yes",
 |      "workerSentActualHelper": "No",
 |      "workerPayActualHelper": "Yes"
 |    }
 |  }
 | } """.stripMargin

  val invalid_invalidSection = """
 |{
 |  "version": "1.0.0-beta",
 |  "correlationID": "12345",
 |  "interview": {
 |    "personalService": {
 |      "contractualObligationForSubstitute": "No",
 |      "contractualObligationInPractise": "Yes",
 |      "contractualRightForSubstitute": "Yes",
 |      "actualRightToSendSubstitute": "Yes",
 |      "contractualRightReflectInPractise": "No",
 |      "engagerArrangeIfWorkerIsUnwillingOrUnable": "No",
 |      "possibleSubstituteRejection": "No",
 |      "contractTermsWorkerPaysSubstitute": "Yes",
 |      "workerSentActualSubstitute": "Yes",
 |      "actualSubstituteRejection": "Yes",
 |      "possibleHelper": "Yes",
 |      "wouldWorkerPayHelper": "Yes",
 |      "workerSentActualHelper": "No",
 |      "workerPayActualHelper": "Yes"
 |    },
 |    "invalidSection": {
 |      "toldWhatToDo": "No",
 |      "engagerMovingWorker": "Yes",
 |      "workerDecidingHowWorkIsDone": "No",
 |      "whenWorkHasToBeDone": "workingPatternStipulated",
 |      "workerDecideWhere": "couldFixWorkerLocation"
 |    }}}
 """.stripMargin


  val invalid_invalidEnum = """
                             |{
 |  "version": "1.0.0-beta",
 |  "correlationID": "12345",
 |  "interview": {
 |    "personalService": {
 |      "contractualObligationForSubstitute": "No",
 |      "contractualObligationInPractise": "Yes",
 |      "contractualRightForSubstitute": "Yes",
 |      "actualRightToSendSubstitute": "Yes",
 |      "contractualRightReflectInPractise": "No",
 |      "engagerArrangeIfWorkerIsUnwillingOrUnable": "No",
 |      "possibleSubstituteRejection": "No",
 |      "contractTermsWorkerPaysSubstitute": "Yes",
 |      "workerSentActualSubstitute": "Yes",
 |      "actualSubstituteRejection": "Yes",
 |      "possibleHelper": "Yes",
 |      "wouldWorkerPayHelper": "Yes",
 |      "workerSentActualHelper": "No",
 |      "workerPayActualHelper": "Yes"
 |    },
 |    "control": {
 |      "toldWhatToDo": "No",
 |      "engagerMovingWorker": "Yes",
 |      "workerDecidingHowWorkIsDone": "No",
 |      "whenWorkHasToBeDone": "workingPatternStipulated",
 |      "workerDecideWhere": "imWellGood"
 |    }}}
 |    """.stripMargin

  val invalid_invalidEnum2 = """{
                                     "version": "5.4.2-b",
                                     "correlationID": "dolor dolor",
                                     "interview": {
                                       "personalService": {
                                         "contractualObligationForSubstitute": "No",
                                         "contractualObligationInPractise": "No"
                                       },
                                       "control": {
                                         "toldWhatToDo": "Yes",
                                         "engagerMovingWorker": "No",
                                         "whenWorkHasToBeDone": "allDayEveryDay"
                                       }
                                     }
                                   }"""


  "json validator" should {

    "return true for valid json" in {
      validateWithInfo(valid_twoSections, jsonRequestValidator) shouldBe true
    }

    "return true for valid json - with New FinancialRiskA fields" in {
      validateWithInfo(valid_withNewFinancialRiskAFields, jsonRequestValidator) shouldBe true
    }

    s"validate a full request when using the strict validator for version ${Versions.VERSION1}" in {
      validateWithInfo(valid_twoSections, JsonRequestStrictValidatorFactory(Versions.VERSION1).get) shouldBe false
    }

    "return true for valid json - no answers" in {
      validateWithInfo(valid_noAnswers, jsonRequestValidator) shouldBe true
    }

    "return false for invalid json - InvalidAnswerValue" in {
      verify(invalid_invalidAnswerValue, "string")
    }

    "return false for invalid json - missing Version" in {
      verify(invalid_missingVersion, "object has missing required properties")
    }

    "return false for invalid json - missing CorrelationID" in {
      verify(invalid_missingCorrelationID, "object has missing required properties")
    }

    "return false for invalid json - invalidSection" in {
      verify(invalid_invalidSection, "[\"invalidSection\"]")
    }

    "return true for valid json - empty interview" in {
      val valid_emptyInterview =
        """{
             "version": "15.16.1-S",
             "correlationID": "ut",
             "interview" : {}
        }"""
      validateWithInfo(valid_emptyInterview, jsonRequestValidator) shouldBe true
    }

    "return false for invalid json - invalidFormatVersionId - should be string" in {
      val invalid_versionIdType =
        """{
        "version": 342571,
        "correlationID": "ut",
        "interview" : {}
      }"""
      verify(invalid_versionIdType, "integer")
    }

    "return false for invalid json - invalidFormatVersionId" in {
      val invalid_versionId =
        """{
        "version": "001-SNAPSHOT",
        "correlationID": "ut",
        "interview" : {}
      }"""
      verify(invalid_versionId, "001-SNAPSHOT")
    }

    "return true for valid json - valid version id" in {
      val valid_versionId =
        """{
        "version": "0.0.1-alpha",
        "correlationID": "ut",
        "interview" : {}
      }"""
      validateWithInfo(valid_versionId, jsonRequestValidator) shouldBe true
    }

    "return false for invalid json - enum value is not valid" in {
      verify(invalid_invalidEnum, "instance value (\"imWellGood\") not found in enum")
    }

    "return false for invalid json - enum value is not valid2" in {
      verify(invalid_invalidEnum2, "instance value (\"allDayEveryDay\") not found in enum")
    }

  }

  private def validateWithInfo(json: String, validator: JsonValidatorTrait) = {
    val result = validator.validate(json)
    if (result.isLeft){
      result.leftMap( r => info(r))
    }
    result.isRight
  }

  def verify(s:String, expectedText:String):Unit = {
    val result = jsonRequestValidator.validate(s)
    result.isRight shouldBe false
    result.leftMap { report => {
      info(report)
      report.contains(expectedText) shouldBe true
    }
    }
  }

}
