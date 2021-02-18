package objektwerks

import reactivemongo.api.{AsyncDriver, MongoConnection}

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext}
import scala.language.postfixOps

trait Mongodb {
  implicit val ec = ExecutionContext.Implicits.global

  val driver = AsyncDriver()

  val futureDB = for {
    uri <- MongoConnection.fromString("mongodb://localhost:27017/mongodb")
    connection <- driver.connect(uri)
    database <- connection.database("mongodb")
  } yield database

  val db = Await.result(futureDB, 6 seconds)

  sys.addShutdownHook {
    db.drop()
    driver.close(3 seconds)
    ()
  }
}