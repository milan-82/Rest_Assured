package com.cydeo.day07;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P04_SpartanFlow extends SpartanTestBase {

    String spartanID;

    @Order(1)
    @Test
    public void POST() {

//        - POST     /api/spartans
//        Create a spartan Map,Spartan class
//        name = "API Flow POST"
//        gender="Male"
//        phone=1231231231l
//
//                - verify status code 201
//                - message is "A Spartan is Born!"
//                - take spartanID from response and save as a global variable

        Map<String, Object> spartanMap = new HashMap<>();

        spartanMap.put("name", "API Flow POST");
        spartanMap.put("gender", "Male");
        spartanMap.put("phone", 1231231231l);

        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(spartanMap)
                .when()
                .post("/api/spartans").prettyPeek()
                .then()
                .statusCode(201)
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath();

        spartanID = jsonPath.getString("data.id");

    }

    @Order(2)
    @Test
    public void GET() {

        //        - GET  Spartan with spartanID     /api/spartans/{id}
        //         - verify status code 200
        //         - verify name is API Flow POST

        given()
                .accept(ContentType.JSON)
                .pathParam("id", spartanID)
                .when()
                .get("/api/spartans/{id}").prettyPeek()
                .then()
                .statusCode(200)
                .body("name", is("API Flow POST"));
    }

    @Order(3)
    @Test
    public void PUT() {

//    - PUT  Spartan with spartanID    /api/spartans/{id}

//    Create a spartan Map
//    name = "API PUT Flow"
//    gender="Female"
//    phone=3213213213l
//  - verify status code 204

        Map<String, Object> spartanMap = new HashMap<>();

        spartanMap.put("name", "API Flow POST");
        spartanMap.put("gender", "Male");
        spartanMap.put("phone", 1231231231l);

        given()
                .contentType(ContentType.JSON)
                .pathParam("id", spartanID)
                .body(spartanMap)
                .when()
                .put("/api/spartans/{id}")
                .then()
                .statusCode(204);

    }

    @Order(4)
    @Test
    public void deleteSpartan() {

//        - DELETE  Spartan with spartanID   /api/spartans/{id}
//
//          - verify status code 204


        given()
                .pathParam("id", 120)
                .when()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(204);


        //after deleted when we send get request to id that we deleted, it needs to give 404
        given()
                .pathParam("id", spartanID)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(404);
    }


}



