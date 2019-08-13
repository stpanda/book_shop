package controller

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import converter.Converter
import dao.model.PersonTable.Person
import service.PersonService

import scala.util.{Failure, Success}

object PersonController {
  dec: Converter =>

  var routes: Route = {
    pathPrefix("person_service") {
      post {
        entity(as[Person]) { person =>
          val res = PersonService.add(person)
          onComplete(res) {
            case Success(_) => complete(StatusCodes.OK)
            case Failure(_) => complete(StatusCodes.BadRequest)
          }
        }
      }
      path(LongNumber) { id =>
        get {
          val res = PersonService.findOne(id)
          onComplete(res) {
            case Success(_) => complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, res.toString))
            case Failure(_) => complete(StatusCodes.BadRequest)
          }
        }
      }
      path(LongNumber) { id =>
      delete {
        val res = PersonService.deleteById(id)
        onComplete(res) {
        case Success(_) => complete(StatusCodes.OK)
        case Failure(_) => complete(StatusCodes.BadRequest)
        }
      }
      }
      put {
        entity(as[Person]) { person =>
          val res = PersonService.update(person)
          onComplete(res) {
            case Success(_) => complete(StatusCodes.OK)
            case Failure(_) => complete(StatusCodes.BadRequest)
          }
        }
      }
    }
  }

}
