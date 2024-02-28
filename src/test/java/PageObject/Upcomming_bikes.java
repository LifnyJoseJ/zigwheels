package PageObject;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;

public class Upcomming_bikes {
	WebDriver driver;
	
	//Constructor 
	public Upcomming_bikes(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
	}
	
	//Locators
	
	@FindBy(xpath=("//*[@class='h-d-nav fnt-16 ']/li[3]/a"))
	WebElement link_newBikes;
	
	@FindBy(xpath=("//*[@id=\"headerNewNavWrap\"]/nav/div/ul/li[3]/ul/li[5]/span"))
	WebElement opt_upcomming;
	
	@FindBy(xpath=("//*[@id='makeId']"))
	WebElement drp_manufacture;
	
	@FindBy(xpath=("//*[@class='p-15 pt-10 mke-ryt rel']/a"))
	List<WebElement> txt_carModel;
	
	@FindBy(xpath=("//*[@id='modelList']/li[16]/span"))
	WebElement btn_viewMore;
	
	@FindBy(xpath=("//*[@class='b fnt-15']"))
	List<WebElement> car_price;
	
	@FindBy(xpath=("//*[@class='clr-try fnt-14']"))
	List<WebElement> launch_date;
	
	@FindBy(xpath=("//*[@class='zw-con pl-15 pr-15 mb-0']/div/h1"))
	WebElement head_honda;
	
	//Actions
	
	public void select_upcommingBikes()
	{
		Actions act = new Actions(driver);
		act.moveToElement(link_newBikes).perform();
		opt_upcomming.click();
	}
	public void manufacture()
	{
		Select honda = new Select(drp_manufacture);
		honda.selectByVisibleText("Honda");
		
	}
	
	public boolean verify_head()
	{
		try
		{
			return(head_honda.isDisplayed());
		}catch(Exception e)
		{
			return(false);
		}
	}
	
	public void click_viewMore()
	{
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", btn_viewMore);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void filter_bikes()
	{
		int l = txt_carModel.size();
		for(int i=0;i<l;i++)
		{
			int amount;
			String p = car_price.get(i).getText();
			p=p.replace("Rs. ","");
			if(p.contains("Lakh"))
			{
			double d = Double.parseDouble(p.substring(p.indexOf(""), p.indexOf(" Lakh")-1));
			 amount =(int) (d*100000);
			}
			else
			{
				if(p.contains(","))
				p =p.replace(",","");
				 amount = Integer.parseInt(p);
			}
			if(amount < 400000)
			{
				
				// p =bike.get(i).getText();
				 System.out.println("***********************************************");
				 String car = txt_carModel.get(i).getText();
				 String cost =  car_price.get(i).getText();
				 String date = launch_date.get(i).getText();
				 System.out.println("Model       :  "+car);
				 System.out.println("Price       :  "+cost);
				 System.out.println(date);
				 
			}
		}
		Shutterbug.shootPage(driver,Capture.FULL, true).save("C:\\Users\\2303711\\eclipse-workspace\\Hackathon\\ScreenShot_Login\\Upcomming_bikes");
	}

}
