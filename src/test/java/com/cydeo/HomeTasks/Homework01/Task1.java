package com.cydeo.HomeTasks.Homework01;

import com.cydeo.utilities.HR_TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Task1 extends HR_TestBase {

   /*
   Task 1 :
- Given accept type is Json
- When users sends request to /countries/US
- Then status code is 200
- And Content - Type is application/json
- And response contains United States of America
    */

    @Test
    public void task1() {

        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/countries/US");

        response.prettyPrint();

        //  Then status code is 200
        assertEquals(200, response.statusCode());

        //  And Content - Type is application/json
        assertEquals("application/json", response.contentType());

        //  And response contains United States of America
        assertTrue(response.body().asString().contains("United States of America"));


    }

}
