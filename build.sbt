import bintray.Keys._
import com.typesafe.sbt.SbtGit._

sbtPlugin := true

name := "sbt-jscs"

organization := "com.hindsightsoftware.sbt"

git.baseVersion := "1.1"

scalaVersion := "2.10.4"

resolvers += "Typesafe Releases Repository" at "http://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("com.typesafe.sbt" % "sbt-js-engine" % "1.0.2")

versionWithGit

publishMavenStyle := false

licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))

bintrayPublishSettings

repository in bintray := "sbt-plugins"

bintrayOrganization in bintray := Some("hindsightsoftware")


