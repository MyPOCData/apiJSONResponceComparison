package com.gojek.api.base;

import java.io.FileInputStream;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


public class BaseUtil {
	
	RestClient restClient;
	CloseableHttpResponse closebaleHttpResponse;

	// Pass file name and get no. of lines it that file
	public long lineCount(String fileName) {
		Path path = Paths.get(fileName);
		long count=0;
		try {
			count = Files.lines(path).count();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	public List<String> readFile(String fileName) throws IOException {		
		FileInputStream inputStream = null;
		Scanner sc = null;
		List<String> urls = new ArrayList<String>();						
		try {
		    inputStream = new FileInputStream(fileName);
		    sc = new Scanner(inputStream, "UTF-8");
		    while (sc.hasNextLine()) {
		        String line = sc.nextLine();
		        urls.add(line);
//		        System.out.println(line);
		    }
		    // note that Scanner suppresses exceptions
//		    if (sc.ioException() != null) {
//		        throw sc.ioException();
//		    }
		} finally {
		    if (inputStream != null) {
		        inputStream.close();
		    }
		    if (sc != null) {
		        sc.close();
		    }
		}
		return urls;
	}

	
	public JSONObject getAPIResponce(String url) throws ClientProtocolException, IOException{
		restClient = new RestClient();
		closebaleHttpResponse = restClient.get(url);
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");		
		JSONObject responseJson = new JSONObject(responseString);	
		return responseJson;
	}
	
	public boolean compareJSONResponce(String url1, String url2) throws ClientProtocolException, IOException {
		boolean isEqual = false;
		JSONObject jsonResponceFile1 = getAPIResponce(url1);
		JSONObject jsonResponceFile2 = getAPIResponce(url2);
		isEqual = jsonResponceFile1.similar(jsonResponceFile2);
		return isEqual;
	}
	
	public final static void main(String[] args) throws IOException {
		String pathFile1 = "//Users//b0097042//eclipse-workspace//ApiResponceComp//Files//File1.txt";
		String pathFile2 = "//Users//b0097042//eclipse-workspace//ApiResponceComp//Files//File2.txt";
		BaseUtil baseUtil = new BaseUtil();
		
		long linesCountFile1 = baseUtil.lineCount(pathFile1);
		List<String> urlsFile1 = baseUtil.readFile(pathFile1);	
		long linesCountFile2 = baseUtil.lineCount(pathFile2);
		List<String> urlsFile2 = baseUtil.readFile(pathFile2);
		
		if(linesCountFile1<=linesCountFile2) {
			for(int i=0; i<urlsFile1.size(); i++) {
				boolean isEqual = baseUtil.compareJSONResponce(urlsFile1.get(i), urlsFile2.get(i));
				if(isEqual)
					System.out.println(urlsFile1.get(i) + " equals " + urlsFile2.get(i));
				else
					System.out.println(urlsFile1.get(i) + " not equals " + urlsFile2.get(i));
			}
		}else {
			for(int i=0; i<urlsFile2.size(); i++) {
				boolean isEqual = baseUtil.compareJSONResponce(urlsFile1.get(i), urlsFile2.get(i));
				if(isEqual)
					System.out.println(urlsFile1.get(i) + " equals " + urlsFile2.get(i));
				else
					System.out.println(urlsFile1.get(i) + " not equals " + urlsFile2.get(i));
			}
		}
    }
}
