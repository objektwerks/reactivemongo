package objektwerks

import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import reactivemongo.api.{ MongoConnection, AsyncDriver, ReadPreference }
import reactivemongo.api.bson.{BSONDocumentWriter, BSONDocumentReader, Macros, document}


import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.language.postfixOps

final case class Todo(category: String, todo: String) extends Product with Serializable

class ReactiveMongoTest extends AnyFunSuite with BeforeAndAfterAll with Matchers {
  val driver = AsyncDriver()
  val parsedUri = MongoConnection.fromString("mongodb://127.0.0.1:27017/reactivemongo")
  val connection = parsedUri.flatMap(driver.connect(_))
  val database = connection.flatMap(_.database("reactivemongo"))
  val collection = database.map(_.collection("todos"))

  implicit def todoWriter: BSONDocumentWriter[Todo] = Macros.writer[Todo]

  implicit def todoReader: BSONDocumentReader[Todo] = Macros.reader[Todo]

  override protected def afterAll(): Unit = {
    database.flatMap(_.drop())
    connection.foreach( c => c.close()(3 seconds) )
    driver.close(3 seconds)
    ()
  }

  test("write") {
    val todo = Todo("Beer", "Drink IPA!")
    collection.flatMap(_.insert.one(todo).map(_ => {})) 
  }

  test("read") {
    val query = document("category" -> "Beer")
    val result: Future[Todo] = collection.flatMap(_.find(query).cursor[Todo](ReadPreference.nearest).head)
    val todo = Await.result(result, 3 seconds)
    todo shouldBe Todo("Beer", "Drink IPA!")
    println(s"ReactiveMongo Todo: $todo")
  }
}