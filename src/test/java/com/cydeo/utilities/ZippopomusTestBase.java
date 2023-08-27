package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class ZippopomusTestBase {

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "https://www.zippopotam.us/#";

    }

    @AfterAll
    public static void destroy() {
        RestAssured.reset();
    }
}
