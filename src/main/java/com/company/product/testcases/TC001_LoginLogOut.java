package com.company.product.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.company.product.pages.LoginPage;
import com.company.testng.service.SetupAndTearDownService;

public class TC001_LoginLogOut extends SetupAndTearDownService{	

	@BeforeTest
	public void setValues() {
		testCaseName = "Login and LoginOut";
		testCaseDescription = "Login testCase using valid credentials and LogOut";
		author = "Gopinath Jayakumar";
		category = "Smoke";
		dataSheetName = "TC001";		
	}

	@Test(dataProvider = "fetchData")
	public void loginLogout(String uName, String pwd) throws InterruptedException {
		new LoginPage(webDriver, testNode)
		.enterUserName(uName)
		.enterPassword(pwd)
		.clickLogin()		
		.clickLogout();
	}
}





