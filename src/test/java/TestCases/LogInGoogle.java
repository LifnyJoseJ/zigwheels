package TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.GoogleSignIn;
import base_pack.BaseClass;

public class LogInGoogle extends BaseClass {
	
	GoogleSignIn google;
	
	@Test(priority =1, groups = {"regression" , "sanity"})
	void verify_url()
	{
		logger.info("Verifying URL");
		String ActualURL = p.getProperty("appURL");
		String ExpectedURL = driver.getCurrentUrl();
		Assert.assertEquals(ActualURL, ExpectedURL);
		
	}
	@Test(priority =2, groups = {"regression" , "sanity"})
	void click_login() throws InterruptedException
	{
		logger.info("*********************** Starting Google Account Login *************************");
		google = new GoogleSignIn(driver);
		//google.home();
		google.logIn();
		logger.info("Login button is clicked");
	}
	
	@Test(priority =3, groups = {"regression"})
	void click_google_opt() throws InterruptedException
	{
		google.google_logIn();
		logger.info("Continue with google option is clicked");
		google.switch_driver();
		//google.enter_email_id();
		logger.info("Driver is switched");
	}
	
	@Test(priority =4, groups = {"regression"})
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
	//https://github.com/LifnyJoseJ/zigwheels.git
	@Test(priority =5, groups = {"regression"})
	void capture_msg() throws IOException, InterruptedException
	{
		google.enter_email_id();
		boolean status = google.verify_errormsg();
		if(status==true)
		{
			Assert.assertTrue(status);
			google.capture_errormsg();
			logger.info("Error message is captured");
		}
		else
		{
			Assert.fail();
			logger.info("Unable to capture Errror mesage");
		}
		logger.info("*********************** Ending Google Account Login *************************");
	}


}