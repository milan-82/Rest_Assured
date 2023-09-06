package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class P03_ResponseTimeTest extends SpartanAuthTestBase {

    @Test
    public void test1(){

        Response response = given()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                // .time(lessThan(800L))
                // .time(greaterThan(100L))
                .time(both(greaterThan(500L)).and(lessThan(800L)))
                .extract().response();

        System.out.println("response.getTime() = " + response.getTime());


    }

}
