package com.cydeo.day08;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

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

    @DisplayName("DELETE/api/spartans/{id} as editor --->403 FORBIDDEN")
    @Test
    public void test3(){

given()
        .accept(ContentType.JSON)
        .pathParam("id", "101")
        .auth().basic("editor", "editor")
        .when()
        .delete("/api/spartans/{id}").prettyPeek()
        .then()
        .statusCode(403)
        .body("error", is("Forbidden"));

    }

    @DisplayName("DELETE/api/spartans/{id} as admin --->204 FORBIDDEN")
    @Test
    public void test4() {

        given()
                .pathParam("id", "100")
                .auth().basic("admin", "admin")
                .when()
                .delete("/api/spartans/{id}").prettyPeek()
                .then()
                .statusCode(204);

    }

    /**
     *  HOMEWORKS
     *
     *  	Role Based Control Test --> RBAC
     *
     * 			ADMIN  -->  GET  POST PUT PATCH  DELETE   --> Spartan Flow
     * 			EDITOR -->  GET  POST PUT PATCH   403
     * 			USER   -->  GET  403  403  403    403
     * 			GUEST  -->  401  401  401  401    401
     *
     *
     *   -- Create RBAC Test for all different roles from Spartan Application including with Negative Test cases
     public static void  GETSpartans(String role,String password,int statusCode,int id){
     *
     *                 given().pathParam("id",id)
     *                 .auth().basic(role,password).
     *                 when().get("/api/spartans/{id}").then().statusCode(statusCode);
     *
     *               }
     *
     *               **/


}
