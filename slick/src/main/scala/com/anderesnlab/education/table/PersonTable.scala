package com.anderesnlab.education.table

import com.andersenlab.education.model.Person
import slick.jdbc.PostgresProfile.api._

class PersonTable(tag: Tag) extends Table[Person](tag, "t_person"){

  def id = column[Long]("id", O.Default(1), O.PrimaryKey, O.AutoInc)
  def firstName = column[String]("firstName")
  def secondName= column[String]("secondName")
  def age = column[String]("age")

  override def * = (id, firstName, secondName, age) <> (Person.tupled, Person.unapply)
}

object PersonTable{
  val query = new TableQuery(tag => new PersonTable(tag))
}