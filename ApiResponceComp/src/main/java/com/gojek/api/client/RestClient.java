package com.gojek.api.client;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //http get request
		CloseableHttpResponse closebaleHttpResponse =  httpClient.execute(httpget); //hit the GET URL
		
		return closebaleHttpResponse;
			
	}
	
//	public JSONObject getAPIResponce(String url) throws ClientProtocolException, IOException{
////		restClient = new RestClient();
//		CloseableHttpResponse closebaleHttpResponse = get(url);
//		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
//		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");		
//		JSONObject responseJson = new JSONObject(responseString);	
//		return responseJson;
//	}
	

}
