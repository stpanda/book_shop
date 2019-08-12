package dao.repository

import dao.model.BookTable
import dao.model.BookTable.Book
import slick.lifted.TableQuery
import slick.jdbc.PostgresProfile.api._
import slick.jdbc.PostgresProfile.backend.Database

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object BookRepository extends TableQuery(new BookTable(_)) {

  def all(implicit db: Database): Future[Seq[Book]] = {
    db.run(this.result)
  }

  def findById(id: Long)
              (implicit db: Database): Future[Option[Book]] = {
    db.run(this.filter(_.id === id).result).map(_.headOption)
  }

  def create(book: Book)
            (implicit db: Database): Future[Int] = {
    db.run(this += book)
  }

  def deleteById(id: Long)
                (implicit db: Database): Future[Int] = {
    db.run(this.filter(_.id === id).delete)
  }

  def updateById(book: Book)
                (implicit db: Database): Future[Int] = {
    db.run(this.filter(_.id === book.id).update(book))
  }
}
