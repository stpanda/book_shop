package com.andersenlab.education

import akka.http.scaladsl.server.{HttpApp, Route}
import com.andersenlab.education.corshttp.CORSHandler
import com.andersenlab.education.routes.{BookRoutes, OrderRoutes, PersonRoutes, PingRoutes}

object WebServer extends HttpApp {
  val services = new Services
  private val cors = new CORSHandler {}
  import services._

  val bookRoutes = new BookRoutes(bookService)
  val personRoutes = new PersonRoutes(personService)
  val orderRoutes = new OrderRoutes(orderService)
  val pingRoutes = new PingRoutes(bookService)

  override protected def routes: Route = ignoreTrailingSlash(
    concat(
      bookRoutes.routes,
      personRoutes.routes,
      orderRoutes.routes,
      pingRoutes.ping
    )
  )
}
