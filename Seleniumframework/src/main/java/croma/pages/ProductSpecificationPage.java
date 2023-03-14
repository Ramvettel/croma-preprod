package croma.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import croma.base.BaseClass;


public class ProductSpecificationPage extends BaseClass{

	@FindBy (xpath = "//*[text()='buy now']")
	WebElement buynowBtn;
	
	public ProductSpecificationPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}

	public void productbuy() {
		scrolldown();
		waitforelementtoclickable(buynowBtn);
		buynowBtn.click();
		logger.debug("Clicked on Buy Now button and navigate to Cart Page");
	}
}
