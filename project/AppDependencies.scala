import sbt._
import play.core.PlayVersion._
import play.sbt.PlayImport._

object AppDependencies {
  
  private val pegdownVersion = "1.6.0"
  private val scalaTestPlayPlusVersion = "5.1.0"

  val compile = Seq(
    "uk.gov.hmrc"       %% "http-caching-client"       % "9.5.0-play-28",
    "uk.gov.hmrc"       %% "bootstrap-backend-play-28" % "5.18.0",
    "com.typesafe.play" %% "play-json-joda"            % "2.9.2",
    ws,
    compilerPlugin("com.github.ghik" % "silencer-plugin" % "1.7.1" cross CrossVersion.full),
    "com.github.ghik" % "silencer-lib" % "1.7.1" % Provided cross CrossVersion.full
  )

  trait TestDependencies {
    lazy val scope: String = "test"
    lazy val test : Seq[ModuleID] = ???
  }

  object Test {
    def apply() = new TestDependencies {
      override lazy val test = Seq(
        "org.pegdown"            %  "pegdown"                     % pegdownVersion           % scope,
        "com.typesafe.play"      %% "play-test"                   % current                  % scope,
        "org.mockito"            %  "mockito-core"                % "4.2.0"                  % scope,
        "org.mockito"            %% "mockito-scala-scalatest"     % "1.16.49"                % scope,
        "org.scalatestplus.play" %% "scalatestplus-play"          % scalaTestPlayPlusVersion % scope,
        "org.scalamock"          %% "scalamock-scalatest-support" % "3.6.0"                  % scope,
        "uk.gov.hmrc"            %% "bootstrap-test-play-28"      % "5.18.0"                 % scope
      )
    }.test
  }

  object IntegrationTest {
    def apply() = new TestDependencies {

      override lazy val scope: String = "it"

      override lazy val test = Seq(
        "org.pegdown"            %  "pegdown"                % pegdownVersion           % scope,
        "com.typesafe.play"      %% "play-test"              % current                  % scope,
        "org.scalatestplus.play" %% "scalatestplus-play"     % scalaTestPlayPlusVersion % scope,
        "com.github.tomakehurst" %  "wiremock-standalone"    % "2.27.2"                 % scope,
        "uk.gov.hmrc"            %% "bootstrap-test-play-28" % "5.18.0"                 % scope
      )
    }.test
  }

  def apply() = compile ++ Test() ++ IntegrationTest()
}
