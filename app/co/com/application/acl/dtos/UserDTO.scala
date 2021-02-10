package co.com.application.acl.dtos

import co.com.domain.models.entities.User
import play.api.libs.json.Json

import scala.language.implicitConversions

case class UserDTO1( username: String, email: String )
//
object UserDTO1 {

  implicit def toUserDTO( user: User ) = UserDTO1( user.username, user.email )
}

trait UserDTO {
  def username: String
  def email: String
}


case class AdminDTO( username: String, email: String, password: String ) extends UserDTO

case class CustomerDTO( username: String, email: String, customerCode: String ) extends UserDTO