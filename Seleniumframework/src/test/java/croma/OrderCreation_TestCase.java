package croma;



import java.awt.AWTException;

import org.testng.annotations.Test;

import croma.base.BaseClass;
import croma.pages.CartPage;
import croma.pages.HomePage;
import croma.pages.OrderConfirmationPage;
import croma.pages.PaymentConfirmationPage;
import croma.pages.PaymentPage;
import croma.pages.ProductSpecificationPage;
import croma.pages.SearchListingPage;
import croma.pages.ShippingPage;
import croma.util.TestUtils;


public class OrderCreation_TestCase extends BaseClass {
	
	@Test(dataProvider = "Testdata" ,dataProviderClass = TestUtils.class)
	public void OrderCreationTest() throws AWTException, InterruptedException {
		
		implicitywait();
		HomePage homepage = new HomePage(driver);
		homepage.login(prop.getProperty("username"), prop.getProperty("OTP"));
		Thread.sleep(1000);
		homepage.changepincode();
		homepage.checknoofitemsincart();
		
		CartPage cartpage = new CartPage(driver);
		cartpage.removeexistingitemfromcart();
		cartpage.searchbyproductcode("218110");
		
		SearchListingPage slp = new SearchListingPage(driver);
		slp.clickonproduct();
		
		ProductSpecificationPage psp = new ProductSpecificationPage(driver);
		psp.productbuy();
		
		cartpage.checkoutproduct();
		
		ShippingPage sp = new ShippingPage(driver);
		sp.addaddressdetails("600042", "RamKumar", "8082764348", "Ram", "no-4,Latha Mageskar Street", "B R Ambedkar Nagar");
		sp.resheduledeliverydate("2");
		sp.proceedtopayment();
		
		PaymentPage pp = new PaymentPage(driver);
		pp.makepayment("Axis Bank");
		
		PaymentConfirmationPage pcp = new PaymentConfirmationPage(driver);
		pcp.paymentsucess();
		
		OrderConfirmationPage ocp = new OrderConfirmationPage(driver);
		ocp.getOrderID();
	
		
	}
	
	
	
}
