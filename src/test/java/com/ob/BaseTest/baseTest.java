package com.ob.BaseTest;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.ob.utilities.Utilities;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class baseTest {

	public WebDriver driver;
	String browser="";
	public static ExtentReports report;
	public ExtentTest test;
	String HubUrl;

	@BeforeMethod
	@Parameters("Browser")
	public void launchingApp(String browser,Method method,ITestContext context) throws IOException {
		HubUrl = Utilities.getProperty("HubUrl");
		this.browser = browser;
		test = report.startTest(method.getName());
		driver = launchApp();
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
		report.endTest(test);
	}
	
	public WebDriver launchApp() throws MalformedURLException {
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setCapability("platformName", "Windows");
			driver = new RemoteWebDriver(new URL(HubUrl), chromeOptions);
		}else if(browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setCapability("platformName", "Windows");
			driver = new RemoteWebDriver(new URL(HubUrl), firefoxOptions);
		}else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOpt = new EdgeOptions();
			edgeOpt.setCapability("platformName","Windows");
			driver = new RemoteWebDriver(new URL(HubUrl), edgeOpt);
		}
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		return driver;
	}
	
	
	@BeforeSuite
	public void initializeReport() {
		report = new ExtentReports("ExtentReports.html");
	}
	
	@AfterSuite
	public void closeReport() {
		report.flush();
	}
		
}
