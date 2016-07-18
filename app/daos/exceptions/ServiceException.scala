package daos.exceptions

/**
  * Trait for service exceptions
  */
trait ServiceException extends Exception {

  val message: String
  val nestedException: Throwable

}
