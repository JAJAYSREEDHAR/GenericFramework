package com.company.product.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.company.testng.service.SetupAndTearDownService;

public class ViewLeadPage extends SetupAndTearDownService{
	
	public ViewLeadPage(RemoteWebDriver driver, ExtentTest eachNode) {
		this.webDriver = driver;
		this.testNode = eachNode;
		if (!verifyTitle("View Lead | opentaps CRM")) {
			reportStep("This is not View Page", "Fail");
		}
	}

	public ViewLeadPage verifyFirstName(String fname) {
		WebElement eleLeadLink = locateElement(prop.getProperty("ViewLead.fName.id"));
		verifyPartialText(eleLeadLink, fname);
		return this;
	}

	public EditLeadPage clickEditLeadLink() {		
		WebElement eleedit = locateElement("link", prop.getProperty("ViewLead.eLead.link"));
		click(eleedit);
		return new EditLeadPage(webDriver, testNode);
	}

	public ViewLeadPage verifyCompanyName(String cName) {
		WebElement eleLeadLink = locateElement("id", prop.getProperty("ViewLead.cName.id"));
		verifyPartialText(eleLeadLink, cName);		
		return this;
	}


	public MyLeadsPage clickDeleteLeadLink(){
		WebElement eleDeleteLeadLink = locateElement("link", prop.getProperty("ViewLead.dLead.link"));
		click(eleDeleteLeadLink);
		return new MyLeadsPage(webDriver, testNode);
	}
	
	
	public DuplicateLeadPage clickDuplicateLeadLink(){
		WebElement eleDuplicateLeadLink = locateElement("link", prop.getProperty("ViewLead.Dupl_Lead.link"));
		click(eleDuplicateLeadLink);
		return new DuplicateLeadPage(webDriver, testNode);
	}
	
	public FindLeadPage clickFindLead(){
		WebElement elefindLead = locateElement("link", prop.getProperty("ViewLead.fLead.link"));
		click(elefindLead);
		return new FindLeadPage(webDriver, testNode);
	}
}
