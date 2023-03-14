package croma.pages;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import croma.base.BaseClass;

public class SearchListingPage extends BaseClass {
	
	@FindBy (css = "h3 a")
	WebElement productlink;
	
	public SearchListingPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public void clickonproduct() {
		
		waitforelementtoclickable(productlink);
		productlink.click();
		logger.debug("Clicked on product link and navigate to Product Specification Page");
		
		Set<String> tabs = driver.getWindowHandles();
		Iterator<String> it = tabs.iterator();
		String parentID = it.next();
		String childID = it.next();
		driver.switchTo().window(childID);
	}

}
