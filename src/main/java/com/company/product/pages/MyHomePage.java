package com.company.product.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.company.testng.service.SetupAndTearDownService;

public class MyHomePage extends SetupAndTearDownService{
	
	public MyHomePage(RemoteWebDriver driver, ExtentTest eachNode) {
		this.webDriver = driver;
		this.testNode = eachNode;
		if (!verifyTitle("My Home | opentaps CRM")) {
			reportStep("This is not My Home Page", "Fail");
		}
	}
	
	public MyLeadsPage clickLeadLink() {
		WebElement eleLeads = locateElement("link", prop.getProperty("MyHome.Leads.Link"));
		click(eleLeads);
		return new MyLeadsPage(webDriver, testNode);
	}
	
}









