package piwebsrv

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import io.gatling.core.scenario.Simulation

class HealthSimulation extends Simulation {

  val httpConf = http
    .baseURL("http://" + System.getenv("GATLING_TARGET"))

  val scn = scenario("healthCheck").exec(
      http("Health Check")
        .get("/health")
        .check(status.is(200))
    )

  setUp(scn.inject(constantUsersPerSec(60) during(1 minutes)).protocols(httpConf))
}

