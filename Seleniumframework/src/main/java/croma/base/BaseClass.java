package croma.base;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;


import com.aventstack.extentreports.ExtentReports;


import croma.util.TestUtils;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	public static Properties prop;
	public static ExtentReports extent;
	public static Logger logger = (Logger) LogManager.getLogger(BaseClass.class);

	public BaseClass() {

		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\rajam\\eclipse-workspace\\Seleniumframework\\src\\main\\java\\croma\\config\\config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void initialization() {
		
		logger.debug("Web Automation Test Execution Started");
		
		String Browsername = prop.getProperty("browser");
		if (Browsername.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (Browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (Browsername.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (Browsername.equalsIgnoreCase("safari")) {
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
		}
		logger.debug("Chrome browser got launched");
		driver.manage().window().maximize();
		logger.debug("Browser got maximized");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtils.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtils.IMPLICITY_WAIT));

		driver.get(prop.getProperty("url"));
		logger.debug("Navigated to "+prop.getProperty("url"));
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
		logger.debug("Browser got closed");
	}

	public static void waitforelementtoappear(By findby) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.WAIT_FOR_ELEMENT_TO_APPEAR,TestUtils.NANO_SECOND));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}

	public static void scrolldown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)", "");
	}

	public static void waitforelementtoclickable(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.WAIT_FOR_ELEMENT_TO_CLICKABLE,TestUtils.NANO_SECOND));
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public static void visibilityofelement(WebElement ele) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.WAIT_FOR_ELEMENT_TO_APPEAR,TestUtils.NANO_SECOND));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public static void invisibilityofelement(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.WAIT_FOR_ELEMENT_TO_DISAPPEAR,TestUtils.NANO_SECOND));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public static void implicitywait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtils.IMPLICITY_WAIT,TestUtils.NANO_SECOND));
	}

	public static void waitforlistofelementtoclickable(List<WebElement> elements) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.WAIT_FOR_ELEMENT_TO_CLICKABLE,TestUtils.NANO_SECOND));
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	public String getScreenShot(String testcasename) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testcasename + ".png");
		FileUtils.copyFile(source, file);
		return (System.getProperty("user.dir") + "//reports//" + testcasename + ".png");

	}
	
	public void github(){
	system.out.println("add github")
	}

}
