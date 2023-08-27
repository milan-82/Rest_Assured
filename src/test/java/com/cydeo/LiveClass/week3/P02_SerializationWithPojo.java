package com.cydeo.LiveClass.week3;

import com.cydeo.POJO.Friut;
import com.cydeo.utilities.FruitTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class P02_SerializationWithPojo extends FruitTestBase {

    @Test
    void createFruit() {

        Friut requestBody = new Friut("Mano", 3.79);

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                //.body("{\n" + "  \"name\": \"Mango,\",\n" + "  \"price\": 3.79\n" + "}")//instead of that one we will use map or POJO
                .body(requestBody)
                .post("/products").prettyPeek()
                .then()
                .statusCode(201)
                .extract().response();

    }
}
