package com.wbl.RestAssured_Automation;

import org.testng.annotations.Test;

import com.wbl.RestAPIAutomation.base.BaseRestAssuredTest;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class RestAssuredAPITest extends BaseRestAssuredTest {
	@Test
	public void getTest(){
		//given().when().get("https://api.qa.talentscreen.io/v1/subjects").then()
		//url is taken from restAssured class from BaseRestAssuredTest.java
		rs.get().then()
		.statusCode(200)
		.body("[1].id", equalTo(37))
		.body("[1].name",equalTo("Java "))
		.body(containsString("icon_class"));
		System.out.println("inside getTest");
	}
	
	@Test
	public void postTest(){
		String reqPayload="{ \"name\": \"APPIUM_NEW\",\"icon_class\": \"ts-seleniumwebdriver\",\"description\": \"MobileAutomation\"}";
		rs.body(reqPayload).post("?authentication=false").then().statusCode(201)
		.body(containsString("id"));
		System.out.println("inside postTest");
	}

}
