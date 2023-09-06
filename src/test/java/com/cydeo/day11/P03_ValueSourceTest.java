package com.cydeo.day11;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;

public class P03_ValueSourceTest {

    @ParameterizedTest
    @ValueSource(ints = {10, 50, 20, 40, 30})
    public void test1(int number) {

        System.out.println("number = " + number);
        Assertions.assertTrue(number < 40);

    }

    @ParameterizedTest(name = "{index} - verify name is {0}")
    @ValueSource(strings = {"Mike", "Rose", "Steven", "TJ", "Harold", "Severus", "Sherlock"})
    public void test2(String name) {
        System.out.println("name = " + name);
        Assertions.assertTrue(name.length() > 4);

    }

    //TASK
    // SEND GET REQUEST TO https://api.zippopotam.us/us/{zipcode}
    // with these zipcodes 22030,22031, 22032, 22033 , 22034, 22035, 22036
    // check status code 200

    @ParameterizedTest
    @ValueSource(ints = {22030, 22031, 2203200, 22033, 22034, 22035, 22036})
    public void zipCodes(int zipCode) {


        given().log().uri()
                .accept(ContentType.JSON)
                .pathParam("zipcode", zipCode)
                .when()
                .get(" https://api.zippopotam.us/us/{zipcode}").prettyPeek()
                .then()
                .statusCode(200);
    }


}
