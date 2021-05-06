package co.com.application.services

import co.com.domain.services.UserService
import co.com.factories.UserDTOFactory.LouisUserDTO
import co.com.factories.UserFactory.LouisUser
import co.com.tool.FutureTool.waitForFutureResult
import co.com.{TestKit, TestKitBase}
import monix.eval.Task
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper

import scala.concurrent.Future
import scala.io.StdIn
import scala.util.Random

class AsynchronousServiceTest extends TestKit with TestKitBase {

  "AsynchronousService" should {

    "to execute Async" when {
      "zip two futures" should {
        "execute future eager" in {

          val future = Future.successful( Random.nextInt() )

          val zipFuture: Future[String] = future.zip( future ).map { case ( random1, random2 ) => s"${random1.toString} - ${random2.toString}" }

          val result = waitForFutureResult( zipFuture )

          result mustBe 10
        }
      }

      "zip two futures transparent" should {
        "execute future eager" in {

          val future = Future.successful( Random.nextInt() )

          val zipFuture: Future[String] = Future.successful( Random.nextInt() ).zip( Future.successful( Random.nextInt() ) ).map { case ( random1, random2 ) => s"${random1.toString} - ${random2.toString}" }

          val result = waitForFutureResult( zipFuture )

          result mustBe 10
        }
      }

      "zip two task" should {
        "execute task lazy" in {

          val task = Task( Random.nextInt() )

          val zipTask = Task.parZip2( task, task ).map { case ( random1, random2 ) => s"${random1.toString} - ${random2.toString}" }

          val result = waitForFutureResult( zipTask.runToFuture )

          result mustBe 10
        }
      }

      "zip two task transparent" should {
        "execute task lazy" in {

          val zipTask: Task[String] = Task.parZip2( Task.now( Random.nextInt() ), Task.now( Random.nextInt() ) ).map { case ( random1, random2 ) => s"${random1.toString} - ${random2.toString}" }

          val result = waitForFutureResult( zipTask.runToFuture )

          result mustBe 10
        }
      }

      "zip two task output" should {
        "execute task lazy" in {

          val task = Task.now( StdIn.readInt() )

          val zipTask = Task.parZip2( task, task ).map { case ( random1, random2 ) => s"${random1.toString} - ${random2.toString}" }

          zipTask.runAsync {
            case Left( value ) => println( value )
            case Right( value ) => println( value )
          }

          val result = waitForFutureResult( zipTask.runToFuture )

          result mustBe 10
        }
      }
    }
  }

}
