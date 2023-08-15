package com.cydeo.HomeTasks.Homework02;

import com.cydeo.utilities.HR_TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured .*;
import static org.junit.jupiter.api.Assertions .*;

public class Task02 extends HR_TestBase {

    /*
    - Given accept type is Json
- Query param value - q={"department_id":80}
- When users sends request to /employees
- Then status code is 200
- And Content - Type is Json
- And all job_ids start with 'SA'
- And all department_ids are 80
- Count is 25
     */

    @Test
    public void test1(){

        Response response =given().accept(ContentType.JSON)
                .and()
                .queryParam("q", "{\"department_id\":80}")
                .when()
                .get("/employees");

      //  response.prettyPrint();

//        Then status code is 200
        assertEquals(200, response.statusCode());

//        And Content - Type is Json
        assertEquals("application/json", response.contentType());

//        And all job_ids start with 'SA'
        List<String> allJobIds = response.path("items.job_id");

        for (String eachId : allJobIds) {
            assertTrue(eachId.startsWith("SA"));
        }

//        And all department_ids are 80
        List<Integer> allDepartIds = response.path("items.department_id");

        for (Integer allIds : allDepartIds) {
            assertEquals(80, allIds);
        }

//        Count is 25
        int actualCount = response.path("count");

        assertEquals(25,actualCount );

    }

}
