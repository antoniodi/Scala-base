package co.com.application.controllers.queries

import co.com.application.acl.formats.Formats._
import co.com.application.acl.http.HTTPError
import co.com.application.controllers.commands.Dependency
import play.api.libs.json.Json
import play.api.mvc.{ MessagesAbstractController, MessagesControllerComponents }

import javax.inject.Inject
import scala.concurrent.ExecutionContextExecutorService

class FindUserQuery @Inject() (
    dependency: Dependency,
    cc: MessagesControllerComponents ) extends MessagesAbstractController( cc ) with HTTPError {

  implicit val ec: ExecutionContextExecutorService = queryExecutionContext

  def findUser( username: String ) = Action.async { implicit request =>
    dependency.userRepo.findWithFuture( username ).run( dependency.dbReadOnly )
      .fold( internalServerError, user => Ok( Json.toJson( user ) ) )
  }

}
