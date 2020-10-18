package com.shop.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.shop.browser.DriverManager;
import com.shop.constants.Constants;
import com.shop.utils.JsonParser;

public class HomePage extends BasePage{
	

	@FindBy(xpath="//a[contains(text(),'Samsung galaxy s7')]")
	WebElement samsungPhone;
	
	@FindBy(xpath="//a[contains(text(),'MacBook air')]")
	WebElement macLaptop;
	
	@FindBy(xpath="//a[contains(text(),'Apple monitor 24')]")
	WebElement appMonitors;
	
	@FindBy(xpath="//a[contains(text(),'Add to cart')]")
	WebElement cartAddButton;
	
	@FindBy(xpath="//a[contains(text(),'Home')]")
	WebElement homeButton;
	
	@FindBy(xpath="//a[contains(text(),'Cart')]")
	WebElement cartButton;
	
	@FindBy(xpath="//button[contains(text(),'Place Order')]")
	WebElement orderButton;
	
	@FindBy(xpath="//input[@id= 'name']")
	WebElement name;
	
	@FindBy(xpath="//input[@id= 'country']")
	WebElement country;
	
	@FindBy(xpath="//input[@id= 'city']")
	WebElement city;
	
	@FindBy(xpath="//input[@id= 'card']")
	WebElement card;
	
	@FindBy(xpath="//input[@id= 'month']")
	WebElement month;
	
	@FindBy(xpath="//input[@id= 'year']")
	WebElement year;
	
	@FindBy(xpath="//button[contains(text(),'Purchase')]")
	WebElement purchaseButton;
	
	@FindBy(xpath="//h2[contains(text(), 'purchase')]//../p")
	WebElement purchaseConfirm;
	
	@FindBy(xpath="//h3")
	WebElement price;
	
	
	String bookingAmount="";
	String orderDetails = "";
	
	public String verifyHomePage() {
		String title = DriverManager.getDriver().getTitle();
		return title;	
	}

	
	public boolean verifyProduct(String product) {
		WebElement prod = DriverManager.getDriver().findElement(By.xpath("//a[contains(text(),'"+product+"')]"));
		click(prod);
		
		if(product.equalsIgnoreCase("Phones")) {
			if(samsungPhone.isDisplayed()) {
				return true;
			}
		} else if(product.equalsIgnoreCase("Laptops")) {
			if(macLaptop.isDisplayed()) {
				return true;
			}
		} else if(product.equalsIgnoreCase("Monitors")) {
			if(appMonitors.isDisplayed()) {
				return true;
			}
		}
		return false;
	}
	
	
	public void addProduct(String laptop) throws InterruptedException {
		WebElement prod = DriverManager.getDriver().findElement(By.xpath("//a[contains(text(),'"+laptop+"')]"));
		click(prod);
		click(cartAddButton);
		acceptAlert();
		click(homeButton);
	}
	
	public void removeProduct(String laptop) {
		
		try {
			click(cartButton);
			WebElement prod = DriverManager.getDriver().findElement(By.xpath("//td[text()='"+laptop+"']//..//td/a"));
			click(prod);
			
			WebDriverWait wait=new WebDriverWait(DriverManager.getDriver(),Constants.EXPLICITWAIT);
			wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//h3"), "790"));
		} catch (Exception ex) {
			
		}

	}
	
	public void placeOrder() {
		bookingAmount = price.getText();
		click(orderButton);
	}
	
	public void fillDetails() throws IOException {
		
		name.sendKeys(JsonParser.getValue("config.global.name"));
		country.sendKeys(JsonParser.getValue("config.global.country"));
		city.sendKeys(JsonParser.getValue("config.global.city"));
		card.sendKeys(JsonParser.getValue("config.global.card"));
		month.sendKeys(JsonParser.getValue("config.global.month"));
		year.sendKeys(JsonParser.getValue("config.global.year"));
		
		click(purchaseButton);
		orderDetails = purchaseConfirm.getText();
		System.out.println(purchaseConfirm.getText());
	}
	
	public boolean verifyOrderDetails() {
		
		bookingAmount = bookingAmount+ " USD" ;
		if (orderDetails.contains(bookingAmount)) {
			return true;
		}
		return false;
	}
	
	

}
