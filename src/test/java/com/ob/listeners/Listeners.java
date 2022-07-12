package com.ob.listeners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.ob.BaseTest.baseTest;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Listeners extends baseTest implements ITestListener {

public void onTestStart(ITestResult result) {
	// TODO Auto-generated method stub

}

public void onTestSuccess(ITestResult result) {
	// TODO Auto-generated method stub

}

public void onTestFailure(ITestResult result) {
	// TODO Auto-generated method stub
	System.out.println("Screenshot capture");

	Object obj = result.getInstance();
	WebDriver driver = ((baseTest)obj).driver;
	ExtentTest test = ((baseTest)obj).test;
	
	File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	try {
		String methodName = result.getMethod().getMethodName();
		String fileName = System.getProperty("user.dir")+ "/Screenshots/"+methodName+"_"+ com.ob.utilities.Utilities.getAccountNum() +".png";
		FileUtils.copyFile(srcFile, new File(fileName));
		test.log(LogStatus.FAIL, result.getMethod().getMethodName() +" is failed" + test.addScreenCapture(fileName));
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public void onTestSkipped(ITestResult result) {
	// TODO Auto-generated method stub

}

public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	// TODO Auto-generated method stub

}

public void onTestFailedWithTimeout(ITestResult result) {
	// TODO Auto-generated method stub

}

public void onStart(ITestContext context) {
	// TODO Auto-generated method stub

}

public void onFinish(ITestContext context) {
	// TODO Auto-generated method stub

}

}
