package com.cydeo.day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_simpleGetRequest {

    String url = "http://54.89.45.138:8000/api/spartans";

    /*
    When users sent request to API/ Spartans endpoint
    Then users should be able to see status code is 200
    and Print out response body into screen
     */

    @Test
    public void simpleGetRequest(){

        // send request to url and get response as Response interface
        Response response = RestAssured.get(url);

        System.out.println("response.statusCode() = " + response.statusCode());

        //verufy that status code is 200
        int actualstatusCode = response.statusCode();

        // assert that is 200
        Assertions.assertEquals(200,actualstatusCode);

        // how to print response body on console
        response.prettyPrint();

    }


}
