package co.com.application.controllers.commands

import akka.actor.ActorSystem
import co.com.application.services.UserManagerService
import co.com.domain.contracts.UserRepositoryBase
import co.com.domain.services.UserService
import co.com.infrastructure.persistance.repositories.UserRepository
import co.com.infrastructure.persistance.{ dbConfigPostgres, dbConfigReadOnlyPostgres }
import com.google.inject.Injector
import play.api.{ Configuration, Environment }
import play.api.libs.ws.WSClient
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

import javax.inject.{ Inject, Singleton }

class CommandController {

}

trait Config extends scala.AnyRef

@Singleton
class Dependency @Inject() (
    val config: Configuration,
    val ws: WSClient,
    val injector: Injector,
    val actor: ActorSystem,
    val environment: Environment
) extends Config {

  lazy val dbConfig: DatabaseConfig[JdbcProfile] = dbConfigPostgres
  lazy val dbReadOnly: DatabaseConfig[JdbcProfile] = dbConfigReadOnlyPostgres

  lazy val userRepo: UserRepositoryBase = UserRepository

  lazy val userManagerService: UserManagerService = UserManagerService
  lazy val userService: UserService = UserService
}
