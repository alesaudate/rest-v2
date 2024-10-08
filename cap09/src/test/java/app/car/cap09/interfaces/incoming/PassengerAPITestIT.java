package app.car.cap09.interfaces.incoming;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.basic;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PassengerAPITestIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "https://localhost:" + port;
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.authentication = basic("admin", "password");
    }

    @Test
    void testCreatePassenger() {


        String createPassengerJSON = "{\"name\":\"Alexandre Saudate\"}";


        given()
                .contentType(io.restassured.http.ContentType.JSON)
                .body(createPassengerJSON)
                .post("/passengers")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", equalTo("Alexandre Saudate"))
        ;
    }
}
