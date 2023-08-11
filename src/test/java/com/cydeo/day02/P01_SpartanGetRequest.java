package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_SpartanGetRequest {

    String url = "http://54.89.45.138:8000";

    /*
     * Given content type is application/json
     * When user sends GET request /api/spartans endpoint
     * Then status code should be 200
     * And Content type should be application/json
     */

    @Test
    public void getAllSpartans() {


        Response response = RestAssured.given().accept(ContentType.JSON) //heu api please send me jason response
                .when()
                .get(url + "/api/spartans");

        // print response body
        response.prettyPrint();

        // how to get status code
        int actualStatusCode = response.statusCode();

        Assertions.assertEquals(200, actualStatusCode);

        // how to ger response content type header?
        String actualContentType = response.contentType();
        System.out.println("actualContentType = " + actualContentType);

        Assertions.assertEquals("application/json", actualContentType);

        // how to get Connection header value
        System.out.println("Content-type = " + response.header("Content-type"));
        System.out.println(response.header("Connection"));

        System.out.println(response.header("Date"));
        System.out.println(response.header("Keep-Alive"));

        // how to verify header exist?
        boolean date = response.headers().hasHeaderWithName("Date");
        Assertions.assertTrue(date);

    }


    /*
     * Given content type is application/json
     * When user sends GET request /api/spartans/3 endpoint
     * Then status code should be 200
     * And Content type should be application/json
     * And response body needs to contains Fidole
     */

    @Test
    public void getSpartan() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(url+"/api/spartans/3");

        response.prettyPrint();

        // verify status code
        int actualStatusCode = response.statusCode();

        Assertions.assertEquals(200, actualStatusCode);

        // verify content type si json
        String actualContentType = response.contentType();

        Assertions.assertEquals("application/json",actualContentType);

        //verify response body contains Fidole
        System.out.println(response.body());


       Assertions.assertTrue(response.body().asString().contains("Fidole"));
        /*
            This is not a good way to make assertion. In this way we are just converting response to String and
            with the help of String contains we are just looking into Response.But we should be able to get json
            "name" key value then verify that one is equal to "Fidole"
         */
    }

/*
        Given no headers provided
        When Users send GET request to /api/hello
        Then response status code should be 200
        And Content type header should be "text/plain;charset=UTF-8"
        And header should contain Date
        And Content-Length should be 17
        And body should be "Hello from Sparta"
         */

    @Test
    public void hello(){

        Response response = RestAssured.given().get(url+"/api/hello");

        response.prettyPrint();

        // verify status code
        Assertions.assertEquals(200, response.statusCode());

        //verify header Content-type
        Assertions.assertEquals("text/plain;charset=UTF-8",response.contentType());

        // verify does header contain Date
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        // verify Content-Length should be 17
        System.out.println("Header Content-Length is: "+response.body().asString().length());
        Assertions.assertEquals(17, response.body().asString().length());

        // verify body should be "Hello from Sparta"
      //  String actualBodyText = response.body().prettyPrint();

      // OP1.  Assertions.assertEquals("Hello from Sparta", actualBodyText);

        Assertions.assertTrue(response.body().asString().equals("Hello from Sparta"));

    }



}
