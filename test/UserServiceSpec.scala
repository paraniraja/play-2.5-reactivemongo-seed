import _root_.util.TestHelper
import daos.exceptions.DuplicateResourceException
import org.scalatest.Matchers
import play.api.Logger
import user.{User, UserRepository}

class UserServiceSpec extends TestHelper with Matchers {

  val userRepository: UserRepository = injector.instanceOf[UserRepository]

  it should "Expect Duplicate Record Exception" in {
    val cont = User("test", "test@test.com")
    whenReady(userRepository.insert(cont).failed) { failure =>
      failure shouldBe a[DuplicateResourceException]
    }
  }

  it should "Expect Duplicate Record Excep" in {
    val cont = User("test", "test@test.com")
    whenReady(userRepository.insert(cont).failed) { failure =>
      failure shouldBe a[DuplicateResourceException]
    }
  }

  it should "Test a method that returns a futu" in {
    val cont = User("test2", "test2@test2.com")
    val persistedContact = userRepository.insert(cont).futureValue.get

    whenReady(userRepository.findById(persistedContact.identity)) {
      _ match {
        case None => Logger.info("No records")
        case e: Some[User] => Logger.info(e.get.identity)
      }

    }

  }

}
