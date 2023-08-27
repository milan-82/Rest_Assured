package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanTestBase {

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "http://54.89.45.138:8000";

    }

    @AfterAll
    public static void destroy() {
        RestAssured.reset();
    }


}
