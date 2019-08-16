import Settings._
import sbt._

lazy val `domain` = Project("domain", file("domain"))
  .settings(libraryDependencies ++= commonDependencies)

lazy val `slick` = Project("slick", file("slick"))
  .settings(libraryDependencies += "org.scala-sbt" %% "zinc-classpath" % "1.2.5", libraryDependencies ++= dbDependencies)
  .dependsOn(`domain`)

lazy val `service` = Project("service", file("service"))
  .dependsOn(`slick`) // зависимости

lazy val `facade` = Project("facade", file("facade"))
  .settings(libraryDependencies ++= akkaDependencies)
  .dependsOn(`service`)

lazy val `boot` = Project("boot", file("boot"))
  .dependsOn(`facade`)

lazy val `education` = Project("education", file("."))
  .settings(moduleName := "education")
  .settings(name := "education")
  .aggregate(`domain`, `slick`, `service`, `facade`, `boot`)