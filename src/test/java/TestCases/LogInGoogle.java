package TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.GoogleSignIn;
import base_pack.BaseClass;

public class LogInGoogle extends BaseClass {
	
	GoogleSignIn google;
	
	@Test(priority =1, groups = {"regression" , "sanity"})
	void click_login() throws InterruptedException
	{
		logger.info("*********************** Starting Google Account Login *************************");
		google = new GoogleSignIn(driver);
		//google.home();
		google.logIn();
		logger.info("Login button is clicked");
	}
	
	@Test(priority =2, groups = {"regression"})
	void click_google_opt()
	{
		google.google_logIn();
		logger.info("Continue with google option is clicked");
		google.enter_mailId();
		logger.info("Email Id is entered");
	}
	
	@Test(priority =3, groups = {"regression"})
	void verify_LoginPage()
	{
		boolean value =google.verify_LogInPage();
		if(value==true)
		{
			logger.info("Test Case Passed");
			Assert.assertEquals(value,true);
		}
		else
		{
			Assert.fail();
		}
	}
	
	@Test(priority =4, groups = {"regression"})
	void capture_msg() throws IOException, InterruptedException
	{
		google.capture_ErrorMsg();
		logger.info("Error message is captured");
		logger.info("*********************** Ending Google Account Login *************************");
	}


}
