package com.gojek.api.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gojek.api.base.BaseUtil;

public class GetAPITest extends BaseUtil { 
	
	BaseUtil baseUtil = new BaseUtil();
	    
	@DataProvider(name="data-provider")
//	@Parameters({"file1","file2"})
	public Object[][] userFormData() throws Exception{
		String fileName1 = "File1.xlsx";
		String fileName2 = "File2.xlsx";
		Object[][] data = setUrlsIn2DArray(fileName1, fileName2);
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
