addSbtPlugin("me.lessis" % "ls-sbt" % "0.1.3")

addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.3.1")

resolvers ++= Seq(
  "less is" at "http://repo.lessis.me",
  "coda" at "http://repo.codahale.com"
)
