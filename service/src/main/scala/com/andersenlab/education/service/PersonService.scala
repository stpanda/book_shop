package service

import com.anderesnlab.education.repository.PersonRepository
import com.andersenlab.education.model.Person

import scala.concurrent.Future

class PersonService(repository: PersonRepository) {

  def all: Future[Seq[Person]] = repository.all

  def deleteById(id: Long): Future[Int] = repository.deleteById(id)

  def add(b: Person): Future[Int] = repository.create(b)

  def update(b: Person): Future[Int] = repository.updateById(b)

  def findOne(id: Long): Future[Option[Person]] = repository
    .findById(id)

}
