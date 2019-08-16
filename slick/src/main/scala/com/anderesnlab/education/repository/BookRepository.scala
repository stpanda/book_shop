package com.anderesnlab.education.repository

import com.anderesnlab.education.table.BookTable
import com.andersenlab.education.model.Book
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.jdbc.PostgresProfile.backend.Database

import scala.concurrent.{ExecutionContext, Future}

class BookRepository(dbConfig: DatabaseConfig[JdbcProfile])(implicit ec: ExecutionContext) {

  def all(implicit db: Database): Future[Seq[Book]] = {
    db.run(BookTable.query.result)
  }

  def findById(id: Long)(implicit db: Database): Future[Option[Book]] = {
    db.run(BookTable.query.filter(_.id === id).result).map(_.headOption)
  }

  def create(book: Book)(implicit db: Database): Future[Int] = {
    db.run(BookTable.query += book)
  }

  def deleteById(id: Long)(implicit db: Database): Future[Int] = {
    db.run(BookTable.query.filter(_.id === id).delete)
  }

  def updateById(book: Book)(implicit db: Database): Future[Int] = {
    db.run(BookTable.query.filter(_.id === book.id).update(book))
  }
}