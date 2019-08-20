package com.andersenlab.education.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import akka.http.scaladsl.server.Directives.{as, complete, delete, entity, get, onComplete, path, pathPrefix, post, put}
import akka.http.scaladsl.server.Route
import com.andersenlab.education.model.Book
import com.andersenlab.education.service.BookService
import converter.JsonFormat._

import scala.util.{Failure, Success}

class BookRoutes(bookService: BookService) {

  def routes: Route = {
    pathPrefix("book_service") {
      post {
        entity(as[Book]) { book =>
          val res = bookService.add(book)
          onComplete(res) {
            case Success(_) => complete(StatusCodes.OK)
            case Failure(_) => complete(StatusCodes.BadRequest)
          }
        }
      }
      path(LongNumber) { id =>
        concat(
        get {
          val res = bookService.findOne(id)
          onComplete(res) {
            case Success(_) => complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, res.toString))
            case Failure(_) => complete(StatusCodes.BadRequest)
          }
        },
          delete {
            val res = bookService.deleteById(id)
            onComplete(res) {
              case Success(_) => complete(StatusCodes.OK)
              case Failure(_) => complete(StatusCodes.BadRequest)
            }
          }
        )
      }
      put {
        entity(as[Book]) { book =>
          val res = bookService.update(book)
          onComplete(res) {
            case Success(_) => complete(StatusCodes.OK)
            case Failure(_) => complete(StatusCodes.BadRequest)
          }
        }
      }
    }
  }
}
