package com.cydeo.HomeTasks.Homework05;

import com.cydeo.POJO.RegionHomework;
import com.cydeo.utilities.HR_TestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Task1 extends HR_TestBase {

    static private int global_region_id;

    static private String global_region_name;

    @Test
    @Order(1)
    void create_regionPOST() {
        /*
        Map<String,Object> requestedBody =new LinkedHashMap<>();
        requestedBody.put("region_id",378);
        requestedBody.put("region_name","Test Region");

         */

        /*
        {
"region_id":100,
"region_name":"Test Region"
}
         */
        Random random=new Random();

        int random_region_id = random.nextInt(1000);

        int expected_region_id = random_region_id;

        global_region_id = expected_region_id;

        String expected_region_name="RegionHomework";

        global_region_name=expected_region_name;

        System.out.println("expected_region_id = " + expected_region_id);

        Response response = RestAssured
                .given()
                .accept(ContentType.JSON) //"application/json"
                .contentType(ContentType.JSON)
                .body(new RegionHomework(expected_region_id,expected_region_name))
                //   .body("        {\n" + "\"region_id\":100,\n" +   "\"region_name\":\"Test Region\" \n" + "}\n")
                .post("/regions/").prettyPeek();

        int actual_region_id = response.jsonPath().getInt("region_id");
        String actual_region_name = response.jsonPath().getString("region_name");

        System.out.println("actual_region_id = " + actual_region_id);

        Assertions.assertEquals(expected_region_id,actual_region_id);

        Assertions.assertEquals(expected_region_name,actual_region_name);

    }


    @Test
    @Order(2)
    void get_region() {

        RestAssured
                .given()
                .accept(ContentType.JSON)
                .get("/regions/"+global_region_id)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON).extract().response().prettyPeek();

    }

    @Test
    @Order(3)
    void update_region() {

        int updateRegionId=global_region_id;

        String updatedRegionName="updated "+global_region_name;

        Response response = RestAssured
                .given()
                .accept(ContentType.JSON) //"application/json"
                .contentType(ContentType.JSON)
                .body(new RegionHomework(updateRegionId,updatedRegionName))
                //   .body("        {\n" + "\"region_id\":100,\n" +   "\"region_name\":\"Test Region\" \n" + "}\n")
                .put("/regions/"+updateRegionId).prettyPeek();

        String actual_region_name = response.jsonPath().getString("region_name");

        Assertions.assertEquals(updatedRegionName, actual_region_name);
    }

    @Test
    @Order(4)
    void delete_region() {

        RestAssured
                .given()
                .accept(ContentType.JSON)
                .delete("/regions/" + global_region_id)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON).extract().response().prettyPeek();

    }
}