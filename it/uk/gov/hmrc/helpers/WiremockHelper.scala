package uk.gov.hmrc.helpers

import akka.actor.ActorSystem
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock._
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import com.github.tomakehurst.wiremock.stubbing.StubMapping
import org.scalatestplus.play.BaseOneServerPerSuite

object WiremockHelper {
  val wiremockPort = 11111
  val wiremockHost = "localhost"
  val wiremockURL = s"http://$wiremockHost:$wiremockPort"
}

trait WiremockHelper {
  self: BaseOneServerPerSuite =>

  import WiremockHelper._
  lazy val wmConfig = wireMockConfig().port(wiremockPort)
  val wireMockServer = new WireMockServer(wmConfig)

  implicit val system = ActorSystem("my-system")

  def startWiremock(): Unit = {
    WireMock.configureFor(wiremockHost, wiremockPort)
    wireMockServer.start()
  }

  def stopWiremock(): Unit = wireMockServer.stop()

  def resetWiremock(): Unit = WireMock.reset()

  def stubGet(url: String, status: Integer, body: String = ""): StubMapping =
    stubFor(get(urlMatching(url))
      .willReturn(
        aResponse().
          withStatus(status).
          withBody(body)
      )
    )

  def stubPost(url: String, status: Integer, responseBody: String = ""): StubMapping =
    stubFor(post(urlMatching(url))
      .willReturn(
        aResponse().
          withStatus(status).
          withBody(responseBody)
      )
    )

  def stubDelete(url: String, status: Integer): StubMapping =
    stubFor(delete(urlMatching(url))
      .willReturn(
        aResponse()
          .withStatus(status)
      ))

  def stubPut(url: String, status: Integer, responseBody: String = ""): StubMapping =
    stubFor(put(urlMatching(url))
      .willReturn(
        aResponse().
          withStatus(status).
          withBody(responseBody)
      )
    )
}
