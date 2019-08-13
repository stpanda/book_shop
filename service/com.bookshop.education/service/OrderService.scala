package service

import dao.model.OrderTable.Order
import dao.repository.OrderRepository
import dao.settings.Settings._

import scala.concurrent.Future

object OrderService {

  def all: Future[Seq[Order]] = OrderRepository.all

  def deleteById(orderId: Long): Future[Int] = OrderRepository.drop(orderId)

  def deleteChain(personId: Long, bookId : Long): Future[Int] = OrderRepository.deleteChain(personId, bookId)

  def addOrder(o: Order): Future[Int] = OrderRepository.addOrder(o)

  def updateOrder(o: Order): Future[Int] = OrderRepository.updateOrder(o)

}
