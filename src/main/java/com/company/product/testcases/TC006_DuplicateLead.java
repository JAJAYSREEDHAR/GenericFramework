package com.company.product.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.company.product.pages.FindLeadPage;
import com.company.product.pages.LoginPage;
import com.company.testng.service.SetupAndTearDownService;



public class TC006_DuplicateLead extends SetupAndTearDownService{

	@BeforeTest
	public void setData() {
		testCaseName="TC006_DuplicateLead";
		testCaseDescription="Duplicate a Lead - LeafTaps";
		author="Gopinath Jayakumar";
		category="Regression";
		dataSheetName="TC006";
	}

	@Test(dataProvider="fetchData")
	public void duplicateLead(String userName, String password ,String emailAddress) throws InterruptedException{

		String fName=
			new LoginPage(webDriver, testNode)
				.enterUserName(userName)
				.enterPassword(password)
				.clickLogin()		
				.clickCRMSFA()		
				.clickLeadLink()		
				.clickFindLead()
				.clickEmailTab()
				.enterEmailAddress(emailAddress)
				.clickFindleadsButton()
				.getFirstResultingFirstName();
			new FindLeadPage(webDriver, testNode)
				.clickFirstResultingLead()
				.clickDuplicateLeadLink()
				.clickCreateLeadDuplicate()
				.verifyFirstName(fName);

	}

}
