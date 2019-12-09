package com.gojek.api.client;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.gojek.api.base.BaseUtil;
import com.gojek.api.base.RestClient;

public class GetAPITest { //extends BaseUtil
	
	BaseUtil baseUtil;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closebaleHttpResponse;
	
	
	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException{
//		baseUtil = new BaseUtil();
//		serviceUrl = prop.getProperty("URL");
//		apiUrl = prop.getProperty("serviceURL");
		//https://reqres.in/api/users
		
		url = "https://reqres.in/api/users/3";		
	}
		
	
	@Test(priority=1)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException{
		restClient = new RestClient();
		closebaleHttpResponse = restClient.get(url);
		
		//a. Status Code:
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code--->"+ statusCode);
		
		Assert.assertEquals(statusCode, 200, "Status code is not 200");

		//b. Json String:
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API---> "+ responseJson);
		
	}
}
