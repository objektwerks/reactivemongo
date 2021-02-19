package objektwerks

import reactivemongo.api.bson.{BSONDocumentReader, BSONDocumentWriter, Macros}

final case class Todo(category: String, todo: String) extends Product with Serializable

object TodoReactiveMongoSupport {
  implicit def todoWriter: BSONDocumentWriter[Todo] = Macros.writer[Todo]
  implicit def todoReader: BSONDocumentReader[Todo] = Macros.reader[Todo]
}