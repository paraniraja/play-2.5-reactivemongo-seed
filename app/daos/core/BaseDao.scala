package daos.core

import daos.exceptions._
import play.api.Logger
import reactivemongo.api.commands.WriteResult
import reactivemongo.core.errors.DatabaseException

import scala.concurrent.Future
import scala.util.Try

/**
  * Base DAO for Mongo resources
  */
trait BaseDao extends MongoHelper {

  def collectionName: String

  def ensureIndexes: Future[Boolean]

  def recover[S](operation: Future[WriteResult])(success: => S): Future[Try[S]] = {
    operation.map {
      case lastError => if (lastError.ok) Try(success) else throw new RuntimeException("exce")
    } recover {
      case exception =>
        Logger.error(s"DB operation failed: [message=${exception.getMessage}]")

        exception match {
          case e: DatabaseException if e.code.get == 10148 => throw new OperationNotAllowedException("", nestedException = e)
          case e: DatabaseException if e.code.get == 11000 => throw new DuplicateResourceException(nestedException = e)
          case _ => throw new UnexpectedServiceException(exception.getMessage)

        }
    }
  }

}
