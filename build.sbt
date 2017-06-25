resolvers in Global ++= Seq(
  "Sbt plugins"                   at "https://dl.bintray.com/sbt/sbt-plugin-releases",
  "Maven Central Server"          at "http://repo1.maven.org/maven2",
  "TypeSafe Repository Releases"  at "http://repo.typesafe.com/typesafe/releases/",
  "TypeSafe Repository Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"
)

val scala_logging = "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0"
val logback_classic = "ch.qos.logback" % "logback-classic" % "1.1.3"

lazy val commonSettings = Seq(
  version := "1.0",
  organization := "com.animatedlew",
  scalaVersion := "2.12.2",
  test in assembly := {}
)

lazy val app = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "logger",
    assemblyJarName in assembly := "log.jar",
    libraryDependencies ++= Seq(scala_logging, logback_classic)
  ).
  enablePlugins(AssemblyPlugin)

//scalaVersion :=  "2.12.2"
