name := "reactivemongo"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "2.13.18"
libraryDependencies ++= {
  val reactivemongoVersion = "1.0.10"
  val akkaVersion = "2.6.21" // Don't upgrade due to BUSL 1.1!
  Seq(
    "org.reactivemongo" %% "reactivemongo" % reactivemongoVersion % Test,
    "org.reactivemongo" %% "reactivemongo-core" % reactivemongoVersion % Test,
    "org.reactivemongo" %% "reactivemongo-bson-api" % reactivemongoVersion % Test,
    "org.reactivemongo" %% "reactivemongo-akkastream" % reactivemongoVersion % Test,
    "com.typesafe.akka" %% "akka-actor" % akkaVersion % Test,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion % Test,
    "com.typesafe.akka" %% "akka-protobuf" % akkaVersion % Test,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion % Test,
    "io.netty" % "netty-all" % "4.1.90.Final" % Test,
    "ch.qos.logback" % "logback-classic" % "1.5.22" % Test,
    "org.scalatest" %% "scalatest" % "3.2.19" % Test
  )
}
