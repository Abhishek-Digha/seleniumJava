package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Base {

	public WebDriver driver;
	public Properties prop;
	//String username = "ak47myself";
    //String accesskey = "7gkFIO9oOFVQfyE1VITQEzpEGD899Sb1K4hHvDGOe0wsfKSKCG";
    //static RemoteWebDriver driver = null;
    //String gridURL = "@hub.lambdatest.com/wd/hub";
    boolean status = false;
	public WebDriver initializeDriver() throws IOException {

		
//		DesiredCapabilities capabilities = new DesiredCapabilities();
//		capabilities.setCapability("browserName", "Chrome");
//		capabilities.setCapability("browserVersion", "114.0");
//		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
//		ltOptions.put("platformName", "Windows 10");
//		ltOptions.put("project", "Selenium With Java");
//		ltOptions.put("selenium_version", "3.141.59");
//		capabilities.setCapability("LT:Options", ltOptions);
//		capabilities.setCapability("build", "Test Login -3");
//        capabilities.setCapability("name", "LambdaTest Execution -3");
//		 try {
//	            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
//	        } catch (MalformedURLException e) {
//	            System.out.println("Invalid grid URL");
//	        } catch (Exception e) {
//	            System.out.println(e.getMessage());
//	        }
		
		prop = loadConfig();
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "/src/main/resources/resources/geckodriver");
			driver = new FirefoxDriver();
		} else if (browserName.equals("ie")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "/src/main/resources/IEDriverServer");
			driver = new InternetExplorerDriver();
		} else {
			System.out.println(browserName + " is not a valid browser");
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		return driver;

	}
	
	public Properties loadConfig() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/config.properties");

		prop.load(fis);
		return prop;
	}

}
