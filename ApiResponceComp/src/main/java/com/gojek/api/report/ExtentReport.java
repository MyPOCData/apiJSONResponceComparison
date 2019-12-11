package com.gojek.api.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReport {
	
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	
	public ExtentReports initExtendReport(String reportName) {
        htmlReporter = new ExtentHtmlReporter(reportName);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
		return extent;
	}
	
	public void flushReport() {
		extent.flush();
	}
}
