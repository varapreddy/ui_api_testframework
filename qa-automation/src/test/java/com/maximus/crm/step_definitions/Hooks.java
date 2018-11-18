package com.maximus.crm.step_definitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.maximus.crm.utilities.Driver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

	@Before
	public void setUp(Scenario scenario) {
		WebDriver driver = Driver.getDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@After
	public void tearDown(Scenario scenario) {
		//takes the screenshot only if fails
		if(scenario.isFailed()) {
		//taking a screenshot
		final byte[] screenshot = ((TakesScreenshot) 
				Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
		//adding the screenshot to the report
		scenario.embed(screenshot, "image/png");
		}
		Driver.closeDriver();
	}

	
}
