package com.maximus.crm.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {

	private static WebDriver driver;
	
	public static WebDriver getDriver() {
		if (driver == null) {
			switch (ConfigurationReader.getProperty("browser")) {
			
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
				
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
				
			case "ie":
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				break;
				
			case "ghost":
				WebDriverManager.phantomjs().setup();
				driver = new PhantomJSDriver();
				break;
				
			default:
				driver = new HtmlUnitDriver();
			}
		}
		return driver;
	}
	
	public static void closeDriver() {
		if (driver !=null) {
			driver.quit();
			driver = null;
		}
	}
}
