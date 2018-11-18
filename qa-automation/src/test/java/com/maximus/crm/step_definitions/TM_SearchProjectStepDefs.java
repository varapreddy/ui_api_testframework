package com.maximus.crm.step_definitions;

import com.maximus.crm.utilities.Driver;
import com.maximus.crm.utilities.TMUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.maximus.crm.pages.tm.TMProjectListPage;
import com.maximus.crm.pages.tm.TMSearchProjectPage;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TM_SearchProjectStepDefs extends TMUtilities {

    private String errorMessage;
    private WebDriver driver = Driver.getDriver();
    private TMProjectListPage tmProjectListPage = new TMProjectListPage();
    private TMSearchProjectPage tmSearchProjectPage = new TMSearchProjectPage();
//    TMViewProjectPage tmViewProjectPage = new TMViewProjectPage();


    /*this method distinguishes which search field should be used*/
    @When("I search for a project by {string} value {string}")
    public void i_search_for_a_project_by_value(String field, String value) {
        // this explicit wait allows the project details to be loaded
        waitForVisibility(tmProjectListPage.projects.get(0), 3000);
        switch (field) {
            case "state":
                tmProjectListPage.searchByState.sendKeys(value);
                break;
            case "project":
                tmProjectListPage.searchProject.sendKeys(value);
                break;
            case "program":
                tmProjectListPage.searchByProgram.sendKeys(value);
                break;
            case "client":
                tmProjectListPage.searchByClientStateAgency.sendKeys(value);
                break;

        }
        tmProjectListPage.search.click();

        System.out.println("Search button clicked");
    }


    /*this method asserts the error message*/
    @Then("I should see {string} message")
    public void i_should_see_message(String error) {
        System.out.println(error);
        assertTrue(error.contains(tmSearchProjectPage.errorMessage.getText()));

    }

    /*this method should display all projects according to the search criteria*/
    //Refactoring 09/27/18
    @Then("I should see all projects with {string} value {string}")
    public void i_should_see_all_projects_with_value(String field, String value) {
        int i = 0;
        for (i = 0; i <= tmProjectListPage.projects.size() - 1; i++) {
            // this explicit wait allows the project details to be loaded
           waitForVisibility(tmProjectListPage.projects.get(i), 3000);
           waitFor(2);
            switch (field) {
                case "state":
                    assertTrue(value.equalsIgnoreCase((tmProjectListPage.projects.get(i).
                            findElement(By.className(tmProjectListPage.projectStateClass)).getText().substring(0, value.length()))));
                    break;
                case "project":
                    assertTrue(value.equalsIgnoreCase((tmProjectListPage.projects.get(i).
                            findElement(By.cssSelector(tmProjectListPage.projectNameCSS)).getText().substring(0, value.length()))));
                    break;
                case "program":
                    assertTrue(value.equalsIgnoreCase((tmProjectListPage.projects.get(i).
                            findElement(By.cssSelector(tmProjectListPage.programNameCSS)).getText().substring(0, value.length()))));
                    break;
                case "client":
                    assertTrue(value.equalsIgnoreCase((tmProjectListPage.projects.get(i).
                            findElement(By.cssSelector(tmProjectListPage.clientNameCSS)).getText().substring(0, value.length()))));
                    break;
            }
        }/*printing the number of found projects*/
        System.out.println(i + " Projects found for " + field + " " + value);
    }

    /*this method pulls each project information and checks if the project information is displayed according to the wildcard search*/
    @Then("I should see the projects according to {string} {string} wild card")
    public void i_should_see_the_projects_according_to_wild_card(String field, String value) {
        int i = 0;
        for (i = 0; i <= tmProjectListPage.projects.size() - 1; i++) {
            switch (field) {
                case "state":
                    assertTrue(value.equalsIgnoreCase((tmProjectListPage.projects.get(i).
                                    findElement(By.className(tmProjectListPage.projectStateClass)).getText().substring(0, value.length()))),
                            "Failed on wildcard search by State" + i);
                    break;
                case "project":
                    assertTrue(value.equalsIgnoreCase((tmProjectListPage.projects.get(i).
                                    findElement(By.cssSelector(tmProjectListPage.projectNameCSS)).getText().substring(0, value.length()))),
                            "Failed on wildcard search by Project name" + i);
                    break;
                case "program":
                    assertTrue(value.equalsIgnoreCase((tmProjectListPage.projects.get(i).
                                    findElement(By.xpath(tmProjectListPage.programNameCSS)).getText().substring(0, value.length()))),
                            "Failed on wildcard searching by Program name" + i);
                    break;
                case "client":
                    assertTrue(value.equalsIgnoreCase((tmProjectListPage.projects.get(i).
                                    findElement(By.xpath(tmProjectListPage.clientNameCSS)).getText().substring(0, value.length()))),
                            "Failed on wildcard searching by Client/State Agemcy" + i);
                    break;
            }

        }
        System.out.println(i + " Projects found for " + field + " " + value);
    }

    /*this method searches for a project with no value in fields*/
    @When("I search for a project with empty search fields")
    public void i_search_for_a_project_with_empty_search_fields() {
        tmProjectListPage.search.click();
    }


    /*this method invokes the dropdown option to autocomplete search
     */
    @Then("I should see potential {string} with {string} autocomplete")
    public void i_should_see_potential_with_autocomplete(String field, String value) {
        List<WebElement> potentialValues = new ArrayList<>();
        switch (field) {
            case "state":
                potentialValues = tmProjectListPage.searchByState.findElements(By.xpath(tmProjectListPage.searchDropDownList));
                break;
            case "project":
                potentialValues = tmProjectListPage.searchProject.findElements(By.xpath(tmProjectListPage.searchDropDownList));
                break;
            case "program":
                potentialValues = tmProjectListPage.searchByProgram.findElements(By.xpath(tmProjectListPage.searchDropDownList));
                break;
            case "client":
                potentialValues = tmProjectListPage.searchByClientStateAgency.findElements(By.xpath(tmProjectListPage.searchDropDownList));
                break;

        }
        /* collects the dropdown options in a List and checks the options being according to search criteria */
        if (potentialValues.size() > 0) {
            for (WebElement el : potentialValues) {
                System.out.println(el.getText());
                assertTrue(value.equalsIgnoreCase(el.getText().substring(0, value.length())),
                        "Failed on autocomplete searching by " + field + " and " + value);
            }
        } else {
            System.out.println(tmSearchProjectPage.errorMessage.getText());
        }
    }

    /*this method verifies no project record is displayed */
    @Then("I should see no projects displayed")
    public void i_should_see_no_projects_displayed() {
        assertTrue(tmProjectListPage.projects.isEmpty());
    }

}


