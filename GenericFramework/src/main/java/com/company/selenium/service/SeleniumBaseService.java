package com.company.selenium.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.locators.RelativeLocator.RelativeBy;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Optional;

import com.company.listeners.lib.ListenerBaseService;
import com.company.selenium.api.BrowserOptions;
import com.company.selenium.api.IBrowser;
import com.company.selenium.api.IElement;
import com.company.selenium.api.LocatorType;
import com.google.common.base.Function;

/**
 * @author janapalaa
 *
 */
public class SeleniumBaseService extends ListenerBaseService implements IBrowser, IElement {

	public int i = 1;
	public RemoteWebDriver webDriver;
	public EventFiringWebDriver driver;
	public static Properties objProp;
	public static Properties config;
	public BrowserOptions browserName;
	public String dataSheetName;
	public String defaultUIUrl = "http://leaftaps.com/opentaps/control/main";
	public String apiBaseurl;
	public String locValue;
	public LocatorType locator;
	public RelativeBy withTagName;
	WebElement uiElement = null;
	static Actions actions = null;

	public void loadObjects() {
		objProp = new Properties();
		try {
			objProp.load(new FileInputStream(new File("./src/main/java/resources/object.properties")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Properties loadConfig() {
		config = new Properties();
		try {
			config.load(new FileInputStream(new File("./src/main/java/resources/config.properties")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// RestAssured.baseURI =
		// "https://"+prop.getProperty("server")+"/"+prop.getProperty("resources")+"/";
		return config;

	}

	public void unloadObjects() {
		objProp = null;
	}

	public void unloadConfig() {
		config = null;
	}

	public void startApp(BrowserOptions browser, String uiUrl) {

		try {
			// if url not defined in test case default url should be app url
			if (uiUrl == null) {
				uiUrl = defaultUIUrl;
			}
			/*
			 * testSuite = startTestModule(testNodes); testSuite.assignCategory(category);
			 * testSuite.assignAuthor(author);
			 */
			switch (browser) {

			case Edge:
				EdgeOptions edgOptions = new EdgeOptions();
				try {
					webDriver = new RemoteWebDriver(new URL("http://10.0.0.14:4444/wd/hub"), edgOptions);
					webDriver.setFileDetector(new LocalFileDetector());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case Firefox:
				FirefoxOptions ffoxOptions = new FirefoxOptions();
				try {

					webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), ffoxOptions);
					webDriver.setFileDetector(new LocalFileDetector());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case IE:
				InternetExplorerOptions ieOptions = new InternetExplorerOptions();

				try {

					DesiredCapabilities cap = new DesiredCapabilities();
					cap.setCapability("disable-popup-blocking", true);
					cap.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
					cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
					cap.setCapability("requireWindowFocus", true);
					cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

					ieOptions.merge(cap);

					webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), ieOptions);
					webDriver.setFileDetector(new LocalFileDetector());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case Opera:
				OperaOptions operaOptions = new OperaOptions();
				try {
					DesiredCapabilities cap = new DesiredCapabilities();
					cap.setCapability("disable-popup-blocking", true);
					cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);

					operaOptions.merge(cap);
					operaOptions.addArguments("--start-maximized");
					webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), operaOptions);
					webDriver.setFileDetector(new LocalFileDetector());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case Safari:
				SafariOptions safariOptions = new SafariOptions();
				try {

					webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), safariOptions);
					webDriver.setFileDetector(new LocalFileDetector());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case Chrome:

				ChromeOptions chromeOptions = new ChromeOptions();
				DesiredCapabilities caps = new DesiredCapabilities();
				caps.setCapability(CapabilityType.BROWSER_NAME, "chrome");
				chromeOptions.addArguments("--start-maximized");
				chromeOptions.addArguments("--disable-notifications");
				chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
				chromeOptions.merge(caps);
				caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
				try {
					webDriver = new RemoteWebDriver(URI.create("http://localhost:4444/").toURL(), chromeOptions);
					webDriver.setFileDetector(new LocalFileDetector());
				} catch (MalformedURLException e) {
					reportStep("Unable to initiate Remote Webdriver", "FAIL");
				}

				break;

			default:
				ChromeOptions chroptions1 = new ChromeOptions();
				try {
					chroptions1.addArguments("--start-maximized");
					chroptions1.addArguments("--disable-notifications");
					chroptions1.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);

					webDriver = new RemoteWebDriver(new URL("http://localhost:4444"), chroptions1);
					// webDriver.setFileDetector(new LocalFileDetector());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					reportStep("Unable to initiate Remote Webdriver", "FAIL");
				}
				break;

			}

			driver = new EventFiringWebDriver(webDriver);
			driver.register((WebDriverEventListener) this);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(uiUrl);

			reportStep("The browser: " + browser + " launched successfully", "PASS");
		} catch (WebDriverException e) {
			reportStep("The browser: " + browser + " could not be launched", "FAIL");
		}
	}

	public WebElement scrollToElement(WebElement element) {

		webDriver.executeScript("arguments[0].scrollIntoView(true);", element);

		return element;
	}

	public WebElement locateAndWaitForElement(LocatorType locator, String locValue) {
		try {
			switch (locator) {
			case id:
				uiElement = webDriver.findElementById(locValue);
				if (waitForElementPresenc(uiElement) && verifyDisplayed(uiElement) && verifyEnabled(uiElement)) {
					moveToElement(uiElement, webDriver);
					return uiElement;
				}

			case className:
				uiElement = webDriver.findElementByClassName(locValue);
				if (waitForElementPresenc(uiElement) && verifyDisplayed(uiElement) && verifyEnabled(uiElement)) {

					moveToElement(uiElement, webDriver);
					return uiElement;
				}

			case name:
				uiElement = webDriver.findElementByName(locValue);
				if (waitForElementPresenc(uiElement) && verifyDisplayed(uiElement) && verifyEnabled(uiElement)) {

					moveToElement(uiElement, webDriver);
					return uiElement;
				}

			case link:
				uiElement = webDriver.findElementByLinkText(locValue);
				if (waitForElementPresenc(uiElement) && verifyDisplayed(uiElement) && verifyEnabled(uiElement)) {

					moveToElement(uiElement, webDriver);
					return uiElement;
				}

			case partialLink:
				uiElement = webDriver.findElementByPartialLinkText(locValue);
				if (waitForElementPresenc(uiElement) && verifyDisplayed(uiElement) && verifyEnabled(uiElement)) {

					moveToElement(uiElement, webDriver);
					return uiElement;
				}

			case tagname:
				uiElement = webDriver.findElementByTagName(locValue);
				if (waitForElementPresenc(uiElement) && verifyDisplayed(uiElement) && verifyEnabled(uiElement)) {

					moveToElement(uiElement, webDriver);
					return uiElement;
				}

			case xpath:
				uiElement = webDriver.findElementByXPath(locValue);
				if (waitForElementPresenc(uiElement) && verifyDisplayed(uiElement) && verifyEnabled(uiElement)) {

					moveToElement(uiElement, webDriver);
					return uiElement;
				}

			case cssSelect:
				uiElement = webDriver.findElementByCssSelector(locValue);
				if (waitForElementPresenc(uiElement) && verifyDisplayed(uiElement) && verifyEnabled(uiElement)) {

					moveToElement(uiElement, webDriver);
					return uiElement;
				}

			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			reportStep("The element with locator: " + locator + "and locator value: " + locValue + "not found.",
					"FAIL");

		} catch (WebDriverException e) {
			e.printStackTrace();
			reportStep("Unknown exception occured while finding " + locator + " with the value " + locValue, "FAIL");
		}
		return uiElement;
	}

	public WebElement locateElement(String locValue) {
		uiElement = webDriver.findElementById(locValue);
		if (waitForElementPresenc(uiElement) && verifyDisplayed(uiElement) && verifyEnabled(uiElement)) {

			moveToElement(uiElement, webDriver);
			return uiElement;
		}
		return uiElement;
	}

	public RelativeBy getRelativeLocator(String tagName) {
		try {

			if (tagName != null) {
				return withTagName = RelativeLocator.withTagName(tagName);
			}
		} catch (Exception e) {
			reportStep("Unable to fetch RelativeBy " + withTagName + " with the tag name " + tagName, "FAIL");

		}
		return withTagName;
	}

	public RelativeBy getRelativeIdentifier(LocatorType locator, String locValue, String tagName,
			@Optional int atMostDistanceInPixels) {
		try {
			if (getRelativeLocator(tagName) != null) {
				switch (locator) {

				case below:
					return withTagName.below(locateAndWaitForElement(LocatorType.xpath, locValue));
				case above:
					return withTagName.above(locateAndWaitForElement(LocatorType.xpath, locValue));
				case toLeftOf:
					return withTagName.toLeftOf(locateAndWaitForElement(LocatorType.xpath, locValue));
				case toRightOf:
					return withTagName.toRightOf(locateAndWaitForElement(LocatorType.xpath, locValue));
				case nearByElement:
					return withTagName.near(locateAndWaitForElement(LocatorType.xpath, locValue));
				case nearByPixel:
					return withTagName.near((locateAndWaitForElement(LocatorType.xpath, locValue)),
							atMostDistanceInPixels);
				}
			}
		} catch (NoSuchElementException e) {
			reportStep("The element with locator: " + locator + "and locator value: " + locValue + "not found.",
					"FAIL");
			e.printStackTrace();
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while finding " + locator + " with the value " + locValue, "FAIL");
			e.printStackTrace();
		}
		return null;
	}

	public WebElement locateRelativeElement(LocatorType locator, String locValue, String tagName,
			@Optional int atMostDistanceInPixels) {

		try {
			if (getRelativeIdentifier(locator, locValue, tagName, atMostDistanceInPixels) != null) {
				uiElement = webDriver
						.findElement(getRelativeIdentifier(locator, locValue, tagName, atMostDistanceInPixels));
				if (waitForElementPresenc(uiElement) && verifyDisplayed(uiElement) && verifyEnabled(uiElement)) {

				moveToElement(uiElement, webDriver);
				return uiElement;
				}
			}
		} catch (Exception e) {
			reportStep("Unknown exception occured while finding " + locator + " with the value " + locValue, "FAIL");
			e.printStackTrace();
		}

		return null;
	}

	public List<WebElement> locateElements(LocatorType locator, String locValue) {
		try {
			switch (locator) {
			case id:
				return webDriver.findElementsById(locValue);
			case className:

				return webDriver.findElementsByClassName(locValue);
			case name:
				return webDriver.findElementsByName(locValue);
			case link:
				return webDriver.findElementsByLinkText(locValue);
			case partialLink:
				return webDriver.findElementsByPartialLinkText(locValue);
			case tagname:
				return webDriver.findElementsByTagName(locValue);
			case xpath:
				return webDriver.findElementsByXPath(locValue);
			case cssSelect:
				return webDriver.findElementsByCssSelector(locValue);
			}
		} catch (NoSuchElementException e) {
			reportStep("The element with locator " + locator + " not found.", "FAIL");
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while finding " + locator + " with the value " + locValue, "FAIL");
		}
		return null;
	}

	public void startRemoteServer() {
		String cmd;
		if (new File("./RunSeleniumStandaloneServer.bat").exists()) {

			String[] command = { "cmd.exe", "/C", "Start", "RunSeleniumStandaloneServer.bat" };
			Runtime r = Runtime.getRuntime();
			Process p = null;

			try {
				p = r.exec(command);

				p.waitFor();
//reportStep("Selenium Server started successfully", "PASS");
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				reportStep("Selenium Server failed to start" + e.getMessage(), "Fail");
			}

		}
	}

	public void clearAndType(WebElement ele, String data) {
		try {
			waitForElementPresenc(ele);
			ele.clear();
			ele.sendKeys(data);
			reportStep("The data: " + data + " entered successfully in the field :" + ele, "PASS");
		} catch (InvalidElementStateException e) {
			reportStep("The data: " + data + " could not be entered in the field :" + ele, "FAIL");
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while entering " + data + " in the field :" + ele, "FAIL");
		}
	}

	public void clickWithNoSnap(WebElement ele) {
		String text = "";
		try {
			waitForElementPresenc(ele);
			ele.click();
			reportStep("The element :" + text + "  is clicked.", "PASS", false);
		} catch (InvalidElementStateException e) {
			reportStep("The element: " + text + " could not be clicked", "FAIL", false);
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while clicking in the field :", "FAIL", false);
		}
	}

	public void click(WebElement ele) {
		String text = "";
		try {

			// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			waitForElementPresenc(ele);
			ele.click();
			reportStep("The element " + text + " is clicked", "PASS");
		} catch (InvalidElementStateException e) {
			reportStep("The element: " + text + " could not be clicked", "FAIL");
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while clicking in the field :", "FAIL");
		}
	}

	public String getTitle() {
		String bReturn = "";
		try {
			bReturn = webDriver.getTitle();
		} catch (WebDriverException e) {
			reportStep("Unknown Exception Occured While fetching Title", "FAIL");
		}
		return bReturn;
	}

	public void selectDropDownUsingText(WebElement ele, String value) {
		try {
			waitForElementPresenc(ele);
			new Select(ele).selectByVisibleText(value);
			reportStep("The dropdown is selected with text " + value, "PASS");
		} catch (WebDriverException e) {
			reportStep("The element: " + ele + " could not be found.", "FAIL");
		}
	}

	public void selectDropDownUsingIndex(WebElement ele, int index) {
		try {
			waitForElementPresenc(ele);
			new Select(ele).selectByIndex(index);
			reportStep("The dropdown is selected with index " + index, "PASS");
		} catch (WebDriverException e) {
			reportStep("The element: " + ele + " could not be found.", "FAIL");
		}
	}

	public void selectDropDownUsingValue(WebElement ele, String value) {
		try {
			waitForElementPresenc(ele);
			new Select(ele).selectByValue(value);
			reportStep("The dropdown is selected with text " + value, "PASS");
		} catch (WebDriverException e) {
			reportStep("The element: " + ele + " could not be found.", "FAIL");
		}
	}

	public boolean verifyTitle(String title) {
		boolean bReturn = false;
		try {
			if (getTitle().equals(title)) {
				reportStep("The title of the page matches with the value :" + title, "PASS");
				bReturn = true;
			} else {
				reportStep("The title of the page:" + webDriver.getTitle() + " did not match with the value :" + title,
						"FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while verifying the title", "FAIL");
		}
		return bReturn;
	}

	public boolean verifyExactText(WebElement ele, String expectedText) {
		try {
			waitForElementPresenc(ele);
			if (getElementText(ele).equals(expectedText)) {
				reportStep("The text: " + getElementText(ele) + " matches with the value :" + expectedText, "PASS");
			} else {
				reportStep("The text " + getElementText(ele) + " doesn't matches the actual " + expectedText, "FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while verifying the Text", "FAIL");
		}
		return false;
	}

	public boolean verifyPartialText(WebElement ele, String expectedText) {
		try {
			waitForElementPresenc(ele);
			if (getElementText(ele).contains(expectedText)) {
				reportStep("The expected text contains the actual " + expectedText, "PASS");
				return true;
			} else {
				reportStep("The expected text doesn't contain the actual " + expectedText, "FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while verifying the Text", "FAIL");
		}
		return false;
	}

	public boolean verifyExactAttribute(WebElement ele, String attribute, String value) {
		try {
			waitForElementPresenc(ele);
			if (getTypedText(ele, attribute).equals(value)) {
				reportStep("The expected attribute :" + attribute + " value matches the actual " + value, "PASS");
				return true;
			} else {
				reportStep("The expected attribute :" + attribute + " value does not matches the actual " + value,
						"FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while verifying the Attribute Text", "FAIL");
		}
		return false;
	}

	public void verifyPartialAttribute(WebElement ele, String attribute, String value) {
		try {
			waitForElementPresenc(ele);
			if (getTypedText(ele, attribute).contains(value)) {
				reportStep("The expected attribute :" + attribute + " value contains the actual " + value, "PASS");
			} else {
				reportStep("The expected attribute :" + attribute + " value does not contains the actual " + value,
						"FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while verifying the Attribute Text", "FAIL");
		}
	}

	public void verifySelected(WebElement ele) {
		try {
			waitForElementPresenc(ele);
			if (ele.isSelected()) {
				reportStep("The element " + ele + " is selected", "PASS");
			} else {
				reportStep("The element " + ele + " is not selected", "FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("WebDriverException : " + e.getMessage(), "FAIL");
		}
	}

	public boolean verifyDisplayed(WebElement ele) {
		try {
			//waitForElementPresenc(ele);
			if (ele.isDisplayed()) {
				reportStep("The element " + ele + " is visible", "PASS");
				return true;
			} else {
				reportStep("The element " + ele + " is not visible", "FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("WebDriverException : " + e.getMessage(), "f");
		}
		return false;
	}

	public void switchToWindow(int index) {
		try {
			Set<String> allWindowHandles = webDriver.getWindowHandles();
			List<String> allHandles = new ArrayList<>();
			allHandles.addAll(allWindowHandles);
			webDriver.switchTo().window(allHandles.get(index));
		} catch (NoSuchWindowException e) {
			reportStep("The driver could not move to the given window by index " + index, "PASS");
		} catch (WebDriverException e) {
			reportStep("WebDriverException : " + e.getMessage(), "FAIL");
		}
	}

	public void switchToFrame(WebElement ele) {
		try {
			waitForElementPresenc(ele);
			webDriver.switchTo().frame(ele);
			reportStep("switch In to the Frame " + ele, "PASS");
		} catch (NoSuchFrameException e) {
			reportStep("WebDriverException : " + e.getMessage(), "FAIL");
		} catch (WebDriverException e) {
			reportStep("WebDriverException : " + e.getMessage(), "FAIL");
		}
	}

	public void switchToFrame(int index) {
		try {
			webDriver.switchTo().frame(index);
			reportStep("switch In to the Frame " + index, "PASS");
		} catch (NoSuchFrameException e) {
			reportStep("WebDriverException : " + e.getMessage(), "FAIL");
		} catch (WebDriverException e) {
			reportStep("WebDriverException : " + e.getMessage(), "FAIL");
		}
	}

	public void switchToFrame(String idOrName) {
		try {
			webDriver.switchTo().frame(idOrName);
			reportStep("switch In to the Frame " + idOrName, "PASS");
		} catch (NoSuchFrameException e) {
			reportStep("WebDriverException : " + e.getMessage(), "FAIL");
		} catch (WebDriverException e) {
			reportStep("WebDriverException : " + e.getMessage(), "FAIL");
		}

	}

	public void acceptAlert() {
		String text = "";
		try {
			Alert alert = webDriver.switchTo().alert();
			text = alert.getText();
			alert.accept();
			reportStep("The alert " + text + " is accepted.", "PASS", false);
		} catch (NoAlertPresentException e) {
			reportStep("There is no alert present.", "FAIL", false);
		} catch (WebDriverException e) {
			reportStep("WebDriverException : " + e.getMessage(), "FAIL", false);
		}
	}

	public void dismissAlert() {
		String text = "";
		try {
			Alert alert = webDriver.switchTo().alert();
			text = alert.getText();
			alert.dismiss();
			reportStep("The alert " + text + " is dismissed.", "PASS", false);
		} catch (NoAlertPresentException e) {
			reportStep("There is no alert present.", "FAIL", false);
		} catch (WebDriverException e) {
			reportStep("WebDriverException : " + e.getMessage(), "FAIL", false);
		}
	}

	public String getAlertText() {
		String text = "";
		try {
			Alert alert = webDriver.switchTo().alert();
			text = alert.getText();
		} catch (NoAlertPresentException e) {
			reportStep("There is no alert present.", "FAIL", false);
		} catch (WebDriverException e) {
			reportStep("WebDriverException : " + e.getMessage(), "FAIL", false);
		}
		return text;
	}

	public long takeSnap() {
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
		try {
			FileUtils.copyFile(webDriver.getScreenshotAs(OutputType.FILE),
					new File("./reports/images/" + number + ".png"));
		} catch (WebDriverException e) {
			System.out.println("The browser has been closed.");
		} catch (IOException e) {
			System.out.println("The snapshot could not be taken");
		}
		return number;
	}

	public void closeBrowser() {
		try {
			webDriver.close();
			reportStep("The browser is closed", "PASS", false);
		} catch (Exception e) {
			reportStep("The browser could not be closed", "FAIL", false);
		}
	}

	public void closeAllBrowsers() {
		try {
			webDriver.quit();
			reportStep("The opened browsers are closed", "PASS", false);
		} catch (Exception e) {
			reportStep("Unexpected error occured in Browser", "FAIL", false);
		}
	}

	public void append(WebElement ele, String data) {
		try {
			waitForElementPresenc(ele);
			ele.sendKeys(data);
			reportStep("The Data :" + data + " entered Successfully", "PASS");
		} catch (ElementNotInteractableException e) {
			reportStep("The Element " + ele + " is not Interactable", "FAIL");
		}
	}

	public void clear(WebElement ele) {
		try {
			waitForElementPresenc(ele);
			ele.clear();
			reportStep("The field is cleared Successfully", "PASS");
		} catch (ElementNotInteractableException e) {
			reportStep("The field is not Interactable", "FAIL");
		}
	}

	public String getElementText(WebElement ele) {
		String bReturn = "";
		try {
			waitForElementPresenc(ele);
			bReturn = ele.getText();
		} catch (WebDriverException e) {
			reportStep("The element: " + ele + " could not be found.", "FAIL");
		}
		return bReturn;
	}

	public String getBackgroundColor(WebElement ele, String prop) {
		String cssValue = "";
		try {
			waitForElementPresenc(ele);
			cssValue = ele.getCssValue("color");
			reportStep("The Element " + ele + "returns color", "PASS");
		} catch (Exception e) {
			reportStep("The Element " + ele + "returns color", "FAIL");
		}
		return cssValue;

	}

	public String getTypedText(WebElement ele, String attribute) {
		String bReturn = "";
		try {
			waitForElementPresenc(ele);
			bReturn = ele.getAttribute(attribute);
		} catch (WebDriverException e) {
			reportStep("The element: " + ele + " could not be found.", "FAIL");
		}
		return bReturn;
	}

	public boolean verifyDisappeared(WebElement ele) {
		try {
			waitForElementPresenc(ele);
			if (ele.isDisplayed()) {
				reportStep("The element " + ele + " is Displayed", "PASS");
				return true;
			} else {
				reportStep("The element " + ele + " is not Displayed", "FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("WebDriverException : " + e.getMessage(), "FAIL");
		}
		return false;
	}

	public boolean verifyEnabled(WebElement ele) {
		try {
			waitForElementPresenc(ele);
			if (ele.isEnabled()) {
				reportStep("The element " + ele + " is Enabled", "PASS");
				return true;
			} else {
				reportStep("The element " + ele + " is not Enabled", "FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("WebDriverException : " + e.getMessage(), "FAIL");
		}
		return false;
	}

	public Alert switchToAlert() {
		Alert alert = null;
		try {
			alert = webDriver.switchTo().alert();
			reportStep("The alert is switched successfully.", "PASS");
			return alert;
		} catch (NoAlertPresentException e) {
			reportStep("There is no alert present.", "FAIL");
		} catch (WebDriverException e) {
			reportStep("WebDriverException : " + e.getMessage(), "FAIL");
		}
		return alert;
	}

	public void typeAlert(String data) {
		try {
			Alert alert = webDriver.switchTo().alert();
			alert.sendKeys(data);
			alert.accept();
			reportStep("The alert " + data + " is accepted.", "PASS");
		} catch (NoAlertPresentException e) {
			reportStep("There is no alert present.", "FAIL");
		} catch (WebDriverException e) {
			reportStep("WebDriverException : " + e.getMessage(), "FAIL");
		}

	}

	public void switchToWindow(String title) {
		try {
			Set<String> allWindows = webDriver.getWindowHandles();
			for (String eachWindow : allWindows) {
				webDriver.switchTo().window(eachWindow);
				if (webDriver.getTitle().equals(title)) {
					break;
				}
				reportStep("The Window With Title :" + title + " is switched", "PASS");
			}
		} catch (NoSuchWindowException e) {
			reportStep("\"The Window With Title: " + title + " not found", "FAIL");
		} catch (WebDriverException e) {
			reportStep("WebDriverException : " + e.getMessage(), "FAIL");
		}

	}

	public void defaultContent() {
		try {
			webDriver.switchTo().defaultContent();
		} catch (Exception e) {
		}

	}

	public boolean verifyUrl(String url) {
		if (webDriver.getCurrentUrl().equals(url)) {
			reportStep("The url: " + url + " matched successfully", "PASS");
			return true;
		} else {
			reportStep("The url: " + url + " not matched", "FAIL");
		}
		return false;
	}

	public void waits(int count) {
		for (int i = 1; i <= count; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	
	
	public boolean retryingFindClick(WebElement element) {
	    boolean result = false;
	    int attempts = 0;
	    while(attempts < 2) {
	        try {
	        	element.click();
	            result = true;
	            break;
	        } catch(StaleElementReferenceException e) {
	        	webDriver.navigate().refresh();
	        }
	        attempts++;
	    }
	    return result;
	}
	
	
	

	public boolean waitForElementPresenc(WebElement element) {

		try {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).pollingEvery(Duration.ofMillis(60))
					.withTimeout(Duration.ofMillis(5)).ignoring(NoSuchElementException.class); // We need to ignore this
																								// exception.

			Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					WebElement ele = element;
					return ele;
				}
			};

			WebElement eleme = wait.until(function);
			return true; // Successfully element presence
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			reportStep("The element with locator " + locator + " not found.", "FAIL");
			return false;
		} catch (WebDriverException e) {
			e.printStackTrace();
			reportStep("Unknown exception occured while finding " + locator + " with the value " + locValue, "FAIL");
			return false;
		}
	}

	public void checkDropDownSorting(WebElement source) {
		Select sel = new Select(source);
		List<WebElement> options = sel.getOptions();
		List<String> originalDropDownValues = new ArrayList<>();
		for (WebElement dd : options) {
			originalDropDownValues.add(dd.getText());
		}
		List<String> sortedValues = new ArrayList<>(originalDropDownValues);
		Collections.sort(sortedValues);
		if (originalDropDownValues.equals(sortedValues)) {
			System.out.println("matched");
			reportStep("DropDown are sorted", "pass");
		} else {
			System.out.println("not matched");
			reportStep("DropDown are not sorted", "fail");
		}
		originalDropDownValues.clear();
		sortedValues.clear();
	}

	public Actions initiateActions(RemoteWebDriver driver) {

		if (actions==null) {
			actions = new Actions(webDriver);
		}

		return actions;
	}

	/**
	 * @author janapalaa Added on May 10, 2020
	 * @param targetElement
	 * @return WebElement
	 */
	public WebElement moveToElement(WebElement targetElement, RemoteWebDriver driver) {
		
		try {
			initiateActions(webDriver).moveToElement(targetElement);

			return targetElement;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * @author janapalaa Added on May 10, 2020
	 * @param xOffset
	 * @param yOffset
	 * @returns void xOffset Offset from the center - A negative value means
	 *          coordinates left from the element. yOffset Offset from the center -
	 *          A negative value means coordinates above the element.
	 */
	public void moveByOffset(int xOffset, int yOffset) {
		initiateActions(webDriver).moveByOffset(xOffset, yOffset);

	}

	/**
	 * @author janapalaa May 10, 2020
	 * @param targetElement
	 * @param xOffset
	 * @param yOffset       target element to move to. xOffset Offset from the
	 *                      center - A negative value means coordinates left from
	 *                      the element. yOffset Offset from the center - A negative
	 *                      value means coordinates above the element.
	 * @return WebElement
	 */
	public WebElement moveByTargetOffset(WebElement targetElement, int xOffset, int yOffset) {

		initiateActions(webDriver).moveToElement(targetElement, xOffset, yOffset);

		return targetElement;

	}

	public Actions mousclickAndHold(WebElement targetElement) {

		return initiateActions(webDriver).clickAndHold(targetElement);

	}

	public Actions mousclick(WebElement targetElement) {

		return initiateActions(webDriver).click(targetElement);

	}

	public Actions mousDoubleClick(WebElement targetElement) {
		return initiateActions(webDriver).doubleClick(targetElement);

	}

	public Actions mousContextClick(WebElement targetElement) {

		return initiateActions(webDriver).contextClick(targetElement);

	}

	public Actions mousKeysDown(Keys keys) {
		return initiateActions(webDriver).keyDown(keys);

	}

	public Actions mousKeysDownAtTarget(WebElement targetElement, Keys keys) {
		return initiateActions(webDriver).keyDown(targetElement, keys);

	}

	public Actions mousKeysUp(Keys keys) {
		return initiateActions(webDriver).keyUp(keys);

	}

	public Actions mousKeysUpAtTarget(WebElement targetElement, Keys keys) {
		return initiateActions(webDriver).keyUp(targetElement, keys);

	}

	public Actions mousDragNdDropByOffset(WebElement sourcelement, int xOffset, int yOffset) {

		return initiateActions(webDriver).dragAndDropBy(sourcelement, xOffset, yOffset);
	}

	public Actions mousDragNdDrop(WebElement sourceElement, WebElement targetElement) {
		return initiateActions(webDriver).dragAndDrop(sourceElement, targetElement);

	}

	public Actions mousReleaseAtTarget(WebElement targetElement) {
		return initiateActions(webDriver).release(targetElement);

	}

}
