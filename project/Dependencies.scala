import sbt._

object Dependencies {
  import Version._

  //PureConf
  val pureConfig = "com.github.pureconfig" %% "pureconfig" % pureConf
  //PostgreSQL
  val postgres = "org.postgresql" % "postgresql" % postgreSqlVersion
  //Flyway
  val flyway = "org.flywaydb" % "flyway-core" % flywayCoreVersion
  //Akka
  val akkaHttp = "com.typesafe.akka" %% "akka-http" % akkaVersion
  val akkaHttpCirce = "de.heikoseeberger" %% "akka-http-circe" % akkaCirceVersion
  //Circe
  val circeCore = "io.circe" %% "circe-core" % circeVersion
  val circeGeneric = "io.circe" %% "circe-generic" % circeVersion
  val circeParser = "io.circe" %% "circe-parser" % circeVersion
  //Slick
  val slick = "com.typesafe.slick" %% "slick" % slickVersion
  val hikari = "com.typesafe.slick" %% "slick-hikaricp" % slickVersion exclude("com.zaxxer", "HikariCP-java6")
  //slf4j
  val slf4jApi = "org.slf4j" % "slf4j-api" % slf4jVersion
  val slf4j = "org.slf4j" % "slf4j-simple" % slf4jVersion
  val httpCors = "ch.megard" %% "akka-http-cors" % Version.httpCors
}

object Version {
  val circeVersion = "0.11.1"
  val slf4jVersion = "1.7.25"
  val slickVersion = "3.3.1"
  val postgreSqlVersion = "9.4-1206-jdbc42"
  val akkaVersion = "10.1.8"
  val akkaCirceVersion = "1.25.2"
  val flywayCoreVersion = "5.2.4"
  val pureConf = "0.10.1"
  val httpCors = "0.4.1"
}