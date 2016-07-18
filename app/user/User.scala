package user

import daos.core.TemporalModel
import org.joda.time.DateTime
import play.api.libs.json._
import reactivemongo.bson.BSONObjectID

case class User(
                 name: String,
                 email: String,
                 var _id: Option[BSONObjectID] = None,
                 var created: Option[DateTime] = None,
                 var updated: Option[DateTime] = None
               ) extends TemporalModel

object User {

  import reactivemongo.play.json.BSONFormats.BSONObjectIDFormat // This is required

  implicit val UserFormat = Json.format[User]

}
