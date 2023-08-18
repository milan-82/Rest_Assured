package com.cydeo.day05;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
public class P01_HamCrestMatchersIntro {

    @Test
    public void number() {

        //junit5 assert equals method
        assertEquals(9, 6 + 3);
        assertThat(6 + 4, Matchers.is(10));

        MatcherAssert.assertThat(6 + 4, Matchers.is(10));

        //HamcrestMatchers comes from Restassured dependency
        //2 static import to get rid of class names ,directly call assertThat and Matchers
        //import static org.hamcrest.MatcherAssert.*;
        //import static org.hamcrest.Matchers.*;

        //Hamcrest Matchers
        //Matchers has 2 overloaded methods
        //-First one will take value to check
        //-Second one will take another Matchers to make it readable / to add new assert functionality
        assertThat(6+3, is(9));
        assertThat(6+3, is(equalTo(9)));
        assertThat(6+3, equalTo(9));

        /**
         is(someValue)
         is(equalTo(someValue))
         equalTo(someValue)
         All of them same in terms of assertion
         */

        assertThat(5+5,not(9));
        assertThat(5+5,is(not(9)));
        assertThat(5+5,is(not(equalTo(9))));
        /**
         * They are all the same for assertion
         */

        assertThat(5+6,is(greaterThanOrEqualTo(11)));
        assertThat(5+6,greaterThan(10));
        assertThat(5+6,lessThan(12));
        assertThat(5+6,lessThanOrEqualTo(11));

    }

    @Test
    public void testString(){

        String msg = "API is fun!";

        assertThat(msg,is("API is fun!"));
        assertThat(msg,equalTo("API is fun!"));
        assertThat(msg,equalToIgnoringCase("api is fun!"));

        assertThat(msg,startsWith("API"));
        assertThat(msg,startsWithIgnoringCase("api"));

        assertThat(msg,endsWith("fun!"));
        assertThat(msg,endsWithIgnoringCase("FUN!"));

        assertThat(msg,containsString("is"));
        assertThat(msg,containsStringIgnoringCase("IS"));

        assertThat(msg,not("FUN!"));
        assertThat(msg,is(not("FUN!")));

    }

    @Test
    public void testCollections(){

        List<Integer> numbersList = Arrays.asList(3,5,1,77,44,86);

        // how to check size if elements
        assertThat(numbersList, hasSize(6));

        // how check 77 is in the collection
        assertThat(numbersList, hasItem(77));

        // how check 77 and 44 is in the collection
        assertThat(numbersList, hasItems(44,77));

        //loop through each of the element and make sure they are matching with Matchers inside the everyItem
        assertThat(numbersList, everyItem(greaterThanOrEqualTo(1)));

        //check if you have values and their position is correct
        assertThat(numbersList, containsInRelativeOrder(3,5,1));

        //check if you have all the values, position might be different
        assertThat(numbersList,containsInAnyOrder(5,3,1,77,44,86) );


    }






}
