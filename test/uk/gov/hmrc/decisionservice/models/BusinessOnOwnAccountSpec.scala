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

package uk.gov.hmrc.decisionservice.models

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import play.api.libs.json.Json
import uk.gov.hmrc.decisionservice.models.enums._

class BusinessOnOwnAccountSpec extends AnyWordSpecLike with Matchers {

  val businessOnOwnAccountModel = BusinessOnOwnAccount(
    exclusiveContract = Some(ExclusiveContract.ableToProvideServices),
    transferRights = Some(TransferRights.ableToTransferRights),
    multipleEngagements = Some(MultipleEngagements.noKnowledgeOfExternalActivity),
    significantWorkingTime = Some(SignificantWorkingTime.consumesSignificantAmount),
    seriesOfContracts = Some(SeriesOfContracts.contractCouldBeExtended)
  )

  val businessOnOwnAccountJson = Json.obj(
    "exclusiveContract" -> ExclusiveContract.ableToProvideServices,
    "transferRights" -> TransferRights.ableToTransferRights,
    "multipleEngagements" -> MultipleEngagements.noKnowledgeOfExternalActivity,
    "significantWorkingTime" -> SignificantWorkingTime.consumesSignificantAmount,
    "seriesOfContracts" -> SeriesOfContracts.contractCouldBeExtended
  )

  "BusinessOnOwnAccount model" should {

    "return the correct model when read from Json" in {

      val expectedResult = businessOnOwnAccountModel
      val actualResult = businessOnOwnAccountJson.as[BusinessOnOwnAccount]

      actualResult shouldBe expectedResult
    }

    "return the correct Json when written to Json" in {

      val expectedResult = businessOnOwnAccountJson
      val actualResult = Json.toJson(businessOnOwnAccountModel)

      actualResult shouldBe expectedResult
    }
  }
}
