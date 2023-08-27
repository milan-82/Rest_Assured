package com.cydeo.LiveClass.week3;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class P03_AuthTestWithBasic extends SpartanAuthTestBase {

    @Test
    public void basic_auth() {

        RestAssured
                .given()
                .auth().basic("user", "user")
                .get("/api/spartans")
                .then()
                .statusCode(200);
    }

    @Test
    public void negative_basic_auth() {

        RestAssured
                .given()
                .auth().basic("user1", "user")
                .get("/api/spartans")
                .then()
                .statusCode(401);

    }

    @Test
    public void basicAuth_in_header() {

        RestAssured
                .given()
                //.auth().basic("user1", "user")
                .header("Authorization", "Basic dXNlcjp1c2Vy")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200);

    }

}