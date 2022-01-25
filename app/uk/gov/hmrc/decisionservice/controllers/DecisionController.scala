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

package uk.gov.hmrc.decisionservice.controllers

import com.google.inject.Inject
import play.api.Logger
import play.api.libs.json._
import play.api.mvc.{Action, MessagesControllerComponents}
import uk.gov.hmrc.decisionservice.models.{DecisionRequest, ErrorResponse}
import uk.gov.hmrc.decisionservice.services._
import uk.gov.hmrc.play.bootstrap.backend.controller.BackendController

import scala.concurrent.{ExecutionContext, Future}

class DecisionController @Inject()(mcc: MessagesControllerComponents,
                                   service: DecisionService) extends BackendController(mcc) {

  implicit val ec: ExecutionContext = defaultExecutionContext
  val logger: Logger = Logger(getClass)

  def decide(): Action[JsValue] = Action.async(parse.json) { request =>
    request.body.validate[DecisionRequest] match {
      case JsSuccess(validRequest, _) =>
        logger.info("Valid interview request to decide API")
        service.calculateResult(validRequest).map(response => Ok(Json.toJson(response)))
      case JsError(jsonErrors) =>
        val errorDetails = s"""{"incorrectRequest":$jsonErrors}"""
        val errorResponseBody = Json.toJson(ErrorResponse(BAD_REQUEST, JsError.toJson(jsonErrors).toString(),errorDetails))
        logger.error(s"incorrect request response: $errorResponseBody")
        Future.successful(BadRequest(errorResponseBody))
    }
  }
}
