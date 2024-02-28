package newBikes;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Bikes {
	public static void main(String args[]) throws InterruptedException, IOException
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver =  new ChromeDriver();
		driver.get("https://www.zigwheels.com/newbikes");
		driver.manage().window().maximize();
		WebElement newBike =driver.findElement(By.xpath("//*[@class='h-d-nav fnt-16 ']/li[3]/a"));
		Actions act = new Actions(driver);
		act.moveToElement(newBike).perform();
		Thread.sleep(3000);
		//Upcomming
		driver.findElement(By.xpath("//*[@id=\"headerNewNavWrap\"]/nav/div/ul/li[3]/ul/li[5]/span")).click();
		WebElement manufacture = driver.findElement(By.xpath("//*[@id='makeId']"));
		//manufacture.click();
		Select honda = new Select(manufacture);
		honda.selectByVisibleText("Honda");
		List<WebElement> model = driver.findElements(By.xpath("//*[@class='p-15 pt-10 mke-ryt rel']/a"));
		int l = model.size();
		//System.out.println(l);
		WebElement viewMore = driver.findElement(By.xpath("//*[@id='modelList']/li[16]/span"));
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", viewMore);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<WebElement> price =driver.findElements(By.xpath("//*[@class='b fnt-15']"));
		//List<WebElement> model = driver.findElements(By.xpath("//*[@class='p-15 pt-10 mke-ryt rel']/a"));
		List<WebElement> launch_date = driver.findElements(By.xpath("//*[@class='clr-try fnt-14']"));
		
		for(int i=0;i <l;i++)
		{
			int amount;
			String p =price.get(i).getText();
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
				 String car = model.get(i).getText();
				 String cost = price.get(i).getText();
				 String date = launch_date.get(i).getText();
				 System.out.println("Model       :  "+car);
				 System.out.println("Price       :  "+cost);
				 System.out.println(date);
				 
			}
		}
			
	//<----------------------------------------------Used Cars---------------------------------------->	
		
		Thread.sleep(3000);
		WebElement usedCars = driver.findElement(By.xpath("//*[@class='h-d-nav fnt-16 ']/li[7]/a"));
		act.moveToElement(usedCars).perform();
		driver.findElement(By.xpath("//*[@class='h-d-dd h-col-1']/li/div[2]/ul/li[4]")).click();
		
		System.out.println("****************** Popular Models************************");
		List<WebElement> n = driver.findElements(By.xpath("//*[@class='zw-sr-secLev usedCarMakeModelList popularModels ml-20 mt-10']/li"));
		for(WebElement models:n)
		{
			String str = models.getText();
			System.out.println(str);
		}
		
		//<----------------------------------------------Google Login---------------------------------------->	
		driver.findElement(By.xpath("//*[@class='container mmv-scrl ']/ol/li[1]")).click();
		//login click
		driver.findElement(By.id("forum_login_wrap_lg")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[12]/div/div/div/div[1]/div/div[3]/div[6]/div")).click();
		Set<String> id = driver.getWindowHandles();
		List<String> list = new ArrayList(id);
		String childId = list.get(1);
		driver.switchTo().window(childId);
		driver.findElement(By.id("identifierId")).sendKeys("adcd@gmail.com");
		//next button
		WebElement next_btn = driver.findElement(By.xpath("//*[@id='identifierNext']/div/button/span"));
		next_btn.click();
		Thread.sleep(3000);
		String msg = driver.findElement(By.xpath("//*[@class='dEOOab RxsGPe']/div")).getText();
		System.out.println("*************************Error Message**********************");
		System.out.println(msg);
		TakesScreenshot ts = (TakesScreenshot) driver;
		File scr = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File(System.getProperty("user.dir") + "\\ScreenShot_Login\\Login_error_msg.png");
		FileUtils.copyFile(scr, trg);
		
		driver.quit();
	}
}





















