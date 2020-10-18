package Steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import com.shop.pages.HomePage;

public class StepDefOnlineShop
{
	
	HomePage homepage;
	

	@Given("User Launches the Online Shop URL")
	public void user_Launch_browser() throws Exception {
		homepage=new HomePage();
	}

	@Then("User is navigated to home page")
	public void home_page() throws Exception {
		String title = homepage.verifyHomePage();
		assertEquals("STORE", title);
	}
	
	@And("User navigates through the product {string}") 
	public void product_navigate(String prod) throws Exception {
		
		boolean status = homepage.verifyProduct(prod);
		assertTrue(status);
	}
	
	@And("User Adds {string} in Cart") 
	public void user_adds(String laptop) throws Exception {
		homepage.addProduct(laptop);
	}
	
	@And("User removes {string} from Cart") 
	public void user_removes(String laptop) throws Exception {
		homepage.removeProduct(laptop);
	}
	
	@And("User places the order") 
	public void user_place() throws Exception {
		homepage.placeOrder();
	}
	
	@And("User completes the web form and clicks Purchase") 
	public void user_details() throws Exception {
		homepage.fillDetails();
	}
	
	@And("User verifies the purchase amount") 
	public void user_verify_details() throws Exception {
		boolean value = homepage.verifyOrderDetails();
		assertTrue(value);
	}
		
	
}
