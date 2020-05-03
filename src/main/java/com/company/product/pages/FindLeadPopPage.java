package com.company.product.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.company.testng.service.SetupAndTearDownService;


public class FindLeadPopPage extends SetupAndTearDownService {
	
	public FindLeadPopPage(RemoteWebDriver driver, ExtentTest eachNode) {
		this.webDriver = driver;
		this.testNode = eachNode;
		if (!verifyTitle("Find Leads")) {
			reportStep("This is not Find Leads", "Fail");
		}
	}


	public FindLeadPopPage enterFirstName(String findfistname){
		WebElement elefindFirstName = locateElement("name", prop.getProperty("FindLeadP.FirstName.Name"));
		clearAndType(elefindFirstName, findfistname);
		return this;
	}

	public FindLeadPopPage clickFindleadsButton(){
		WebElement eleFindleadsButton = locateElement("xpath", prop.getProperty("FindLeadP.Findleads.Xpath"));
		click(eleFindleadsButton);
		return this;
	}
	
	public String getFirstResultingLead(){	
		WebElement eleGetResultingLeads = locateElement("xpath", prop.getProperty("FindLeadP.FirstResultLead.Xpath"));
		return getElementText(eleGetResultingLeads);
	}

	public MergeLeadPage clickResultingLeads(){
		WebElement eleResultingLeads = locateElement("xpath", prop.getProperty("FindLeadP.FirstResultLead.Xpath"));
		clickWithNoSnap(eleResultingLeads);
		switchToWindow(0);
		return new MergeLeadPage(webDriver, testNode);
	}
}