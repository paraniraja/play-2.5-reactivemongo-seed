package util

import org.scalatest.FlatSpec
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}
import play.api.inject.guice.GuiceApplicationBuilder


trait TestHelper extends FlatSpec with ScalaFutures {

  implicit val defaultPatience =
    PatienceConfig(timeout = Span(5, Seconds), interval = Span(500, Millis))

  val injector = new GuiceApplicationBuilder().injector

  override def withFixture(test: NoArgTest) = {
    // Perform setup
    JsonFixtures.apply.removeAll("resources/resetData.conf").futureValue
    JsonFixtures.apply.load("resources/users.conf").futureValue
    try super.withFixture(test) // Invoke the test function
    finally {
      // Perform cleanup
    }
  }
}
