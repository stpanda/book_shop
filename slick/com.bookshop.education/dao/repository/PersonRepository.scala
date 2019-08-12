package dao.repository

import dao.model.PersonTable
import dao.model.PersonTable.Person
import slick.lifted.TableQuery
import slick.jdbc.PostgresProfile.api._
import slick.jdbc.PostgresProfile.backend.Database

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object PersonRepository extends TableQuery(new PersonTable(_)) {
  def all(implicit db: Database): Future[Seq[Person]] = {
    db.run(this.result)
  }

  def findById(id: Long)
              (implicit db: Database): Future[Option[Person]] = {
    db.run(this.filter(_.id === id).result).map(_.headOption)
  }

  def create(person: Person)
            (implicit db: Database): Future[Int] = {
    db.run(this += person)
  }

  def deleteById(id: Long)
                (implicit db: Database): Future[Int] = {
    db.run(this.filter(_.id === id).delete)
  }

  def updateById(person: Person)
                (implicit db: Database): Future[Int] = {
    db.run(this.filter(_.id === person.id).update(person))
  }
}
