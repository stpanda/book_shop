package com.andersenlab.education

import akka.http.scaladsl.server.{HttpApp, Route}
import com.andersenlab.education.routes.{BookRoutes, OrderRoutes, PersonRoutes}

object WebServer extends HttpApp {
  val services = new Services
  import services._

  val bookRoutes = new BookRoutes(bookService)
  val personRoutes = new PersonRoutes(personService)
  val orderRoutes = new OrderRoutes(orderService)

  override protected def routes: Route = ignoreTrailingSlash(
    concat(
      bookRoutes.routes,
      personRoutes.routes,
      orderRoutes.routes
    )
  )
}
