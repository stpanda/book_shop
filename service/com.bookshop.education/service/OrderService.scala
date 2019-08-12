package service

import dao.model.OrderTable.Order
import dao.repository.OrderRepository

import scala.concurrent.Future

object OrderService {

  def all: Future[Seq[Order]] = OrderRepository.all

  def deleteById: Future[Int] = OrderRepository.drop

  def deleteChain(personId : Long, bookId : Long): Future[Int] = OrderRepository.deleteChain(personId, bookId)

}
