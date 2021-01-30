package co.com.infrastructure.persistance.tables

import co.com.infrastructure.persistance.tables.enhancements.ModificableTB
import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

import java.sql.Timestamp

final case class UserRecord(
    username: String,
    email: String,
    validFrom: Timestamp,
    validTo: Option[Timestamp] = None
)

class UserTable( tag: Tag ) extends Table[UserRecord]( tag, "USER" ) with ModificableTB {

  def username = column[String]( "USERNAME" )
  def email = column[String]( "EMAIL" )
  def validFrom = column[Timestamp]( "START_DATE" )
  def validTo = column[Option[Timestamp]]( "END_DATE", O.Default( None ) )

  def pk = primaryKey( "USER_PK", username )

  //def idx = index("user_unique", ( username, email ), unique = true )

  def * = ( username, email, validFrom, validTo ).mapTo[UserRecord]
}