package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataBuild;
import resources.Utils;
import org.hamcrest.Matchers;

public class StepDefination extends Utils {
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data =new TestDataBuild();
	static String pet_id="";
	


	@Given("Create Gets Pets Payload with \"(.*)\"")
	public void create_Gets_Pets_Payload_with(String Status)  throws IOException {
		 res=given().spec(requestSpecification()).queryParam("status", "available");
		 System.out.println(res.toString());
	}
	
	@When("User calls \"(.*)\" of Demo Pet Store")
	public void user_calls_API(String API) throws IOException {
		
		String completeURI = "";
		if(API.contains("Update")) {
			completeURI = getConfigValue(API).trim()+pet_id;
			response = res.when().post(completeURI);
		} else if(API.contains("Find")) {
			completeURI=getConfigValue(API).trim();
			response =res.when().get(completeURI);
		}else if(API.contains("Delete")) {
			completeURI = getConfigValue(API).trim()+pet_id;
			response = res.when().delete(completeURI);
		}else if(API.contains("New")) {
			completeURI = getConfigValue(API).trim();
			response = res.when().post(completeURI);
		}
				
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		assertEquals(200, response.getStatusCode());
		System.out.println(response.asString());
		
	}
	
	@Then("User verify \"(.*)\" of all the Pets in response")
	public void user_verify_of_all_the_Pets_in_response(String Status) {
	 
		String actualStatus=getJsonPath(response,"status");
		System.out.println(actualStatus);
		actualStatus = actualStatus.replaceAll("\\[|\\]","");
		String statusArray[] = actualStatus.split(",");
		for (int i=0; i<statusArray.length; i++)
			assertEquals(Status, statusArray[i].trim());
	}
	
	@Given("Create New Pet Payload with {string} {string} {string}")
	public void create_New_Pet_Payload_with(String id, String name, String status) throws IOException {
		res=given().spec(requestSpecification()).header("accept", "application/json")
				 .body(data.createPet(id, name, status));
		pet_id = id;
	}
	
	@Then("User verify {string} {string} {string} of the Pet in response")
	public void user_verify_of_the_Pet_in_response(String id, String name, String status) {
		assertEquals(name,getJsonPath(response,"name"));
		assertEquals(id,getJsonPath(response,"id"));
	}
	
	
	@Given("Create update Pet Payload")
	public void create_Update_Pet_Payload()  throws IOException {
		 res=given().spec(requestSpecification()).header("Content-Type", "application/x-www-form-urlencoded")
				 .body("status=sold");
	}
	
	
	@Then("User verify Message response")
	public void user_verify_message_in_response() {
		assertEquals(pet_id,getJsonPath(response,"message"));
	}
	
	@Given("Create delete Pet Payload")
	public void create_delete_Pet_Payload()  throws IOException {
		 res=given().spec(requestSpecification()).header("api_key", "special-key");
	}

}
