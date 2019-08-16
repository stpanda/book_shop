package com.anderesnlab.education.repository

import com.anderesnlab.education.table.OrderTable
import com.andersenlab.education.model.Order
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.jdbc.PostgresProfile.backend.Database

import scala.concurrent.{ExecutionContext, Future}

class OrderRepository(dbConfig: DatabaseConfig[JdbcProfile])(implicit ec: ExecutionContext) {

  def all(implicit db: Database): Future[Seq[Order]] = {
    db.run(OrderTable.query.result)
  }

  def drop(id: Long)(implicit db: Database): Future[Int] = {
    db.run(OrderTable.query.filter(_.orderId === id).delete)
  }

  def findByPersonId(id: Long)(implicit db: Database): Future[Seq[Long]] = {
    db.run(OrderTable.query.filter(_.personId === id).map(_.bookId).result)
  }

  def findByBookId(id : Long)(implicit db: Database): Future[Seq[Long]] = {
    db.run(OrderTable.query.filter(_.bookId === id).map(_.personId).result)
  }

  def deleteChain(personId : Long, bookId : Long)(implicit db: Database): Future[Int] = {
    db.run(OrderTable.query.filter(data => data.personId === personId && data.bookId === bookId).delete)
  }

  def addOrder(order: Order)(implicit db: Database): Future[Int] = {
    db.run(OrderTable.query += order)
  }

  def updateOrder(order: Order)(implicit db: Database): Future[Int] = {
    db.run(OrderTable.query.filter(_.orderId === order.orderId).update(order))
  }
}