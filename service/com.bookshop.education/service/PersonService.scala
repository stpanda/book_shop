package service

import dao.model.PersonTable.Person
import dao.repository.PersonRepository

import scala.concurrent.Future

object PersonService {

  def all: Seq[Person] = PersonRepository.all.transform()

  def deleteById(id: Long): Int = PersonRepository.deleteById(id).transform()

  def add(b: Person): Int = PersonRepository.create(b).transform()

  def update(b: Person): Int = PersonRepository.updateById(b).transform()

  def findOne(id: Long): Option[Person] = PersonRepository.findById(id).transform()

}
