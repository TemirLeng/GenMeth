package utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericMethods {
	private static WebDriver driver;

	public GenericMethods(WebDriver driver) {
		this.driver = driver;
	}

	public static WebDriver setup() {

		System.setProperty("webdriver.chrome.driver",
				"\\C:\\Users\\Nurkulov\\Documents\\selenuim dependencies\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	public WebElement getElement(String type, String locator) {
		type = type.toLowerCase();
		if (type.equals("id")) {
			return this.driver.findElement(By.id(locator));
		} else if (type.equals("css")) {
			System.out.println("Element found with css: " + locator);
			return this.driver.findElement(By.cssSelector(locator));
		} else if (type.equals("xpath")) {
			System.out.println("Element found with xpath: " + locator);
			return this.driver.findElement(By.xpath(locator));
		} else if (type.equals("name")) {
			System.out.println("Element found with name: " + locator);
			return driver.findElement(By.name(locator));
		} else if (type.equals("className")) {
			System.out.println("Element found with className: " + locator);
			return driver.findElement(By.className(locator));
		} else if (type.equals("linkText")) {
			System.out.println("Element found with linkText: " + locator);
			return driver.findElement(By.linkText(locator));
		} else if (type.equals("partialText")) {
			System.out.println("Element found with partialText: " + locator);
			return driver.findElement(By.partialLinkText(locator));
		} else {
			System.out.println("Locator type is not supported");
			return null;
		}

	}

	public List<WebElement> getElements(String type, String locator) {
		type = type.toLowerCase();
		List<WebElement> elementList = new ArrayList();
		if (type.equals("id")) {
			elementList = this.driver.findElements(By.id(locator));
		} else if (type.equals("css")) {
			elementList = this.driver.findElements(By.cssSelector(locator));
		} else if (type.equals("xpath")) {
			elementList = this.driver.findElements(By.xpath(locator));
		} else if (type.equals("name")) {
			elementList = driver.findElements(By.name(locator));
		} else if (type.equals("className")) {
			elementList = driver.findElements(By.className(locator));
		} else if (type.equals("linkText")) {
			elementList = driver.findElements(By.linkText(locator));
		} else if (type.equals("partialText")) {
			elementList = driver.findElements(By.partialLinkText(locator));
		} else if (type.equals("tagname")) {
			elementList = driver.findElements(By.tagName(locator));
		} else {
			System.out.println("Locator type is not supported");
		}
		if (elementList.isEmpty()) {
			System.out.println("Element not found with " + type + ": " + locator);
		} else {
			System.out.println("Element found with " + type + ": " + locator);
		}
		return elementList;

	}

	public static void sleep(int sec) {
		sec = sec * 1000;
		try {
			Thread.sleep(sec);
		} catch (Exception e) {
			System.out.println(e + " Exception thrown");
		}
	}

	public boolean isElementPresent(String locator, String type) {
		List<WebElement> elementList = getElements(locator, type);
		int size = elementList.size();
		if (size > 0) {
			return true;
		} else {
			return false;
		}
	}

	public WebElement waitForElement(By locator, int timeout) {
		WebElement element = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			System.out.println("Element appeared on the web page");
		} catch (Exception e) {
			System.out.println("Element ");
		}
		return element;
	}

	public void waitToClick(By locator, int timeout) {
		try {
			WebElement element = null;
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			System.out.println("Element is ready to be clicked");
			element.click();
		} catch (Exception e) {
			System.out.println("Element did not appear on the page.");
		}
	}

	public static WebDriver setupBrowser(String driverType) {
		driverType = driverType.toLowerCase();

		switch (driverType) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", Config.getPropValue("chromePath"));
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();

			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver", Config.getPropValue("ffPath"));
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();

			break;

		case "ie":
			System.setProperty("webdriver.ie.driver", Config.getPropValue("iePath"));
			driver = new InternetExplorerDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();

			break;

		default:
			System.setProperty("webdriver.chrome.driver", Config.getPropValue("chromePath"));
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			break;
		}
		return driver;
	}
}
