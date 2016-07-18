package daos.exceptions

import reactivemongo.api.commands.LastError

/**
  * Service related exceptions
  */
case class UnexpectedServiceException(
  message: String,
  nestedException: Throwable = null
) extends ServiceException

case class DBServiceException(
  message: String,
  lastError: Option[LastError] = None,
  nestedException: Throwable = null
) extends ServiceException

object DBServiceException {
  def apply(lastError: LastError): ServiceException = {
    DBServiceException(lastError.errmsg.getOrElse(lastError.message), Some(lastError))
  }
}

case class DuplicateResourceException(
  message: String = "error.duplicate.resource",
  nestedException: Throwable = null
) extends ServiceException

case class OperationNotAllowedException(
  message: String = "error.operation.not.allowed",
  nestedException: Throwable = null
) extends ServiceException

case class ResourceNotFoundException(
  id: String,
  message: String = "error.resource.not.found",
  nestedException: Throwable = null
) extends ServiceException
