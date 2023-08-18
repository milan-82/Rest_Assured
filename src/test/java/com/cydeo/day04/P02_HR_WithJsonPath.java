package com.cydeo.day04;

import com.cydeo.utilities.HR_TestBase;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class P02_HR_WithJsonPath extends HR_TestBase {
    @DisplayName("GET all countries")
    @Test
    public void test(){

        Response response = get("/countries");

        //response.prettyPrint();

        // verify status code
        assertEquals(200, response.statusCode());

        // create jsonpath object
        JsonPath jsonPath = response.jsonPath();

        System.out.println("----get me 3rd country name---");
        System.out.println(jsonPath.getString("items[2].country_name"));

        System.out.println("----get me 3rd and 4th country name at same time---");
        System.out.println(jsonPath.getString("items[2,3].country_name"));

        System.out.println("----get me all country names where region_id is 2----");
        List<String> list =jsonPath.getList("items.findAll {it.region_id==2}.country_name");

        System.out.println("list = " + list);

    }

      /*
    Given accept type is application/json
    And query param limit is 200
    When user send request /employees
    Then user should see ............

     */
   @DisplayName("Get All /employees?limit=200 with JSON PATH")
    @Test
    public void test2(){

       Response response = given().accept(ContentType.JSON)
               .and()
               .queryParam("limit", "200")
               .when()
               .get("/employees");

       //response.prettyPrint();

       // assert status code
       assertEquals(200, response.statusCode());

       JsonPath jsonPath = response.jsonPath();

       System.out.println("---get all emails from response---");
       List<String> allEmails = jsonPath.getList("items.email");
       System.out.println("allEmails = " + allEmails);
       System.out.println("allEmails.size() = " + allEmails.size());

       System.out.println("");
       //get me emails who's working as IT_PROG
       List<String> itEmails = jsonPath.getList("items.findAll {it.job_id=='IT_PROG'}.email");
       System.out.println("itEmails = " + itEmails);

       System.out.println("");
       //get me all employees first name who salary is more than 10000
       List<String> allEmpSalaryMoreThan10k = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
       System.out.println(allEmpSalaryMoreThan10k);

       System.out.println("");
      //get me all information from response who has max salary
       String maxSalary = jsonPath.getString("items.max {it.salary}.first_name");
       System.out.println("maxSalary = " + maxSalary);

       //get me firstname from response who has min salary
       String minSalary = jsonPath.getString("items.min {it.salary}.first_name");
       System.out.println("minSalary = " + minSalary);

   }

  /*
    TASK
    Given
             accept type is application/json
     When
             user sends get request to /locaitons
     Then
             response status code must be 200
             content type equals to application/json
             get the second city with JsonPath
             get the last city with JsonPath
             get all country ids
             get all city where their country id is UK
      */

    @Test
    public void test3(){

        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/locations");

      //  response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

//        response status code must be 200
       assertEquals(200, response.statusCode());

//        content type equals to application/json
        assertEquals("ap    plication/json", response.contentType());

//        get the second city with JsonPath
        String secondCity = jsonPath.getString("items[1].city");
        System.out.println("secondCity = " + secondCity);

//        get the last city with JsonPath
        String lastCity = jsonPath.getString("items[22].city");
        System.out.println("lastCity = " + lastCity);

//        get all country ids
        String allCountryIds = jsonPath.getString("items.country_id");
        System.out.println("allCountryIds = " + allCountryIds);


//        get all city where their country id is UK
        String allCityInUK = jsonPath.getString("items.findAll {it.country_id='UK'}.city");
        System.out.println("allCityInUK = " + allCityInUK);
    }

}
