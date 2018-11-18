package com.maximus.crm.step_definitions;

import com.maximus.crm.pages.crm.CRMContactRecordUIPage;
import com.maximus.crm.utilities.CRMUtilities;
import com.maximus.crm.utilities.ConfigurationReader;
import com.maximus.crm.utilities.Driver;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertTrue;


public class CRM_ContactRecordUIStepDef extends CRMUtilities {
    private WebDriver driver = Driver.getDriver();
    CRMUtilities crmUtils = new CRMUtilities();

    CRMContactRecordUIPage contactRecord = new CRMContactRecordUIPage();
    Map<String, String> newConsumer;
    public String userNameData = ConfigurationReader.getProperty("login");
    public String password = ConfigurationReader.getProperty("password");

    String comments = getCellValueBySheetRowAndColoumn("Reasons", "Valid ", "Comments");

    @Given("I logged into CRM and click on initiate contact")
    public void i_logged_into_CRM_and_click_on_initiate_contact() {
        driver.get(ConfigurationReader.getProperty("crmPageURL"));
        login(userNameData, password);
        waitForVisibility(contactRecord.initContact, 2);
        contactRecord.initContact.click();
        assertTrue(contactRecord.contactInProgressGreenSign.isDisplayed());
    }

    //this method verifies the system provides the local time in <dd/mm/yyyy> format
    @When("I verify Contact Start Date is captured")
    public void i_verify_Contact_Start_Date_is_captured() {
        waitForVisibility(contactRecord.headerDate, 2);
        String actualDate = contactRecord.headerDate.getText();
        String expectedDate = getCurrentDate();
        assertTrue(actualDate.equals(expectedDate));
    }


    //this method verifies the system provides the local time in <hh:mm a> format
    @Then("I verify Contact Start Time is captured")
    public void i_verify_Contact_Start_Time_is_captured() {
        waitForVisibility(contactRecord.contactStart, 5);
        String actualTime = contactRecord.contactStart.getText();
        String expectedTime = timeNow();
        assertTrue(actualTime.endsWith(expectedTime));
        System.out.println(actualTime + " " + expectedTime);

    }

    /*Refactoring 09/23/18*/
    @Then("I verify Contact Duration has no value while in progress")
    public void i_verify_Contact_Duration_has_no_value_while_in_progress() {
        String progressDuration = contactRecord.contactDurationValue.getText();
        assertTrue(progressDuration.length() == 8, "Call Duration has incorrect value ");

    }

    @Then("I verify Contact Duration Time is captured")
    public void i_verify_Contact_Duration_Time_is_captured() {
        int callDuration = 15;
        int expectedWaitTime = 1;
        String expectedDuration = "";
        waitFor(callDuration);
        contactRecord.stopContact.click();
        waitFor(expectedWaitTime);

        String actualDuration = contactRecord.contactDurationValue.getText();
        expectedDuration = callDuration + expectedWaitTime + "";
        assertTrue(actualDuration.endsWith(expectedDuration));


    }

    @Then("I verify the mandatory fields for contact initiation")
    public void i_verify_the_mandatory_fields_for_contact_initiation() {
//todo check on implementation to be done
    }

    @Given("I search for an existing contact by criteria")
    public void i_search_for_an_existing_contact_by_criteria() {
        contactRecord.firstName.sendKeys("Ethan"); //refactoring
        contactRecord.searchButton.click();
        waitFor(1);
        assertTrue(contactRecord.searchResultSign.isDisplayed());

    }

    /*This method links a consumer to an existing Case or Consumer profile*/
    @Then("I link the contact to an existing Case or Consumer Profile")
    public void i_link_the_contact_to_an_existing_Case_or_Consumer_Profile() {
        contactRecord.linkRecordButton.click();
        assertTrue(contactRecord.linkRecordButton.isDisplayed());
        assertTrue(contactRecord.unLink.isDisplayed());
        //todo choosing an existing project (not implemented yet) 08/24/18
    }

    /*Refactoring 09/23/18*/
    @Given("I see {string} field accept only letters")
    public void i_see_field_accept_only_letters(String field, List<String> texts) {

        for (String text : texts) {
            String actual = "";
            switch (field) {
                case "firstName":
                    clearAndFillText(contactRecord.firstName, text);
                    waitFor(1);
                    actual = contactRecord.firstName.getAttribute("value");
                    break;
                case "lastName":
                    clearAndFillText(contactRecord.lastName, text);
                    waitFor(1);
                    actual = contactRecord.lastName.getAttribute("value");
                    break;
            }
            assertTrue(hasOnlyLettersSpaces(actual));
        }
    }


    @Then("I see {string} field accept only {int} characters")
    public void i_see_field_accept_only_characters(String field, int stringLength) {

        String actual = "";
        String sentText = createTextString(stringLength * 4);
        switch (field) {
            case "firstName":
                clearAndFillText(contactRecord.firstName, sentText);
                waitFor(1);
                actual = contactRecord.firstName.getAttribute("value");
                break;
            case "lastName":
                clearAndFillText(contactRecord.lastName, sentText);
                waitFor(1);
                actual = contactRecord.lastName.getAttribute("value");
                break;
            case "otherReason":
                clearAndFillText(contactRecord.otherReasonComments, sentText);
                waitFor(1);
                actual = contactRecord.otherReasonComments.getText();
        }
        assertTrue(actual.length() <= stringLength);


    }

    @And("I click on {string} type of call option in {string} dropdown")
    public void i_click_on_type_of_call_option_in_dropdowm(String option, String field) {
        selectDropDown(contactRecord.contactType, option);
    }

    /*this method checks if called field has listed dropdown options to be displayed*/
    @Then("I should see following dropdown options for {string} field displayed")
    public void i_should_see_following_dropdown_options_for_field_displayed(String field, List<String> options) {
        int count = 0;
        for (String option : options) {
            switch (field) {
                case "consumer type":
                    selectDropDown(contactRecord.consumerType, option);
                    break;
                case "contact type":
                    selectDropDown(contactRecord.contactType, option);
                    break;
                case "contact channel":
                    selectDropDown(contactRecord.contactChannelType, option);
                    break;
                case "preferred language":
                    selectDropDown(contactRecord.preferredLanguage, option);
                    break;
                case "contact reason":
                    while (count == 0) {
                        waitFor(1);
                        contactRecord.expendReasonButton.click();
                        count++;
                    }
                    selectDropDown(contactRecord.contactReason, option);
                    break;
                case "contact action":
//refactoring 10/23/18
                    singleSelectFromMultipleOptionDropDown(contactRecord.contactAction, option);

                    break;
				case "call campaign reference":
                    selectDropDown(contactRecord.callCampaignReference,option);
                    break;
                case "outcome of contact":
                    selectDropDown(contactRecord.outcomeOfContact,option);
                    break;
                
            }
        }
    }

    /*this method checks if text of the element is present on the page*/
    //refactoring 10/23/18
    @When("I should see Contact Action Dropdown disabled")
    public void i_should_see_Contact_Action_Dropdown_disabled() {
        assertTrue(IsElementDisplayed(contactRecord.contactAction), "Contact Action dropdown should not be displayed");
    }

    @When("I populate Search criteria fields for a new consumer")
    public void i_populate_Search_criteria_fields_for_a_new_consumer() {
        newConsumer = new HashMap<>(crmUtils.getNewTestData());
        //System.out.println(newConsumer.keySet());
        clearAndFillText(contactRecord.firstName, (newConsumer.get("name")));
        clearAndFillText(contactRecord.lastName, (newConsumer.get("surname")));
        assertTrue(newConsumer.get("name").equalsIgnoreCase(contactRecord.firstName.getAttribute("value")), "Consumer first name is not correct");
        assertTrue(newConsumer.get("surname").equalsIgnoreCase(contactRecord.lastName.getAttribute("value")), "Consumer last name is not correct");

    }

    @When("I click on reset button")
    public void i_click_on_reset_button() {
        contactRecord.resetButton.click();
    }

    @Then("I see Search criteria fields have no value")
    public void i_see_Search_criteria_fields_have_no_value() {
        String expected = "";
        assertTrue(expected.equals(contactRecord.firstName.getAttribute("value")));
        assertTrue(expected.equals(contactRecord.lastName.getAttribute("value")));
    }

    @When("I enter Consumer details {string} on contact record UI")
    public void i_enter_Consumer_details_on_contact_record_UI(String name) {
        clearAndFillText(contactRecord.firstName, (name));
        contactRecord.searchButton.click();
        selectDropDown(contactRecord.contactChannelType, "Phone");
        selectDropDown(contactRecord.preferredLanguage, "English");

    }

    @When("I click on cancel button and see a message")
    public void i_click_on_cancel_button_and_see_a_message() {
        waitFor(10);

        contactRecord.cancelButton.click();
        waitFor(1);
        assertTrue(contactRecord.cancelWarningContinueButton.isDisplayed(), "Warning pop up is not displayed");

    }

    @When("I confirm cancellation on the message")
    public void i_confirm_cancellation_on_the_message() {

        contactRecord.cancelWarningContinueButton.click();
    }

    //By Vinuta, Search without entering any parameters
    @When("I do not enter any search parameters")
    public void i_do_not_enter_any_search_parameters() {
        Reporter.log("Clear all textboxes");
        contactRecord.firstName.clear();
        contactRecord.lastName.clear();
        contactRecord.middleName.clear();
        contactRecord.ssnTextbox.clear();
        contactRecord.dobTextbox.clear();
        contactRecord.uniqueIDTextbox.clear();
        assertTrue(contactRecord.searchButton.isDisplayed(), "Search button is not displayed on Contact Record UI");
        contactRecord.searchButton.click();
    }

    @Then("I should be navigated to dashboard")
    public void i_should_be_navigated_to_dashboard() {
        assertTrue(contactRecord.noContactInProgressGraySign.isDisplayed());
    }

    @When("I should see following options for {string} section displayed")
    public void i_should_see_following_options_for_section_displayed(String string) {
        contactRecord.expendReasonButton.click();
        waitFor(1);
        assertTrue(contactRecord.contactReason.isDisplayed(), "Contact Reason dropdown is not displayed");
        assertTrue(contactRecord.contactAction.isDisplayed(), "Contact Action dropdown is not displayed");
        assertTrue(contactRecord.reasonsComments.isDisplayed(), "Comments Field is not displayed");
        assertTrue(contactRecord.expendAdditionalCommentsButton.isDisplayed(), "Additional Comments dropdown is not displayed");
    }

    @When("I click on Hamburger Menu I should see the create Task button and I should be able to click")
    public void i_click_on_Hamburger_Menu_I_should_see_the_create_Task_button_and_I_should_be_able_to_click() {
        navigateToHamBurgerMenu("CREATEATASK");
    }

    @When("I click on the Hamburger Menu")
    public void i_click_on_the_hamburger_menu() {
        contactRecord.hamBurgerMenu.click();

    }

    @Then("I should see all the options present")
    public void i_should_see_all_the_options_present() {
        waitForVisibility(contactRecord.createATask, 1);
        assertTrue(contactRecord.createATask.isDisplayed());
        waitForVisibility(contactRecord.moreOption, 1);
        assertTrue(contactRecord.moreOption.isDisplayed());

    }

    @And("I should see the Username should be present")
    public void i_should_see_the_username_should_be_present() {
        assertTrue(contactRecord.headerUsername.isDisplayed());

    }

    @And("I should see the Role present in the header")
    public void i_should_see_the_role_present_in_the_header() {
        assertTrue(contactRecord.headerRole.isDisplayed());

    }

    @And("I should see the Current Date and Time Displayed")
    public void i_should_see_the_current_date_time_displayed() {
        String actualcontDate = contactRecord.headerDate.getText();
        String actaulTime = contactRecord.headerTime.getText();
        String expectedDate = getCurrentDate();
        String expectedTime = timeNow();
        //assertTrue(contactRecord.headerID.isDisplayed(), "headerID");
        waitForVisibility(contactRecord.headerTime, 10);
        assertTrue(contactRecord.headerTime.isDisplayed(), "time");
        assertTrue(actaulTime.equals(expectedTime), "time is not correct");
        waitForVisibility(contactRecord.getHeaderDate(expectedDate), 10);
        assertTrue(contactRecord.getHeaderDate(expectedDate).isDisplayed());
    }

    @And("I should see the office Address displayed in the bottom")
    public void i_should_see_the_office_address_displayed_in_the_bottom() {
        assertTrue(tmListOfProjectsPage.addressLine1.isDisplayed());
        highLightElement(tmListOfProjectsPage.addressLine1);
        assertTrue(tmListOfProjectsPage.getAddressLine2.isDisplayed());
        highLightElement(tmListOfProjectsPage.getAddressLine2);
    }

    @Given("I logged into the CRM Application")
    public void i_logged_into_the_CRM_Application() {
        driver.get(ConfigurationReader.getProperty("crmPageURL"));
        login(userNameData, password);
    }

    //By Vinuta, Method to verify error when blank search is performed
    @Then("I see notification to fill search criteria")
    public void i_see_notification_to_fill_search_criteria() {
        assertTrue(contactRecord.blankSearchError.isDisplayed(), "Blank Search Error is not displayed");
    }

    //By Vinuta, Method to enter search parameters into all search criteria fields on contact record UI
    @When("I enter First Name as {string}, Middle Initial as {string}, Last Name as {string}, SSN as {string}, Date Of Birth as {string}, Unique ID as {string}  on Contact Record")
    public void i_enter_First_Name_as_Middle_Initial_as_Last_Name_as_SSN_as_Date_Of_Birth_as_Unique_ID_as_on_Contact_Record(String fname, String mname, String lname, String ssn, String dob, String UID) {
        contactRecord.resetButton.click();
        assertTrue(contactRecord.firstName.isDisplayed(), "Consumer first name textbox is not displayed");
        contactRecord.firstName.sendKeys(fname);
        contactRecord.lastName.sendKeys(lname);
        contactRecord.middleName.sendKeys(mname);
        contactRecord.ssnTextbox.sendKeys(ssn);
        contactRecord.dobTextbox.sendKeys(dob);
        contactRecord.uniqueIDTextbox.sendKeys(UID);
    }

    //By Vinuta, Validate SSN field-level error
    @Then("I see SSN field validation displayed")
    public void i_see_SSN_field_validation_displayed() {
        assertTrue(contactRecord.headerError.isDisplayed());
        assertTrue(contactRecord.ssnError.isDisplayed());
    }

    //By Vinuta, Validate DOB field-level error
    @Then("I see DOB field validation displayed")
    public void i_see_DOB_field_validation_displayed() {
        assertTrue(contactRecord.headerError.isDisplayed());
        assertTrue(contactRecord.dobError.isDisplayed());
    }

    //By Vinuta, Validate error on Contact reason,comments section
    @Then("I should receive error to fill out required fields on reasons and comments")
    public void i_should_receive_error_to_fill_out_required_fields_on_reasons_and_comments() {
        assertTrue(contactRecord.contactReasonError.isDisplayed(), "Contact Reason error is not displayed");
        assertTrue(contactRecord.contactActionError.isDisplayed(), "Contact Action error is not displayed");
        assertTrue(contactRecord.additionalCommentsError.isDisplayed(), "Additional Comments error is not displayed");

    }

    //refactoring 10/25/18 Selecting single Contact Action
    @When("I choose {string} option for Contact Action field")
    public void i_choose_option_for_Contact_Action_field(String option) {
        singleSelectFromDropDown(contactRecord.contactAction, option);

    }


}
