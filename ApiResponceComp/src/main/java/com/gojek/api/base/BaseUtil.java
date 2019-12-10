package com.gojek.api.base;

import java.io.File;
import java.io.IOException;

import java.util.Iterator;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.JSONObject;


public class BaseUtil {
	
	RestClient restClient;
	CloseableHttpResponse closebaleHttpResponse;
	List<String> urlsFile1;
	List<String> urlsFile2;
	long flag;
	Sheet sheet;

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
	
	public Iterator<Row> readRowsFromSheet(String fileLocation) throws EncryptedDocumentException, InvalidFormatException, IOException{
		File urlFile = new File(fileLocation);
		Workbook workbook = WorkbookFactory.create(urlFile);
		sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		return rowIterator;
	}
	
	public String readURL(Iterator<Row> linesOfFile) {
		DataFormatter dataFormatter = new DataFormatter();
		String urlF1=null;
		Row rowF1 = linesOfFile.next();
		Iterator<Cell> cellIterator1 = rowF1.cellIterator();				
		while (cellIterator1.hasNext()) {
            Cell cell = cellIterator1.next();
            urlF1 = dataFormatter.formatCellValue(cell);
		}	
		return urlF1;
	}
	
	public Object[][] setUrlsIn2DArray(String filePath1, String filePath2) throws IOException {
		String testData[][] = null;
		int i=0;
		int size =0;
		try {
			Iterator<Row> linesOfFile1 = readRowsFromSheet(filePath1);
			int totalURLsF1 = sheet.getLastRowNum();
			Iterator<Row> linesOfFile2 = readRowsFromSheet(filePath2);
			int totalURLsF2 = sheet.getLastRowNum();
			Iterator<Row> setOfUrls;
			if(totalURLsF1<=totalURLsF2) {
				size = totalURLsF1;
				setOfUrls = linesOfFile1;
			}else {
				size = totalURLsF2;
				setOfUrls = linesOfFile2;
			}
			testData = new String[size][2];			
			while (setOfUrls.hasNext()) {								
				testData[i][0] = readURL(linesOfFile1);
				testData[i][1] = readURL(linesOfFile2);;
				i++;
			}
		} catch (Exception e) {
		}
		return testData;
	}

	
}
