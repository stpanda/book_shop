package service

import com.anderesnlab.education.repository.{BookRepository, OrderRepository}
import com.andersenlab.education.model.Order

import scala.concurrent.Future

class OrderService(repository: OrderRepository) {

  def all: Future[Seq[Order]] = repository.all

  def deleteById(orderId: Long): Future[Int] = repository.drop(orderId)

  def deleteChain(personId: Long, bookId : Long): Future[Int] = repository.deleteChain(personId, bookId)

  def addOrder(o: Order): Future[Int] = repository.addOrder(o)

  def updateOrder(o: Order): Future[Int] = repository.updateOrder(o)

}
