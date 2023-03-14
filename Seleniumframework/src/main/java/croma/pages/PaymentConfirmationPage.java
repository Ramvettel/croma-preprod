package croma.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import croma.base.BaseClass;

public class PaymentConfirmationPage extends BaseClass {
	
	@FindBy (css = "button.success")
	WebElement successBtn;
	
	public PaymentConfirmationPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	
	public void paymentsucess() {
		waitforelementtoclickable(successBtn);
		successBtn.click();
		logger.debug("Clicked on Success Button and confirmed payment successfull");
	}

}
