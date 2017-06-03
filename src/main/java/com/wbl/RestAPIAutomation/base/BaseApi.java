package com.wbl.RestAPIAutomation.base;

/*import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;*/

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
//import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.testng.annotations.Test;
import org.apache.http.message.BasicNameValuePair;

import com.wbl.RestAPIAutomation.helper.ConfigUtils;
import com.wbl.RestAPIAutomation.helper.RestResponse;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

public class BaseApi {
	
	private String url;
	RestResponse restResponse;
	HttpClient httpClient;
	OAuthConsumer consumer;
	
	public BaseApi(String url){
		this.url = url;
		httpClient = HttpClientBuilder.create().build();
		setAuthentication();
	}
	
	public void setAuthentication(){
		Properties prop = ConfigUtils.loadProperties("config.properties");
		consumer = new CommonsHttpOAuthConsumer(prop.getProperty("consumerKey"), prop.getProperty("consumerSecret"));
		consumer.setTokenWithSecret(prop.getProperty("accessToken"), prop.getProperty("accessSecret"));
	}
	
	@SuppressWarnings("deprecation")
	public RestResponse get(String resource){
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		//create required httpmethod object
		HttpGet get = new HttpGet(url+resource);
		restResponse = new RestResponse();
		
		try {
			//sends request to API and get response(header and payload)
			HttpResponse response = httpClient.execute(get);
			restResponse.setStatusCode(response.getStatusLine().getStatusCode());
			restResponse.setHeaders(response.getAllHeaders());
			restResponse.setStatusMessage(response.getStatusLine().toString());
			restResponse.setPayload(IOUtils.toString(response.getEntity().getContent()));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return restResponse;
	}
	
	@SuppressWarnings("deprecation")
	public RestResponse post(String resource){
		
		
		//create required httpmethod object
		HttpPost post = new HttpPost(url+resource);
		restResponse = new RestResponse();
		
		try {
			post.setHeader("Content-Type","application/json");
			consumer.sign(post);
			//setting body for post way 2
			/*List<NameValuePair> entityList = new ArrayList <NameValuePair>();
			entityList.add(new BasicNameValuePair("lang","en"));
			HttpEntity entity = new UrlEncodedFormEntity(entityList);
			post.setEntity(entity);*/
			//HttpEntity entity = new StringEntity(createRequestPayload());
			//post.setEntity(entity);
			
			HttpResponse response = httpClient.execute(post);
			restResponse.setStatusCode(response.getStatusLine().getStatusCode());
			restResponse.setHeaders(response.getAllHeaders());
			restResponse.setStatusMessage(response.getStatusLine().toString());
			restResponse.setPayload(IOUtils.toString(response.getEntity().getContent()));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		  catch (OAuthMessageSignerException e) {
			e.printStackTrace();
		} catch (OAuthExpectationFailedException e) {
			e.printStackTrace();
		} catch (OAuthCommunicationException e) {
			e.printStackTrace();
		}
		return restResponse;
	}
	
	@SuppressWarnings("deprecation")
	public RestResponse put(String resource,String requestParam){
		
		
		//create required httpmethod object
		HttpPut put = new HttpPut(url+resource+"/"+requestParam);
		restResponse = new RestResponse();
		
		try {
			put.setHeader("Content-Type","application/json");
			HttpEntity entity = new StringEntity(createRequestPayload());
			put.setEntity(entity);
			
			HttpResponse response = httpClient.execute(put);
			restResponse.setStatusCode(response.getStatusLine().getStatusCode());
			restResponse.setHeaders(response.getAllHeaders());
			restResponse.setStatusMessage(response.getStatusLine().toString());
			restResponse.setPayload(IOUtils.toString(response.getEntity().getContent()));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return restResponse;
	}
	
	
	public RestResponse delete(String resource,String requestparam){
		
		
		//create required httpmethod object
		HttpDelete delete = new HttpDelete(url+resource+"/"+requestparam);
		restResponse = new RestResponse();
		
		try {
								
			HttpResponse response = httpClient.execute(delete);
			restResponse.setStatusCode(response.getStatusLine().getStatusCode());
			restResponse.setHeaders(response.getAllHeaders());
			restResponse.setStatusMessage(response.getStatusLine().toString());
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return restResponse;
	}
	
	private String createRequestPayload(){
		String reqPayload="{ \"name\": \"Appium1\",\"icon_class\": \"ts-seleniumwebdriver\",\"description\": \"MobileAutomation\"}";
		return reqPayload;
		
		//setting body for post - way1
		/*
		 * JSONObject json = new JSONObject();
		 * json.put("name", "Appium1");
		 * json.put("icon_class", "ts-seleniumwebdriver");
		 * json.put("description", "MobileAutomation");
		 * return json.toString();
		 */
	}
	
}
