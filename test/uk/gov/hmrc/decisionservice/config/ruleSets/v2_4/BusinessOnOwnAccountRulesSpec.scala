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

package uk.gov.hmrc.decisionservice.config.ruleSets.v2_4

import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.libs.json.Json
import uk.gov.hmrc.decisionservice.config.ruleSets.BaseRuleSpec
import uk.gov.hmrc.decisionservice.models.BusinessOnOwnAccount._
import uk.gov.hmrc.decisionservice.models.enums._
import uk.gov.hmrc.decisionservice.ruleSets.BusinessOnOwnAccountRules_v24

class BusinessOnOwnAccountRulesSpec extends BaseRuleSpec with GuiceOneAppPerSuite {

  implicit val ruleSet = BusinessOnOwnAccountRules_v24.ruleSet

  "For the High rules" should {

    val actual = getRules(WeightedAnswerEnum.HIGH)

    val expected = List(
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      )
    )

    checkRules(expected, actual)
  }

  "For the MEDIUM rules" should {

    val actual = getRules(WeightedAnswerEnum.MEDIUM)

    val expected = List(
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.retainOwnershipRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      )
    )
    checkRules(expected, actual)
  }

  "For the LOW rules" should {

    val actual = getRules(WeightedAnswerEnum.LOW)

    val expected = List(
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.unableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.ableToTransferRights,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServicesWithPermission,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.noKnowledgeOfExternalActivity,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.providedServicesToOtherEngagers,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.rightsTransferredToClient,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.noSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractIsPartOfASeries
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.standAloneContract
      ),
      Json.obj(
        exclusiveContract -> ExclusiveContract.ableToProvideServices,
        transferRights -> TransferRights.noRightsArise,
        multipleEngagements -> MultipleEngagements.onlyContractForPeriod,
        significantWorkingTime -> SignificantWorkingTime.consumesSignificantAmount,
        seriesOfContracts -> SeriesOfContracts.contractCouldBeExtended
      )
    )

    checkRules(expected, actual)
  }
}
