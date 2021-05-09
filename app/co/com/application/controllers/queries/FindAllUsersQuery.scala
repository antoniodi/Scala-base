package co.com.application.controllers.queries

import co.com.application.acl.formats.Formats.userDTOWrite
import co.com.application.acl.http.HTTPError
import co.com.application.controllers.commands.{ Dependency, commandScheduler }
import monix.execution.schedulers.ExecutorScheduler
import org.slf4j
import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc.{ Action, AnyContent, MessagesAbstractController, MessagesControllerComponents }

import javax.inject.Inject

class FindAllUsersQuery @Inject() (
    dependency: Dependency,
    cc: MessagesControllerComponents ) extends MessagesAbstractController( cc ) with HTTPError {

  implicit val sc: ExecutorScheduler = commandScheduler

  val logger: slf4j.Logger = Logger( getClass ).logger

  def findAll(): Action[AnyContent] = Action.async { implicit request =>

    logger.info( "find all users" )
    dependency.userRepo.findAll().run( dependency.dbReadOnly )
      .fold( { errors =>
        logger.error( s"an error was occurred: ${errors.toString()}." )
        internalServerError( errors )
      }, users => {
        logger.info( "several users found." )
        Ok( Json.toJson( users ) )
      } ).runToFuture
  }

}
