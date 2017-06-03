package com.wbl.RestAPIAutomation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wbl.RestAPIAutomation.base.BaseAPITest;
import com.wbl.RestAPIAutomation.base.BaseApi;
import com.wbl.RestAPIAutomation.helper.RestResponse;

public class TalentScreenTest extends BaseAPITest {
	
BaseApi api;
	
	@BeforeClass
	public void beforeClass(){
		api = new BaseApi(endpoint);
	}
	
	@Test
	public void getSubjectsTest(){
		
		RestResponse response = api.get("/subjects?authentication=false");
		//getting important header info from response
		//assert for status code
		assertEquals(response.getStatusCode(),200);
		//assert for status message
		assertTrue(response.getStatusMessage().contains("OK"));
		//getting actual body(response payload)
		//parsing json object
		JSONArray json = new JSONArray(response.getPayload());
		System.out.println(json.toString());
		
		System.out.println("This is from get method : id::"+((JSONObject) (json.get(0))).get("id"));
		assertNotNull(json.get(0));
	}
	
	@Test
	public void postSubjectsTest(){
		
		RestResponse response = api.post("/subjects?authentication=false");
		//getting important header info from response
		//assert for status code
		assertEquals(response.getStatusCode(),201);
		//assert for status message
		assertTrue(response.getStatusMessage().contains("Created"));
		//getting actual body(response payload)
		//parsing json object
		JSONObject json = new JSONObject(response.getPayload());
		System.out.println(json.toString());
		
		System.out.println("This is from post method : id::"+json.get("id"));
		assertNotNull(json.get("id"));
	}
	
	@Test(dependsOnMethods="postSubjectsTest")
	public void updateSubjectsTest(){
		
		RestResponse response = api.put("/subjects","/71?authentication=false");
		//getting important header info from response
		//assert for status code
		assertEquals(response.getStatusCode(),200);
		//assert for status message
		assertTrue(response.getStatusMessage().contains("OK"));
		//getting actual body(response payload)
		//parsing json object
		JSONObject json = new JSONObject(response.getPayload());
		System.out.println(json.toString());
		
		System.out.println("This is from update method : id::"+json.get("id"));
		assertNotNull(json.get("id"));
	}
	
	@Test(dependsOnMethods="updateSubjectsTest")
	public void deleteSubjectsTest(){
		
		RestResponse response = api.delete("/subjects","/71?authentication=false");
		//getting important header info from response
		//assert for status code
		assertEquals(response.getStatusCode(),204);
		
	}

}
