package tests.rest;

import java.io.File;
import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;


public class TC002_DeleteFirstIncident extends RESTAssuredBase{

	@BeforeTest
	public void setValues() {

		testCaseName = "Delete the first incident";
		testDescription = "Get all incidents from the search and delete the first incident";
		nodes = "Incident";
		authors = "Babu";
		category = "smoke";
		dataFileName = "TC002";
		dataFileType = "JSON";
	}

	@Test()
	public void createIncident() {		
		
		// Post the request
		Response response = get();		
			
		// Verify the Content type
		verifyContentType(response, "JSON");
		
		// Verify the response status code
		verifyResponseCode(response, 200);	
		
		// Verify the response time
		verifyResponseTime(response, 15000);
		
		// Get the Incidents
		List<String> contents = getContentsWithKey(response, "result.sys_id");
		
		// Delete the first incident
		response = delete("/"+contents.get(0));
		
		response.prettyPrint();
		
		// Verify the response status code
		verifyResponseCode(response, 204);	
		
		
	}


}





