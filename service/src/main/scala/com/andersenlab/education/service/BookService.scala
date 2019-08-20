package com.andersenlab.education.service

import com.anderesnlab.education.repository.BookRepository
import com.andersenlab.education.model.Book

import scala.concurrent.Future

class BookService(repository: BookRepository) {

  def all: Future[Seq[Book]] = repository.all()

  def deleteById(id: Long): Future[Int] = repository.deleteById(id)

  def add(b: Book): Future[Int] = repository.create(b)

  def update(b: Book): Future[Int] = repository.updateById(b)

  def findOne(id: Long): Future[Option[Book]] = repository.findById(id)
}
