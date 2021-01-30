package co.com.application.acl.formats

import co.com.application.acl.dtos.UserDTO
import co.com.suite.error.ApplicationError
import play.api.libs.json.{ JsObject, JsString, Json, Writes }

object Formats {

  implicit val applicationErrorFormat: Writes[ApplicationError] =
    ( error: ApplicationError ) => JsObject( Map( "errorMessage" -> JsString( error.errorMessage ) ) )

  //  implicit val writerErrorDominio: Writes[ApplicationError] = new Writes[ApplicationError] {
  //    override def writes(e: ErrorDominio): JsValue = {
  //      JsObject(Map("mensaje" -> JsString(e.mensaje)))
  //    }
  //  }

  implicit val userDTOFormat = Json.format[UserDTO]
}
