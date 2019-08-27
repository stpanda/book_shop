package com.andersenlab.education

import org.slf4j.{Logger, LoggerFactory}

object Main extends App {
  def log : Logger = LoggerFactory.getLogger( Main.getClass )
  log.info("Akka education server started")
  WebServer.startServer("0.0.0.0", 8080)
}
