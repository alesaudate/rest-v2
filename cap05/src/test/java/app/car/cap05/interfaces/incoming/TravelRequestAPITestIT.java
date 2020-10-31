package app.car.cap05.interfaces.incoming;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static app.car.cap05.infrastructure.FileUtils.loadFileContents;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = WireMockConfiguration.DYNAMIC_PORT, stubs = "classpath:/stubs")
@ActiveProfiles("test")
public class TravelRequestAPITestIT {

    @LocalServerPort
    private int port;

    @Autowired
    private WireMockServer server;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
    }

    @Test
    public void testFindNearbyTravelRequests() {

        String passengerId =given()
                .contentType(ContentType.JSON)
                .body(loadFileContents("/requests/passengers_api/create_new_passenger.json"))
                .post("/passengers")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", is("Alexandre Saudate"))
                .extract()
                .body()
                .jsonPath().getString("id")
        ;

        Map<String, String> data = new HashMap<>();
        data.put("passengerId", passengerId);

        Integer travelRequestId = given()
                .contentType(ContentType.JSON)
                .body(loadFileContents("/requests/travel_requests_api/create_new_request.json", data))
                .post("/travelRequests")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("origin", is("Avenida Paulista, 1000"))
                .body("destination", is("Avenida Ipiranga, 100"))
                .body("status", is("CREATED"))
                .body("_links.passenger.title", is("Alexandre Saudate"))
                .extract()
                .jsonPath()
                .get("id")
                ;

        given()
                .get("/travelRequests/nearby?currentAddress=Avenida Paulista, 900")
                .then()
                .statusCode(200)
                .body("[0].id", is(travelRequestId))
                .body("[0].origin", is("Avenida Paulista, 1000"))
                .body("[0].destination", is("Avenida Ipiranga, 100"))
                .body("[0].status", is("CREATED"))
                ;
    }
}
