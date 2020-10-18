Feature: Web Automation for DEMO ONLINE SHOP

@TC1
Scenario Outline: Purchase Products from Demo Online Shop 
	Given User Launches the Online Shop URL 
	Then User is navigated to home page
	And User navigates through the product "Phones"
	And User navigates through the product "Monitors"
	And User navigates through the product "Laptops"
	And User Adds "<Laptop01>" in Cart
	And User navigates through the product "Laptops"
	And User Adds "<Laptop02>" in Cart
	And User removes "<Laptop02>" from Cart
	Then User places the order
	And User completes the web form and clicks Purchase
	And User verifies the purchase amount
	
	Examples:
		|Laptop01 		| Laptop02		|
		|Sony vaio i5 	| Dell i7 8gb	|
	
		
	