package com.company.product.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.company.product.pages.FindLeadPopPage;
import com.company.product.pages.LoginPage;
import com.company.testng.service.SetupAndTearDownService;



public class TC007_MergeLead extends SetupAndTearDownService {

	@BeforeTest
	public void setValues(){
		testCaseName = "TC007_MergeLead";
		testCaseDescription = "Merge two Lead on LeafTaps";		
		author = "Gopinath Jayakumar";
		category = "Regression";
		dataSheetName = "TC007";
	}

	@Test(dataProvider = "fetchData")
	public void mergeLead(String uName, String pwd,String fLeadName,String lLeadName, String errorMsg){
		String fromLeadId = 
			new LoginPage(webDriver, testNode)
				.enterUserName(uName)
				.enterPassword(pwd)
				.clickLogin()
				.clickCRMSFA()
				.clickLeadLink()
				.clickMergeLead()
				.clickFromLeadLookup()
				.enterFirstName(fLeadName)
				.clickFindleadsButton()
				.getFirstResultingLead();
			new FindLeadPopPage(webDriver, testNode)
				.clickResultingLeads()
				.clickToLeadLookup()
				.enterFirstName( lLeadName)
				.clickFindleadsButton()
				.clickResultingLeads()
				.clickMergeButton()
				.clickFindLead()
				.enterLeadId(fromLeadId)
				.clickFindleadsButton()
				.verifyErrorMsg(errorMsg);

	}


}










