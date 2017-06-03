package com.wbl.RestAPIAutomation.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtils {

	public static Properties loadProperties(String filename){
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(Constants.PATH+filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
}
