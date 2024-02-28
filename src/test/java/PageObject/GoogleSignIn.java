package PageObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;

public class GoogleSignIn {

	WebDriver driver;
	//Constructor
	public GoogleSignIn(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	//Locators
	@FindBy(xpath=("//*[@class='container mmv-scrl ']/ol/li[1]"))
	WebElement btn_home;
	
	@FindBy(id=("forum_login_wrap_lg"))
	WebElement btn_login;
	
	@FindBy(xpath=("/html/body/div[12]/div/div/div/div[1]/div/div[3]/div[6]/div"))
	WebElement btn_google;
	
	@FindBy(id=("identifierId"))
	WebElement txt_emailId;
	
	@FindBy(xpath=("//*[@id='identifierNext']/div/button/span"))
	WebElement btn_next;
	
	@FindBy(xpath=("//*[@class='dEOOab RxsGPe']/div"))
	WebElement msg_error;
	
	@FindBy(xpath=("//*[@class='rQszV ']"))
	WebElement signIn;
	
	
	//Actions
	public void home()
	{
		btn_home.click();
	}
	public void logIn() throws InterruptedException
	{
	
		btn_login.click();
		Thread.sleep(5000);
		
	}
	
	public void google_logIn()
	{
		btn_google.click();
	}
	
	public void enter_mailId() 
	{
		Set<String> id = driver.getWindowHandles();
		List<String> list = new ArrayList(id);
		String childId = list.get(1);
		driver.switchTo().window(childId);
		//Thread.sleep(3000);
		txt_emailId.sendKeys("adcd@gmail.com");
	}
	
	public boolean verify_LogInPage()
	{
		try
		{
			return(signIn.isDisplayed());
		}catch(Exception e)
		{
			return(false);
		}
	}
	
	public void capture_ErrorMsg() throws IOException, InterruptedException
	{
		btn_next.click();
		Thread.sleep(3000);
		String msg =  msg_error.getText();
		System.out.println("*************************Error Message**********************");
		System.out.println(msg);
//		TakesScreenshot ts = (TakesScreenshot) driver;
//		File scr = ts.getScreenshotAs(OutputType.FILE);
//		File trg = new File(System.getProperty("user.dir") + "\\ScreenShot_Login\\Login_error_msg.png");
//		FileUtils.copyFile(scr, trg);
		Shutterbug.shootPage(driver,Capture.FULL, true).save("C:\\Users\\2303711\\eclipse-workspace\\Hackathon\\ScreenShot_Login\\GoogleLogin");
	}
}
