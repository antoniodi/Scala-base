package co.com.application.controllers.commands

import play.api.mvc.{ MessagesAbstractController, MessagesControllerComponents }

import javax.inject.Inject
import scala.concurrent.ExecutionContext

class AddUserCommand @Inject() (
    dependency: Dependency,
    cc: MessagesControllerComponents )( implicit ec: ExecutionContext )
  extends MessagesAbstractController( cc ) {

}
