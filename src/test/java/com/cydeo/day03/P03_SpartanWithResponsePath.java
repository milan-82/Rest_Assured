package com.cydeo.day03;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_SpartanWithResponsePath extends SpartanTestBase {

    /*
       Given accept type is json
       And path param id is 10
       When user sends a get request to "/api/spartans/{id}"
       Then status code is 200
       And content-type is "application/json"
       And response payload values match the following:
            id is 10,
            name is "Lorenza",
            gender is "Female",
            phone is 3312820936
     */

    @DisplayName("GET spartan with Response Path")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id",10)
                .when()
                .get("/api/spartans/{id}");

        response.prettyPrint();

        //        Then status code is 200
        assertEquals(200, response.statusCode());

        //        And content-type is "application/json"
        assertEquals("application/json", response.contentType());

//              And response payload values match the following:
//                id is 10,
//                name is "Lorenza",
//                gender is "Female",
//                phone is 3312820936,

        int id = response.path("id");
        System.out.println("id = " + id);

        String name = response.path("name");
        System.out.println("name = " + name);

        String gender = response.path("gender");
        System.out.println("gender = " + gender);

        long phone = response.path("phone");
        System.out.println("phone = " + phone);

        // if the path is incorrect it will return null
        String address = response.path("address");
        System.out.println("address = " + address);

        // Assertion
        assertEquals(10, id);
        assertEquals("Lorenza", name);
        assertEquals("Female", gender);
        assertEquals(3312820936l, phone);

    }

    @DisplayName("GET All Spartans")
    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/api/spartans");

        //response.prettyPrint();

        // get me first Spartan ID
        int fistID = response.path("[0].id");
        System.out.println("fistID = " + fistID);

        int IdFirst = response.path("id[0]");
        System.out.println("IdFirst = " + IdFirst);

        //get first spartan name
        System.out.println("First Spartan name = " + response.path("[0].name"));
        System.out.println("First Spartan name opt.2 = " + response.path("name[0]"));

        System.out.println("--------------------------------------------------------------------------");

        //get me last Spartan name
        // name[-1] --> -1 refers last element of the name list
        System.out.println("Last Spartan name = " + response.path("name[-1]"));

        System.out.println("------------------------------------------------------------------------------");

      // get me all Spartans names
        List<String> allNames = response.path("name");

        // how to print all names
        for (String eachName : allNames) {
            System.out.println("eachName = " + eachName);
        }


    }
}
