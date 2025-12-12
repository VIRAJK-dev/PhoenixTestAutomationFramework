package com.api.tests;

import static com.api.utils.AuthTokenProvider.getToken;
import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.constant.Role;

import static com.api.utils.AuthTokenProvider.*;

import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {
	
	@Test
	
	public void userDetailsAPITest() throws IOException
	{
//		ConfigManager configManager = new ConfigManager();
		Header authHeader = new Header("Authorization",getToken(Role.SUP));
		
		given()
		      .baseUri(getProperty("BASE_URI"))
		      .and()
		      .header(authHeader)
		      .and()
		      .contentType(JSON)
		      .and()
		      .accept(JSON)
		      
		.when()
		       .get("userdetails")
		       
		.then()
		       .statusCode(200)
		       .body("message",equalTo("Success"))
		       .time(lessThan(1000L))
		       .log().body()
		       .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));	
		
		
		
	}
	
	

}
