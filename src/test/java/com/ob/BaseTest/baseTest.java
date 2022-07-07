package com.ob.BaseTest;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class baseTest {

	public WebDriver driver;
	String browser="";
	public static ExtentReports report;
	public static ExtentTest test;

	@BeforeMethod
	@Parameters("Browser")
	public void launchingApp(String browser,Method method,ITestContext context) {
		
		test = report.startTest(method.getName());
		
		this.browser = browser;
		driver = launchApp();
		context.setAttribute("WebDriver", driver);
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
		report.endTest(test);
	}
	
	public WebDriver launchApp() {
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new InternetExplorerDriver();
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
