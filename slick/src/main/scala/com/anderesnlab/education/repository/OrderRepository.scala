package com.anderesnlab.education.repository

import com.anderesnlab.education.table.OrderTable
import com.andersenlab.education.model.Order
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class OrderRepository(dbConfig: DatabaseConfig[JdbcProfile])(implicit ec: ExecutionContext) {
  import dbConfig._
  import profile.api._

  def all(): Future[Seq[Order]] = {
    db.run(OrderTable.query.result)
  }

  def drop(id: Long): Future[Int] = {
    db.run(OrderTable.query.filter(_.orderId === id).delete)
  }

  def findByPersonId(id: Long): Future[Seq[Long]] = {
    db.run(OrderTable.query.filter(_.personId === id).map(_.bookId).result)
  }

  def findByBookId(id : Long): Future[Seq[Long]] = {
    db.run(OrderTable.query.filter(_.bookId === id).map(_.personId).result)
  }

  def deleteChain(personId : Long, bookId : Long): Future[Int] = {
    db.run(OrderTable.query.filter(data => data.personId === personId && data.bookId === bookId).delete)
  }

  def addOrder(order: Order): Future[Int] = {
    db.run(OrderTable.query += order)
  }

  def updateOrder(order: Order): Future[Int] = {
    db.run(OrderTable.query.filter(_.orderId === order.orderId).update(order))
  }
}