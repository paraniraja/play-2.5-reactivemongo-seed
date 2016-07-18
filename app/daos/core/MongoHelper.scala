package daos.core

import reactivemongo.bson.{BSONObjectID, BSONValue}

/**
  * Helper around MongoDB resources
  */
trait MongoHelper extends ContextHelper {

}

object MongoHelper extends MongoHelper {

  def identity(bson: BSONValue) = bson.asInstanceOf[BSONObjectID].stringify

}
