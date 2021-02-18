package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import reactivemongo.api.ReadPreference
import reactivemongo.api.bson.document

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

class ReactiveMongoTest extends AnyFunSuite with Matchers {
  import Mongodb._
  import Todo._
  
  val todo = Todo("Beer", "Drink IPA!")

  test("write") {
    val future = todos.insert.one(todo)
    val result = Await.result(future, 3 seconds)
    result.writeErrors.isEmpty shouldBe true
  }

  test("read") {
    val query = document("category" -> "Beer")
    val future = todos.find(query).cursor[Todo](ReadPreference.nearest).head
    val result = Await.result(future, 3 seconds)
    result shouldBe todo
  }
}