package co.com.application.services

import cats.data.{EitherT, Reader}
import co.com.application.acl.dtos.UserDTO
import co.com.application.controllers.commands.Dependency
import co.com.infrastructure.Types.EitherTResult
import monix.eval.Task

trait UserManagerService {

  def findUser( username: String ): Reader[Dependency, EitherTResult[UserDTO]] = Reader {
    dependecy: Dependency =>

      dependecy.userRepo.find( username ).run( dependecy.dbConfig )
        .flatMap(user => EitherT( Task( dependecy.userService.validateUser( user ) ) ) )
  }

}

object UserManagerService extends UserManagerService