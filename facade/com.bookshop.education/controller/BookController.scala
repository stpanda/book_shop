package controller

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import dao.model.BookTable.Book
import service.BookService

object BookController {
  var routes: Route = {
    pathPrefix("book_service") {
      post {
        entity(as[Book]) { book =>
          val res = BookService.add(book)
          res match {
            case res => complete(StatusCodes.OK)
            case _ => complete(StatusCodes.BadRequest)
          }
        }
      }
      path(LongNumber) { id =>
        get {
          val res = BookService.findOne(id)
          res match {
            case Some(b) => complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, b.toString))
            case None => complete(StatusCodes.BadRequest)
          }
        }
      }
      path(LongNumber) { id =>
        delete {
          BookService.deleteById(id) match {
            case res => complete(StatusCodes.OK)
            case _ => complete(StatusCodes.BadRequest)
          }
        }
      }
      put {
        entity(as[Book]) { book =>
          val res = BookService.update(book)
          res match {
            case res => complete(StatusCodes.OK)
            case _ => complete(StatusCodes.BadRequest)
          }
        }
      }
    }
  }

}
