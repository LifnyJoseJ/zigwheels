package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.UsedCars;
import base_pack.BaseClass;

public class Identify_UsedCars extends BaseClass{
	
	UsedCars car;
	
	
	
	@Test(priority =1, groups = {"regression" , "sanity"})
	void verify_url()
	{
		logger.info("Verifying URL");
		String ActualURL = p.getProperty("appURL");
		String ExpectedURL = driver.getCurrentUrl();
		Assert.assertEquals(ActualURL, ExpectedURL);
		
	}
	
	@Test(priority =2 , groups = {"regression" , "sanity"})
	void usedCars_menu() throws InterruptedException
	{
		logger.info("*********************** Starting Used Cars *************************");
		car = new UsedCars(driver);
		car.select_Cars();
		logger.info("Used car menu is hovered and Location is chosen as Chennai");
	}
	@Test(priority =3 , groups = {"regression" })
	void verify_head()
	{
		boolean value = car.verify_head_car();
		if(value==true)
		{
			logger.info("Test Case Passed");
			Assert.assertTrue(value);
		}
		else
		{
			Assert.fail();
		}
	}
	@Test(priority =4 , groups = {"regression"})
	void display_cars()
	{
		car.display_popularModels();
		logger.info("Popular Models are displayed");
		logger.info("*********************** Ending Used Cars *************************");
	}

		
}
	


