package stepDefination;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import commonUtility.base;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefination {
	base bs = new base();

	public Response response;
	String endpoint = "";
	static String petID = "";
	static String petStatus = "";

	@Given("^Endpoint to \"([^\"]*)\"$")
	public void endpoint_to(String path) throws Throwable {
		
		endpoint = bs.readValueFromPropertiesFile(path);
		
		if(endpoint.contains("<petID>")) {
			endpoint = endpoint.replace("<petID>", petID);
		} else if(endpoint.contains("<petStatus>")) {
			endpoint = endpoint.replaceAll("<petStatus>", petStatus);
		}
		
	}

	@When("^User hits the POST Request with request body \"([^\"]*)\"$")
	public void user_hits_the_POST_Request_with_request_body(String fileName) throws Throwable {

		File requestFile = new File(System.getProperty("user.dir") + "/src/test/resources/testData/" + fileName);
		String requestData = new String(Files.readAllBytes(Paths.get(requestFile.getCanonicalPath())));
		
		Map<String, Object> headerMap = new HashMap<String, Object>();
		headerMap.put("accept", "application/json");
		headerMap.put("Content-Type", "application/json");

		response = RestAssured.given()
				.baseUri(bs.readValueFromPropertiesFile("host"))
				.basePath(endpoint)
				.headers(headerMap)
				.body(requestData)
				.post();

	}

	@Then("^Validate Response Status code: (\\d+)$")
	public void validate_Response_Status_code(int expectedStatusCode) throws Throwable {
		int actualStatusCode = response.getStatusCode();
		assertThat("Verified response statusCode", actualStatusCode, is(expectedStatusCode));
	}

	@Then("^Fetch Pet ID$")
	public void fetch_Pet_ID() throws Throwable {
		JsonPath jsonPath = response.jsonPath();
		petID = jsonPath.getString("id");

	}

	@When("^User hits the GET Request$")
	public void user_hits_the_GET_Request() throws Throwable {
		
		response = RestAssured.given()
				.baseUri(bs.readValueFromPropertiesFile("host"))
				.basePath(endpoint)
				.headers("accept", "application/json")
				.get();
	}

	@Then("^Validate status value as \"([^\"]*)\" from response body$")
	public void validate_status_value_as_from_response_body(String expectedStatusValue) throws Throwable {
		JsonPath jsonPath = response.jsonPath();
		petStatus = jsonPath.getString("status");

	}

	@When("^User hits the PUT Request with request body \"([^\"]*)\"$")
	public void user_hits_the_PUT_Request_with_request_body(String fileName) throws Throwable {
		
		File requestFile = new File(System.getProperty("user.dir") + "/src/test/resources/testData/" + fileName);
		String requestData = new String(Files.readAllBytes(Paths.get(requestFile.getCanonicalPath())));

		Map<String, Object> headerMap = new HashMap<String, Object>();
		headerMap.put("accept", "application/json");
		headerMap.put("Content-Type", "application/json");

		response = RestAssured.given()
				.baseUri(bs.readValueFromPropertiesFile("host"))
				.basePath(endpoint)
				.headers(headerMap)
				.body(requestData)
				.put();

	}

	@When("^User hits the DELETE Request$")
	public void user_hits_the_DELETE_Request() throws Throwable {
		
		response = RestAssured.given()
				.baseUri(bs.readValueFromPropertiesFile("host"))
				.basePath(endpoint)
				.headers("accept", "application/json")
				.delete();

	}
	
	@Then("^Validate Pet ID in the sold category$")
	public void validate_Pet_ID_in_the_sold_category() throws Throwable {
		
		JsonPath jsonPath = response.jsonPath();
		assertThat("Verified response statusCode", jsonPath.getString("id"), is(petID));
	    
	}

//	@Then("^User hits the GET Request$")
//	public void then_user_hits_the_GET_Request() throws Throwable {
//		response = RestAssured.given()
//				.baseUri(bs.readValueFromPropertiesFile("host"))
//				.basePath(endpoint)
//				.headers("accept", "application/json")
//				.get();
//
//	}

}
