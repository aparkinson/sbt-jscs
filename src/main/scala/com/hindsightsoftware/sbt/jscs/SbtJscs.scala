package com.hindsightsoftware.sbt.jscs

import com.typesafe.sbt.jse.SbtJsTask
import sbt.Keys._
import sbt._

object JscsImport {

  object JscsKeys {
    val jscs = TaskKey[Seq[File]]("jscs", "Check JavaScript code style with jscs")
  }

}

object SbtJscs extends AutoPlugin {

  override def requires = SbtJsTask

  override def trigger = AllRequirements

  val autoImport = JscsImport

  import com.hindsightsoftware.sbt.jscs.JscsImport.JscsKeys._
  import com.typesafe.sbt.jse.SbtJsTask.autoImport.JsTaskKeys._
  import com.typesafe.sbt.web.Import.WebKeys._
  import com.typesafe.sbt.jse.JsEngineImport.JsEngineKeys._
  import com.typesafe.sbt.web.SbtWeb.autoImport._

  val settings = Seq(
    includeFilter := "*.js" | "*.jsx",
    sourceDirectory in jscs := (sourceDirectory in Assets).value,
    unmanagedSourceDirectories := Seq(sourceDirectory.value)
  )

  override def projectSettings = inTask(jscs)(
    SbtJsTask.jsTaskSpecificUnscopedSettings ++
      inConfig(Assets)(settings) ++
      inConfig(TestAssets)(settings) ++
      Seq(
        moduleName := "jscs",
        shellFile := getClass.getClassLoader.getResource("jscs-shell.js"),
        taskMessage in Assets := "Checking JavaScript code style",
        taskMessage in TestAssets := "Checking JavaScript test code style"
      )
  ) ++ SbtJsTask.addJsSourceFileTasks(jscs) ++ Seq(
    jscs in Assets := (jscs in Assets).dependsOn(nodeModules in Assets).value,
    jscs in TestAssets := (jscs in TestAssets).dependsOn(nodeModules in TestAssets).value,

    // 'jscs' is provided via npm
    nodeModuleGenerators in Plugin <+= npmNodeModules in Assets,
    nodeModuleDirectories in Plugin += baseDirectory.value / "node_modules"
  )
}

