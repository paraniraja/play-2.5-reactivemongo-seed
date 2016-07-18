package util

import daos.core.MongoHelper
import play.api.Configuration
import play.api.libs.json.{JsObject, Json}
import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.api.{DefaultDB, MongoDriver}
import reactivemongo.api.commands.WriteResult
import reactivemongo.extensions.fixtures.Fixtures
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.Future


class JsonFixtures(db: Future[DefaultDB]) extends Fixtures[JsObject] with MongoHelper {

  override def map(document: JsObject): JsObject = {
    document
  }

  def remove(collectionName: String): Future[WriteResult] = {
    import play.modules.reactivemongo.json.ImplicitBSONHandlers.JsObjectDocumentWriter

    db.map(_.collection[JSONCollection](collectionName)).flatMap(_.remove(query = Json.obj(), firstMatchOnly = false))
  }

  override def drop(collectionName: String): Future[Unit] = {
    db.map(_.collection[JSONCollection](collectionName)).flatMap(_.drop())
  }

  override def bulkInsert(collectionName: String, documents: Stream[JsObject]): Future[Int] = {
    db.map(_.collection[JSONCollection](collectionName)).flatMap(_.bulkInsert(documents, ordered = true).map(_.n))
  }

}

object JsonFixtures extends TestHelper with MongoHelper {
  lazy val reactiveMongoApi = injector.instanceOf[ReactiveMongoApi]
  lazy val configuration = injector.instanceOf[Configuration]

  val connection = reactiveMongoApi.connection

  val db = connection.database(configuration.getString("mongodb.db").get)

  def apply: JsonFixtures = new JsonFixtures(db)
}
