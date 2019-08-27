package com.anderesnlab.education.repository

import com.anderesnlab.education.table.BookTable
import com.andersenlab.education.model.Book
import org.slf4j.{Logger, LoggerFactory}
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class BookRepository(dbConfig: DatabaseConfig[JdbcProfile])(implicit ec: ExecutionContext){

  def log : Logger = LoggerFactory.getLogger(this.getClass)

  import dbConfig._
  import profile.api._

  def all(): Future[Seq[Book]] = {
    db.run(BookTable.query.result)
  }

  def findById(id: Long): Future[Option[Book]] = {
    log.info(s"Getting book: $id")
    db.run(BookTable.query.filter(_.id === id).result).map(_.headOption)
  }

  def create(book: Book): Future[Int] = {
    log.info(s"Creating new book: $book")
    db.run(BookTable.query += book)
  }

  def deleteById(id: Long): Future[Int] = {
    db.run(BookTable.query.filter(_.id === id).delete)
  }

  def updateById(book: Book): Future[Int] = {
    db.run(BookTable.query.filter(_.id === book.id).update(book))
  }
}