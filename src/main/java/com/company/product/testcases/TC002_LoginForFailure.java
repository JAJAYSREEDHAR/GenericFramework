package com.company.product.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.company.product.pages.LoginPage;
import com.company.testng.service.SetupAndTearDownService;

public class TC002_LoginForFailure extends SetupAndTearDownService{

	@BeforeTest
	public void setValues() {
		testCaseName = "Login(Negative)";
		testCaseDescription = "Login for Failure(Negative testCase)";
		author = "Gopinath Jayakumar";
		category = "Smoke";
		dataSheetName = "TC002";
	}

	@Test(dataProvider = "fetchData")
	public void createLeaf(String uName, String pwd, String errMsg) {
		new LoginPage(webDriver, testNode)
		.enterUserName(uName)
		.enterPassword(pwd)
		.clickLogInForFailer()
		.verifyErrorMsg(errMsg);
	}
}





