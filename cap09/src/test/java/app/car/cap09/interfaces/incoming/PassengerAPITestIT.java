package app.car.cap09.interfaces.incoming;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PassengerAPITestIT {

    @LocalServerPort
    private int port;

    private String url;

    @BeforeEach
    public void setup() {
        url = "https://localhost:" + port;
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    public void testCreatePassenger() {


        String createPassengerJSON = "{\"name\":\"Alexandre Saudate\"}";


        given()
                .auth().preemptive().basic("admin", "password")
                .contentType(io.restassured.http.ContentType.JSON)
                .body(createPassengerJSON)
                .post(url + "/passengers")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", equalTo("Alexandre Saudate"))
        ;
    }
}
