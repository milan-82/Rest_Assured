package com.cydeo.LiveClass.week4;

import com.cydeo.utilities.FormulaOneRace;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


public class P01XMLPath extends FormulaOneRace {

    @Test
    public void test1(){

       Response response = RestAssured
               .given()
                .accept(ContentType.XML)
                        .get("/drivers");


        XmlPath xmlPath = response.xmlPath();

        String firstGivenName = xmlPath.getString("MRData.DriverTable.Driver[0].GivenName");
        System.out.println("firstGivenName = " + firstGivenName);

        // I want to get first driver id
        String firstDiverId = xmlPath.getString("MRData.DriverTable.Driver[0].@driverId");
        System.out.println("firstDiverId = " + firstDiverId);


    }

}
