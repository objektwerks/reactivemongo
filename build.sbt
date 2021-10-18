name := "reactivemongo"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "2.13.6"
libraryDependencies ++= {
  val reactivemongoVersion = "1.0.7"
  val akkaVersion = "2.6.16"
  Seq(
    "org.reactivemongo" %% "reactivemongo" % reactivemongoVersion % Test,
    "org.reactivemongo" %% "reactivemongo-core" % reactivemongoVersion % Test,
    "org.reactivemongo" %% "reactivemongo-bson-api" % reactivemongoVersion % Test,
    "org.reactivemongo" %% "reactivemongo-akkastream" % reactivemongoVersion % Test,
    "com.typesafe.akka" %% "akka-actor" % akkaVersion % Test,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion % Test,
    "com.typesafe.akka" %% "akka-protobuf" % akkaVersion % Test,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion % Test,
    "io.netty" % "netty-all" % "4.1.59.Final" % Test,
    "ch.qos.logback" % "logback-classic" % "1.2.6" % Test,
    "org.scalatest" %% "scalatest" % "3.2.10" % Test
  )
}
