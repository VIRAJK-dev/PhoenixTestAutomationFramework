package com.api.tests;

import static io.restassured.RestAssured.*;

import static io.restassured.http.ContentType.*;

import static org.hamcrest.Matcher.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;


import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
@Test
	
	public void loginAPITest()
	{
		UserCredentials userCredentials = new UserCredentials("iamfd", "password");
		
		
	    given()
        .baseUri("http://64.227.160.186:9000/v1")
        .contentType(JSON)
        .accept(JSON)
        .body(userCredentials)
        .log().uri()
        .log().method()
        .log().body()
        .log().headers()
    .when()
        .post("/login")
    .then()
        .statusCode(200)
        .time(lessThan(5000L))
        .body("message", equalTo("Success"))
	    .log().body()
	    .and()
	    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
}

		    
		
	}


