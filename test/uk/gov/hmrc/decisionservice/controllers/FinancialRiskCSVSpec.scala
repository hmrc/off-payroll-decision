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

import uk.gov.hmrc.decisionservice.Versions
import uk.gov.hmrc.play.test.{UnitSpec, WithFakeApplication}

/**
  * Created by work on 09/01/2017.
  */
class FinancialRiskCSVSpec extends UnitSpec with WithFakeApplication with DecisionControllerClusterCsvSpec {
  val clusterName = "financialRisk"
  val FINANCIAL_RISK_SCENARIO_0 = s"/test-scenarios/${Versions.VERSION100_FINAL}/financial-risk/scenario_0.csv"
  val FINANCIAL_RISK_SCENARIOS_VERSION100_FINAL = s"/test-scenarios/${Versions.VERSION100_FINAL}/financial-risk/scenarios.csv"
  val FINANCIAL_RISK_SCENARIOS_v111 = s"/test-scenarios/${Versions.VERSION111_FINAL}/financial-risk/scenarios.csv"
  val FINANCIAL_RISK_SCENARIOS_LATEST = s"/test-scenarios/${Versions.LATEST}/financial-risk/scenarios.csv"

  "POST /decide" should {
    //FIXME add tests for 1.1.0 and 1.2.0
    s"return 200 and expected decision for financial risk scenarios for version ${Versions.VERSION111_FINAL}" in {
      createMultipleRequestsSendVerifyDecision(FINANCIAL_RISK_SCENARIOS_v111, Versions.VERSION111_FINAL)
    }
  }
}
