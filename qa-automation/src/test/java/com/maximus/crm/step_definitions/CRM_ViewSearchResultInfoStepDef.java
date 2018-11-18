package com.maximus.crm.step_definitions;

import com.maximus.crm.pages.crm.CRMConsumerSearchResultPage;
import com.maximus.crm.pages.crm.CRMContactRecordUIPage;
import com.maximus.crm.utilities.CRMUtilities;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class CRM_ViewSearchResultInfoStepDef extends CRMUtilities {


    CRMConsumerSearchResultPage searchResult = new CRMConsumerSearchResultPage();
    CRMContactRecordUIPage contactRecord = new CRMContactRecordUIPage();
    private List<String> searchResults = new ArrayList<String>();

    @Then("I see all Search results have SSN field value masked in {string} format")
    public void i_see_all_Search_results_have_SSN_field_value_masked_in_format(String format) {
        for (WebElement e : searchResult.SSNs) {
            if (e.getText().equalsIgnoreCase("")) {
                continue;
            }
            searchResults.add(e.getText());
        }
        for (String ssn : searchResults) {
            assertTrue(ssn.equals(format), "SSN has to be displayed in " + format + ".");
        }
    }


    @Then("I see unmasking button displayed")
    public void i_see_unmasking_button_displayed() {
        assertTrue(searchResult.ssnEyeUnMaskButton.isDisplayed());
        waitFor(10);
    }

    @When("I and click on unmasking button")
    public void i_and_click_on_unmasking_button() {
        waitFor(2);
        searchResult.ssnEyeUnMaskButton.click();


        assertTrue(searchResult.ssnEyeMaskButton.isDisplayed());
    }

    @Then("I see SSN {int} digits value unmasked")
    public void i_see_SSN_digits_value_unmasked(Integer snnLength) {
        for (WebElement e : searchResult.SSNs) {
            if (e.getText().equalsIgnoreCase("")) {
                continue;
            }
            searchResults.add(e.getText());
        }
        for (String ssn : searchResults) {
            ssn= ssn.replaceAll("-", ""); //refactored
            assertTrue(ssn.matches("[0-9]*"), "SSN has to contain only digits.");
            assertTrue(ssn.length() == snnLength, "SSN has to have " + snnLength + " digits.");
        }

    }


    @When("I search for an existing contact by SSN {string}")
    public void i_search_for_an_existing_contact_by_SSN(String expected) {
        clearAndFillText(contactRecord.ssnSearch, expected);
        waitFor(1);
        String actual = contactRecord.ssnSearch.getAttribute("value");
        assertTrue(expected.equals(actual), "Searching wrong SSN criteria");
    }

    @Then("I see all search results according to SSN {string}")
    public void i_see_all_search_results_according_to_SSN(String expected) {
        waitFor(2);
        for (WebElement e : searchResult.SSNs) {
            searchResults.add(e.getText());
        }
        for (String actualSsn : searchResults) {
            assertTrue(actualSsn.equals(expected), "SSN search result is incorrect");
        }

    }

    @When("I search for an existing contact by first name {string}")
    public void i_search_for_an_existing_contact_by_first_name(String expected) {
        clearAndFillText(contactRecord.firstName, expected);
        waitFor(1);
        String actual = contactRecord.firstName.getAttribute("value");
        assertTrue(expected.equalsIgnoreCase(actual.substring(0, expected.length())), "Searching wrong first name criteria");

    }

    @Then("I see all search results according to the first name {string}")
    public void i_see_all_search_results_according_to_the_first_name(String expected) {
        waitFor(2);
        for (WebElement e : searchResult.FirstNames) {
            searchResults.add(e.getText());
        }
        for (String actualFName : searchResults) {
            assertTrue(expected.equalsIgnoreCase(actualFName.substring(0, expected.length())), "First name search result is incorrect");
        }
    }

    @When("I search for an existing contact by last name {string}")
    public void i_search_for_an_existing_contact_by_last_name(String expected) {
        clearAndFillText(contactRecord.lastName, expected);
        waitFor(1);
        String actual = contactRecord.lastName.getAttribute("value");
        assertTrue(expected.equalsIgnoreCase(actual.substring(0, expected.length())), "Searching wrong last name criteria");
    }

    @Then("I see all search results according to the last name {string}")
    public void i_see_all_search_results_according_to_the_last_name(String expected) {
        waitFor(2);
        for (WebElement e : searchResult.LastNames) {
            searchResults.add(e.getText());
        }
        for (String actualLName : searchResults) {
            assertTrue(expected.equalsIgnoreCase(actualLName.substring(0, expected.length())), "Last name search result is incorrect");
        }
    }

    @When("I search for an existing contact by DOB {string}")
    public void i_search_for_an_existing_contact_by_DOB(String expected) {
        clearAndFillText(contactRecord.dobSearch, expected);
        waitFor(1);
        String actual = contactRecord.dobSearch.getAttribute("value");
        assertTrue(expected.equals(actual), "Searching wrong DOB criteria");
    }

    @Then("I see all search results according to DOB {string}")
    public void i_see_all_search_results_according_to_DOB(String expected) {
        waitFor(2);
        for (WebElement e : searchResult.DOBs) {
            searchResults.add(e.getText());
        }
        for (String actualDOB : searchResults) {
            assertTrue(actualDOB.equals(expected), "DOB search result is incorrect");
        }
    }

    @When("I search for an existing contact by Unique ID {string}")
    public void i_search_for_an_existing_contact_by_Unique_ID(String expected) {
        clearAndFillText(contactRecord.uniqueIDSearch, expected);
        waitFor(1);
        String actual = contactRecord.uniqueIDSearch.getAttribute("value");
        assertTrue(expected.equals(actual), "Searching wrong Unique ID criteria");
    }

    @Then("I see all search results according to Unique ID {string}")
    public void i_see_all_search_results_according_to_Unique_ID(String expected) {
        waitFor(2);
        for (WebElement e : searchResult.LastNames) {
            searchResults.add(e.getText());
        }
        for (String actualID : searchResults) {
            assertTrue(actualID.equals(expected), "Unique ID search result is incorrect");
        }
    }

    @When("I search for a Consumer with blank fields")
    public void i_search_for_a_Consumer_with_blank_fields() {
        assertTrue(contactRecord.firstName.getAttribute("value").isEmpty(), "First name field has value");
        assertTrue(contactRecord.lastName.getAttribute("value").isEmpty(), "Last name field has value");
        assertTrue(contactRecord.ssnSearch.getAttribute("value").isEmpty(), "SSN field has value");
        assertTrue(contactRecord.dobSearch.getAttribute("value").isEmpty(), "DOB field has value");
       //refactoring 10/25/18 till the Unique ID field not being displayed clarified
        // assertTrue(contactRecord.uniqueIDSearch.getAttribute("value").isEmpty(), "Unique ID field has value");
    }

    @Then("I see warning to enter search parameter")
    public void i_see_warning_to_enter_search_parameter() {
        assertTrue(contactRecord.enterSearchParametersWarning.isDisplayed(), "Warning: 'Please enter the search parameters' is displayed");
    }

    @Then("I see No Records Available is displayed")
    public void i_see_no_records_available_is_displayed() {
       assertTrue(textIsNotPresent("contactRecord.noRecordsAvailableMessage"), "'No Records Available' message is not displayed");
    }

    @Then("I see no results are displayed")
    public void i_see_no_results_are_displayed() {
        assertTrue(contactRecord.noRecordsAvailableMessage.isDisplayed(), "'No Records Available' message is not displayed");
    }

}

