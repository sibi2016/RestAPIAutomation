package com.wbl.RestAPIAutomation.helper;

import org.apache.http.Header;

public class RestResponse {
	
	private int statuscode;
	private String statusMessage;
	private String payload;
	private Header[] headers;
	
	public int getStatusCode(){
		return statuscode;
	}
	public void setStatusCode(int statuscode){
		this.statuscode = statuscode;
	}
	
	public String getStatusMessage(){
		return statusMessage;
	}
	
	public void setStatusMessage(String statusMessage){
		this.statusMessage = statusMessage;
	}

	public String getPayload(){
		return payload;
	}
	
	public void setPayload(String payload){
		this.payload = payload;
	}
	
	public Header[] getHeaders(){
		return headers;
	}
	
	public void setHeaders(Header[] headers){
		this.headers = headers;
	}
	
	
}
