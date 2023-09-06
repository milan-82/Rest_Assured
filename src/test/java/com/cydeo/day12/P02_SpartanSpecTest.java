package com.cydeo.day12;

import com.cydeo.utilities.SpartanNewTestBase;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P02_SpartanSpecTest extends SpartanNewTestBase {

    @Test
    public void getAllSpartans() {

        given()
                .log().all()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .when()
                .get("/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);

    }

    @Test
    public void getAllSpartansWithRequestResponseSpec() {

        given()
                .spec(requestSpec)
                .when()
                .get("/spartans")
                .then()
                .spec(responseSpec);

    }

    @Test
    public void getOneSpartanWithRequestResponseSpec() {

        given()
                .spec(requestSpec)
                .and()
                .pathParam("id", 10)
                .when()
                .get("/spartans/{id}")
                .then()
                .spec(responseSpec)
                .body("id", is(10));
    }

    @Test
    public void getOneSpartanAsUser() {

        given()
                .spec(requestUserSpec)
                .and()
                .pathParam("id", 10)
                .when()
                .get("/spartans/{id}")
                .then()
                .spec(responseSpec)
                .body("id", is(10));

    }


    @Test
    public void getOneSpartanWithDynamicSpec() {

        given()
                .spec(dynamicRequestSpec("user", "user"))
                .and()
                .pathParam("id", 10)
                .when()
                .get("/spartans/{id}")
                .then()
                .spec(dynamicResponseSpec(200))
                .body("id", is(10));

    }


    /**
     * Create GET_RBAC.csv
     * username,password,id,statusCode
     * admin,admin,5,200
     * editor,editor,5,200
     * user,user,5,200
     * <p>
     * Create a parameterized test to check RBAC for GET method
     */

    @ParameterizedTest
    @CsvFileSource(resources = "/GET_RBAC.csv", numLinesToSkip = 1)
    public void tack1(String username, String password, int id, int statusCode) {

        given()
                .spec(dynamicRequestSpec(username, password))
                .pathParam("id", id)
                .when()
                .get("/spartans/{id}")
                .then()
                .spec(dynamicResponseSpec(statusCode))
                .contentType(ContentType.JSON);
    }

    /**
     *  Create DELETE_RBAC.csv
     *   username,password,id,statusCode
     *    editor,editor,5,403
     *    user,user,5,403
     *    admin,admin,5,204
     *
     *  Create a parameterized test to check RBAC for DELETE method
     *
     *
     */

    @ParameterizedTest
    @CsvFileSource(resources = "/DELETE_RBAC.csv", numLinesToSkip = 1)
    public void tackDELETE(String username, String password, int id, int statusCode) {

        given()
                .spec(dynamicRequestSpec(username, password))
                .pathParam("id", id)
                .when()
                .delete("/spartans/{id}")
                .then()
                .spec(dynamicResponseSpec(statusCode));

    }



}
