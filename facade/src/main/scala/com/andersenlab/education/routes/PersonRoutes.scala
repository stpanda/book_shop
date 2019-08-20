package com.andersenlab.education.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import akka.http.scaladsl.server.Directives.{as, complete, delete, entity, get, onComplete, path, pathPrefix, post, put}
import akka.http.scaladsl.server.Route
import com.andersenlab.education.model.Person
import com.andersenlab.education.service.PersonService
import converter.JsonFormat._

import scala.util.{Failure, Success}

class PersonRoutes(personService: PersonService) {

  def routes: Route = {
    pathPrefix("person_service") {
      post {
        entity(as[Person]) { person =>
          val res = personService.add(person)
          onComplete(res) {
            case Success(_) => complete(StatusCodes.OK)
            case Failure(_) => complete(StatusCodes.BadRequest)
          }
        }
      }
      path(LongNumber) { id =>
        concat(
        get {
          val res = personService.findOne(id)
          onComplete(res) {
            case Success(_) => complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, res.toString))
            case Failure(_) => complete(StatusCodes.BadRequest)
          }

      },
      delete {
        val res = personService.deleteById(id)
        onComplete(res) {
        case Success(_) => complete(StatusCodes.OK)
        case Failure(_) => complete(StatusCodes.BadRequest)
        }
      }
      )
      }
      put {
        entity(as[Person]) { person =>
          val res = personService.update(person)
          onComplete(res) {
            case Success(_) => complete(StatusCodes.OK)
            case Failure(_) => complete(StatusCodes.BadRequest)
          }
        }
      }
    }
  }
}
