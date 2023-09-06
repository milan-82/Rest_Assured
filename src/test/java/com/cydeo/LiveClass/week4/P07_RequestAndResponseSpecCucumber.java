package com.cydeo.LiveClass.week4;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class P07_RequestAndResponseSpecCucumber extends SpartanTestBase {

   static RequestSpecification requestSpecification;


    @Test
    @Order(1)
    public void acceTypeShouldBeJson(){

        requestSpecification = RestAssured.given()
                .accept(ContentType.JSON);

    }


}
