package com.company.product.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.company.product.pages.FindLeadPage;
import com.company.product.pages.LoginPage;
import com.company.testng.service.SetupAndTearDownService;



public class TC005_DeleteLead extends SetupAndTearDownService{

	@BeforeTest
	public void setData() {
		testCaseName="TC005_DeleteLead";
		testCaseDescription="Delete a Lead - LeafTaps";
		author="Gopinath Jayakumar";
		category="Sanity";
		dataSheetName="TC005";
	}

	@Test(dataProvider="fetchData")
	public void deleteLead(String userName, String password, String phoneNum,String errorMsg){

		String firstResultingLead =
			new LoginPage(webDriver, testNode)
				.enterUserName(userName)
				.enterPassword(password)
				.clickLogin()
				.clickCRMSFA()
				.clickLeadLink()		
				.clickFindLead()
				.clickPhoneTab()
				.enterPhoneNumberField(phoneNum)
				.clickFindleadsButton()
				.getFirstResultingLead();		
			new FindLeadPage(webDriver, testNode)
				.clickFirstResultingLead()
				.clickDeleteLeadLink()
				.clickFindLead()
				.enterLeadId(firstResultingLead)
				.clickFindleadsButton()
				.verifyErrorMsg(errorMsg);
	}
}
