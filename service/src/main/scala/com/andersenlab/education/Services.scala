package com.andersenlab.education

import com.anderesnlab.education.repository.{BookRepository, OrderRepository, PersonRepository}
import service.{BookService, OrderService, PersonService}
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

class Services {

  import scala.concurrent.ExecutionContext.Implicits.global

  val configPath = "education.db"
  val dbConfig: DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig(configPath)

  val bookRepo = new BookRepository(dbConfig)
  val personRepo = new PersonRepository(dbConfig)
  val orderRepo = new OrderRepository(dbConfig)

  val bookService =  new BookService(bookRepo)
  val personService =  new PersonService(personRepo)
  val orderService =  new OrderService(orderRepo)
}