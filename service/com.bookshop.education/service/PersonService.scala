package service

import dao.model.PersonTable.Person
import dao.repository.PersonRepository
import dao.settings.Settings._

import scala.concurrent.Future

object PersonService {

  def all: Future[Seq[Person]] = PersonRepository.all

  def deleteById(id: Long): Future[Int] = PersonRepository.deleteById(id)

  def add(b: Person): Future[Int] = PersonRepository.create(b)

  def update(b: Person): Future[Int] = PersonRepository.updateById(b)

  def findOne(id: Long): Future[Option[Person]] = PersonRepository.findById(id)

}
