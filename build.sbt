import Settings._
import sbt._

lazy val `domain` = Project("domain", file("domain"))

lazy val `slick` = Project("slick", file("slick"))
  .settings(libraryDependencies += "org.scala-sbt" %% "zinc-classpath" % "1.2.5", libraryDependencies ++= dbDependencies)

lazy val `service` = Project("service", file("service"))
  .dependsOn(`slick`, `domain`) // зависимости

lazy val `facade` = Project("facade", file("facade"))
  .settings(libraryDependencies ++= akkaDependencies)
  .dependsOn(`service`)

lazy val `education` = Project("education", file("."))
  .settings(moduleName := "education")
  .settings(name := "education")
  .aggregate(`domain`, `slick`, `service`, `facade`)