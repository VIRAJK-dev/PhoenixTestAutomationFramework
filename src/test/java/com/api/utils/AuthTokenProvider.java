package com.api.utils;



import static com.api.constant.Role.*;

import com.api.constant.Role;
//import com.api.constant.Role;
import com.api.pojo.UserCredentials;

import static io.restassured.http.ContentType.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AuthTokenProvider {
	
	private AuthTokenProvider()
	{
		//private constructor
	}

	public static String getToken(Role role) {
		// TODO Auto-generated method stub
		// I want to make a request for Login API and we want to extrcat the token and
		// print it on the console
		
		UserCredentials userCredentials = null;
		
		if(role == FD )
		{
			userCredentials =new UserCredentials("iamfd", "password");
		}
		
		else if(role == SUP)
		{
			userCredentials =new UserCredentials("iamsup", "password");
		}
		
		else if(role == ENG)
		{
			userCredentials =new UserCredentials("iameng", "password");
		}
		
		else if(role ==QC)
		{
			userCredentials =new UserCredentials("iamqc", "password");
		}
		
		

		String token = given().baseUri(ConfigManager.getProperty("BASE_URI")).contentType(JSON).accept(JSON)
				.body(userCredentials).when().post("login").then().log().ifValidationFails()
				.statusCode(200).body("message", equalTo("Success")).extract().body().jsonPath()
				.getString("data.token");

		return token;

	}

}
