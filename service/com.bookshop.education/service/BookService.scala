package service

import dao.model.BookTable.Book
import dao.repository.BookRepository
import dao.settings.Settings._


import scala.concurrent.Future

object BookService {

  def all: Future[Seq[Book]] = BookRepository.all

  def deleteById(id: Long): Future[Int] = BookRepository.deleteById(id)

  def add(b: Book): Future[Int] = BookRepository.create(b)

  def update(b: Book): Future[Int] = BookRepository.updateById(b)

  def findOne(id: Long): Future[Option[Book]] = BookRepository.findById(id)
}
