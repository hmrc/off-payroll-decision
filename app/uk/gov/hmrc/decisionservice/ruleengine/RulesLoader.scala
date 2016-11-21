package uk.gov.hmrc.decisionservice.ruleengine

import java.io.IOException

import cats.data.Xor
import uk.gov.hmrc.decisionservice.model.rules._
import uk.gov.hmrc.decisionservice.model._

import scala.io.Source
import scala.util.{Failure, Success, Try}

case class RulesFileMetaData(valueCols:Int, resultCols:Int, path:String, name:String){
  def numCols = valueCols + resultCols
}


trait RulesLoader {
  type ValueType
  type Rule
  type RuleSet

  val Separator = ','

  def using[R <: { def close(): Unit }, B](resource: R)(f: R => B): B = try { f(resource) } finally { resource.close() }

  def load(rulesFileMetaData: RulesFileMetaData):Xor[RulesFileLoadError,RuleSet] = {
      val tryTokens = Try {
          val is = getClass.getResourceAsStream(rulesFileMetaData.path)
          if (is == null) { throw new IOException(s"resource not found: ${rulesFileMetaData.path}") }
        using(Source.fromInputStream(is)) { res =>
          res.getLines.map(_.split(Separator).map(_.trim).toList).toList
        }
      }
      tryTokens match {
        case Success(tokens) =>
          val (headings::rest) = tokens
          val (ruleTokens, errorRuleTokens) = rest.partition { isValidRule(_, rulesFileMetaData) }
          errorRuleTokens match {
            case Nil => createRuleSet(rulesFileMetaData, ruleTokens, headings)
            case l => Xor.left(RulesFileLoadError(createErrorMessage(l)))
          }
        case Failure(e) =>
          Xor.left(RulesFileLoadError(e.getMessage))
      }
    }

  def isValidRule(tokens:List[String], rulesFileMetaData: RulesFileMetaData):Boolean = {
    tokens.size == rulesFileMetaData.numCols
  }

  def createRule(tokens:List[String], rulesFileMetaData: RulesFileMetaData):Rule

  def createRuleSet(rulesFileMetaData:RulesFileMetaData, ruleTokens:List[List[String]], headings:List[String]):Xor[RulesFileLoadError,RuleSet]

  def createErrorMessage(tokens:List[List[String]]):String = tokens.map(a => s"$a").mkString(" ")
}


object SectionRulesLoader extends RulesLoader {
  type ValueType = String
  type Rule = SectionRule
  type RuleSet = SectionRuleSet

  def createRule(tokens:List[String], rulesFileMetaData: RulesFileMetaData):SectionRule = {
    val result = CarryOverImpl(tokens.drop(rulesFileMetaData.valueCols).head, tokens.last.toBoolean)
    val values = tokens.take(rulesFileMetaData.valueCols)
    SectionRule(values, result)
  }

  def createRuleSet(rulesFileMetaData:RulesFileMetaData, ruleTokens:List[List[String]], headings:List[String]):Xor[RulesFileLoadError,SectionRuleSet] = {
    Try {
      val rules = ruleTokens.map(createRule(_, rulesFileMetaData))
      SectionRuleSet(rulesFileMetaData.name, headings, rules)
    }
    match {
      case Success(sectionRuleSet) => Xor.right(sectionRuleSet)
      case Failure(e) => Xor.left(RulesFileLoadError(e.getMessage))
    }
  }
}


object MatrixRulesLoader extends RulesLoader {
  type ValueType = CarryOver
  type Rule = MatrixRule
  type RuleSet = MatrixRuleSet

  def createRule(tokens:List[String], rulesFileMetaData: RulesFileMetaData):MatrixRule = {
    val result = MatrixDecisionImpl(tokens.last)
    val values = tokens.take(tokens.size-1).map(CarryOverImpl(_,false))
    MatrixRule(values, result)
  }

  def createRuleSet(rulesFileMetaData:RulesFileMetaData, ruleTokens:List[List[String]], headings:List[String]):Xor[RulesFileLoadError,MatrixRuleSet] = {
    Try {
      val rules = ruleTokens.map(createRule(_, rulesFileMetaData))
      MatrixRuleSet(headings, rules)
    }
    match {
      case Success(matrixRuleSet) => Xor.right(matrixRuleSet)
      case Failure(e) => Xor.left(RulesFileLoadError(e.getMessage))
    }
  }
}
