package dao.model

import dao.model.BookTable.Book
import slick.jdbc.PostgresProfile.api._

class BookTable(tag: Tag) extends Table[Book](tag, "t_book") {

  def id = column[Long]("id", O.Default(1), O.PrimaryKey, O.AutoInc)

  def name = column[String]("name")

  override def * = (id, name) <> (Book.tupled, Book.unapply)
}

object BookTable{
  case class Book(id: Long, name: String)
}

