package controller

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import dao.model.PersonTable.Person
import service.PersonService

object PersonController {

  var routes: Route = {
    pathPrefix("person_service") {
      post {
        entity(as[Person]) { person =>
          val res = PersonService.add(person)
          res match {
            case res => complete(StatusCodes.OK)
            case _ => complete(StatusCodes.BadRequest)
          }
        }
      }
      path(LongNumber) { id =>
        get {
          val res = PersonService.findOne(id)
          res match {
            case Some(p) => complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, p.toString))
            case None => complete(StatusCodes.BadRequest)
          }
        }
      }
      path(LongNumber) { id =>
      delete {
        PersonService.deleteById(id) match {
          case res => complete(StatusCodes.OK)
          case _ => complete(StatusCodes.BadRequest)
        }
      }
      }
      put {
        entity(as[Person]) { person =>
          val res = PersonService.update(person)
          res match {
            case res => complete(StatusCodes.OK)
            case _ => complete(StatusCodes.BadRequest)
          }
        }
      }
    }
  }

}
