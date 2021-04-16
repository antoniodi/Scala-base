package co.com.application.services

import akka.Done
import cats.data.{ EitherT, Reader }
import co.com.application.acl.dtos.UserDTO
import co.com.application.controllers.commands.Dependency
import co.com.infrastructure.Types.EitherTResult
import monix.eval.Task

trait PersistenceUserService {

  def saveUser( userDTO: UserDTO ): Reader[Dependency, EitherTResult[Done]] = Reader {
    dependepncy: Dependency =>

      val userId = dependepncy.userRepo.generateId()

      for {
        user <- dependepncy.userRepo.findWithTask( userDTO.username ).run( dependepncy.dbReadOnly )
        _ <- EitherT( Task( dependepncy.userService.validateExistingUser( user ) ) )
        newUser = UserDTO.toUser( userId, userDTO )
        currentDate = dependepncy.serviceHelper.getCurrentLocalDateTime
        _ <- dependepncy.userRepo.add( newUser, currentDate ).run( dependepncy.dbConfig )
      } yield Done

  }

}

object PersistenceUserService extends PersistenceUserService
