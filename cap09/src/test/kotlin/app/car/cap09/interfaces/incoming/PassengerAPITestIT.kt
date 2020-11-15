package app.car.cap09.interfaces.incoming

import app.car.car09.Cap09Application
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort

@SpringBootTest(classes = [Cap09Application::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PassengerAPITestIT(
        @LocalServerPort
        val port: Int
) {

    @BeforeEach
    fun setup() {
        RestAssured.baseURI = "https://localhost:$port"
        RestAssured.useRelaxedHTTPSValidation()
        RestAssured.authentication = RestAssured.basic("admin", "password")
    }

    @Test
    fun testCreatePassenger() {
        val createPassengerJSON = """
            {"name":"Alexandre Saudate"}
        """.trimIndent()

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(createPassengerJSON)
                .post("/passengers")
                .then()
                .statusCode(200)
                .body("id", Matchers.notNullValue())
                .body("name", Matchers.equalTo("Alexandre Saudate"))
    }



}