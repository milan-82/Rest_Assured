package com.cydeo.HomeTasks.Homework01;

import com.cydeo.utilities.HR_TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Task2 extends HR_TestBase {


    /*
    Task 2 :
- Given accept type is Json
- When users sends request to /employees/1
- Then status code is 404
     */

    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/employees/1");

        response.prettyPrint();

       // Then status code is 404
        assertEquals(404, response.statusCode());



    }
}
