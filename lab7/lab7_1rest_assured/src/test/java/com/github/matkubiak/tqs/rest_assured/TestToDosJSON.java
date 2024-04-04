package com.github.matkubiak.tqs.rest_assured;

//io.restassured.RestAssured.*;
//io.restassured.matcher.RestAssuredMatchers.*;
//static import org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestToDosJSON {

    @Test
    public void testEndpointOpen() {
        when().
            get("https://jsonplaceholder.typicode.com/todos/1").
        then().
            statusCode(200);
    }

    @Test
    public void testToDo4Title() {
        when().
            get("https://jsonplaceholder.typicode.com/todos/4").
        then().
            body("title", equalTo( "et porro tempora"));
    }

    @Test
    public void testToDosSize() {
        Response response =
            when().
                get("https://jsonplaceholder.typicode.com/todos").
            then().
                extract().response();

        JsonPath path = response.jsonPath();
        assertThat(path.getList("$"), hasSize(greaterThanOrEqualTo(199)));
    }

    @Test
    public void testToDosResponseTime() {
        when().
            get("https://jsonplaceholder.typicode.com/todos").
        then().
            time(lessThan(2000L));
    }
}
