package converter

import akka.http.scaladsl.model.ContentTypeRange
import akka.http.scaladsl.model.MediaTypes.`application/json`
import akka.http.scaladsl.unmarshalling.{FromEntityUnmarshaller, Unmarshaller}
import com.andersenlab.education.model.{Book, Order, Person}
import de.heikoseeberger.akkahttpcirce.BaseCirceSupport
import io.circe._
import io.circe.generic.semiauto._
import io.circe.parser.decode

import scala.concurrent.Future

object JsonFormat extends BaseCirceSupport{

  implicit def unmarshaller[A](implicit dec: Decoder[A]): FromEntityUnmarshaller[A] ={
    def jsonContentTypes: List[ContentTypeRange] =
      List(`application/json`)
    Unmarshaller.stringUnmarshaller
      .forContentTypes(jsonContentTypes: _*)
      .flatMap{ _ => _ => json =>
        decode[A](json).fold(Future.failed, Future.successful)}
  }

  implicit val personDecoder: Decoder[Person] = deriveDecoder[Person]
  implicit val personEncoder: Encoder[Person] = deriveEncoder[Person]
  implicit val bookDecoder: Decoder[Book] = deriveDecoder[Book]
  implicit val bookEncoder: Encoder[Book] = deriveEncoder[Book]
  implicit val orderDecoder: Decoder[Order] = deriveDecoder[Order]
  implicit val orderEncoder: Encoder[Order] = deriveEncoder[Order]
}
