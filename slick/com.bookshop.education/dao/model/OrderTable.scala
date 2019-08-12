package dao.model

import dao.model.OrderTable.Order
import slick.jdbc.PostgresProfile.api._

class OrderTable(tag: Tag) extends Table[Order](tag, "t_order") {

  def personId = column[Long]("person")

  def bookId = column[Long]("book")

  override def * = (personId, bookId) <> (Order.tupled, Order.unapply)
}

object OrderTable{
  case class Order(personId: Long, bookId: Long)
}
