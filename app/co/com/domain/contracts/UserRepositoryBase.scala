package co.com.domain.contracts

import akka.Done
import cats.data.Reader
import co.com.domain.model.entities.User
import co.com.infrastructure.Types.{ EitherFResult, EitherTResult }
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

import java.time.LocalDateTime
import scala.concurrent.ExecutionContext

trait UserRepositoryBase {

  def add( user: User, validFrom: LocalDateTime ): Reader[DatabaseConfig[JdbcProfile], EitherTResult[Done]]
  def findWithTask( username: String ): Reader[DatabaseConfig[JdbcProfile], EitherTResult[Option[User]]]
  def findWithFuture( username: String )( implicit ec: ExecutionContext ): Reader[DatabaseConfig[JdbcProfile], EitherFResult[Option[User]]]
  def generateId(): String

}
