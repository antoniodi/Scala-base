package co.com.application.services

import cats.data.{ EitherT, Reader }
import co.com.application.acl.dtos.UserDTO
import co.com.application.controllers.commands.Dependency
import co.com.infrastructure.Types.EitherTResult
import monix.eval.Task
import co.com.application.acl.dtos.UserDTO._

trait UserManagerService {

  def findUser( username: String ): Reader[Dependency, EitherTResult[UserDTO]] = Reader {
    dependecy: Dependency =>

      for {
        user <- dependecy.userRepo.find( username ).run( dependecy.dbConfig )
        validUser <- EitherT( Task( dependecy.userService.validateUser( user ) ) )
      } yield validUser
  }

}

object UserManagerService extends UserManagerService