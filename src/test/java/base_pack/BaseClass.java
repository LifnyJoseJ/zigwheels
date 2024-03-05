package base_pack;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;

//import com.beust.jcommander.Parameter;

public class BaseClass {
	
	/*static public WebDriver driver;
	public Logger logger;
	public Properties p;
	//@Parameters({"browser"})
	@BeforeClass(groups = {"regression" , "smoke"})
	
	@Parameters({"os" , "browser"})
	public void getDriver(String os ,String br) throws IOException
	{
		//Loading Properties file
		FileReader file = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		p=new Properties();
		p.load(file);
		logger = LogManager.getLogger(this.getClass());
		
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
	 	{	
		
		DesiredCapabilities capabilities=new DesiredCapabilities();
		
		//os
		if(os.equalsIgnoreCase("windows"))
		{
			capabilities.setPlatform(Platform.WIN11);
		}
		else if(os.equalsIgnoreCase("mac"))
		{
			capabilities.setPlatform(Platform.MAC);
		}
		else
		{
			System.out.println("No matching os..");
			
		}
		
		//browser
		switch(br.toLowerCase())
		{
		case "chrome" : capabilities.setBrowserName("chrome"); break;
		case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
		default: System.out.println("No matching browser.."); return;
		}
		
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		
	    }
	else if(p.getProperty("execution_env").equalsIgnoreCase("local"))
	{
		
		switch(br.toLowerCase())
		{
		case "chrome" :
		{
			System.out.println("chrome");
			driver = new ChromeDriver();
		}
		break;
		case "edge"	: 
		{
			System.out.println("edge");
			driver=new EdgeDriver();
			
		}
		break;
		default : System.out.println("No Matching browser found!");
					return;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
		//driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(p.getProperty("appURL"));
	//return driver;
	}
		//return driver;
		
	}*/
	
	
	
	
	
	
	
	
	
	
	static public WebDriver driver;
	public Logger logger;
	public Properties p;
	
	
	@BeforeClass(groups= {"sanity","regression","master"})
	@Parameters({"os", "browser"})
	public void setup(String os, String br) throws IOException
	
	{
		//loading properties file
		 FileReader file=new FileReader(".//src//test//resources//config.properties");
		 p=new Properties();
		 p.load(file);
		
		
		//loading log4j 
		logger=LogManager.getLogger(this.getClass());//Log4j
				
				
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		 	{	
			
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching os..");
				return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome" : capabilities.setBrowserName("chrome"); break;
			case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
			default: System.out.println("No matching browser.."); return;
			}
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
			
		    }
		else if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			//launching browser based on condition - locally
			switch(br.toLowerCase())
			{
			case "chrome": 
			{
				System.out.println("chrome");
				driver=new ChromeDriver();
				break;
			}
			case "edge": 
			{
				System.out.println("edge");
				driver=new EdgeDriver(); 
				break;
			}
			default: System.out.println("No matching browser..");
						return;
			}
		}
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
		
		
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@AfterClass(groups = {"regression" ,"smoke"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;
		
	}
	
	
}