package co.com.application.controllers.commands

import cats.data.NonEmptyList
import co.com.application.acl.dtos.UserDTO
import co.com.application.acl.formats.Formats._
import co.com.suite.error.SaveError
import play.api.libs.json.Json
import play.api.mvc.{MessagesAbstractController, MessagesControllerComponents}

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class AddUserCommand @Inject() (
    dependency: Dependency,
    cc: MessagesControllerComponents )( implicit ec: ExecutionContext )
  extends MessagesAbstractController( cc ) {

//  def autorizar() = Action.async { request =>
//    request.body.asJson.fold {
//      Future.successful( internalServerError( NonEmptyList.of( SaveError( "No se pudo parsear el cuerpo del request como JSON" ) ) ) )
//    }( a => a.validate[UserDTO].asOpt.fold {
//      Future.successful( internalServerError( NonEmptyList.of( SaveError( "La estructura del request no es la requerida" ) ) ) )
//    } { req =>
//      Future.successful( dependency.userManagerService.save( req ).map( value => Ok( s"""{\"resultado\": \"$value\"}""" ).as( JSON ) ) )
//    })
//  }

}

object hol {

  def main(args: Array[String]): Unit = {
    val json = """{
      |  "rol": "customer",
      |  "username": "luiscocr",
      |  "email": "anthonydicortes@gmail.com",
      |  "customerCode": "1234"
      |}""".stripMargin

    val obj = Json.parse( json ).validate
    print( obj.toString )
  }
}
