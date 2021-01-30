package co.com.application.controllers

import monix.execution.ExecutionModel.AlwaysAsyncExecution
import monix.execution.{ Features, UncaughtExceptionReporter }
import monix.execution.schedulers.ExecutorScheduler

import java.util.concurrent.Executors
import scala.concurrent.{ ExecutionContext, ExecutionContextExecutorService }

package object commands {

  lazy val commandExecutionContext: ExecutionContextExecutorService = ExecutionContext.fromExecutorService( Executors.newFixedThreadPool( 5 ) )

  lazy val commandScheduler: ExecutorScheduler = ExecutorScheduler(
    Executors.newFixedThreadPool( 10 ),
    UncaughtExceptionReporter( t => println( s"this should not happen: ${t.getMessage}" ) ),
    AlwaysAsyncExecution,
    Features.empty
  )

}
