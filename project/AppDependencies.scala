import sbt._

object AppDependencies {
  import play.core.PlayVersion
  import play.sbt.PlayImport._


  private val hmrcTestVersion = "3.9.0-play-26"
  private val scalaTestVersion = "3.0.7"
  private val pegdownVersion = "1.6.0"
  private val scalaTestPlayPlusVersion = "3.1.2"

  val compile = Seq(
    "uk.gov.hmrc" %% "http-caching-client" % "9.0.0-play-26",
    "uk.gov.hmrc" %% "bootstrap-play-26" % "1.6.0",
    "com.typesafe.play" %% "play-json-joda" % "2.7.4",
    ws
  )

  trait TestDependencies {
    lazy val scope: String = "test"
    lazy val test : Seq[ModuleID] = ???
  }

  object Test {
    def apply() = new TestDependencies {
      override lazy val test = Seq(
        "uk.gov.hmrc" %% "hmrctest" % hmrcTestVersion % scope,
        "org.scalatest" %% "scalatest" % scalaTestVersion % scope,
        "org.pegdown" % "pegdown" % pegdownVersion % scope,
        "com.typesafe.play" %% "play-test" % PlayVersion.current % scope,
        "uk.gov.hmrc" %% "http-caching-client" % "8.3.0" % scope,
        "org.mockito" % "mockito-core" % "2.26.0" % scope,
        "org.scalatestplus.play" %% "scalatestplus-play" % scalaTestPlayPlusVersion % scope,
        "org.scalamock" %% "scalamock-scalatest-support" % "3.6.0" % scope
      )
    }.test
  }

  object IntegrationTest {
    def apply() = new TestDependencies {

      override lazy val scope: String = "it"

      override lazy val test = Seq(
        "uk.gov.hmrc" %% "hmrctest" % hmrcTestVersion % scope,
        "org.scalatest" %% "scalatest" % scalaTestVersion % scope,
        "org.pegdown" % "pegdown" % pegdownVersion % scope,
        "com.typesafe.play" %% "play-test" % PlayVersion.current % scope,
        "org.scalatestplus.play" %% "scalatestplus-play" % scalaTestPlayPlusVersion % scope,
        "com.github.tomakehurst" % "wiremock-standalone" % "2.26.3" % scope
      )
    }.test
  }

  def apply() = compile ++ Test() ++ IntegrationTest()
}

