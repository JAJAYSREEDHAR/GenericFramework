package com.company.product.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.company.product.pages.LoginPage;
import com.company.testng.service.SetupAndTearDownService;



public class TC004_EditLead extends SetupAndTearDownService{

	@BeforeTest
	public void setData() {
		testCaseName="TC004_EditLead";
		testCaseDescription="Edit excisting Lead on LeafTaps";
		author="Gopinath Jayakumar";
		category="Sanity";
		dataSheetName="TC004";
	}

	@Test(dataProvider="fetchData")
	public void editLead(String userName, String password , String f_Name, String updcompanyName){

		new LoginPage(webDriver, testNode)
		.enterUserName(userName)
		.enterPassword(password)
		.clickLogin()		
		.clickCRMSFA()		
		.clickLeadLink()		
		.clickFindLead()
		.enterFirstName(f_Name)
		.clickFindleadsButton()
		.clickFirstResultingLead()
		.clickEditLeadLink()
		.updateCompanyName(updcompanyName)
		.clickUpdateSubmit()
		.verifyCompanyName(updcompanyName);
		
	}

}
