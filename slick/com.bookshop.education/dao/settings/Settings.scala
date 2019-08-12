package dao.settings

import slick.jdbc.PostgresProfile.backend.Database

object Settings {
  implicit val db: Database = Database.forConfig("database")
}
