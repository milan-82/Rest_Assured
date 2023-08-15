package com.cydeo.day03;

import com.cydeo.utilities.HR_TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured .*;
import static org.junit.jupiter.api.Assertions .*;
public class P04_HRWithResponsePath extends HR_TestBase {

  @DisplayName("GET Request to countries with using Response Path")
    @Test
    public void test1(){

      Response response = given().accept(ContentType.JSON)
              .and()
              .queryParam("q", "{\"region_id\":2}")
              .when()
              .get( "/countries");

      //response.prettyPrint();

      // print limit
      System.out.println("limit = " + response.path("limit"));

      // print hasMore
      System.out.println("hasMore = " + response.path("hasMore"));

      // print second country name
      System.out.println("second country name = " + response.path("items[1].country_name"));

      // print 4th element country name
      System.out.println("4th element country name = " + response.path("items[3].country_name"));

      // print 3rd element href
      System.out.println("3rd element href = " + response.path("items[2].links[0].href"));

      // get all countries names
      System.out.println("all countries names = " + response.path("items.country_name"));

      // verify all region_ids equal to 2
      List<Integer> allRegionIds = response.path("items.region_id");

      for (Integer ID : allRegionIds) {
          assertEquals(2, ID);
          System.out.println("ID = " + ID);
      }


  }
}
