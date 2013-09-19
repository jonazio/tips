import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "tips"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    "commons-io" % "commons-io" % "2.3",
    javaCore,
    javaJdbc,
    javaEbean
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
