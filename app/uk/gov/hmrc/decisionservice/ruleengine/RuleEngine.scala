/*
 * Copyright 2016 HM Revenue & Customs
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

package uk.gov.hmrc.decisionservice.ruleengine

import cats.data.Xor
import play.api.Logger
import uk.gov.hmrc.decisionservice.model.DecisionServiceError
import uk.gov.hmrc.decisionservice.model.rules._

import scala.annotation.tailrec

sealed trait RuleEngineDecision {
  def value: String
  def facts: Map[String,CarryOver]
  def isFinal: Boolean
}

case class RuleEngineDecisionUndecided(facts: Map[String,CarryOver]) extends RuleEngineDecision {
  override def value = "Undecided"
  override def isFinal = false
}

case class RuleEngineDecisionImpl(value: String, facts: Map[String,CarryOver]) extends RuleEngineDecision {
  override def isFinal = true
}

object FinalFact {
  def unapply(facts: Facts) = facts.facts.values.find(_.exit)
}

trait RuleEngine {
  def processRules(rules: Rules, facts: Facts): Xor[DecisionServiceError, RuleEngineDecision] = {
    @tailrec
    def go(rules: List[SectionRuleSet], facts: Facts): Xor[DecisionServiceError, Facts] = {
      facts match {
        case FinalFact(_) => Xor.right(facts)
        case _ =>
          rules match {
            case Nil => Xor.right(facts)
            case ruleSet :: ruleSets =>
              ruleSet ==+>: facts match {
                case Xor.Right(newFacts) => go(ruleSets, newFacts)
                case e@Xor.Left(_) => e
              }
          }
      }
    }
    val maybeFacts = go(rules.rules, facts)
    maybeFacts.map {
      case f@FinalFact(ff) =>
        Logger.info(s"decision found: '${ff.value}'\n")
        RuleEngineDecisionImpl(ff.value, f.facts)
      case f =>
        Logger.info(s"decision not found - undecided\n")
        RuleEngineDecisionUndecided(f.facts)
    }
  }
}

object RuleEngineInstance extends RuleEngine
