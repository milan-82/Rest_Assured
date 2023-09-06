package com.cydeo.LiveClass.week4;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class P02_SchemaValidation {

    @Test
    void task1() {


        RestAssured
                .given()
                .accept(ContentType.JSON)
                .get("/api/spartans/2")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/SingleSpartanSchema.json"))
                .body("id", Matchers.is(2));
        //123456789
        //123-456-78-90
        //phone number should be like this 12-12-34-45-22
    }
}
