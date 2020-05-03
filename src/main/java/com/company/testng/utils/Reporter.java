package com.company.testng.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.company.listeners.lib.ExtentReportsListener;

public abstract class Reporter extends ExtentReportsListener {

	public static ExtentHtmlReporter html;
	public static ExtentReports extent;
	public ExtentTest testSuite, testNode;
	public String testCaseName, testNodes, testCaseDescription, category, author;
	protected static File file;
	
	
	
	public void startReport() {
		String date = new SimpleDateFormat("dd-MMM-yy").format(new Date());
		file = new File("./reports/"+date);
		if(!file.exists()) {
			System.out.println("Exists? "+file.exists());
			file.mkdir();
		}
		html = new ExtentHtmlReporter(file.toString()+"/TestAutomationReport.html");
		html.config().setChartVisibilityOnOpen(false);
		html.config().setDocumentTitle("rGUEST SEAT PRODUCT");
		html.setAppendExisting(true);		
		extent = new ExtentReports();	
		extent.attachReporter(html);	
	}


	

	public ExtentTest startTestCase(String testCaseName, String testCaseDescription) {
		testNode = extent.createTest(testCaseName, testCaseDescription);	
		testSuite.assignAuthor(author);
		testSuite.assignCategory(category); 
		return testNode;
	}

	public ExtentTest startTestModule(String testNodes) {
		testSuite = testNode.createNode(testNodes);
		return testSuite;
	}
	
	
	public abstract long takeSnap();

	public void reportStep(String desc, String status,boolean bSnap ) {
		
		MediaEntityModelProvider img = null;
		if(bSnap && !status.equalsIgnoreCase("INFO")){

			long snapNumber = 100000L;
			snapNumber = takeSnap();
			try {
				img = MediaEntityBuilder.createScreenCaptureFromPath
						("./../reports/images/"+snapNumber+".png").build();
			} catch (IOException e) {

			}
		}
		if(status.equalsIgnoreCase("PASS")) {
			testNode.pass(desc, img);			
		}else if (status.equalsIgnoreCase("FAIL")) {
			testNode.fail(desc, img);
			testNode.log(Status.FAIL, "Usage: BOLD TEXT");
			throw new RuntimeException();
		}else if (status.equalsIgnoreCase("WARNING")) {
			testNode.warning(desc, img);
		}else if (status.equalsIgnoreCase("INFO")) {
			testNode.info(desc);
		}							
	}


	public void reportStep(String desc, String status) {
		reportStep(desc, status, true);
	}



	public void endReport() {
		extent.flush();
	}
}














