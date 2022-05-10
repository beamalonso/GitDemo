package stepdefinitions;

import static io.restassured.RestAssured.given;


import static org.junit.Assert.*;

import org.junit.runner.RunWith;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

//@RunWith(Cucumber.class)
public class StepDefinition extends Utils {
	
	RequestSpecification req;
	Response res;
	static String placeId; //static to use the same copy of this var for all test cases. We need to retain the value.
	TestDataBuild data = new TestDataBuild(); 
		
	@Given("AddPlace payload with {string} {string} {string}")
	public void addplace_payload_with(String name, String language, String address) throws Throwable {
				
		//RestAssured.baseURI = "https://rahulshettyacademy.com";
		//Using request builder
		//RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		//.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		//
		//Using response builder
		//resSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		req = given().spec(getRequestSpecification()).body(data.addPlacePayload(name, language, address));  
	}

    
    @When("User calls {string} with {string} http method")
    public void user_calls_something_with_post_http_method(String resource, String httpMethod) throws Throwable { 
    	APIResources resourceAPI = APIResources.valueOf(resource);
    	//Submit request and store response
    	if (httpMethod.equalsIgnoreCase("POST")) 
    	   res = req.when().post(resourceAPI.getResource()).then().spec(getResponseSpecification()).extract().response();  
         else if (httpMethod.equalsIgnoreCase("GET")) 
            res = req.when().get(resourceAPI.getResource()).then().spec(getResponseSpecification()).extract().response();  
    }     
    

    @Then("^Http Status Code is 200$")
    public void http_status_code_is_200() throws Throwable {
    	assertEquals(res.statusCode(), 200);
    }
      
    @Then("{string} in response body is {string}")
    public void something_in_response_body_is_something(String key, String expectedValue) throws Throwable {
    	   	
    	assertEquals(getJsonPath(res,key), expectedValue); 	
        
    }
    
    @Then("verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using_getPlaceApi(String expectedName, String resource) throws Throwable {
    	placeId = getJsonPath(res,"place_id");
     	System.out.println("place_id = "+ placeId);
    	req = given().spec(getRequestSpecification()).queryParam("place_id", placeId);
    	user_calls_something_with_post_http_method(resource, "GET");
    	String actualName  = getJsonPath(res,"name");
    	System.out.println("Expected Name = " + expectedName);
    	System.out.println("Actual Name = " + actualName);
    	assertEquals(actualName, expectedName);
    	
    }
    
    @Given("DeletePlace payload")
    public void deleteplace_payload() throws Throwable {    	
    	req = given().spec(getRequestSpecification()).body(data.deletePlacePayload(placeId));  
    	
    }
    
    
}
