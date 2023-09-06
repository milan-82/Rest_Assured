package com.cydeo.LiveClass.week4;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

public class P05_RequestAndResponseSpec extends SpartanTestBase {

    public static RequestSpecification getRequestSpec(){


       RequestSpecification requestSpecification = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON);

return requestSpecification;

    }

    @Test
    public void getTheSingleSpartan(){

               getRequestSpec().get("/api/spartans/2");

    }


    @Test
    public void getAllSpartan(){

        getRequestSpec().get("/api/spartans")
                .then()
                .statusCode(200);


    }
}
