package com.shah.persistence.demo


package object AccountApi {

  import akka.actor.{ActorSystem, Props}
  import com.shah.persistence.demo.account.Account

  // Transaction Types
  private[demo] sealed trait TransactionType

  case object CR extends TransactionType

  case object DR extends TransactionType

  // Commands
  case class Operation(amount: Float, `type`: TransactionType)

  def startActor(system: ActorSystem) = system.actorOf(Props[Account])

}

package object AccountViewApi {

  import akka.actor.Props
  import com.shah.persistence.demo.account.AccountViewImpl

  import scala.concurrent.ExecutionContext

  case object PrintAccountBalance

  case object ReturnAccountBalance

  def props(snapshotFrequency: Int)(implicit ec: ExecutionContext) =
    Props(new AccountViewImpl(snapshotFrequency, 5))
}