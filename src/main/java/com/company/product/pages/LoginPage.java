package com.company.product.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.company.testng.service.SetupAndTearDownService;

public class LoginPage extends SetupAndTearDownService{
	
	public LoginPage(RemoteWebDriver driver, ExtentTest eachNode) {
		this.webDriver = driver;
		this.testNode = eachNode;
		if (!verifyTitle("Leaftaps - TestLeaf Automation Platform")) {
			reportStep("This is not Login Page", "Fail");
		}
	}

	public LoginPage enterUserName(String data) {
		WebElement eleUserName = locateElement("id", prop.getProperty("Login.uName.id"));
		clearAndType(eleUserName, data);
		return this;
	}
	
	public LoginPage enterPassword(String data) {
		WebElement elePassword = locateElement("id", prop.getProperty("Login.pwd.id"));
		clearAndType(elePassword, data);
		return this;
	}
	
	public HomePage clickLogin() {
		WebElement eleLogin = locateElement("class", prop.getProperty("Login.Login.class"));
		click(eleLogin);
		return new HomePage(webDriver, testNode);
	}
	
	public LoginPage clickLogInForFailer() {
		WebElement eleLogin = locateElement("class", prop.getProperty("Login.Login.class"));
		click(eleLogin);
		return this;
	}
	
		
	public LoginPage verifyErrorMsg(String data) {
		WebElement eleVerifyErrMsg = locateElement("xpath", prop.getProperty("Login.Error.xpath"));
		verifyPartialText(eleVerifyErrMsg, data);
		return this;
	}
	
}









