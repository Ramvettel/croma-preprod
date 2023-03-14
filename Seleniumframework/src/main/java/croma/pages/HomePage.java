package croma.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import croma.base.BaseClass;




public class HomePage extends BaseClass{
	
	@FindBy (xpath = "//*[contains(@class,'text-bold')]")
	WebElement myaccount;
	
	@FindBy (xpath = "//input[contains(@placeholder,'Email')]")
	WebElement username;
	
	@FindBy (xpath = "//*[text()='Continue']")
	WebElement continueBtn;
	
	@FindBy (id = "partitioned")
	WebElement otp;
	
	@FindBy (xpath = "//*[text()='Submit OTP']")
	WebElement submitBtn;
	
	@FindBy (xpath = "//div[@class='delivery-location delivery-location-maindiv']")
	WebElement pincodeIcn;
	
	@FindBy (xpath = "//input[@placeholder='Enter Pincode']")
	WebElement pincodeInput;
	
	@FindBy (xpath = "//button[text()='Continue']")
	WebElement continueBtn2;
	
	By msg =By.cssSelector("div.MuiAlert-message");
	
	@FindBy (xpath = "//span[contains(@class,'cart-count')]")
	WebElement ccount;
	
	@FindBy (css = "div.cart-wrap")
	WebElement carticon;
	
	@FindBy (xpath = "//div[text()='Logged In Successfully']")
	WebElement loginmsg;
	
	@FindBy (xpath = "//div[text()='Pincode updated successfully']")
	WebElement pincodemsg;
	
	//div.cart-wrap
	//initializing page objects
	public HomePage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public void login(String usrn, String OTP) {
		
		waitforelementtoclickable(myaccount);
		myaccount.click();
		logger.debug("clicked on Myaccount icon");
		username.sendKeys(usrn);
		logger.debug("Enter phone number in username field");
		waitforelementtoclickable(continueBtn);
		continueBtn.click();
		logger.debug("clicked on continue button");
		otp.sendKeys(OTP);
		logger.debug("Enter OTP in required field");
		waitforelementtoclickable(submitBtn);
		submitBtn.click();
		logger.debug("clicked on Submit button");
		visibilityofelement(loginmsg);
		Assert.assertEquals(loginmsg.getText(), "Logged In Successfully");
	}	
	public void changepincode(String pincode) throws InterruptedException {
		waitforelementtoclickable(pincodeIcn);
		pincodeIcn.click();
		logger.debug("clicked on pincode icon");
		pincodeInput.clear();
		pincodeInput.sendKeys(pincode);
		logger.debug("Enter pincode in required field");
		waitforelementtoclickable(continueBtn2);
		continueBtn2.click();
		logger.debug("Clicked on continue button");
		visibilityofelement(pincodemsg);
		Assert.assertEquals(pincodemsg.getText(),"Pincode updated successfully");
	}
	public void checknoofitemsincart() {
		String noofitems = ccount.getText();
		int cartcount = Integer.parseInt(noofitems);
		logger.debug(cartcount + " Item present in cart before start the order creation");
		if(cartcount>0) {
			carticon.click();
			logger.debug("Clicked on cart icon and navigate to CartPage for remove " + cartcount +" item present in the cart");
	}	
	}
	
	
}
