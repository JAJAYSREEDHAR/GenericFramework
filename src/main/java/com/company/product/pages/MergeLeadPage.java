package com.company.product.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.company.testng.service.SetupAndTearDownService;



public class MergeLeadPage extends SetupAndTearDownService {
	
	public MergeLeadPage(RemoteWebDriver driver, ExtentTest eachNode) {
		this.webDriver = driver;
		this.testNode = eachNode;
		if (!verifyTitle("Merge Leads | opentaps CRM")) {
			reportStep("This is not Merge Lead Page", "Fail");
		}
	}

	public FindLeadPopPage clickFromLeadLookup(){
		WebElement eleFromLeadLookup = locateElement("xpath", prop.getProperty("MergeLead.fromLead.xpath"));
		clickWithNoSnap(eleFromLeadLookup);
		switchToWindow(1);
		return new FindLeadPopPage(webDriver, testNode);		
	}

	public FindLeadPopPage clickToLeadLookup(){
		WebElement eleToLeadLookup = locateElement("xpath", prop.getProperty("MergeLead.toLead.xpath"));
		clickWithNoSnap(eleToLeadLookup);
		switchToWindow(1);
		return new FindLeadPopPage(webDriver, testNode);		
	}

	public ViewLeadPage clickMergeButton(){
		WebElement eleclickMergeButton = locateElement("link", prop.getProperty("MergeLead.merge.link"));
		clickWithNoSnap(eleclickMergeButton);
		acceptAlert();
		return new ViewLeadPage(webDriver, testNode);
	}
}