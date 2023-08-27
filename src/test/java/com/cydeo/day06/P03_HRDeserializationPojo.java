package com.cydeo.day06;

import com.cydeo.POJO.Region;
import com.cydeo.POJO.Regions;
import com.cydeo.utilities.HR_TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class P03_HRDeserializationPojo extends HR_TestBase {
    @DisplayName("GET regions to deserialization to POJO - LOMBOK -JSON PROPERTY")
    @Test
    public void test1() {

        JsonPath jsonPath = get("/regions")
                .then()
                .statusCode(200)
                .extract().jsonPath();

        //get first region from items array and convert it to Region class
        Region region1 = jsonPath.getObject("items[0]", Region.class);

        System.out.println("region1 = " + region1);

        // System.out.println("region1.getRegion_id() = " + region1.getRegion_id());
        // System.out.println("region1.getRegion_name() = " + region1.getRegion_name());
        System.out.println("region1.getRegionId() = " + region1.getRegionId());
        System.out.println("region1.getRegionName() = " + region1.getRegionName());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());
        System.out.println("region1.getLinks().get(0).getRel() = " + region1.getLinks().get(0).getRel());

    }

    @DisplayName("GET employee to deserialization to POJO with only required fields")
    @Test
    public void test2() {

        Response response = get("/employees")
                .then()
                .statusCode(200)
                .extract().response();

        Regions regions = response.as(Regions.class);

        System.out.println("regions.getRegionId() = " + regions.getRegionId());


    }

       /*
    TASK

    Given accept is application/json
    When send request  to /regions endpoint
    Then status should be 200
            verify that region ids are 1,2,3,4
            verify that regions names Europe ,Americas , Asia, Middle East and Africa
            verify that count is 4
        -- Create Regions POJO
        -- And ignore field that you don't need

     */

    @Test
    public void test3() {


        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .when()
                .get("/regions")
                .then()
                .statusCode(200)
                .extract().jsonPath();

        Regions regions = jsonPath.getObject("", Regions.class);

        List<Regions> items = regions.getItems();

        List<Integer> regionId = Arrays.asList(1, 2, 3, 4);
        List<String> regionNames = Arrays.asList("Europe", "Americas", "Asia", "Middle East and Africa");

        for (int i = 0; i <items.size() ; i++) {
            Regions region = items.get(i);

          //  System.out.println("region = " + region);

            assertThat(regionId.get(i), is(equalTo(region.getRegionId())));
            assertThat(regionNames.get(i), is(equalTo(region.getRegionName())));

        }

        assertThat(regions.getCount(), is(equalTo(4)));
        assertThat(items.size(), is(4));

    }

}
