package tests.selenium;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import lib.selenium.PreAndPost;
import pages.selenium.LoginPage;

public class TC001_CreateIncident extends PreAndPost{

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
	public void createIncident(String uName, String pwd,String run) {
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
	}


}





