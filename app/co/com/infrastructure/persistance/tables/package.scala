package co.com.infrastructure.persistance

import slick.lifted.TableQuery

package object tables {

  val users = TableQuery[UserTable]

}
