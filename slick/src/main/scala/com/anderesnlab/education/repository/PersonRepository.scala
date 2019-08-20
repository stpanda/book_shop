package com.anderesnlab.education.repository

import com.anderesnlab.education.table.PersonTable
import com.andersenlab.education.model.Person
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class PersonRepository(dbConfig: DatabaseConfig[JdbcProfile])(implicit ec: ExecutionContext) {
  import dbConfig._
  import profile.api._

  def all(): Future[Seq[Person]] = {
    db.run(PersonTable.query.result)
  }

  def findById(id: Long): Future[Option[Person]] = {
    db.run(PersonTable.query.filter(_.id === id).result).map(_.headOption)
  }

  def create(person: Person): Future[Int] = {
    db.run(PersonTable.query += person)
  }

  def deleteById(id: Long): Future[Int] = {
    db.run(PersonTable.query.filter(_.id === id).delete)
  }

  def updateById(person: Person): Future[Int] = {
    db.run(PersonTable.query.filter(_.id === person.id).update(person))
  }
}