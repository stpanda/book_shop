package com.anderesnlab.education.repository

import com.anderesnlab.education.table.PersonTable
import com.andersenlab.education.model.Person
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import slick.jdbc.PostgresProfile.backend.Database

import scala.concurrent.{ExecutionContext, Future}

class PersonRepository(dbConfig: DatabaseConfig[JdbcProfile])(implicit ec: ExecutionContext) {
  def all(implicit db: Database): Future[Seq[Person]] = {
    db.run(PersonTable.query.result)
  }

  def findById(id: Long)(implicit db: Database): Future[Option[Person]] = {
    db.run(PersonTable.query.filter(_.id === id).result).map(_.headOption)
  }

  def create(person: Person)(implicit db: Database): Future[Int] = {
    db.run(PersonTable.query += person)
  }

  def deleteById(id: Long)(implicit db: Database): Future[Int] = {
    db.run(PersonTable.query.filter(_.id === id).delete)
  }

  def updateById(person: Person)(implicit db: Database): Future[Int] = {
    db.run(PersonTable.query.filter(_.id === person.id).update(person))
  }
}