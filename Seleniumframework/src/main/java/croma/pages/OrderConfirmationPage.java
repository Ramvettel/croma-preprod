package croma.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import croma.base.BaseClass;

public class OrderConfirmationPage extends BaseClass {

	@FindBy (css = "dd.order-id-desc")
	WebElement orderID;
	
	public OrderConfirmationPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	
	public void getOrderID() {
		
		String ProductorderID = orderID.getText();
		logger.debug("Get the generated product order ID "+ ProductorderID);
		System.out.println(orderID);
	}
	
}
