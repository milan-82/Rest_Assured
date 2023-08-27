package com.cydeo.LiveClass.week3;

import com.cydeo.utilities.BookItTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class P04_AuthTestWIthBearer extends BookItTestBase {

    @Test
    public void getCampus(){

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer "+token)
                .accept(ContentType.JSON)
                .get("/api/campuses");

        System.out.println("response.statusCode() = " + response.statusCode());


    }
}
