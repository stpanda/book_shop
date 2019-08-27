package com.andersenlab.education.routes

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import akka.http.scaladsl.server.Directives.{as, complete, delete, entity, get, onComplete, path, pathPrefix, post, put, _}
import akka.http.scaladsl.server.Route
import com.andersenlab.education.corshttp.CORSHandler
import com.andersenlab.education.model.Person
import com.andersenlab.education.service.PersonService
import converter.JsonFormat._

import scala.util.{Failure, Success}

class PersonRoutes(personService: PersonService) {
  private val cors = new CORSHandler {}

  def routes: Route = pathPrefix("person_service"){

    path("get_person"/Segment) { id =>
      concat(
        get {
          cors.corsHandler {
            val res = personService.findOne(id.toLong)
            onComplete(res) {
              case Success(_) => complete(HttpEntity(ContentTypes.`application/json`, res.toString))
              case Failure(_) => complete(StatusCodes.BadRequest)
            }
          }
        })
    }
    path("delete_person"/Segment) { id =>
      concat(
        delete {
          cors.corsHandler {
            val res = personService.deleteById(id.toLong)
            onComplete(res) {
              case Success(_) => complete(StatusCodes.OK)
              case Failure(_) => complete(StatusCodes.BadRequest)
            }
          }
        })
    }

        post {
          entity(as[Person]) { person =>
            val res = personService.add(person)
            onComplete(res) {
              case Success(_) => complete(StatusCodes.OK)
              case Failure(_) => complete(StatusCodes.BadRequest)
            }
          }
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
