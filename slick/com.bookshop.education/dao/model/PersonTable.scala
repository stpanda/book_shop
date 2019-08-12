package dao.model

import dao.model.PersonTable.Person
import slick.jdbc.PostgresProfile.api._

class PersonTable(tag: Tag) extends Table[Person](tag, "t_person"){

  def id = column[Long]("id", O.Default(1), O.PrimaryKey, O.AutoInc)

  def name = column[String]("name")

  def firstName = column[String]("firstName")

  def secondName= column[String]("secondName")

  def age = column[String]("age")

  override def * = (id, firstName, secondName, age) <> (Person.tupled, Person.unapply)
}

object PersonTable {
  case class Person(id: Long,  firstName: String, secondName: String, age: String)
}
