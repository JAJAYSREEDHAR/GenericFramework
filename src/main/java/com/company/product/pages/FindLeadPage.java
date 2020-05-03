package com.company.product.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.company.testng.service.SetupAndTearDownService;

public class FindLeadPage extends SetupAndTearDownService{

	public FindLeadPage(RemoteWebDriver driver, ExtentTest eachNode) {
		this.webDriver = driver;
		this.testNode = eachNode;
		if (!verifyTitle("Find Leads | opentaps CRM")) {
			reportStep("This is not Find Lead", "Fail");
		}
	}

	public FindLeadPage enterFirstName(String data) {
		WebElement eleFirstName = locateElement("xpath", prop.getProperty("FindLead.fName.xpath"));
		clearAndType(eleFirstName, data);
		return this;
	}

	public FindLeadPage clickFindleadsButton() {
		WebElement eleearchLeads = locateElement("xpath", prop.getProperty("FindLead.fLeads.xpath"));
		click(eleearchLeads);
		waits(3);
		return this;
	}

	public ViewLeadPage clickFirstResultingLead() {
		WebElement eleresult = locateElement("xpath", prop.getProperty("FindLead.fResult.xpath"));
		click(eleresult);
		return new ViewLeadPage(webDriver, testNode);
	}


	public FindLeadPage clickPhoneTab(){
		WebElement elePhoneTab = locateElement("xpath", prop.getProperty("FindLead.phoneTab.xpath"));
		click(elePhoneTab);
		return this;
	}

	public FindLeadPage clickEmailTab(){
		WebElement eleEmailTab = locateElement("xpath", prop.getProperty("FindLead.eMailTab.xpath"));
		click(eleEmailTab);
		return this;
	}


	public FindLeadPage enterPhoneNumberField(String PhoneNumber){
		WebElement elePhoneNumberField = locateElement("name", prop.getProperty("FindLead.pNumber.name"));
		clearAndType(elePhoneNumberField, PhoneNumber);
		return this;
	}


	public FindLeadPage enterEmailAddress(String emailAddress){
		WebElement eleEmailAddress = locateElement("name", prop.getProperty("FindLead.eMailAddr.name"));
		clearAndType(eleEmailAddress, emailAddress);
		return this;
	}


	public FindLeadPage verifyErrorMsg(String eleErrorMsgValue){
		WebElement eleErrorMsg = locateElement("class", prop.getProperty("FindLead.errorMsg.class"));
		verifyPartialText(eleErrorMsg, eleErrorMsgValue);
		return this;
	}

	public String getFirstResultingLead(){	
		WebElement eleGetResultingLeads = locateElement("xpath", prop.getProperty("FindLead.FirstResultLead.xpath"));
		return 	getElementText(eleGetResultingLeads);
	}

	public String getFirstResultingFirstName() throws InterruptedException{	
		WebElement eleGetResultingFName = locateElement("xpath", prop.getProperty("FindLead.FirstResultFName.xpath"));
		waits(2);
		return 	getElementText(eleGetResultingFName);
	}

	public FindLeadPage enterLeadId(String findfistname){
		WebElement eleLeadId = locateElement("name", prop.getProperty("FindLead.LeadId.name"));
		clearAndType(eleLeadId, findfistname);
		return this;

	}

}









