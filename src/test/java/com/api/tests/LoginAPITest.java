package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import static com.api.utils.ConfigManager.*;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
@Test
	
	public void loginAPITest() throws IOException
	{
	  
		UserCredentials userCredentials = new UserCredentials("iamfd", "password");
		
		
	    given()
        .baseUri(getProperty("BASE_URI"))
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


