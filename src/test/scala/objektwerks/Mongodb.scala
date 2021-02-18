package objektwerks

import reactivemongo.api.{AsyncDriver, MongoConnection}
import reactivemongo.api.bson.{BSONDocumentReader, BSONDocumentWriter, Macros}


import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.concurrent.ExecutionContext

final case class Todo(category: String, todo: String) extends Product with Serializable

object Mongodb {
  implicit val ec = ExecutionContext.Implicits.global

  val driver = AsyncDriver()

  val futureDB = for {
    uri <- MongoConnection.fromString("mongodb://127.0.0.1:27017/reactivemongo")
    connection <- driver.connect(uri)
    database <- connection.database("reactivemongo")
  } yield database

  val db = Await.result(futureDB, 3 seconds)
  val todos = db.collection("todos")

  implicit def todoWriter: BSONDocumentWriter[Todo] = Macros.writer[Todo]

  implicit def todoReader: BSONDocumentReader[Todo] = Macros.reader[Todo]

  sys.addShutdownHook {
    db.drop()
    driver.close(3 seconds)
    ()
  }
}