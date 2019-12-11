package com.gojek.api.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.gojek.api.base.BaseUtil;
import com.gojek.api.client.RestClient;

public class GetAPITest extends BaseUtil { 
	
	BaseUtil baseUtil = new BaseUtil();
	RestClient restClient ;
	
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	

	@BeforeSuite
	public void setup(){
		htmlReporter = new ExtentHtmlReporter("extent.html");
	    extent = new ExtentReports();
	    extent.attachReporter(htmlReporter);
	}
	    
	@DataProvider(name="data-provider")
	public Object[][] userFormData() throws Exception{
		String fileName1 = "File1.xlsx";
		String fileName2 = "File2.xlsx";
		Object[][] data = setUrlsIn2DArray(fileName1, fileName2);
		return data;
	}
	    
	@Test(dataProvider="data-provider")
	public void getURLs(String url1, String url2) throws ClientProtocolException, IOException {
		
		ExtentTest test = extent.createTest("File Compare", "Compare the JSON Response");
		
		if(url1.equals("") || url2.equals("")) {
			test.fail("One URL is missing. So " + url1 + "   not equals   " + url2);
			Assert.assertTrue(false);
		}else {
			boolean isEqual = baseUtil.compareJSONResponce(url1,url2);
		    if(isEqual) {
				test.log(Status.INFO, url1 + "   equals   " + url2);
		    }
			else {
				test.log(Status.INFO, url1 + "   not equals   " + url2);
			}
		   	Assert.assertTrue(true);
		}	    
	}
	
	@AfterSuite
	public void tearDown() {
		extent.flush();
	}
}
