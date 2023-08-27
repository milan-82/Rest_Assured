package com.cydeo.day08;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P01_SpartanAuthTest extends SpartanAuthTestBase {

    @DisplayName("GET/api/spartans as GUEST user ---> EXPECT ---->401")
    @Test
    public void test1(){

        given()
                    .accept(ContentType.JSON)
                .when()
                    .get("/api/spartans").prettyPeek()
                .then()
                    .statusCode(401)
                    .body("error", is("Unauthorized"));

    }

    @DisplayName("GET /api/spartans as USER --> EXPECT -->401")
    @Test
    public void test2(){

        given()
                .accept(ContentType.JSON)
                .auth().basic("user", "user")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .log().all();






    }
}
