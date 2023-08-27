package com.cydeo.HomeTasks.Homework04;

import com.cydeo.POJO.DriverAlonso.Drivers;
import com.cydeo.utilities.FormulaOneRace;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Task01 extends FormulaOneRace {

//    TASK 1 : Solve same task with 4 different way
//- Given accept type is json
//- And path param driverId is alonso
//- When user send request /drivers/{driverId}.json
//- Then verify status code is 200
// - And content type is application/json; charset=utf-8
// - And total is 1
// - And givenName is Fernando
//- And familyName is Alonso
//- And nationality is Spanish


//- Use JSONPATH
//    int total = jsonpath.getInt(“pathOfField”)


// - Use HAMCREST MATCHERS
//    then().body(..........)
//    Print givenName of Driver by using extract method after HamCrest


//- Convert Driver information to Java Structure
//    Map<String,Object> driverMap=jsonpath.getMap(“pathOfDriver”)


// - Convert Driver information POJO Class
//    Driver driver=getObject(“pathOfDriver”,Driver.class)

    @DisplayName("JSONPATH")
    @Test
    public void test1() {

        //- Given accept type is json
//- And path param driverId is alonso
//- When user send request /drivers/{driverId}.json
//- Then verify status code is 200
// - And content type is application/json; charset=utf-8
// - And total is 1
// - And givenName is Fernando
//- And familyName is Alonso
//- And nationality is Spanish

        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .pathParam("driverId", "alonso")
                .when()
                .get("/drivers/{driverId}.json").prettyPeek()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().jsonPath();

        // - And total is 1
        assertEquals("1", jsonPath.get("MRData.total"));

        // - And givenName is Fernando
        assertEquals("Fernando", jsonPath.getString("MRData.DriverTable.Drivers[0].givenName"));

        //- And familyName is Alonso
        assertEquals("Alonso", jsonPath.getString("MRData.DriverTable.Drivers[0].familyName"));

        //- And nationality is Spanish
        assertEquals("Spanish", jsonPath.getString("MRData.DriverTable.Drivers[0].nationality"));

    }

    @DisplayName("Hamcrest matchers")
    @Test
    public void test2() {

        //- Given accept type is json
//- And path param driverId is alonso
//- When user send request /drivers/{driverId}.json
//- Then verify status code is 200
// - And content type is application/json; charset=utf-8
// - And total is 1
// - And givenName is Fernando
//- And familyName is Alonso
//- And nationality is Spanish

        given()
                .accept(ContentType.JSON)
                .pathParam("driverId", "alonso")
                .when()
                .get("/drivers/{driverId}.json").prettyPeek()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json; charset=utf-8")
                .body(" MRData.total", is("1"))
                .body("MRData.DriverTable.Drivers[0].givenName", is(equalTo("Fernando")))
                .body("MRData.DriverTable.Drivers[0].familyName", is(equalTo("Alonso")))
                .body("MRData.DriverTable.Drivers[0].nationality", is(equalTo("Spanish")));


    }


    @DisplayName("Convert Driver information to Java Structure")
    @Test
    public void test3() {

        //- Given accept type is json
//- And path param driverId is alonso
//- When user send request /drivers/{driverId}.json
//- Then verify status code is 200
// - And content type is application/json; charset=utf-8
// - And total is 1
// - And givenName is Fernando
//- And familyName is Alonso
//- And nationality is Spanish

        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .pathParam("driverId", "alonso")
                .when()
                .get("/drivers/{driverId}.json")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json; charset=utf-8")
                .extract().jsonPath();

        // - And total is 1
        assertEquals("1", jsonPath.get("MRData.total"));

        Map<String, Object> driverMap = jsonPath.getMap("MRData.DriverTable.Drivers[0]");
        System.out.println("driverMap = " + driverMap);

        // - And givenName is Fernando
        assertEquals("Fernando", driverMap.get("givenName"));

        //- And familyName is Alonso
        assertEquals("Alonso", driverMap.get("familyName"));

        //- And nationality is Spanish
        assertEquals("Spanish", driverMap.get("nationality"));


    }

    @DisplayName("GET Driver information with POJO")
    @Test
    public void test4() {

        //- Given accept type is json
//- And path param driverId is alonso
//- When user send request /drivers/{driverId}.json
//- Then verify status code is 200
// - And content type is application/json; charset=utf-8
// - And total is 1
// - And givenName is Fernando
//- And familyName is Alonso
//- And nationality is Spanish

        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .pathParam("driverId", "alonso")
                .when()
                .get("/drivers/{driverId}.json").prettyPeek()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json; charset=utf-8")
                .extract().jsonPath();

        Drivers driver = jsonPath.getObject("MRData.DriverTable.Drivers[0]", Drivers.class);

        // - And total is 1
        String total = jsonPath.get("MRData.total");
        assertEquals("1", total);

        // - And givenName is Fernando
        String givenName = driver.getGivenName();

        assertEquals("Fernando", givenName);

        //- And familyName is Alonso
        String familyName = driver.getFamilyName();

        assertEquals("Alonso", familyName);

        //- And nationality is Spanish
        String nationality = driver.getNationality();

        assertEquals("Spanish", nationality);

    }


}
