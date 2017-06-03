package com.wbl.RestAPIAutomation.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.testng.annotations.BeforeSuite;

import com.wbl.RestAPIAutomation.helper.ConfigUtils;

import static io.restassured.RestAssured.*;
import io.restassured.specification.RequestSpecification;

public class BaseRestAssuredTest {
	
	protected RequestSpecification rs;
	HashMap<String,String> hm = null;
	
	@BeforeSuite
	public void beforeSuite(){
		Properties prop = ConfigUtils.loadProperties("config.properties");
		
		baseURI = prop.getProperty("TSurl");
		basePath = prop.getProperty("subjectsPath");
		rs=given().when();
		
		if(prop.getProperty("auth").equals("true")){
			rs.auth().oauth(prop.getProperty("consumerKey"), prop.getProperty("consumerSecret"), 
					prop.getProperty("accessToken"), prop.getProperty("accessSecret"));
		}
		if(prop.getProperty("headers").equals("true")){
			
			rs.headers(createHearderMap());
			}	
		}
	
		private HashMap createHearderMap(){
			Properties prop = ConfigUtils.loadProperties("headers.properties");
			hm = new HashMap<String,String>();
			for(Map.Entry entry:prop.entrySet()){
				hm.put((String)entry.getKey(), (String)entry.getValue());
			}
			return hm;
			
		}
}
