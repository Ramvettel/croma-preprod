package croma.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import croma.base.BaseClass;
import croma.util.TestUtils;

public class ShippingPage extends BaseClass{

	@FindBy(xpath = "(//button[text()='Add Address'])[1]")
	WebElement addaddress;
	
	@FindBy (xpath = "//input[contains(@placeholder,'street')]")
	WebElement inputpincode;
	
	@FindBy (name ="addAddressFullName")
	WebElement fullname;
	
	@FindBy (name ="addAddressMobileNo")
	WebElement mobileno;
	
	@FindBy (name ="addAddressNickName")
	WebElement nickname;
	
	@FindBy (name ="addAddressLine1")
	WebElement addressline1;
	
	@FindBy (name ="addAddressLine2")
	WebElement addressline2;
	
	@FindBy (xpath = "//button[text()='Save Address']")
	WebElement saveaddBtn;
	
	@FindBy (xpath = "//span[contains(@class,'terms-link')]")
	WebElement resheduledelivery;
	
	@FindBy (xpath = "//div[@class='slt-bx']/div[@class='dt-txt']")
	List <WebElement> date;
	
	@FindBy (xpath = "//button[text()='Continue']")
	WebElement continueBtn;
	
	@FindBy (id = "checkout-payment-button")
	WebElement proceedpaymentBtn;
	
	@FindBy (css = "button.skipButton")
	WebElement skipBtn;
	
	public ShippingPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public void addaddressdetails(String pincode,String name,String phoneno,String nick,String addressLineno1,String addressLineno2) throws AWTException, InterruptedException {
		
		waitforelementtoclickable(addaddress);
		addaddress.click();
		
		inputpincode.sendKeys(pincode);
		inputpincode.clear();
		
		Robot r = new Robot();
		Thread.sleep(2000);
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyPress(KeyEvent.VK_ENTER);
		
		fullname.sendKeys(name);
		mobileno.sendKeys(phoneno);
		nickname.sendKeys(nick);
		addressline1.sendKeys(addressLineno1);
		addressline2.sendKeys(addressLineno2);
		saveaddBtn.click();
	}
	
	public void resheduledeliverydate(String Date) {	
		waitforelementtoclickable(resheduledelivery);
		resheduledelivery.click();
		logger.debug("Clicked on Reshedule Delivery");
		
		for(int i=0; i<date.size(); i++) {
			if((date.get(i).getText()).equals(Date)) {
				date.get(i).click();
				logger.debug("Date got select");
				break;
			}
		}
		continueBtn.click();
		logger.debug("Clicked on continue button to confirmed delivery date and time");
	}
	public void proceedtopayment() {
		proceedpaymentBtn.click();
		logger.debug("Clicked on Proceed-to-payment button and navigate to Payment Page");
		waitforelementtoclickable(skipBtn);
		if(skipBtn.isDisplayed()) {
			skipBtn.click();
			logger.debug("Clicked on skip to close popup message in Payment Page");
		}
		
	}
}
