package com.wbl.RestAPIAutomation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wbl.RestAPIAutomation.base.BaseAPITest;
import com.wbl.RestAPIAutomation.base.BaseApi;
import com.wbl.RestAPIAutomation.helper.RestResponse;

public class TwitterTest extends BaseAPITest{
	
BaseApi api;
	
	@BeforeClass
	public void beforeClass(){
		api = new BaseApi(endpoint);
	}
	
	@Test
	public void postAccountTestingTest(){
		
		RestResponse response = api.post("/account/settings.json?lang=en");
		//getting important header info from response
		//assert for status code
		assertEquals(response.getStatusCode(),200);
		//assert for status message
		assertTrue(response.getStatusMessage().contains("OK"));
		//getting actual body(response payload)
		//parsing json object
		JSONObject json = new JSONObject(response.getPayload());
		System.out.println(json.toString());
		
	}

}
