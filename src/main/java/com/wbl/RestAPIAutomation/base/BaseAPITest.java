package com.wbl.RestAPIAutomation.base;

import java.util.Properties;

import org.testng.annotations.BeforeSuite;

import com.wbl.RestAPIAutomation.helper.ConfigUtils;

public class BaseAPITest {

	protected String endpoint;
	
	@BeforeSuite
	public void beforSuite(){
		Properties prop = ConfigUtils.loadProperties("config.properties");
		endpoint = prop.getProperty("url");
	}
}
