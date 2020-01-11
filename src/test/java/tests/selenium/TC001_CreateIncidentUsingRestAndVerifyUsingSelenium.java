package tests.selenium;

import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;
import lib.selenium.PreAndPost;
import lib.utils.HTMLReporter;
import pages.selenium.LoginPage;

public class TC001_CreateIncidentUsingRestAndVerifyUsingSelenium extends PreAndPost{

	@BeforeTest
	public void setValues() {

		testCaseName = "Create Incident (Using REST Assured)";
		testDescription = "Create a new Incident";
		nodes = "Incident Management";
		authors = "Babu";
		category = "smoke";
		dataSheetName = "TC001";

	}

	@Test(dataProvider = "fetchData")
	public void createIncident(String uName, String pwd) {

		// Post the request
		Response response = RESTAssuredBase.post("incident");

		RESTAssuredBase.verifyResponseCode(response, 201);

		//Verify the Content by Specific Key
		incidentNumber = RESTAssuredBase.getContentWithKey(response, "result.number");

		// Selenium - Create Incident		
		new LoginPage(driver,test)
		.typeUserName(uName)
		.typePassword(pwd)
		.clickLogIn()
		.searchUsingFilter("incident")
		.clickOpen()
		.typeAndEnterSearch(incidentNumber);
	
	}


}





