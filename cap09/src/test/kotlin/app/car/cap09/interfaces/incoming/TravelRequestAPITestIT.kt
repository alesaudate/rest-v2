package app.car.cap09.interfaces.incoming

import app.car.cap09.infrastructure.loadFileContents
import app.car.car09.Cap09Application
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.test.context.ActiveProfiles


@SpringBootTest(classes = [Cap09Application::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = WireMockConfiguration.DYNAMIC_PORT)
@ActiveProfiles("test")
class TravelRequestAPITestIT(
        @LocalServerPort
        val port: Int
) {

    @Autowired
    lateinit var server: WireMockServer

    @BeforeEach
    fun setup() {
        RestAssured.baseURI = "https://localhost:$port"
        RestAssured.authentication = RestAssured.basic("admin", "password")
        RestAssured.useRelaxedHTTPSValidation()
    }

    @Test
    fun testFindNearbyTravelRequests() {
        setupServer()
        val passengerId = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(loadFileContents("/requests/passengers_api/create_new_passenger.json"))
                .post("/passengers")
                .then()
                .statusCode(200)
                .body("id", Matchers.notNullValue())
                .body("name", Matchers.`is`("Alexandre Saudate"))
                .extract()
                .body()
                .jsonPath().getString("id")

        val data = mapOf("passengerId" to passengerId)

        val travelRequestId = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(loadFileContents("/requests/travel_requests_api/create_new_request.json", data))
                .post("/travelRequests")
                .then()
                .statusCode(200)
                .body("id", Matchers.notNullValue())
                .body("origin", Matchers.`is`("Avenida Paulista, 1000"))
                .body("destination", Matchers.`is`("Avenida Ipiranga, 100"))
                .body("status", Matchers.`is`("CREATED"))
                .body("_links.passenger.title", Matchers.`is`("Alexandre Saudate"))
                .extract()
                .jsonPath()
                .get<Int>("id")

        RestAssured.given()["/travelRequests/nearby?currentAddress=Avenida Paulista, 900"]
                .then()
                .statusCode(200)
                .body("[0].id", Matchers.`is`(travelRequestId))
                .body("[0].origin", Matchers.`is`("Avenida Paulista, 1000"))
                .body("[0].destination", Matchers.`is`("Avenida Ipiranga, 100"))
                .body("[0].status", Matchers.`is`("CREATED"))
    }

    fun setupServer() {
        server.stubFor(WireMock.get(WireMock.urlPathEqualTo("/maps/api/directions/json"))
                .withQueryParam("origin", WireMock.equalTo("Avenida Paulista, 900"))
                .withQueryParam("destination", WireMock.equalTo("Avenida Paulista, 1000"))
                .withQueryParam("key", WireMock.equalTo("chaveGoogle"))
                .willReturn(WireMock.okJson(loadFileContents("/responses/gmaps/sample_response.json")))
        )
    }
}