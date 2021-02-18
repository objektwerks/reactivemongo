package objektwerks

import reactivemongo.api.bson.collection.BSONCollection
import reactivemongo.api.bson.{BSONDocumentReader, BSONDocumentWriter, Macros}
import reactivemongo.api.{AsyncDriver, MongoConnection}

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext}
import scala.language.postfixOps

final case class Todo(category: String, todo: String) extends Product with Serializable

object Todo {
  implicit def todoWriter: BSONDocumentWriter[Todo] = Macros.writer[Todo]
  implicit def todoReader: BSONDocumentReader[Todo] = Macros.reader[Todo]
}

object Mongodb {
  implicit val ec = ExecutionContext.Implicits.global

  val driver = AsyncDriver()

  val futureDB = for {
    uri <- MongoConnection.fromString("mongodb://127.0.0.1:27017/mongodb")
    connection <- driver.connect(uri)
    database <- connection.database("mongodb")
  } yield database

  val db = Await.result(futureDB, 9 seconds)
  val todos: BSONCollection = db.collection("todos")

  sys.addShutdownHook {
    db.drop()
    driver.close(3 seconds)
    ()
  }
}