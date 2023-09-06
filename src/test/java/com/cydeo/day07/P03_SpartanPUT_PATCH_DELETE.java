package com.cydeo.day07;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;


public class P03_SpartanPUT_PATCH_DELETE extends SpartanTestBase {
    @DisplayName("PUT Spartan with Map")
    @Test
    public void test1() {

        Map<String, Object> requestBodyMap = new LinkedHashMap<>();

        requestBodyMap.put("name", "John Doe");
        requestBodyMap.put("gender", "Male");
        requestBodyMap.put("phone", "8877445596");

        //PUT will update existing record so we choose one the existing ID, make sure it exist in your IP address

        int id = 126;

        given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(requestBodyMap)
                .when()
                .put("/api/spartans/{id}")
                .then()
                .statusCode(204);

    }

    @DisplayName("PATCH Spartan with Map")
    @Test
    public void test2() {

        Map<String, Object> requestBodyMap = new LinkedHashMap<>();

        requestBodyMap.put("name", "John Doe PATCH");

        int id = 126;

        given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(requestBodyMap)
                .when()
                .patch("/api/spartans/{id}")
                .then()
                .statusCode(204);

    }

    @DisplayName("DELETE Spartan with Map")
    @Test
    public void test3() {

//we can delete one id only one time, so it will give 204 only for the first execution
        int id = 11;

        given()
                    .pathParam("id",id)
                .when()
                    .delete("/api/spartans/{id}")
                .then()
                    .statusCode(204);

        //after deleted when we send get request to id that we deleted, it needs to give 404
        given()
                    .pathParam("id",id)
                .when()
                    .get("/api/spartans/{id}")
                .then()
                    .statusCode(404);


    }
}