package com.maximus.crm.runners;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		plugin = {"pretty",
				"html:target/cucumber-report",
				"json:target/cucumber.json"
		},
		tags = "@API-Regression",
		features = {"src/test/resources/api.features"},
		glue = "com/maximus/crm/api_step_definitions",
		dryRun= false
		)

public class APIRegressionRunner extends AbstractTestNGCucumberTests {

}
