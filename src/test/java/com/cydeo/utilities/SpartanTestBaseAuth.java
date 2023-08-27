package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.reset;

public class SpartanTestBaseAuth {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://54.89.45.138:1000/ords/hr";
    }

    @AfterAll
    public static void destroy() {
        RestAssured.reset();
    }

}
