package controller

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives.{complete, _}
import dao.model.BookTable.Book
import service.BookService

import scala.util.{Failure, Success}

object BookController {
  var routes: Route = {
    pathPrefix("book_service") {
      post {
        entity(as[Book]) { book =>
          val res = BookService.add(book)
          onComplete(res) {
            case Success(_) => complete(StatusCodes.OK)
            case Failure(_) => complete(StatusCodes.BadRequest)
          }
        }
      }
      path(LongNumber) { id =>
        get {
          val res = BookService.findOne(id)
          onComplete(res) {
            case Success(_) => complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, res.toString))
            case Failure(_) => complete(StatusCodes.BadRequest)
          }
        }
      }
      path(LongNumber) { id =>
        delete {
          val res = BookService.deleteById(id)
          onComplete(res) {
            case Success(_) => complete(StatusCodes.OK)
            case Failure(_) => complete(StatusCodes.BadRequest)
          }
        }
      }
      put {
        entity(as[Book]) { book =>
          val res = BookService.update(book)
          onComplete(res) {
            case Success(_) => complete(StatusCodes.OK)
            case Failure(_) => complete(StatusCodes.BadRequest)
          }
        }
      }
    }
  }

}
