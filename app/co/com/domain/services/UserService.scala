package co.com.domain.services

import cats.data.NonEmptyList
import co.com.domain.models.entities.User
import co.com.suite.error.{ ApplicationError, DomainValidation, UserNotFound }

trait UserService {

  def validateUser( user: Option[User] ): Either[NonEmptyList[ApplicationError], User] =

    user.fold[Either[NonEmptyList[ApplicationError], User]]( Left( NonEmptyList.of( UserNotFound ) ) )( Right( _ ) )
}

object UserService extends UserService
