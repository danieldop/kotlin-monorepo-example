package com.acme

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Test

@QuarkusTest
class PersonTest {

    @Test
    fun testPersonEndpoint() {
        given()
            .`when`().get("/api/v1/person")
            .then()
            .statusCode(200)
    }
}