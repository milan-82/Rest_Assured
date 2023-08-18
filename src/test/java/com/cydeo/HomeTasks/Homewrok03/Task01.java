package com.cydeo.HomeTasks.Homewrok03;

import com.cydeo.utilities.ZippopomusTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Task01 extends ZippopomusTestBase {

    //    Given Accept application/json
//    And path zipcode is 22031
//    When I send a GET request to /us endpoint
//    Then status code must be 200
//    And content type must be application/json
//    And Server header is cloudflare
//    And Report-To header exists
//    And body should contains following information
//    post code is 22031
//    country is United States
//    country abbreviation is US
//    place name is Fairfax state is Virginia
    @Test
    public void test1() {

            given()
                    .accept(ContentType.JSON)
                    .pathParam("zipcode", 22031)
                .when()
                    .get("us/{zipcode}")
                .then()
                .log().all() // Log request and response details
                    .statusCode(200)
                    .contentType("application/json")
                    .header("Server", "cloudflare")
                    .header("Report-To", notNullValue())
                    .body("'post code'", is("22031"))
                    .body("country", is("United States"))
                    .body("'country abbreviation'", is("US"))
                    .body("places[0].'place name'", is("Fairfax"))
                    .body("places[0].state", is("Virginia"));

    }

}
