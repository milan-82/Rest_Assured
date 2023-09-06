package com.cydeo.day10;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_MovieXML {

    @Test
    public void test1() {


        Response response = given()
                .queryParams("apiKey", "81815fe6")
                .queryParams("r", "xml")
                .queryParams("t", "Inception")
                .when()
                .get("http://www.omdbapi.com").prettyPeek();

        XmlPath xmlPath = response.xmlPath();

        //get me year attribute
        System.out.println("xmlPath.getString(\"root.movie.@year\") = " + xmlPath.getString("root.movie.@year"));

        //get me year title

        System.out.println("xmlPath.getString(\"root.movie.@title\") = " + xmlPath.getString("root.movie.@title"));

        //get me director
        System.out.println("xmlPath.getString(\"root.movie.@director\") = " + xmlPath.getString("root.movie.@director"));

        // get me imdb rating
        System.out.println("xmlPath.getDouble(\"roo.movie.@imdbRating\") = " + xmlPath.getString("roo.movie.@imdbRating"));

    }

    /**
     * http://www.omdbapi.com?apikey=81815fe6&r=xml&s=Harry Potter
     * --Try to get all years and verify they are greater then 2000
     * --Print each title and make sure each of them contains Harry Potter
     * --verify total result is 127
     */





}
