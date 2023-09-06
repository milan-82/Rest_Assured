package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class P01_SpartanXMLTest extends SpartanAuthTestBase {

    /**
     * Given accept type is application/xml
     * and basic auth is admin admin
     * When send the request /api/spartans
     * Then status code is 200
     * And content type is application/xml
     * print first spartan name
     * .....
     * ...
     */

    @Test
    public void test1() {

        given()
                .accept(ContentType.XML)
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans").prettyPeek()
                .then()
                .statusCode(200)
                .contentType(ContentType.XML)
                .body("List.item[0].name", is("Meade"));

    }

    @DisplayName("GET /api/spartans with using XMLPath")
    @Test
    public void test2() {

        Response response = given()
                .accept(ContentType.XML)
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans").prettyPeek();

        XmlPath xmlPath = response.xmlPath();

        String firstSpartanName = xmlPath.getString("List.item[0].name");

        // can you get the name from db and compare with this variable?


        // get me the last Spartan name
        System.out.println("xmlPath.getString(\"List.item[-1].name\") = " + xmlPath.getString("List.item[-1].name"));

        //get me all Spartan names
        List<String> allNames = xmlPath.getList("List.item.name");
        System.out.println("allNames = " + allNames);

    }
}