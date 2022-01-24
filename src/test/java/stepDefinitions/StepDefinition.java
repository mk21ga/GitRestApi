package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import junit.framework.Assert;
import pojo.AddPlace;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils{

	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	Response response;
	static String place_id;
	TestDataBuild tdb = new TestDataBuild();
	//Utils utils = new Utils();
	//@Given("Add Place Payload")
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		
		reqSpec = given().relaxedHTTPSValidation().spec(requestSpecification()).body(tdb.addPlacePayload(name,language,address));//seperated the request
		
	}
		 
	//}
	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String string, String method) {
	 ApiResources apiResources = ApiResources.valueOf(string);
		System.out.println(apiResources.getResource());
		resSpec =  new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("get"))
		{
			response = reqSpec.when().get(apiResources.getResource());
		}
		else if(method.equalsIgnoreCase("post"))
		{
			response = reqSpec.when().post(apiResources.getResource());
		}
	}
	@Then("The Api call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String string2) {
		//String resp = response.asString();
		assertEquals(getJsonPath(response, key), string2);

	}
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		place_id = getJsonPath(response, "place_id");
		reqSpec = given().relaxedHTTPSValidation().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(expectedName, actualName);
	}
	@Given("DeletePlacePayload")
	public void delete_place_payload() throws IOException {
		reqSpec = given().relaxedHTTPSValidation().spec(requestSpecification()).body(tdb.deletePlacePayload(place_id));
	}
}
