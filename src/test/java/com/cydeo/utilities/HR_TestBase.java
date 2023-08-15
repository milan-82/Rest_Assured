package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class HR_TestBase {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://54.89.45.138:1000/ords/hr";
    }

}
