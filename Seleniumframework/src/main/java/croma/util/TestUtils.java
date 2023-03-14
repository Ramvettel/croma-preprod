package croma.util;


import java.io.FileInputStream;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;

import croma.OrderCreation_TestCase;
import croma.base.BaseClass;


public class TestUtils extends OrderCreation_TestCase{
	
	static WebDriver driver;
	
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICITY_WAIT = 30;
	public static long WAIT_FOR_ELEMENT_TO_CLICKABLE = 90;
	public static long WAIT_FOR_ELEMENT_TO_APPEAR = 100;
	public static long WAIT_FOR_ELEMENT_TO_DISAPPEAR = 2;
	public static long NANO_SECOND = 5;
	public TestUtils(WebDriver driver) {
		this.driver = driver;
	}
	@DataProvider(name = "Testdata")
	public static void ExcelData() throws IOException 
	{
		
		FileInputStream fis = new FileInputStream("C:\\Users\\rajam\\OneDrive\\Desktop\\Nagpur for 23.01.2023.xlsx");
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		
		int rowcount = sheet.getLastRowNum();
		
		for(int i=0; i<rowcount; i++) 
		{
			XSSFRow currentrow = sheet.getRow(i);
			
			String pincode = currentrow.getCell(1).getStringCellValue();
			
			String productID = currentrow.getCell(2).getStringCellValue();
			
			String addressLine1 = currentrow.getCell(3).getStringCellValue();
			
			String addressLine2 = currentrow.getCell(4).getStringCellValue();
			
			
		}
		
	}
}
