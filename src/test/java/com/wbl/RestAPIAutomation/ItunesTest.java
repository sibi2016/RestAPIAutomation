package com.wbl.RestAPIAutomation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wbl.RestAPIAutomation.base.BaseAPITest;
import com.wbl.RestAPIAutomation.base.BaseApi;
import com.wbl.RestAPIAutomation.helper.RestResponse;

public class ItunesTest extends BaseAPITest {

	BaseApi api;
	
	@BeforeClass
	public void beforeClass(){
		api = new BaseApi(endpoint);
	}
	
	@Test
	public void getItunesAPI(){
		RestResponse response = api.get("/search?term=jack+johnson&limit=25");
		//getting important header info from response
		//assert for status code
		assertEquals(response.getStatusCode(),200);
		//assert for status message
		assertTrue(response.getStatusMessage().contains("OK"));
		//getting actual body(response payload)
		//parsing json object
		JSONObject json = new JSONObject(response.getPayload());
		System.out.println(json.toString());
		//assert for key value in payload
		assertTrue(json.getInt("resultCount")>=25);
		JSONArray resultArray = json.getJSONArray("results");
		//assert for data count in payload
		assertTrue(resultArray.length()>0);
		for(int i=0;i<resultArray.length();i++){
			JSONObject result = resultArray.getJSONObject(i);
			//assert for key presence in payload
			assertTrue(result.has("wrapperType"));
			System.out.println(result.get("artistId"));
			System.out.println(result.get("trackName"));
			//assert for value of key is not null in payload
			assertNotNull(result.get("artistId"));
			assertNotNull(result.get("trackName"));
		}
		
		for(Header header:response.getHeaders()){
			System.out.println(header.getName()+"::"+header.getValue());
		}
	}
}
