package com.cydeo.HomeTasks.Homework02;

import com.cydeo.utilities.HR_TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured .*;
import static org.junit.jupiter.api.Assertions .*;
public class Task03 extends HR_TestBase {

    /*
 Given accept type is Json
 Query param value q={“region_id":3}
 When users sends request to /countries
 Then status code is 200
 And all regions_id is 3
 And count is 6
 And hasMore is false
 And Country_name are;
Australia,China,India,Japan,Malaysia,Singapore
     */


    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("q", "{\"region_id\":3}")
                .when()
                .get("/countries");

        response.prettyPrint();

//        Then status code is 200
        assertEquals(200, response.statusCode());

//        And all regions_id is 3
        List<Integer> allRegionId = response.path("items.region_id");

        for (Integer eachID : allRegionId) {
            assertEquals(3, eachID);
            System.out.println("eachID = " + eachID);
        }

//        And count is 6
        int actualCount = response.path("count");
        assertEquals(6, actualCount);

//        And hasMore is false
        boolean hasMore = response.path("hasMore");
        assertEquals(false, hasMore);


//        And Country_name are;
        List<String> countyNames = response.path("items.country_name");

        for (String eachName : countyNames) {
            System.out.print(eachName+", ");
        }

    }
}
