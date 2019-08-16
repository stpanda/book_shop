package com.anderesnlab.education.table

import com.andersenlab.education.model.Book
import slick.jdbc.PostgresProfile.api._

class BookTable(tag: Tag) extends Table[Book](tag, "t_book") {

  def id = column[Long]("id", O.Default(1), O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")

  override def * = (id, name) <> (Book.tupled, Book.unapply)
}

object BookTable{
  val query = new TableQuery(tag => new BookTable(tag))
}