package daos.core

import play.api.libs.json.{JsObject, Json, OWrites, Writes}
import reactivemongo.bson.BSONObjectID
import reactivemongo.play.json._

object DBQueryBuilder {

  def id(objectId: String): JsObject = id(BSONObjectID(objectId))

  def id(objectId: BSONObjectID): JsObject = Json.obj("_id" -> objectId)

  def set(field: String, data: JsObject): JsObject = set(Json.obj(field -> data))

  def set[T](field: String, data: T)(implicit writer: Writes[T]): JsObject = set(Json.obj(field -> data))

  def set(data: JsObject): JsObject = Json.obj("$set" -> data)

  def set[T](data: T)(implicit writer: Writes[T]): JsObject = Json.obj("$set" -> data)

  def push(data: JsObject): JsObject = Json.obj("$push" -> data)

  def push[T](field: String, data: T)(implicit writer: OWrites[T]): JsObject = Json.obj("$push" -> Json.obj(field -> data))

  def pull(data: JsObject): JsObject = Json.obj("$pull" -> data)

  def pull[T](field: String, query: T)(implicit writer: Writes[T]): JsObject = Json.obj("$pull" -> Json.obj(field -> query))

  def unset(field: String): JsObject = Json.obj("$unset" -> Json.obj(field -> 1))

  def inc(field: String, amount: Int): JsObject = Json.obj("$inc" -> Json.obj(field -> amount))

  def or(criterias: JsObject*): JsObject = Json.obj("$or" -> criterias)

  def gt[T](field: String, value: T)(implicit writer: Writes[T]): JsObject = Json.obj(field -> Json.obj("$gt" -> value))

  def lt[T](field: String, value: T)(implicit writer: Writes[T]): JsObject = Json.obj(field -> Json.obj("$lt" -> value))

  def in[T](field: String, value: T)(implicit writer: Writes[T]): JsObject = Json.obj(field -> Json.obj("$in" -> value))

  def query[T](query: T)(implicit writer: Writes[T]): JsObject = Json.obj("$query" -> query)

  def orderBy[T](query: T)(implicit writer: Writes[T]): JsObject = Json.obj("$orderby" -> query)

}
