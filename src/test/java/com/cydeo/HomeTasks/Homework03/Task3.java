package com.cydeo.HomeTasks.Homework03;

import com.cydeo.utilities.ZippopomusTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class Task3 extends ZippopomusTestBase {

    @Test
    public void test1() {

//        Given Accept application/json
//        And path state is va
//        And path city is fairfax
//        When I send a GET request to /us endpoint
//        Then status code must be 200
//        And content type must be application/json
//        And payload should contains following information
//        country abbreviation is US
//        country United States
//        place name Fairfax
//        each places must contains fairfax as a value each post code must start with 22

        given()
                    .accept(ContentType.JSON)
                    .pathParam("state", "VA")
                .and()
                    .pathParam("city", "Fairfax")
                .when()
                    .get("/us/{state}/{city}")
                .then()
                .log().all()
                    .statusCode(200)
                    .contentType("application/json")
                    .body("'country abbreviation'", is("US"))
                    .body("country", is("United States"))
                    .body("'place name'", is("Fairfax"))
                    .body("places.'place name'", containsInRelativeOrder("Fairfax"))
                    .body("places.'post code'", everyItem(stringContainsInOrder("22")));


    }
}
