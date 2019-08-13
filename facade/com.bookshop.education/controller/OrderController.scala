package controller

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import dao.model.OrderTable.Order
import service.OrderService

import scala.util.{Failure, Success}

object OrderController {

  var routes: Route = {
    pathPrefix("order_service") {
      post {
        entity(as[Order]) { order =>
          val res = OrderService.addOrder(order)
          onComplete(res) {
            case Success(_) => complete(StatusCodes.OK)
            case Failure(_) => complete(StatusCodes.BadRequest)
          }
        }
      }
      put {
        entity(as[Order]) { order =>
          val res = OrderService.updateOrder(order)
          onComplete(res) {
            case Success(_) => complete(StatusCodes.OK)
            case Failure(_) => complete(StatusCodes.BadRequest)
          }
        }
      }
      path(LongNumber) { id =>
        delete {
          val res = OrderService.deleteById(id)
          onComplete(res) {
            case Success(_) => complete(StatusCodes.OK)
            case Failure(_) => complete(StatusCodes.BadRequest)
          }
        }
      }
    }
  }
}
