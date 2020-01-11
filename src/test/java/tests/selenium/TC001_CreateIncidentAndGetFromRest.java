package tests.selenium;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;
import lib.selenium.PreAndPost;
import lib.utils.HTMLReporter;
import pages.selenium.LoginPage;

public class TC001_CreateIncidentAndGetFromRest extends PreAndPost{

	@BeforeTest
	public void setValues() {

		testCaseName = "Create Incident (Using Selenium)";
		testDescription = "Create a new Incident";
		nodes = "Incident Management";
		authors = "Babu";
		category = "smoke";
		dataSheetName = "TC001";

	}

	@Test(dataProvider = "fetchData")
	public void createIncident(String uName, String pwd) {
		
		// Selenium - Create Incident		
		new LoginPage(driver,test)
		.typeUserName(uName)
		.typePassword(pwd)
		.clickLogIn()
		.searchUsingFilter("incident")
		.clickCreateNew()
		.getIncidentNumber()
		.selectUser("Abel")
		.typeShortDescription("This is automated test")
		.clickSubmit();
		
		
		// Verify Using REST Assured		
		Response response = RESTAssuredBase.get("incident?number="+incidentNumber);
		RESTAssuredBase.verifyContentsWithKey(response, "result.number",incidentNumber);
	}


}





