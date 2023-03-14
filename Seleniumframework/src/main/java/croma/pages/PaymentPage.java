package croma.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import croma.base.BaseClass;



public class PaymentPage extends BaseClass {

	@FindBy (css = "button.skipButton")
	WebElement skipBtn;
	
	@FindBy (xpath = "//*[text()='Netbanking']")
	WebElement netbanking;
	
	@FindBy (xpath = "//input[@placeholder='Search']")
	WebElement search;
	
	@FindBy (xpath = "(//*[text()='Axis Bank'])[2]")
	WebElement axisbank;
	
	@FindBy (xpath = "(//*[text()='Place Order & Pay'])[2]")
	WebElement payBtn;
	
	@FindBy (css = "iframe#juspay_iframe")
	WebElement iframe;
	
	public PaymentPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public void makepayment(String Bankname) {
		driver.switchTo().frame(iframe);
		logger.debug("Switch to payment frame");
		waitforelementtoclickable(netbanking);
		netbanking.click();
		logger.debug("Clicked on netbanking option in Payment method");
		search.sendKeys(Bankname);
		logger.debug("Search for" + Bankname + " in serchbox");
		axisbank.click();
		logger.debug(Bankname + " is selected");
		waitforelementtoclickable(payBtn);
		payBtn.click();
		logger.debug("Clicked on Payment Button and navigate to Payment Confirmation Page");
	}
	
	
}
