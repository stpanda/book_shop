import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory

import scala.concurrent.ExecutionContextExecutor

object Main extends App {
  val config = ConfigFactory.load()
  val host = config.getString("url")
  val port = config.getInt("port")

  implicit val system: ActorSystem = ActorSystem()
  implicit val ec: ExecutionContextExecutor = system.dispatcher


  implicit val materializer: ActorMaterializer = ActorMaterializer()

}
