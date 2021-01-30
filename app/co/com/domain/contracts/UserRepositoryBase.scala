package co.com.domain.contracts

import akka.Done
import cats.data.Reader
import co.com.domain.models.entities.User
import co.com.infrastructure.Types.EitherTResult
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

import java.time.LocalDateTime

trait UserRepositoryBase {

  def add( user: User, validDate: LocalDateTime ): Reader[DatabaseConfig[JdbcProfile], EitherTResult[Done]]
  def find( username: String ): Reader[DatabaseConfig[JdbcProfile], EitherTResult[Option[User]]]

}
