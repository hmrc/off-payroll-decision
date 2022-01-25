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

package uk.gov.hmrc.decisionservice.ruleSets

import uk.gov.hmrc.decisionservice.models.RuleSet
import uk.gov.hmrc.decisionservice.models.enums.DecisionServiceVersion

sealed trait MatrixOfMatricesRules extends BaseRules {
  def parseRuleSet(version: DecisionServiceVersion.Value): Seq[RuleSet] = parseRules("matrix-of-matrices", version)
}

object MatrixOfMatricesRules_v150 extends MatrixOfMatricesRules {
  override lazy val ruleSet: Seq[RuleSet] = parseRuleSet(DecisionServiceVersion.v1_5_0)
}

object MatrixOfMatricesRules_v24 extends MatrixOfMatricesRules {
  override lazy val ruleSet: Seq[RuleSet] = parseRuleSet(DecisionServiceVersion.v2_4)
}

object MatrixOfMatricesRules {
  def apply(version: DecisionServiceVersion.Value): MatrixOfMatricesRules = version match {
    case DecisionServiceVersion.`v1_5_0` => MatrixOfMatricesRules_v150
    case DecisionServiceVersion.`v2_4` => MatrixOfMatricesRules_v24
  }
}
