package com.company.product.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.company.testng.service.SetupAndTearDownService;



public class DuplicateLeadPage extends SetupAndTearDownService {
	
	public DuplicateLeadPage(RemoteWebDriver driver, ExtentTest eachNode) {
		this.webDriver = driver;
		this.testNode = eachNode;
		if (!verifyTitle("Duplicate Lead | opentaps CRM")) {
			reportStep("This is not Duplicate Lead", "Fail");
		}
	}

	public ViewLeadPage clickCreateLeadDuplicate(){
		WebElement eleCreateLeadDublicate = locateElement("class", prop.getProperty("DuplicateLead.cLead.class"));
		click(eleCreateLeadDublicate);
		return new ViewLeadPage(webDriver, testNode);
	}
}
