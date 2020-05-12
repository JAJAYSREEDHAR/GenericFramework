package com.company.reports;

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

public abstract class Reporter1 {

	public static ExtentHtmlReporter html;
	public static ExtentReports extent;
	public ExtentTest test, testSuite;
	public String testCaseName, testNodes, testCaseDescription, category, author;
	protected static File file;

	public void startReport() {
		String date = new SimpleDateFormat("dd-MMM-yy").format(new Date());
		file = new File("./reports/" + date);
		if (!file.exists()) {
			System.out.println("Exists? " + file.exists());
			file.mkdir();
		}
		html = new ExtentHtmlReporter(file.toString() + "/TestAutomationReport.html");
		html.config().setChartVisibilityOnOpen(false);
		html.config().setDocumentTitle("rGUEST SEAT PRODUCT");
		html.setAppendExisting(true);
		extent = new ExtentReports();
		extent.attachReporter(html);
	}

	public ExtentTest startTestCase(String testCaseName, String testCaseDescription) {
		testSuite = extent.createTest(testCaseName, testCaseDescription);
		test.assignAuthor(author);
		test.assignCategory(category);
		return testSuite;
	}

	public ExtentTest startTestModule(String testNodes) {
		test = testSuite.createNode(testNodes);
		return test;
	}

	public abstract long takeSnap();

	public void reportStep(String desc, String status, boolean bSnap) {

		MediaEntityModelProvider img = null;
		if (bSnap && !status.equalsIgnoreCase("INFO")) {

			long snapNumber = 100000L;
			snapNumber = takeSnap();
			try {
				img = MediaEntityBuilder.createScreenCaptureFromPath("./../reports/images/" + snapNumber + ".png")
						.build();
			} catch (IOException e) {

			}
		}
		if (status.equalsIgnoreCase("PASS")) {
			testSuite.pass(desc, img);
		} else if (status.equalsIgnoreCase("FAIL")) {
			testSuite.fail(desc, img);
			testSuite.log(Status.FAIL, "Usage: BOLD TEXT");
			throw new RuntimeException();
		} else if (status.equalsIgnoreCase("WARNING")) {
			testSuite.warning(desc, img);
		} else if (status.equalsIgnoreCase("INFO")) {
			testSuite.info(desc);
		}
	}

	public void reportStep(String desc, String status) {
		reportStep(desc, status, true);
	}

	public void endReport() {
		extent.flush();
	}
}
