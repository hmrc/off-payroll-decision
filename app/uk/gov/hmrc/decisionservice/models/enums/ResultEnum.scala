package uk.gov.hmrc.decisionservice.models.enums

import play.api.libs.json.Format

object ResultEnum extends Enumeration with EnumFormats {

  val OUTSIDE_IR35: ResultEnum.Value = Value("Outside IR35")
  val SELF_EMPLOYED: ResultEnum.Value = Value("Self-Employed")
  val INSIDE_IR35: ResultEnum.Value = Value("Inside IR35")
  val EMPLOYED: ResultEnum.Value = Value("Employed")
  val UNKNOWN: ResultEnum.Value = Value("Unknown")
  val NOT_MATCHED: ResultEnum.Value = Value("Not Matched")

  implicit val format: Format[ResultEnum.Value] = enumFormat(ResultEnum)
}