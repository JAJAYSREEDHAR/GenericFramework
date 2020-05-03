package com.company.product.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.company.testng.service.SetupAndTearDownService;

public class MyLeadsPage extends SetupAndTearDownService{
	
	public MyLeadsPage(RemoteWebDriver driver, ExtentTest eachNode) {
		this.webDriver = driver;
		this.testNode = eachNode;
		if (!verifyTitle("My Leads | opentaps CRM")) {
			reportStep("This is not My Leads Page", "Fail");
		}
	}
	
	public CreateLeadPage clickCreateLead() {
		WebElement eleCreateLead = locateElement("link", prop.getProperty("MyLead.CreateLead.Link"));
		click(eleCreateLead);
		return new CreateLeadPage(webDriver, testNode);
	}
	
	public FindLeadPage clickFindLead() {
		WebElement eleFindLeads = locateElement("link", prop.getProperty("MyLead.FindLeads.Link"));
		click(eleFindLeads);
		return new FindLeadPage(webDriver, testNode);
	}
	
		
	public MergeLeadPage clickMergeLead(){
		WebElement elemergeLead = locateElement("link", prop.getProperty("MyLead.MergeLead.Link"));
		click(elemergeLead);		
		return new MergeLeadPage(webDriver, testNode);
	}
	
	
	
}









