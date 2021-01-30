package co.com.infrastructure

import cats.data.{ EitherT, NonEmptyList }
import co.com.suite.error.ApplicationError
import monix.eval.Task

object Types {

  type EitherTResult[A] = EitherT[Task, NonEmptyList[ApplicationError], A]

}
