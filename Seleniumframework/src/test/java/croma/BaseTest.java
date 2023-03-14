package croma;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static void main(String[] args) throws InterruptedException, AWTException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://preprod4-pwa.croma.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebElement myaccount = driver.findElement(By.xpath("//*[contains(@class,'text-bold')]"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(myaccount));
		myaccount.click();
		driver.findElement(By.xpath("//input[contains(@placeholder,'Email')]")).sendKeys("8082764348");
		driver.findElement(By.xpath("//*[text()='Continue']")).click();
		driver.findElement(By.id("partitioned")).sendKeys("2142");
		driver.findElement(By.xpath("//*[text()='Submit OTP']")).click();
		WebElement Loginmsg = driver.findElement(By.xpath("//div[text()='Logged In Successfully']"));
		Assert.assertEquals(Loginmsg.getText(),"Logged In Successfully");
		Thread.sleep(3000);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='delivery-location delivery-location-maindiv']")));
		driver.findElement(By.xpath("//div[@class='delivery-location delivery-location-maindiv']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@placeholder='Enter Pincode']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Enter Pincode']")).sendKeys("400063");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue']")));
		driver.findElement(By.xpath("//button[text()='Continue']")).click();

		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.MuiAlert-message")));
		String cartcount = driver.findElement(By.xpath("//span[contains(@class,'cart-count')]")).getText();
		int ccount = Integer.parseInt(cartcount);
		System.out.println(ccount);
		Thread.sleep(1000);
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(60));
		if (ccount>0) {
			wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/cart']")));
			driver.findElement(By.xpath("//a[@href='/cart']")).click();

			for (int j=1; j<=ccount; j++) {
				wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Remove'])[1]")));
				driver.findElement(By.xpath("(//*[text()='Remove'])[1]")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//button[text()='Yes']")).click();
			}
		}
		WebElement searchbox = driver.findElement(By.cssSelector("input#search"));
		searchbox.sendKeys("221573");
		searchbox.sendKeys(Keys.ENTER);
		System.out.println(driver.getTitle());

		driver.findElement(By.cssSelector("h3 a")).click();

		Set<String> tabs = driver.getWindowHandles();

		Iterator<String> it = tabs.iterator();
		String parentID = it.next();
		String childID = it.next();
		driver.switchTo().window(childID);
		System.out.println(driver.getTitle());

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)", "");
		driver.findElement(By.xpath("//*[text()='buy now']")).click();
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.main-text")));
		String deliverytype = driver.findElement(By.cssSelector("span.main-text")).getText();
		System.out.println(deliverytype);
		wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='CHECKOUT']")));
		driver.findElement(By.xpath("//*[text()='CHECKOUT']")).click();
		Thread.sleep(2000);
		if((driver.findElement(By.xpath("//div[contains(@class,'cross-right')]"))).isDisplayed()) {
		driver.findElement(By.xpath("//div[contains(@class,'cross-right')]")).click();
		}
		Thread.sleep(15000);
		WebElement addaddress = driver.findElement(By.xpath("(//button[text()='Add Address'])[1]"));
		wait2.until(ExpectedConditions.elementToBeClickable(addaddress));
		addaddress.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[contains(@placeholder,'street')]")).sendKeys("400063");
		driver.findElement(By.xpath("//input[contains(@placeholder,'street')]")).clear();
		//driver.findElement(By.xpath("//input[contains(@placeholder,'street')]")).sendKeys("400063");
		
		Robot r = new Robot();
		Thread.sleep(2000);
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyPress(KeyEvent.VK_ENTER);
		
		driver.findElement(By.name("addAddressFullName")).sendKeys("Ramkumar");
		driver.findElement(By.name("addAddressMobileNo")).sendKeys("8082764348");
		driver.findElement(By.name("addAddressNickName")).sendKeys("Ram");
		driver.findElement(By.name("addAddressLine1")).sendKeys("B-9  NIT qtr Kapil Nagar");
		driver.findElement(By.name("addAddressLine2")).sendKeys("nari road  NAGPUR");
		driver.findElement(By.xpath("//button[text()='Save Address']")).click();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//span[contains(@class,'terms-link')]")).click();
		Thread.sleep(1000);
		
		List<WebElement> date = driver.findElements(By.xpath("//div[@class='slt-bx']/div[@class='dt-txt']"));
		for(int i=0; i<date.size(); i++) {
			if((date.get(i).getText()).equals("22")) {
				date.get(i).click();
				break;
			}
		}
		driver.findElement(By.xpath("//button[text()='Continue']")).click();
		
		driver.findElement(By.id("checkout-payment-button")).click();
		
		WebElement skip = driver.findElement(By.cssSelector("button.skipButton"));
		wait2.until(ExpectedConditions.visibilityOf(skip));
		if(skip.isDisplayed())
		{
			skip.click();
		}
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#juspay_iframe")));
		driver.findElement(By.xpath("//*[text()='Netbanking']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("Axis bank");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='Axis Bank']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//*[text()='Place Order & Pay'])[2]")).click();
		driver.switchTo().defaultContent();
		WebElement SuccessBtn = driver.findElement(By.cssSelector("button.success"));
		wait2.until(ExpectedConditions.elementToBeClickable(SuccessBtn));
		SuccessBtn.click();
		
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("dd.order-id-desc")));
		String OrderID = driver.findElement(By.cssSelector("dd.order-id-desc")).getText();
		System.out.println(OrderID);
		
		WebElement accountBttn = driver.findElement(By.cssSelector("a.user-link"));
		wait2.until(ExpectedConditions.elementToBeClickable(accountBttn));
		accountBttn.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//a[contains(@href,'/address-book')])[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[text()='DELETE'])[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='Yes']")).click();
		Thread.sleep(1000);
		accountBttn.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//a[contains(@href,'/orders')])[2]")).click();
		Thread.sleep(25000);
		
		List<WebElement> OrderIDs = driver.findElements(By.xpath("//dd[contains(@class,'order-id-')]"));
		for(int i=0; i<OrderIDs.size(); i++) {
			if((OrderIDs.get(i).getText()).equals(OrderID)) {
				System.out.println("OrderID is Matched");
				break;
			}
		}
		//driver.findElement(By.xpath("//input[contains(@placeholder,'street')]")).sendKeys(Keys.ARROW_DOWN);
		//driver.findElement(By.xpath("//input[contains(@placeholder,'street')]")).sendKeys(Keys.ENTER);
		
		//driver.findElement(By.xpath("(//div[@class='pac-item'])[1]")).click();
		//(//span[@class="pac-matched"]/parent::span)[1]
				//"(//span[contains(text(),'400063')])[1]"
		//List <WebElement> sugg = driver.findElements(By.xpath("//div[@class='pac-item']"));
		//wait2.until(ExpectedConditions.visibilityOfAllElements(sugg));
		//List <WebElement> sugg = driver.findElements(By.cssSelector("span.pac-matched"));
		//for(WebElement e:sugg)
		//
			System.out.println("Success");
		//}
		
		//span[contains(text(),'Mumbai')]
		public void github(){
	system.out.println("add github")
	}


	}
}
