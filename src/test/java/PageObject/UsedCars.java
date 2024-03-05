package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;

public class UsedCars {
	
	WebDriver driver;
	//Constructor
	
	public UsedCars(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	//Locators
	@FindBy(xpath=("//*[@class='h-d-nav fnt-16 ']/li[7]/a"))
	WebElement link_usedCar;
	
	@FindBy(xpath=("//*[@class='h-d-dd h-col-1']/li/div[2]/ul/li[4]"))
	WebElement car_location;
	
	@FindBy(xpath=("//*[@class='zw-sr-secLev usedCarMakeModelList popularModels ml-20 mt-10']/li"))
	List<WebElement> car_models;
	
	@FindBy(xpath=("//*[@class='zm-cmn-colorBlack ml-30 mob-nonclick div-h3 mt-20 mb-10']"))
	WebElement head_popularModel;
	
	@FindBy(xpath=("//*[@class='col-lg-12 pt-20 pl-25 pr-25']/h1"))
	WebElement chennai_cars;
	
	//Actions
	
	public void select_Cars() throws InterruptedException
	{
		Actions act = new Actions(driver);
		act.moveToElement(link_usedCar).perform();//Used Cars
		car_location.click();//chennai
		//Thread.sleep(3000);
	}
	
	public boolean verify_head_car()
	{
		try
		{
			return(chennai_cars.isDisplayed());
		}catch(Exception e)
		{
			return(false);
		}
	}
	
	public void display_popularModels()
	{
		
		System.out.println("****************** Popular Models ************************");
		for(WebElement models:car_models)
		{
			String str = models.getText();
			System.out.println(str);
		}
		Shutterbug.shootPage(driver,Capture.FULL, true).save("C:\\Users\\2303711\\eclipse-workspace\\Hackathon\\ScreenShot_Login\\Used_Cars");
	}
}
