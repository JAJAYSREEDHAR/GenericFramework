package com.company.testng.service;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.company.selenium.api.BrowserOptions;
import com.company.selenium.service.SeleniumBaseService;
import com.company.testng.utils.DataLibrary;

public class SetupAndTearDownService extends SeleniumBaseService {

	public String dataSheetName;

	@BeforeSuite
	public void loadPrerequisites() {
		loadObjects();
		loadConfig();
		startReport();
		startRemoteServer();

	}

	@BeforeClass
	public void startTestCaseReport() {
		startTestCase(testCaseName, testCaseDescription);

	}

	@BeforeMethod
	public void startApplication() {
		// testNode = startTestModule(testNodes);
		startApp(BrowserOptions.valueOf(config.getProperty("browser")), config.getProperty("url"));
	}

	@AfterMethod
	public void close() {
		closeBrowser();
	}

	@DataProvider(name = "fetchData")
	public Object[][] fetchData() throws IOException {
		return DataLibrary.readExcelData(dataSheetName);
	}

	@AfterSuite
	public void unloadObj() {
		unloadObjects();
		unloadConfig();
		endReport();

	}

}