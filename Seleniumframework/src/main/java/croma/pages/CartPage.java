package croma.pages;

import java.util.List;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import croma.base.BaseClass;

public class CartPage extends BaseClass{
	
	@FindBy (how=How.CSS, using = "h3 a")
	List<WebElement> noofproduct;

	@FindBy (xpath="//button[text()='Remove']")
	WebElement removeBtn;
	
	@FindBy (xpath="//button[text()='Yes']")
	WebElement yesBtn;
	
	@FindBy (css = "input#search")
	WebElement searchbox;
	
	@FindBy (xpath = "//*[text()='CHECKOUT']")
	WebElement checkoutBtn;
	
	@FindBy (css = "span.main-text")
	WebElement deliverytype;
	
	@FindBy (xpath = "//div[contains(@class,'cross-right')]")
	WebElement closeBtn;
	
	@FindBy (xpath = "//span[@class='main-text']")
	List<WebElement> deliveryoption;
	
	@FindBy (xpath = "//div[text()='Product removed from cart']")
	WebElement productremovemsg;
	
	
	public CartPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public void removeexistingitemfromcart() {
		
		if((noofproduct.size())>0) {
			waitforelementtoclickable(removeBtn);
			int items=noofproduct.size();
			for(int i=0; i<items; i++) {
		waitforelementtoclickable(removeBtn);
		removeBtn.click();
		waitforelementtoclickable(yesBtn);
		yesBtn.click();
		visibilityofelement(productremovemsg);
		Assert.assertEquals(productremovemsg.getText(), "Product removed from cart");
		}
		}
		logger.debug("Removed all items from the cart");
		}
	
	public void searchbyproductcode(String productID) {
		
		searchbox.sendKeys(productID);
		logger.debug("Enter "+ productID +" in Searchbox");
		searchbox.sendKeys(Keys.ENTER);
		logger.debug("Clicked on search button and navigate to Search Listing Page");
		
	}
	
	public void checkoutproduct() throws InterruptedException {
		if(deliveryoption.size()>1) {
			deliveryoption.get(1).click();
			logger.debug("Change Express delivery option to Standard delivery option");
		}
		waitforelementtoclickable(checkoutBtn);
		checkoutBtn.click();
		logger.debug("Clicked on Checkout Button and navigate to Shipping Page");
		waitforelementtoclickable(closeBtn);
		if(closeBtn.isDisplayed()) {
			Assert.assertTrue(closeBtn.isDisplayed());
			closeBtn.click();
		}
	}
}
