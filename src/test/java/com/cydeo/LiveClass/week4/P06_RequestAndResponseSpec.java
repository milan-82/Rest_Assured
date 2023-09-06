package com.cydeo.LiveClass.week4;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class P06_RequestAndResponseSpec extends SpartanTestBase {

    RequestSpecification requestSpecification;

    @BeforeEach
    void setUp(){


       requestSpecification = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON);

    }

    @Test
    void getTheSingleSpartan(){

       Response response = requestSpecification.get("/api/spartans");

       response.prettyPeek();
    }


}
