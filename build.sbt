name := "oversentimental-analyzer"

version := "1.0"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.6.0-M1",
  "org.apache.opennlp" % "opennlp-tools" % "1.8.0",
  "org.scalactic" %% "scalactic" % "3.0.1",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)
