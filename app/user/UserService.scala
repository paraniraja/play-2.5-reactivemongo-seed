package user

import javax.inject.{Inject, Singleton}

import scala.concurrent.Future
import scala.util.Try

@Singleton
class UserService @Inject()(repository: UserRepository) {

  def create(user: User): Future[Try[User]] = {
    repository.insert(user)
  }

}
