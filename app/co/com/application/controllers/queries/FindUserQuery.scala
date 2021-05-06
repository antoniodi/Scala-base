package co.com.application.controllers.queries

import co.com.application.acl.formats.Formats._
import co.com.application.acl.http.HTTPError
import co.com.application.controllers.commands.Dependency
import org.slf4j
import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc.{MessagesAbstractController, MessagesControllerComponents}

import javax.inject.Inject
import scala.concurrent.ExecutionContextExecutorService

class FindUserQuery @Inject() (
    dependency: Dependency,
    cc: MessagesControllerComponents ) extends MessagesAbstractController( cc ) with HTTPError {

  implicit val ec: ExecutionContextExecutorService = queryExecutionContext

  val logger: slf4j.Logger = Logger( getClass ).logger //LoggerFactory.getLogger( getClass )

  def findUser( username: String ) = Action.async { implicit request =>

    logger.info( "find users" )
    dependency.userRepo.findWithFuture( username ).run( dependency.dbReadOnly )
      .fold( { errors =>
        logger.error( s"errors occurs: ${errors.toString()}." )
        internalServerError( errors )
      }, user => {
        logger.info( s"user was found: ${user.toString}." )
        Ok( Json.toJson( user ) )
      } )
  }

}
