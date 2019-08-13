package converter

import akka.http.scaladsl.model.ContentTypeRange
import akka.http.scaladsl.model.MediaTypes.`application/json`
import akka.http.scaladsl.unmarshalling.{FromEntityUnmarshaller, Unmarshaller}
import dao.model.BookTable.Book
import dao.model.OrderTable.Order
import dao.model.PersonTable.Person
import de.heikoseeberger.akkahttpcirce.BaseCirceSupport
import io.circe._
import io.circe.generic.semiauto._
import io.circe.parser.decode

import scala.concurrent.Future

trait Converter extends BaseCirceSupport{

  implicit def unmarshaller[A](implicit dec: Decoder[A]): FromEntityUnmarshaller[A] ={
    def jsonContentTypes: List[ContentTypeRange] =
      List(`application/json`)
    Unmarshaller.stringUnmarshaller
      .forContentTypes(jsonContentTypes: _*)
      .flatMap{ _ => _ => json =>
        decode[A](json).fold(Future.failed, Future.successful)}
  }

  implicit val personDecoder: Decoder[Person] = deriveDecoder
  implicit val personEncoder: Encoder[Person] = deriveEncoder
  implicit val bookDecoder: Decoder[Book] = deriveDecoder
  implicit val bookEncoder: Encoder[Book] = deriveEncoder
  implicit val orderDecoder: Decoder[Order] = deriveDecoder
  implicit val orderEncoder: Encoder[Order] = deriveEncoder
}
