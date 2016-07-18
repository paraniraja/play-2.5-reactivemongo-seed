package user

import javax.inject.Inject

import daos.core.ContextHelper
import daos.exceptions.ServiceException
import play.api.libs.json.{JsArray, JsObject, JsString, _}
import play.api.mvc.{Action, Controller, Request, Result}

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

class UserController @Inject()(userService: UserService) extends Controller with ContextHelper {

  def create = Action.async(parse.json) { implicit request =>
    validateAndThen[User] {
      entity =>
        userService.create(entity).map {
          case Success(e) => Created(Json.toJson(e))
        } recover {
          case e : ServiceException => BadRequest(e.message)
          case _ => BadRequest("Unknown Exception")
        }
    }
  }

  def validateAndThen[T: Reads](t: T => Future[Result])(implicit request: Request[JsValue]) = {
    request.body.validate[T].map(t) match {
      case JsSuccess(result, _) => result
      case JsError(err) => Future.successful(BadRequest(Json.toJson(err.map {
        case (path, errors) => Json.obj("path" -> path.toString, "errors" -> JsArray(errors.flatMap(e => e.messages.map(JsString(_)))))
      })))
    }
  }

  def toError(t: (String, Try[JsValue])): JsObject = t match {
    case (paramName, Failure(e)) => Json.obj(paramName -> e.getMessage)
    case _ => Json.obj()
  }
}
