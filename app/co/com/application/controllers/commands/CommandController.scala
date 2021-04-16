package co.com.application.controllers.commands

import akka.actor.ActorSystem
import co.com.application.services.{ PersistenceUserService, ServiceHelper }
import co.com.domain.contracts.UserRepositoryBase
import co.com.domain.services.UserService
import co.com.infrastructure.persistance.repositories.UserRepository
import co.com.infrastructure.persistance.{ dbConfigPostgres, dbConfigReadOnlyPostgres }
import co.com.infrastructure.services.RequestPostService
import com.google.inject.Injector
import play.api.libs.ws.WSClient
import play.api.{ Configuration, Environment }
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

import javax.inject.{ Inject, Singleton }

class CommandController {

}

@Singleton
class Dependency @Inject() (
    val config: Configuration,
    val ws: WSClient,
    val injector: Injector,
    val actor: ActorSystem,
    val environment: Environment
) {

  lazy val dbConfig: DatabaseConfig[JdbcProfile] = dbConfigPostgres
  lazy val dbReadOnly: DatabaseConfig[JdbcProfile] = dbConfigReadOnlyPostgres

  lazy val userRepo: UserRepositoryBase = UserRepository

  lazy val serviceHelper: ServiceHelper = ServiceHelper
  lazy val savePersistenceUserService: PersistenceUserService = PersistenceUserService
  lazy val userService: UserService = UserService
  lazy val requestPostService: RequestPostService = RequestPostService
}
