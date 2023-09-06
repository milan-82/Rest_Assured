package com.cydeo.utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class SpartanNewTestBase {


    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;
    public static RequestSpecification requestUserSpec;

    @BeforeAll
    public static void init() {

        baseURI = "http://54.89.45.138";
        port = 7000;
        basePath = "/api";


        //baseUti+port+basePath
        //http://54.89.45.138:7000/api


        requestSpec = given()
                .log().all()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin");


        requestUserSpec = given()
                .log().all()
                .accept(ContentType.JSON)
                .auth().basic("user", "user");


        responseSpec = expect().statusCode(200)
                .contentType(ContentType.JSON);

    }


    //Create dynamic method which is accepting username and password as a parameter and returning
    //request specification dynamicReqSpec()

    public static RequestSpecification dynamicRequestSpec(String username, String password) {


        return given()
                .log().all()
                .accept(ContentType.JSON)
                .auth().basic(username, password);
    }

    //Create dynamic method which has parameter for status code, and returning ResponseSpecification
    // dynamicResSpec()

    public static ResponseSpecification dynamicResponseSpec(int statusCode) {

        return expect().statusCode(statusCode)
                .contentType(ContentType.JSON);

    }

}
