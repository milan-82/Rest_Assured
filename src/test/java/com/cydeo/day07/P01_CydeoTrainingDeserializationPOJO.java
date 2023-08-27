package com.cydeo.day07;

import com.cydeo.POJO.Student;
import com.cydeo.POJO.Students;
import com.cydeo.utilities.CydeoTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P01_CydeoTrainingDeserializationPOJO extends CydeoTestBase {

     /*
    Given accept type is application/json
    And path param is 2
    When user send request /student/{id}
    Then status code should be 200

    And Server header is envoy
    And verify following
                firstName Mark
                batch 13
                major math
                emailAddress mark@email.com
                companyName Cydeo
                street 777 5th Ave
                zipCode 33222
    */

    @DisplayName("GET /students/2")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .when()
                .pathParam("id", 2)
                .get("/student/{id}");

        // response.prettyPrint();

        //  Then status code should be 200
        assertEquals(200, response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        // Deserialization to Student class
        Student student = jsonPath.getObject("students[0]", Student.class);


        //    firstName Mark
        assertEquals("Mark", student.getFirstName());

        //        batch 13
        assertEquals(13, student.getBatch());

        //        major math
        assertEquals("math", student.getMajor());

        //        emailAddress mark@email.com
        assertEquals("mark@email.com", student.getContact().getEmailAddress());

        //        companyName Cydeo
        assertEquals("Cydeo", student.getCompany().getCompanyName());

        //        street 777 5th Ave
        assertEquals("777 5th Ave", student.getCompany().getAddress().getStreet());

        //        zipCode 33222
        assertEquals(33222, student.getCompany().getAddress().getZipCode());
    }

    @DisplayName("GET /students/2")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .when()
                .pathParam("id", 2)
                .get("/student/{id}");

         response.prettyPrint();

        //  Then status code should be 200
        assertEquals(200, response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        // Deserialization to Student class
        Students students = jsonPath.getObject("", Students.class);

        //we deserialize everything to Students class which is holding list of Student
        System.out.println("students = " + students);
        Student student = students.getStudents().get(0);
        System.out.println("student = " + student);


        //    firstName Mark
        assertEquals("Mark", student.getFirstName());

        //        batch 13
        assertEquals(13, student.getBatch());

        //        major math
        assertEquals("math", student.getMajor());

        //        emailAddress mark@email.com
        assertEquals("mark@email.com", student.getContact().getEmailAddress());

        //        companyName Cydeo
        assertEquals("Cydeo", student.getCompany().getCompanyName());

        //        street 777 5th Ave
        assertEquals("777 5th Ave", student.getCompany().getAddress().getStreet());

        //        zipCode 33222
        assertEquals(33222, student.getCompany().getAddress().getZipCode());
    }


    @DisplayName("GET /students/2")
    @Test
    public void test3() {

        Response response = given().accept(ContentType.JSON)
                .when()
                .pathParam("id", 2)
                .get("/student/{id}");

        // response.prettyPrint();

        //  Then status code should be 200
        assertEquals(200, response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        com.cydeo.POJO.ready.Student student = jsonPath.getObject("students[0]", com.cydeo.POJO.ready.Student.class);

        System.out.println("student.joinDate = " + student.joinDate);
        System.out.println("student.getBirthDate() = " + student.getBirthDate());
        System.out.println("student.company.address.city = " + student.company.address.city);
        System.out.println("student.contact.permanentAddress = " + student.contact.permanentAddress);
    }

}
