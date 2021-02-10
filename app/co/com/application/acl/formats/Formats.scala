package co.com.application.acl.formats

import co.com.application.acl.dtos.{AdminDTO, CustomerDTO, UserDTO, UserDTO1}
import co.com.suite.error.ApplicationError
import play.api.libs.json.{JsObject, JsString, JsValue, Json, Reads, Writes}

object Formats {

  implicit val applicationErrorFormat: Writes[ApplicationError] =
    ( error: ApplicationError ) => JsObject( Map( "errorMessage" -> JsString( error.errorMessage ) ) )

  //  implicit val writerErrorDominio: Writes[ApplicationError] = new Writes[ApplicationError] {
  //    override def writes(e: ErrorDominio): JsValue = {
  //      JsObject(Map("mensaje" -> JsString(e.mensaje)))
  //    }
  //  }

  implicit val userDTOWrite: Writes[UserDTO1] = Json.writes[UserDTO1]

  implicit val userDTOReads: Reads[UserDTO] = ( json: JsValue ) => {
    ( json \ "rol" ).asOpt[String] match {
      case Some( "admin" )    => Json.reads[AdminDTO].reads( json )
      case Some( "customer" ) => Json.reads[CustomerDTO].reads( json )
      case _ => Json.reads[CustomerDTO].reads( json )
    }
  }
}

object UserFormats {

  val AdminDTOReads: Reads[AdminDTO] = Json.reads[AdminDTO]
  val CustomerDTOReads: Reads[CustomerDTO] = Json.reads[CustomerDTO]
}
