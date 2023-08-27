package com.cydeo.HomeTasks.Homework03;

import com.cydeo.utilities.ZippopomusTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class Task2 extends ZippopomusTestBase {

//    Given Accept application/json
//    And path zipcode is 50000
//    When I send a GET request to /us endpoint
//    Then status code must be 404
//    And content type must be application/json
    @Test
    public void test1(){

         given()
                .accept(ContentType.JSON)
                .pathParam("zipcode", "50000")
                .when()
                .get("/us/{zipcode}")
                 .then()
                 .statusCode(404)
                 .contentType("application/json");

    }

}
