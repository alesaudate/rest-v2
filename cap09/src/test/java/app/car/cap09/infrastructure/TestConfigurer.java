package app.car.cap09.infrastructure;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.basic;

public class TestConfigurer {

    public static void setup(int port) {
        RestAssured.baseURI = "https://localhost:" + port;
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.authentication = basic("admin", "password");
    }
}
