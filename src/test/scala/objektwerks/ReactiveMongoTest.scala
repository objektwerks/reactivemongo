package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import reactivemongo.api.ReadPreference
import reactivemongo.api.bson.document

import scala.concurrent.duration._
import scala.language.postfixOps
import scala.concurrent.Await

class ReactiveMongoTest extends AnyFunSuite with Matchers {
  import Mongodb._

  val todo = Todo("Beer", "Drink IPA!")

  test("write") {
    todos.insert.one(todo).map(_ => {})
  }

  test("read") {
    val query = document("category" -> "Beer")
    val result = todos.find(query).cursor[Todo](ReadPreference.nearest).head
    Await.result(result, 3 seconds) shouldBe todo
  }
}