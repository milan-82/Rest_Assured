package com.cydeo.HomeTasks.Homework05;

import com.cydeo.utilities.HR_TestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Task1 extends HR_TestBase {

    /*
    TASK 1:

    —> POST a region then do GET same region to do validations.
    Please use Map or POJO class, or JsonPath

    Given accept is json
    And content type is json
    When I send post request to "/regions/" With json:
    {
        "region_id":100,
            "region_name":"Test Region"
    }
    Then status code is 201
    And content type is json
    And region_id is 100
    And region_name is Test Region

—> GET

    Given accept is json
    When I send GET request to "/regions/100"
    Then status code is 200
    And content type is json
    And region_id is 100
    And region_name is Test Region
*/

    @Test
    public void test1(){

       // {
       //     "region_id":100,
      //          "region_name":"Test Region"
       // }

        Map<String, Object> requestRegionMap = new LinkedHashMap<>();

        requestRegionMap.put("region_id", 100);
        requestRegionMap.put("region_name", "Test Region");

        System.out.println("requestRegionMap = " + requestRegionMap);

        given()
                    .accept(ContentType.JSON)
                .and()
                    .contentType(ContentType.JSON)
                    .body(requestRegionMap)
                .when()
                .post("/regions").prettyPeek();



    }

}
