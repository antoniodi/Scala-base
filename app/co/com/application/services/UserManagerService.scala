package co.com.application.services

import cats.data.{EitherT, Reader}
import co.com.application.acl.dtos.{UserDTO, UserDTO1}
import co.com.application.controllers.commands.Dependency
import co.com.infrastructure.Types.EitherTResult
import monix.eval.Task

trait UserManagerService {

  def findUser( username: String ): Reader[Dependency, EitherTResult[UserDTO1]] = Reader {
    dependecy: Dependency =>

      for {
        user <- dependecy.userRepo.find( username ).run( dependecy.dbConfig )
        validUser <- EitherT( Task( dependecy.userService.validateUser( user ) ) )
      } yield validUser
  }

  def save( userDTO: UserDTO ) = {

    userDTO.toString
  }

}

object UserManagerService extends UserManagerService