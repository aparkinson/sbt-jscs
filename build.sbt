import bintray.Keys._

sbtPlugin := true

name := "sbt-jscs"

organization := "com.hindsightsoftware.sbt"

git.baseVersion := "0.2"

scalaVersion := "2.10.4"

addSbtPlugin("com.typesafe.sbt" % "sbt-js-engine" % "1.0.2")

enablePlugins(GitVersioning, GitBranchPrompt)

publishMavenStyle := false

licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))

bintrayPublishSettings

repository in bintray := "sbt-plugins"

bintrayOrganization in bintray := Some("hindsightsoftware")

git.useGitDescribe := true

