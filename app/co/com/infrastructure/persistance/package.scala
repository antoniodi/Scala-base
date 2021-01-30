package co.com.infrastructure

import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

package object persistance {

  lazy val dbConfigPostgres = DatabaseConfig.forConfig[JdbcProfile]( "slick.dbs.default" )
  lazy val dbConfigReadOnlyPostgres = DatabaseConfig.forConfig[JdbcProfile]( "slick.dbs.postgres-read-only" )

  lazy val dbConfigH2 = DatabaseConfig.forConfig[JdbcProfile]( "slick.dbs.h2" )

}
