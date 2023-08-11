package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.AssertionSupport;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class P02_NegativeSpartanTests {

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "http://54.89.45.138:8000";

    }

        /*
         * Given accept  content type is application/json
         * When user sends GET request /api/spartans endpoint
         * Then status code should be 200
         */

    @DisplayName("Get All Spatrans")
        @Test
        public void getAllSpartans(){

          Response response = given().accept(ContentType.JSON)
                               .when()
                               .get("/api/spartans");

           assertEquals(200, response.statusCode());

    }

     /*
        Given Accept type application/xml
        When user send GET request to /api/spartans/10 end point
        Then status code must be 406
        And response Content Type must be application/xml;charset=UTF-8;
        */

    @Test
    public void negativeTest(){

        Response response = given().accept(ContentType.XML)
                            .when()
                            .get("/api/spartans/10");

        // verify status code is 406?
        assertEquals(406, response.statusCode());

        //response Content Type must be application/xml;charset=UTF-8
        assertEquals("application/xml;charset=UTF-8", response.contentType());


    }

}
