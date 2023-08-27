package com.cydeo.LiveClass.week3;

import com.cydeo.utilities.FruitTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class P01_SerializationWithWithMap extends FruitTestBase {

    int createdId;
    @Test
    void createFruit(){

        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("name", "Mango");
        requestBody.put("price", "3.79");

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                //.body("{\n" + "  \"name\": \"Mango,\",\n" + "  \"price\": 3.79\n" + "}")//instead of that one we will use map or POJO
                .body(requestBody)
                .post("/products").prettyPeek()
                .then()
                .statusCode(201)
                .extract().response();

        String selfLink = response.path("self_link");

        String idOfString = selfLink.substring((selfLink.lastIndexOf("/") + 1));

        int id = Integer.parseInt(idOfString);
        int createdId = id;



    }


    //let's assume that API is not returning id how to get created fruit in next test???
//    {
//            "name": "Mango",
//            "price": 3.79,
//            "self_link": "/shop/v2/products/392"
//    }




}
