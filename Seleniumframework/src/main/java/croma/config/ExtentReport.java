package croma.config;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

	public static ExtentReports getReportObject() {
		
		String File = System.getProperty(("user.dir")+"//reports//index.html");
		
		ExtentSparkReporter report = new ExtentSparkReporter(File);
		
		report.config().setReportName("Web Automation Results");
		report.config().setDocumentTitle("Test Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Tested By", "Raja");
		return extent;
		
		
	}
}
