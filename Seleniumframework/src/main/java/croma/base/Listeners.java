package croma.base;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import croma.config.ExtentReport;

public class Listeners extends BaseClass implements ITestListener {
	
	ExtentTest test;
	ExtentReports extent = ExtentReport.getReportObject();
	@Override
	public void onTestStart(ITestResult result) {
		
		test = extent.createTest(result.getMethod().getMethodName());
	}
	
	public void onTestSucess(ITestResult result) {
		test.log(Status.PASS, "Test Pass");

	}
	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
		String filepath = null;
		try {
			filepath = getScreenShot(result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromBase64String(filepath, result.getMethod().getMethodName());

	}
	@Override
	public void onTestSkipped(ITestResult result) {

	}
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
	
}
