name := "play-2-5-reactivemongo-seed"

version := "0.1"

lazy val `play-2-5-reactivemongo-seed` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))

startYear := Some(2016)

libraryDependencies ++= Seq(
  "org.reactivemongo" %% "play2-reactivemongo" % "0.11.13",
  "org.reactivemongo" %% "reactivemongo-play-json" % "0.11.14",
  "org.mockito" % "mockito-core" % "1.10.19" % Test,
  "org.scalatestplus.play" % "scalatestplus-play_2.11" % "1.5.1" % Test,
  cache)

// disable documentation generation
sources in(Compile, doc) := Seq.empty

// avoid to publish the documentation artifact
publishArtifact in(Compile, packageDoc) := false

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

net.virtualvoid.sbt.graph.Plugin.graphSettings

javaOptions in test += "-ea"

findbugsSettings

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-encoding", "UTF-8")

routesGenerator := InjectedRoutesGenerator
