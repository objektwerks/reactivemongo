name := "reactivemongo"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "2.13.4"
libraryDependencies ++= {
  val reactivemongoVersion = "1.0.3"
  Seq(
    "org.reactivemongo" %% "reactivemongo" % reactivemongoVersion % Test,
    "org.reactivemongo" %% "reactivemongo-core" % reactivemongoVersion % Test,
    "org.reactivemongo" %% "reactivemongo-bson-api" % reactivemongoVersion % Test,
    "com.typesafe.akka" %% "akka-slf4j" % "2.6.12" % Test,
    "ch.qos.logback" % "logback-classic" % "1.2.3" % Test,
    "org.scalatest" %% "scalatest" % "3.2.3" % Test
  )
}