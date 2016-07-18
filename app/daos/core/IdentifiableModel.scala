package daos.core

import reactivemongo.bson.BSONObjectID

trait IdentifiableModel {

  var _id: Option[BSONObjectID]

  def identity = _id.map(_.stringify).getOrElse("")

}
