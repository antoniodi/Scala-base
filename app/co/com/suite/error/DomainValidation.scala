package co.com.suite.error

sealed trait DomainValidation extends ApplicationError

case object UserNotFound extends DomainValidation {
  def errorMessage: String = "User not found."
}
