package com.company.product.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.company.testng.service.SetupAndTearDownService;

public class HomePage extends SetupAndTearDownService{
	
	public HomePage(RemoteWebDriver driver, ExtentTest eachNode) {
		this.webDriver = driver;
		this.testNode = eachNode;
		if (!verifyTitle("Leaftaps - TestLeaf Automation Platform")) {
			reportStep("This is not Home Page", "Fail");
		}
	}
	
	public MyHomePage clickCRMSFA() {
		WebElement eleCRMSFA = locateElement("link", prop.getProperty("Home.CRMSFA.link"));
		click(eleCRMSFA);
		return new MyHomePage(webDriver, testNode);
	}
	
	public LoginPage clickLogout() {
		WebElement eleLogin = locateElement("class", prop.getProperty("Home.Logout.class"));
		click(eleLogin);
		return new LoginPage(webDriver, testNode);
	}
	
}









