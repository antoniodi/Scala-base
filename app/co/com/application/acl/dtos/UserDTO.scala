package co.com.application.acl.dtos

import co.com.domain.models.entities.User
import scala.language.implicitConversions

case class UserDTO( username: String, email: String )

object UserDTO {

  implicit def toUserDTO( user: User ) = UserDTO( user.username, user.email )
}
