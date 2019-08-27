package com.andersenlab.education.routes

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import akka.http.scaladsl.server.Directives.{as, complete, entity, onComplete, path, post, _}
import akka.http.scaladsl.server.Route
import com.andersenlab.education.corshttp.CORSHandler
import com.andersenlab.education.model.Book
import com.andersenlab.education.service.BookService
import converter.JsonFormat._

import scala.util.{Failure, Success}

class BookRoutes(bookService: BookService) {
  private val cors = new CORSHandler {}

  def routes: Route = pathPrefix("book_service"){

    options {
      cors.corsHandler(complete(StatusCodes.OK))
    } ~ post {
            cors.corsHandler(
            entity(as[Book]) { book =>
              val res = bookService.add(book)
              onComplete(res) {
                case Success(_) => complete(StatusCodes.OK)
                case Failure(_) => complete(StatusCodes.BadRequest)
              }
            })
        }
    path("get_book"/Segment) { id =>
      concat(
        get {
          cors.corsHandler {
            val res = bookService.findOne(id.toLong)
            onComplete(res) {
              case Success(_) => complete(HttpEntity(ContentTypes.`application/json`, res.toString))
              case Failure(_) => complete(StatusCodes.BadRequest)
            }
          }
        }
      )
    }
    path("delete_book"/Segment) { id =>
      concat(
        delete {
          cors.corsHandler {
            val res = bookService.deleteById(id.toLong)
            onComplete(res) {
              case Success(_) => complete(StatusCodes.OK)
              case Failure(_) => complete(StatusCodes.BadRequest)
            }
          }
        }
      )
    }
        /*put {
          entity(as[Book]) { book =>
            val res = bookService.update(book)
            onComplete(res) {
              case Success(_) => complete(StatusCodes.OK)
              case Failure(_) => complete(StatusCodes.BadRequest)
            }
          }
        }*/
      }
}