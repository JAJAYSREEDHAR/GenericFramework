package com.company.product.testcases;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.company.selenium.api.LocatorType;
import com.company.testng.service.SetupAndTearDownService;

public class TC_PsuedoCodeEvaluation extends SetupAndTearDownService {

	@BeforeTest
	public void setData() {
		testCaseName = "TC_PsuedoCodeEvaluation";
		testCaseDescription = "Search Womens Hand Bag, filter out a product and add to card and then compare the discount matches, the delete from Cart";
		author = "Ajay Sreedhar";
		category = "AjioTest";
		// dataSheetName="TC_PsuedoCodeEvaluation";
	}

	// @Test(dataProvider="fetchData")
	@Test
	public void ajioTest() {
		// Enter Bags in the Search field and Select Bags in Women Handbags
		locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioHome.searchBox")).sendKeys("Bags");

		locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioHome.selectSearchText")).click();

		// Click on five grid and Select SORT BY as "What's New"

		locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioProductsView.fiveGridSelect")).click();

		selectDropDownUsingText(locateRelativeElement(LocatorType.toRightOf, objProp.getProperty("ajioProductsView.sortBySelection"),"select", 0),"What's New");

		// Enter Price Range Min as 2000 and Max as 5000

		locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioProductsView.expandPrice")).click();

		locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioProductsView.enterMinPrice")).sendKeys("2500");

		locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioProductsView.enterMaxPrice")).sendKeys("5000");

		locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioProductsView.clickPriceFilterButton")).click();

		// Click on the product "Puma Ferrari LS Shoulder Bag"

		retryingFindClick(locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioProductsView.selectProductByLabel")));
		
		switchToWindow(webDriver.getWindowHandles().size()-1);
		
		// Verify the Coupon code for the price above 2690 is applicable for your
		// product, if applicable the get the Coupon Code and Calculate the discount
		// price for the coupon
		String prdPrice = locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioProductsView.productPrice")).getText().replaceAll("[\\D]", "");
		System.out.println("Price of Product before discount: " + prdPrice);

		String discPrice = locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioProductsView.discountedPrice"))
				.getText().replaceAll("[\\D]", "");
		System.out.println("Price of Product after discount: " + discPrice);
		int disc = Integer.valueOf(prdPrice) - Integer.valueOf(discPrice);
		// Get the Promo code
		String promoCode = locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioProductsView.promocode")).getText()
				.replace("Use Code", "");
System.out.println("PromoCode is: "+promoCode);
		// Check the availability of the product for pincode 560043, print the expected
		// delivery date if it is available

		locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioProductsView.inputPinCodeLink")).click();

		locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioProductsView.clearAndEnterNewPinCode")).clear();

		locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioProductsView.clearAndEnterNewPinCode"))
				.sendKeys("560043");

		locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioProductsView.confirmPinCode")).click();


		
				String delveryDate = locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioProductsView.printDeliveryDate")).getText();
				System.out.println("Delivery date is: "+delveryDate);


// Click on Other Informations under Product Details and Print the Customer Care address, phone and email
				
		locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioProductsView.OtherInformation")).click();


		String[] contactDetails= locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioProductsView.addressDetails")).getText().split("[.]");
		
		System.out.println("Customer Care Phone number details: "+contactDetails.toString());
		
		//span[text()='Customer Care Address']/following-sibling::span[@class='other-info']
		String address=contactDetails[0].toString();
		System.out.println("Customer Care Address details: "+address);

		String contactNo =contactDetails[1].toString()+contactDetails[2].toString();
		System.out.println("Customer Care Phone number details: "+contactNo);

		
		String emailAddr =contactDetails[3].toString()+contactDetails[4].toString();
		System.out.println("Customer Care Email details: "+emailAddr);
				

		// Click on ADD TO BAG and then GO TO BAG

		locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioProductsView.addToBag")).click();

		locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioCart.proceedToBag")).click();
	
//		locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioCart.goToBag")).click();			
		// Check the Order Total before apply coupon

		String orderTotal = locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioCart.orderTotal"))
		.getText().replaceAll("[\\D]|(.[0-9]*$)", "");
		System.out.println("Order Total is: " + orderTotal);
		
		// Enter Coupon Code and Click Apply

		locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioCart.enterPromoCode")).sendKeys(promoCode);

		locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioCart.applyPromoCode")).click();

		// Verify the Coupon Savings amount(round off if it in decimal) under Order
		// Summary and the matches the amount calculated in Product details

		String copounSavingAmount = locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioCart.couponSavings"))
				.getText().replaceAll("[A-Za-z]", "").replaceFirst("[\\.]", "").trim();
		//---System.out.println(copounSavingAmount);
		
		int roundedDiscount = Integer.parseInt(copounSavingAmount.split("[\\.]")[0]);
		System.out.println("Copoun discount under order summary is: " + roundedDiscount);
		System.out.println("disc--:"+disc);

		Assert.assertTrue((disc==roundedDiscount), "Discount Amount doesn't match with that in product view & cart pages");
	
		// Click on Delete and Delete the item from Bag

		locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioCart.deleteFromCart")).click();

		locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioCart.confirmDelete")).click();

		if (locateAndWaitForElement(LocatorType.xpath, objProp.getProperty("ajioCart.emptyCart")).isDisplayed()) {
			System.out.println("Test Complete");
		}

		// Close all the browsers

	}

}
