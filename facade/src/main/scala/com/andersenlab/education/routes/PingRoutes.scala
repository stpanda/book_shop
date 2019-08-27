package com.andersenlab.education.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.andersenlab.education.corshttp.CORSHandler
import com.andersenlab.education.model.Book
import com.andersenlab.education.service.BookService
import converter.JsonFormat._

import scala.util.{Failure, Success}

class PingRoutes(bookService: BookService) {
  private val cors = new CORSHandler {} //Cache this somewhere

  //A simple /ping endpoint which will echo 'Pong' for any POST requests

  val ping: Route = path("ping") {
    //The OPTIONS endpoint - doesn't need to do anything except send an OK
    options {
      cors.corsHandler(complete(StatusCodes.OK))
    } ~ post(cors.corsHandler(
      entity(as[Book]) { book =>
        val res = bookService.add(book)
        onComplete(res) {
          case Success(_) => complete(StatusCodes.OK)
          case Failure(_) => complete(StatusCodes.BadRequest)
        }
      }
    ))
  }

}
