package com.cydeo.day06;

import com.cydeo.utilities.HR_TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class P01_HRDeserialization extends HR_TestBase {


    /**
     * Create a test called getLocation
     * 1. Send request to GET /locations
     * 2. log uri to see
     * 3. Get all Json as a map and print out screen all the things with the help of  map
     * System.out.println("====== GET FIRST LOCATION  ======");
     * System.out.println("====== GET FIRST LOCATION LINKS  ======");
     * System.out.println("====== GET ALL LOCATIONS AS LIST OF MAP======");
     * System.out.println("====== FIRST LOCATION ======");
     * System.out.println("====== FIRST LOCATION ID ======");
     * System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
     * System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
     */

    @DisplayName("GET /locations to deserialization into Java Collections")
    @Test
    public void test1(){

        Response response = given().log().uri()
                .accept(ContentType.JSON)
                .when()
                .get("/locations")
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        System.out.println("====== GET FIRST LOCATION  ======");

        Map<String,Object> firstMap = jsonPath.getMap("items[0]");
        System.out.println("firstMap = " + firstMap);

        System.out.println();
        System.out.println("====== GET FIRST LOCATION LINKS  ======");

       // System.out.println("firstMap.get(\"links\") = " + firstMap.get("links"));
        Map<String, Object> firstLinksMap = jsonPath.getMap("items[0].links[0]");
        System.out.println("firstLinksMap = " + firstLinksMap);

        System.out.println();
        System.out.println("====== GET ALL LOCATIONS AS LIST OF MAP======");

        List<Map<String, Object>> allLocations = jsonPath.getList("items");

        for (Map<String, Object> eachLocation : allLocations) {
            System.out.println("eachLocation = " + eachLocation);
        }

        System.out.println();
        System.out.println("====== FIRST LOCATION ======");

        System.out.println("allLocations.get(0) = " + allLocations.get(0));

        System.out.println();
        System.out.println("====== FIRST LOCATION ID ======");
        System.out.println("firstMap.get(\"location_id\") = " + firstMap.get("location_id"));

        System.out.println();
        System.out.println("====== FIRST LOCATION COUNTRY_ID ======");

        System.out.println("firstMap.get(\"country_id\") = " + firstMap.get("country_id"));

        System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
        System.out.println("firstMap.get(\"links\") = " + firstMap.get("links"));

        System.out.println("===================================================================");
        // we want to get href from first location ?
        List<Map<String,Object>> links= (List<Map<String, Object>>) firstMap.get("links");
        System.out.println("links = " + links);

        System.out.println("href from first location = " + links.get(0).get("href"));

    }



}
