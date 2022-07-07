package com.ob.tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.testng.annotations.Test;

import com.ob.BaseTest.baseTest;
import com.ob.pages.AdminHomePage;
import com.ob.pages.AdminLoginPage;
import com.ob.pages.DepositPage;
import com.ob.pages.ManageAccountPage;
import com.ob.pages.NewAccountPage;
import com.ob.pages.UserHomePage;
import com.ob.pages.UserLoginPage;
import com.ob.utilities.Utilities;
import com.relevantcodes.extentreports.LogStatus;

public class DepositAmount extends baseTest {
Logger log;
String UserName;
String Password;
String adminURL;
String fname = "Venkat";
String lname = "Krishna";
String middleName = "VK";
String emailId  = "onlinetestinghelp" + Utilities.getRandomNum() +"@gmail.com";
String password = "Online@123";
int pinNumber = 1234;
int begBalance = 10000;
String accNo = String.valueOf(Utilities.getAccountNum());
String depositAmt = "10000";
String balance="";
	
	@Test
	public void depositAmount() throws IOException, InterruptedException {
		try {
			log = (Logger) LogManager.getLogger(creatingUser.class);
				
				//Read properties file
				adminURL = Utilities.getProperty("AdminUrl");
				UserName = Utilities.getProperty("AdminUserName");
				Password = Utilities.getProperty("AdminPassword");
				
				//Navigate to Admin Login 
				log.info("Test Execution Started");
				driver.get(adminURL);

				AdminLoginPage al = new AdminLoginPage(driver);
				al.login(UserName, Password);
				AdminHomePage ah = new AdminHomePage(driver);
				ah.verifyAdminLogin();
				
				//Click on New User
				ah.clickOnAccManagement();
				
				//New Account page
				NewAccountPage nc = new NewAccountPage(driver);
				nc.clickOnNewAccount();		
				nc.createNewAccount(accNo,fname, lname, middleName, emailId,password,pinNumber, begBalance);
				
				ManageAccountPage ma = new ManageAccountPage(driver);
				ma.enterAccountNo(accNo);
				Thread.sleep(3000);
				ma.verifyAccountNo(accNo);
				
				//Click on Transaction
				ah.clickOnTransaction();
				
				//Click on Deposit
				DepositPage dp = new DepositPage(driver);
				dp.clickOnDepositLink();
				dp.depositAmt(accNo, depositAmt);
				
				//Get latest balance
				AdminHomePage ahp = new AdminHomePage(driver);
				ahp.clickOnAccManagement();
				ahp.clickOnManageAccount();
				ma.enterAccountNo(accNo);
				Thread.sleep(3000);
				balance = ma.getCurrentBalance();

				//Login with newly created user
				driver.get(Utilities.getProperty("UserUrl"));
				
				UserLoginPage ulp = new UserLoginPage(driver);
				ulp.login(emailId, password);
				
				UserHomePage uhp = new UserHomePage(driver);
				uhp.verifyAccountNumber(accNo);
				
				//Verify Deposited amount in user login
				uhp.verifyBalance(balance);
			
//				test.log(LogStatus.PASS,"New User Creation is done successfully");
//				log.info("New User creation is done successfully");
				//Assert.assertFalse(true);
		}catch(Exception e) {
			log.info("New User is not successful");
			e.printStackTrace();
		}
		
	}
}
