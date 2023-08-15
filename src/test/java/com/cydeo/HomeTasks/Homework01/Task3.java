package com.cydeo.HomeTasks.Homework01;

import com.cydeo.utilities.HR_TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Task3 extends HR_TestBase {

    /*
    - Given accept type is Json
- When users sends request to /regions/1
- Then status code is 200
- And Content - Type is application/json
- And response contains Europe
- And header should contains Date
- And Transfer-Encoding should be chunked
     */

    @Test
    public void test3() {

        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/regions/1");

        response.prettyPrint();


//        Then status code is 200
        assertEquals(200, response.statusCode());

//        And Content - Type is application/json
        assertEquals("application/json", response.contentType());

//        And response contains Europe
        assertTrue(response.body().asString().contains("Europe"));

//        And header should contains Date
        assertTrue(response.getHeaders().hasHeaderWithName("Date"));

//         And Transfer-Encoding should be chunked
        assertEquals("chunked", response.header("Transfer-Encoding"));




    }
}
