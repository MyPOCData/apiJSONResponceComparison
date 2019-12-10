package com.gojek.api.client;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gojek.api.base.BaseUtil;

public class GetAPITest extends BaseUtil { 

	String pathFile1 = "//Users//b0097042//git//apiJSONResponceComparison///ApiResponceComp//Files//File1.xlsx";
	String pathFile2 = "//Users//b0097042//git//apiJSONResponceComparison///ApiResponceComp//Files//File2.xlsx";
	BaseUtil baseUtil = new BaseUtil();
	    
	@DataProvider(name="data-provider")
	public Object[][] userFormData() throws Exception{
		Object[][] data = setUrlsIn2DArray(pathFile1, pathFile2);
		return data;
	}
	    
	@Test(dataProvider="data-provider")
	public void getURLs(String url1, String url2) throws ClientProtocolException, IOException {
	    boolean isEqual = baseUtil.compareJSONResponce(url1,url2);
	    if(isEqual)
			System.out.println(url1 + " equals " + url2);
		else
			System.out.println(url1 + " not equals " + url2);
	}
}
