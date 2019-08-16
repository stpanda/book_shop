package com.andersenlab.education.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{as, complete, delete, entity, onComplete, path, pathPrefix, post, put}
import akka.http.scaladsl.server.Route
import com.andersenlab.education.model.Order
import converter.JsonFormat._
import service.OrderService

import scala.util.{Failure, Success}

class OrderRoutes(orderService: OrderService) {

  def routes: Route = {
    pathPrefix("order_service") {
      post {
        entity(as[Order]) { order =>
          val res = orderService.addOrder(order)
          onComplete(res) {
            case Success(_) => complete(StatusCodes.OK)
            case Failure(_) => complete(StatusCodes.BadRequest)
          }
        }
      }
      put {
        entity(as[Order]) { order =>
          val res = orderService.updateOrder(order)
          onComplete(res) {
            case Success(_) => complete(StatusCodes.OK)
            case Failure(_) => complete(StatusCodes.BadRequest)
          }
        }
      }
      path(LongNumber) { id =>
        delete {
          val res = orderService.deleteById(id)
          onComplete(res) {
            case Success(_) => complete(StatusCodes.OK)
            case Failure(_) => complete(StatusCodes.BadRequest)
          }
        }
      }
    }
  }
}
