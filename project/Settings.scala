import sbt._
import Dependencies._

object Settings {
  val dbDependencies: Seq[ModuleID] = 
    Seq(postgres, slick, hikari, flyway, slf4jApi, slf4jLog, circeCore, circeGeneric, circeParser, pureConfig)
  val akkaDependencies: Seq[ModuleID] = Seq(akkaHttp, akkaHttpCirce)
}
