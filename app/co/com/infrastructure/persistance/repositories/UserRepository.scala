package co.com.infrastructure.persistance.repositories

import akka.Done
import cats.data.{ EitherT, NonEmptyList, Reader }
import co.com.domain.contracts.UserRepositoryBase
import co.com.domain.models.entities.User
import co.com.infrastructure.Types.EitherTResult
import co.com.infrastructure.persistance.tables.{ UserRecord, users }
import co.com.suite.error.{ FindError, SaveError, TransactionError }
import monix.eval.Task
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._

import java.sql.Timestamp
import java.time.LocalDateTime

object UserRepository extends UserRepositoryBase {

  def add( user: User, validDate: LocalDateTime ): Reader[DatabaseConfig[JdbcProfile], EitherTResult[Done]] = Reader {
    dbConfig: DatabaseConfig[JdbcProfile] =>

      val query = users += UserRecord( user.username, user.email, Timestamp.valueOf( validDate ) )

      EitherT {
        Task.deferFuture( dbConfig.db.run( query ) )
          .map( _ => Right( Done ) )
          .onErrorRecover {
            case error: Throwable => Left( NonEmptyList.of( SaveError( "user" ), TransactionError( error.getMessage ) ) )
          }
      }
  }

  def find( username: String ): Reader[DatabaseConfig[JdbcProfile], EitherTResult[Option[User]]] = Reader {
    dbConfig: DatabaseConfig[JdbcProfile] =>

      val query = users.filter( _.username === username )

      EitherT {
        Task.deferFuture( dbConfig.db.run( query.result ) )
          .map( result => Right( result.headOption.map( userRecord => User( userRecord.username, userRecord.email ) ) ) )
          .onErrorRecover {
            case error: Throwable => Left( NonEmptyList.of( FindError( "user", username ), TransactionError( error.getMessage ) ) )
          }
      }
  }

}
