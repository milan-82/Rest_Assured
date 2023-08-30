package com.cydeo.LiveClass.week2;

import com.cydeo.utilities.FruitTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class P04_Deserialization extends FruitTestBase {

    /**
     * Send request to FruitAPI url and save the response
     * Accept application/json
     * GET /customers
     * Store the response in Response Object that comes from get Request
     * Print out followings
     * - Print response
     * - Content-Type is application/json
     * - Status Code is 200
     * - Retrieve data as JAVA Collections and print out following informations
     * <p>
     * System.out.println("====== GET META ======");
     * System.out.println("====== GET LIMIT ======");
     * System.out.println("====== GET CUSTOMERS ======");
     * System.out.println("====== GET FIRST CUSTOMER ======");
     * System.out.println("====== PRINT CUSTOMERS IDs ======");
     * System.out.println("====== PRINT CUSTOMERS Names ======");
     */
    @Test
    public void getCustomers() {

        JsonPath jsonPath = given().log().uri()
                .accept(ContentType.JSON)
                .when()
                .get("/customers").prettyPeek()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().jsonPath();

        Map<String, Object> allData = jsonPath.getMap("");
        System.out.println("allData = " + allData);


        System.out.println("====== GET META ======");

        Map<String, Object> meta = (Map<String, Object>) allData.get("meta");
        System.out.println(meta);

        System.out.println("====== GET LIMIT ======");
        int actualLimit = jsonPath.get("meta.limit");
        Assertions.assertEquals(10, actualLimit);

        System.out.println("====== GET CUSTOMERS ======");
        List<Map<String,Object>> allCustomers = (List<Map<String, Object>>) allData.get("customers");
        System.out.println("allCustomers = " + allCustomers);


        System.out.println("====== GET FIRST CUSTOMER ======");
        Map<String, Object> firstCustomer= allCustomers.get(0);
        System.out.println("firstCustomer = " + firstCustomer);

        System.out.println("====== PRINT CUSTOMERS IDs ======");
        List<Integer> allCustomerIDs = jsonPath.get("customers.id");
        System.out.println("allCustomerIDs = " + allCustomerIDs);

        System.out.println("====== PRINT CUSTOMERS Names ======");
        List<String> allCustomerNames = jsonPath.get("customers.name");
        System.out.println("allCustomerNames = " + allCustomerNames);

        System.out.println("====== GET FIRST CUSTOMER IDs ======");
        Integer fistCustomerID = allCustomerIDs.get(0);

        Assertions.assertEquals(6, fistCustomerID);

        System.out.println("====== GET FOURTH CUSTOMER NAME ======");
        String fourthCustomerName = allCustomerNames.get(3);

        Assertions.assertEquals("Larry Lawson", fourthCustomerName);
    }
}
