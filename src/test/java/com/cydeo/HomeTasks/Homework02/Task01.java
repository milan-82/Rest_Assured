package com.cydeo.HomeTasks.Homework02;

import com.cydeo.utilities.HR_TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured .*;
import static org.junit.jupiter.api.Assertions .*;

public class Task01 extends HR_TestBase {

    /*
    - Given accept type is Json
- Path param value- US
- When users sends request to /countries
- Then status code is 200
- And Content - Type is Json
- And country_id is US
- And Country_name is United States of America
And Region_id is 2
     */

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/countries/US");

        response.prettyPrint();

//        Then status code is 200
        assertEquals(200, response.statusCode());

//        And Content - Type is Json
        assertEquals("application/json", response.contentType());
//        And country_id is US
        assertEquals("US", response.path("country_id"));

//        And Country_name is United States of America
        assertEquals("United States of America", response.path("country_name"));

//        And Region_id is 2
       // assertEquals(2, response.path("region_id"));
       assertEquals(2, (Integer) response.path("region_id"));


    }
}
