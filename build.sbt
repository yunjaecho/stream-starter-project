name := "stream-starter-project"

version := "0.1"

scalaVersion := "2.12.7"

scalacOptions in Compile ++= Seq(
  "-encoding", "UTF-8",
  "-target:jvm-1.8",
  "-deprecation",
  "-feature",
  "-unchecked",
  "-Xlog-reflective-calls",
  "-Xlint")

javacOptions in Compile ++= Seq(
  "-source", "1.8",
  "-target", "1.8",
  "-Xlint:unchecked",
  "-Xlint:deprecation")


// https://mvnrepository.com/artifact/com.lightbend/kafka-streams-scala
libraryDependencies += "com.lightbend" %% "kafka-streams-scala" % "0.2.1"

// https://mvnrepository.com/artifact/org.apache.kafka/kafka-streams
//libraryDependencies += "org.apache.kafka" % "kafka-streams" % "0.10.0.0"


// https://mvnrepository.com/artifact/org.slf4j/slf4j-api
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.25"

// https://mvnrepository.com/artifact/org.slf4j/slf4j-log4j12
libraryDependencies += "org.slf4j" % "slf4j-log4j12" % "1.7.25"


assemblyMergeStrategy in assembly := {
  case PathList(xs@_*) if xs.last == "pom.xml" || xs.last == "pom.properties" =>
    MergeStrategy.rename
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

mainClass in assembly := Some("com.yunjae.kafka.streams.StreamStarterApp")