package com.cydeo.LiveClass.week2;

import com.cydeo.utilities.HR_TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class P03_HamcrestHR extends HR_TestBase {

    /**
     * Given accept type is Json
     * And parameters: q={"region_id":2}
     * When users sends a GET request to "/countries"
     * Then status code is 200
     * And Content type is application/json
     * And Date header is not null
     * Verify
     * - count is 5
     * - hasMore is false
     * - first country id is AR
     * - country names have Canada
     * - country names have Canada,Mexico
     * - total country size is 5
     * - each country has country_id
     * - each country region_id is 2
     * - Print country names
     */
    @Test
    public void getCountries() {

        JsonPath jsonPath = given().log().uri()
                .accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}")
                .when()
                .get("/countries").prettyPeek()
                .then()
                .statusCode(200)
                .contentType("application/json")
                .header("Date", is(notNullValue()))
                .body("count", is(5))
                .body("hasMore", is(false))
                .body("items[0].country_id", is("AR"))
                .body("items.country_name", hasItem("Canada"))
                .body("items.country_name", hasItems("Canada", "Mexico"))
                .body("items", hasSize(5))
                .body("items.country_id", everyItem(notNullValue()))
                .body("items.region_id", everyItem(is(2)))
                .extract().jsonPath();


        //Print country names
        List<String> allCountries = jsonPath.getList("items.country_name");
        System.out.println("allCountries = " + allCountries);

        Assertions.assertEquals(5, allCountries.size());

    }
}
