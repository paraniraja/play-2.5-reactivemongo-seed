package daos.core

import play.api.libs.json._
import reactivemongo.bson.BSONObjectID

import scala.concurrent.Future
import scala.util.Try

trait Repository[E <: TemporalModel] {

  def insert(document: E)(implicit writer: OWrites[E]): Future[Try[E]]

  def findOne(query: JsObject)(implicit reader: Reads[E]): Future[Option[E]]

  def findById(id: String)(implicit reader: Reads[E]): Future[Option[E]]

  def findById(id: BSONObjectID)(implicit reader: Reads[E]): Future[Option[E]]

  def update(id: String, document: E)(implicit writer: OWrites[E]): Future[Try[E]]

  def update(id: String, query: JsObject): Future[Try[JsObject]]

  def push(id: String, data: JsObject): Future[Try[JsObject]]

  def push[S](id: String, field: String, data: S)(implicit writer: OWrites[S]): Future[Try[S]]

  def pull(id: String, data: JsObject): Future[Try[JsObject]]

  def pull[S](id: String, field: String, query: S)(implicit writer: OWrites[S]): Future[Try[Boolean]]

  def unset(id: String, field: String): Future[Try[Boolean]]

  def remove(id: String): Future[Try[Boolean]]

  def remove(id: BSONObjectID): Future[Try[Boolean]]

  def remove(query: JsObject, firstMatchOnly: Boolean = false): Future[Try[Boolean]]
}
