package tests.rest;

import java.io.File;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;


public class TC001_CreateIncident extends RESTAssuredBase{

	@BeforeTest
	public void setValues() {

		testCaseName = "Create a new Incident";
		testDescription = "Login, Create a new Incident and Logout";
		nodes = "Incident";
		authors = "Sudarshan";
		category = "smoke";
		dataFileName = "TC001";
		dataFileType = "JSON";
	}

	@Test(dataProvider = "fetchData",invocationCount=10)
	public void createIncident(File file) {		
		
		// Post the request
		Response response = postWithBodyAsFile(file);
		
		//Verify the Content by Specific Key
		verifyContentWithKey(response, "result.short_description", "This is Rest Assured Automation framework - Makaia");
		
		// Verify the Content type
		verifyContentType(response, "JSON");
		
		// Verify the response status code
		verifyResponseCode(response, 201);	
		
		// Verify the response time
		verifyResponseTime(response, 5000);
		
	}


}





