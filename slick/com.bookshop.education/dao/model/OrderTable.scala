package dao.model

import dao.model.OrderTable.Order
import slick.jdbc.PostgresProfile.api._

class OrderTable(tag: Tag) extends Table[Order](tag, "t_order") {

  def orderId = column[Long]("orderId", O.Default(1), O.PrimaryKey, O.AutoInc)

  def personId = column[Long]("person")

  def bookId = column[Long]("book")

  override def * = (personId, bookId, orderId) <> (Order.tupled, Order.unapply)
}

object OrderTable{
  case class Order(orderId: Long, personId: Long, bookId: Long)
}
