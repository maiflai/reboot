import sbt._

object Common {
  import Keys._

  val defaultScalaVersion = "2.11.8"

  val testSettings:Seq[Setting[_]] = Seq(
    testOptions in Test += Tests.Cleanup { loader =>
      val c = loader.loadClass("unfiltered.spec.Cleanup$")
      c.getMethod("cleanup").invoke(c.getField("MODULE$").get(c))
    }
  )

  val settings: Seq[Setting[_]] = ls.Plugin.lsSettings ++ Seq(
    version := "0.12.0",

    crossScalaVersions := Seq("2.10.6", "2.11.8"),

    scalaVersion := defaultScalaVersion,

    organization := "com.github.maiflai",

    homepage :=
      Some(new java.net.URL("https://github.com/maiflai/reboot")),

    publishMavenStyle := true,

    publishTo <<= version { (v: String) =>
      val nexus = "https://oss.sonatype.org/"
      if (v.trim.endsWith("SNAPSHOT")) 
        Some("snapshots" at nexus + "content/repositories/snapshots") 
      else
        Some("releases"  at nexus + "service/local/staging/deploy/maven2")
    },

    publishArtifact in Test := false,

    licenses := Seq("LGPL v3" -> url("http://www.gnu.org/licenses/lgpl.txt")),

    pomExtra := (
      <scm>
        <url>git@github.com:maiflai/reboot.git</url>
        <connection>scm:git:git@github.com:maiflai/reboot.git</connection>
      </scm>
      <developers>
        <developer>
          <id>n8han</id>
          <name>Nathan Hamblen</name>
          <url>http://twitter.com/n8han</url>
        </developer>
        <developer>
          <id>maiflai</id>
          <name>Stu</name>
        </developer>
      </developers>)
  )
}
