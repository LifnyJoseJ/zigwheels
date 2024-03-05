package TestCases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageObject.GoogleSignIn;
import PageObject.Upcomming_bikes;
import PageObject.UsedCars;
import base_pack.BaseClass;


public class IdentifyBikes extends BaseClass{
	//WebDriver driver;
	Upcomming_bikes bike;
	
	
	@Test(priority =1, groups = {"regression" , "sanity"})
	void verify_url()
	{
		logger.info("Verifying URL");
		String ActualURL = p.getProperty("appURL");
		String ExpectedURL = driver.getCurrentUrl();
		Assert.assertEquals(ActualURL, ExpectedURL);
	}
		@Test(priority =2 , groups= {"regression","sanity"})
		void upcomingMenu()
		{
		logger.info("*********************** Starting Upcomming Bikes*************************");
		bike = new Upcomming_bikes(driver);
		bike.select_upcommingBikes();
		logger.info("New Bikes menu is hovered and Upcoming Bikes is cliked");
		}
		
		
		
		@Test(priority = 3 , groups= {"regression","sanity"})
		void choose_manu()
		{
		bike.manufacture();
		logger.info("Manufacture HONDA is chosen");
		}
		
		@Test(priority =4 , groups= {"regression"})
		void verify_head()
		{
		boolean value = bike.verify_head();
		if(value==true)
		{
			logger.info("Test Case Passed");
			Assert.assertTrue(value);
		}
		else
		{
			Assert.fail();
		}
		bike.click_viewMore();
		logger.info("View More button is clicked");
		}
		
		
		@Test(priority =5,groups= {"regression"})
		void display_bikes()
		{
		bike.filter_bikes();
		logger.info("*********************** Ending Upcoming Bikes*************************");
		}
	
	
	

}
