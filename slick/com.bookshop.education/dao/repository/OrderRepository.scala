package dao.repository

import slick.jdbc.PostgresProfile.api._
import slick.jdbc.PostgresProfile.backend.Database
import dao.model.OrderTable
import dao.model.OrderTable.Order
import slick.lifted.TableQuery

import scala.concurrent.Future

object OrderRepository extends TableQuery(new OrderTable(_)) {

  def all(implicit db: Database): Future[Seq[Order]] = {
    db.run(this.result)
  }

  def drop(implicit db: Database): Future[Int] = {
    db.run(this.delete)
  }

  def findByPersonId(id: Long)
                  (implicit db: Database): Future[Seq[Long]] = {
    db.run(this.filter(_.personId === id).map(_.bookId).result)
  }

  def findByBookId(id : Long)
                (implicit db: Database): Future[Seq[Long]] = {
    db.run(this.filter(_.bookId === id).map(_.personId).result)
  }

  def deleteChain(personId : Long, bookId : Long)
                 (implicit db: Database): Future[Int] = {
    db.run(this.filter(data => data.personId === personId && data.bookId === bookId).delete)
  }

}
