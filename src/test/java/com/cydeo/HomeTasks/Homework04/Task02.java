package com.cydeo.HomeTasks.Homework04;

import com.cydeo.POJO.ConstructorPojo.MRData;
import com.cydeo.utilities.FormulaOneRace;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Task02 extends FormulaOneRace {

/*
    TASK 2 : Solve same task with 4 different way

 1) Use JSONPATH
    int total = jsonpath.getInt(“pathOfField”)

 2) Use HAMCREST MATCHERS
    then().body(..........)

 3) Convert Constructor information to Java Structure
    List<Map<String,Object>> constructorMap=jsonpath.getList(“pathOfConsts”);

 4) Convert Constructor information POJO Class
    List<ConstructorPOJO constr = getObject(“pathOfConstr”,ConstructorPOJO.class)

    NOTE
—> There is a class in JAVA That’s why give your class name
ConstrutorPOJO to separate from existing one. Wrong imports may cause  issue

- Given accept type is json
- When user send request /constructorStandings/1/constructors.json
- Then verify status code is 200
     - And content type is application/json; charset=utf-8
     - And total is 17
     - And limit is 30
     - And each constructor has constructorId
     - And constructor has name
     - And size of constructor is 17
     - And constructor IDs has “benetton”, “mercedes”,”team_lotus”


 */

    @DisplayName("JSONPATH")
    @Test
    public void test1() {

        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .when()
                .get("/constructorStandings/1/constructors.json")
                .then()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .extract().jsonPath();

        //- And total is 17
        int total = jsonPath.getInt("MRData.total");
        assertEquals(17, total);

        // - And limit is 30
        int limit = jsonPath.getInt("MRData.limit");
        assertEquals(30, limit);

        //- And each constructor has constructorId
        List<Map<String, Object>> allConstructors = jsonPath.getList("MRData.ConstructorTable.Constructors");

        for (Map<String, Object> eachConstructor : allConstructors) {

            assertTrue(eachConstructor.containsKey("constructorId"));
        }

        // - And constructor has name
        for (Map<String, Object> eachConstructor : allConstructors) {

            assertTrue(eachConstructor.containsKey("name"));
        }

        //And size of constructor is 17
        assertEquals(17, allConstructors.size());

        //- And constructor IDs has “benetton”, “mercedes”,”team_lotus”
        List<String> expectedIDs = Arrays.asList("benetton", "mercedes", "team_lotus");
        List<String> actualIds = jsonPath.getList("MRData.ConstructorTable.Constructors.constructorId");

        assertTrue(actualIds.containsAll(expectedIDs));


    }


    @DisplayName("HAMCREST MATCHERS")
    @Test
    public void test2() {

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/constructorStandings/1/constructors.json").prettyPeek()
                .then()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .body("MRData.total", is(equalTo("17")))
                .body("MRData.limit", is(equalTo("30")))
                .body("MRData.ConstructorTable.Constructors.constructorId", everyItem(notNullValue()))
                .body("MRData.ConstructorTable.Constructors.name", everyItem(notNullValue()))
                .body("MRData.ConstructorTable.Constructors", hasSize(17))
                .body("MRData.ConstructorTable.Constructors.constructorId", containsInRelativeOrder("benetton", "mercedes", "team_lotus"));

    }


    @DisplayName("Convert Constructor information to Java Structure")
    @Test
    public void test3() {
        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .when()
                .get("/constructorStandings/1/constructors.json")
                .then()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .body("MRData.total", is("17"))
                .body("MRData.limit", is("30"))
                .extract().jsonPath();

//      - And total is 17
        assertEquals(17, jsonPath.getInt("MRData.total"));

//      - And limit is 30
        assertEquals(30, jsonPath.getInt("MRData.limit"));

//      - And each constructor has constructorId
        List<Map<String, Object>> constructorMap = jsonPath.getList("MRData.ConstructorTable.Constructors");

        for (Map<String, Object> eachConstructor : constructorMap) {

            assertThat(eachConstructor.get("constructorId"), notNullValue());

            // - And constructor has name
            assertThat(eachConstructor.get("name"), notNullValue());

            //      - And constructor IDs has “benetton”, “mercedes”,”team_lotus”;
            List<String> actualIds = jsonPath.getList("MRData.ConstructorTable.Constructors.constructorId");
            List<String> expectedIDs = Arrays.asList("benetton", "mercedes", "team_lotus");

            assertTrue(actualIds.containsAll(expectedIDs));

        }

//      - And size of constructor is 17
        assertThat(constructorMap.size(), is(17));

    }


    @DisplayName("Convert Constructor information POJO Class")
    @Test
    public void test4() {

        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .when()
                .get("/constructorStandings/1/constructors.json").prettyPeek()
                .then()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .body("MRData.total",equalTo("17"))
                .body("MRData.limit",equalTo("30"))
                .extract().jsonPath();

       MRData mrData = jsonPath.getObject("",MRData.class);
        System.out.println("mrData = " + mrData);

//                - And each constructor has constructorId



//                - And constructor has name


//                - And size of constructor is 17


//                - And constructor IDs has “benetton”, “mercedes”,”team_lotus”




    }




}