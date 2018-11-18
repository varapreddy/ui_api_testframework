package com.maximus.crm.runners;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		plugin = {"pretty",
				"html:target/cucumber-report",
				"json:target/cucumber.json"
		},
		tags = "@regression",
		features = {"src/test/resources/com/maximus/crm/features"},
		glue = "com/maximus/crm/step_definitions",
		dryRun= false
		)

public class UIRegressionRunner extends AbstractTestNGCucumberTests {

}
