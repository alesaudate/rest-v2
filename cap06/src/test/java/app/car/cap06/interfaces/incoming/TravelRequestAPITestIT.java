package app.car.cap06.interfaces.incoming;


import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import app.car.cap06.interfaces.outcoming.GMapsService;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.io.InputStream;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TravelRequestAPITestIT {


    @LocalServerPort
    private int port;

    @Autowired
    private GMapsService service;

    private static WireMockServer server;

    @BeforeAll
    public static void startWireMock() {
        server = new WireMockServer(wireMockConfig().dynamicPort());
        server.start();
    }

    @AfterAll
    public static void stopWiremock() {
        server.stop();
    }

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        service.setGMapsHost("http://localhost:" + server.port());

    }

    @Test
    public void testFindCloseTravelRequests() {

        setupServer();
        given()
                .contentType(ContentType.JSON)
                .body(loadInput("/requests/passengers_api/create_new_passenger.json"))
                .post("/passengers")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", is("Alexandre Saudate"))
        ;

        given()
                .contentType(ContentType.JSON)
                .body(loadInput("/requests/travel_requests_api/create_new_request.json"))
                .post("/travelRequests")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("origin", is("Avenida Paulista, 1000"))
                .body("destination", is("Avenida Ipiranga, 100"))
                .body("status", is("CREATED"))
                .body("_links.passenger.title", is("Alexandre Saudate"))
                ;

        given()
                .get("/travelRequests/close?currentAddress=Avenida Paulista, 900")
                .then()
                .statusCode(200)
                .body("[0].id", notNullValue())
                .body("[0].origin", is("Avenida Paulista, 1000"))
                .body("[0].destination", is("Avenida Ipiranga, 100"))
                .body("[0].status", is("CREATED"))
                ;

    }


    public void setupServer() {

        server.stubFor(get(urlPathEqualTo("/maps/api/directions/json"))
            .withQueryParam("origin", equalTo("Avenida Paulista, 900"))
            .withQueryParam("destination", equalTo("Avenida Paulista, 1000"))
            .withQueryParam("key", equalTo("chaveGoogle"))
            .willReturn(okJson(loadInput("/responses/gmaps/sample_response.json")))
        );
    }


    @SneakyThrows
    String loadInput(String fileName) {

        InputStream is = new ClassPathResource(fileName).getInputStream();
        byte[] data = new byte[is.available()];
        is.read(data);
        return new String(data);
    }


}
