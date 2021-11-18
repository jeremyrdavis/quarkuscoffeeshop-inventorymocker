package io.quarkuscoffeeshop.inventorymocker.infrastructure;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class StartMockerTest {

    @Test
    public void testStartEndpoint() {
        given()
          .when().get("/api/start")
          .then()
             .statusCode(200);
    }

}