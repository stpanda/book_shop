import sbt._
import Dependencies._

object Settings {
  val dbDependencies: Seq[ModuleID] = Seq(postgres, slick, hikari, flyway, circeCore, circeGeneric, circeParser)
  val akkaDependencies: Seq[ModuleID] = Seq(akkaHttp, akkaHttpCirce)
  val commonDependencies: Seq[ModuleID] = Seq(slf4jApi, slf4j, pureConfig, httpCors)
}
