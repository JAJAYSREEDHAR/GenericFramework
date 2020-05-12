/**
 * 
 */
package com.company.product.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.company.selenium.api.LocatorType;
import com.company.testng.service.SetupAndTearDownService;

/**
 * @author janapalaa
 *
 */

public class TC_PseudoCode001 extends SetupAndTearDownService {

	@BeforeTest
	public void setData() {
		testCaseName = "TC_PseudoCode001";
		testCaseDescription = "Create a new Lead on LeafTaps";
		author = "Ajay Sreedhar";
		category = "MyntraTest";
		dataSheetName = "TC_PseudoCode001";
	}

	@Test(dataProvider = "fetchData")
	public void myntraTest() {

		// 2) Mouse over on WOMEN (Actions -> moveToElement)
		locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("myntraHome.womensLink"));
		// 3) Click Jackets & Coats

		/*
		 * 4) Find the total count of item (top) -> getText -> String //5) Validate the
		 * sum of categories count matches
		 * 
		 * //6) Check Coats
		 * 
		 * 
		 * //7) Click + More option under BRAND
		 * 
		 * //8) Type MANGO and click checkbox
		 * 
		 * //9) Close the pop-up x
		 * 
		 * /*10) Confirm all the Coats are of brand MANGO findElements (brand) ->
		 * List<WebElement> foreach -> getText of each brand compare > if(condition)
		 */

		// 11) Sort by Better Discount

		/*
		 * 12) Find the price of first displayed item findElements (price) ->
		 * List<WebElement> get(0) -> WebElement -> getText -> String -> int
		 */

		// 13) Mouse over on size of the first item

		// 14) Click on WishList Now

		// 15) Close Browser
	}
}