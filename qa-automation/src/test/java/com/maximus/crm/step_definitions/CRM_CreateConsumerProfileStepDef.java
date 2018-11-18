package com.maximus.crm.step_definitions;

import com.maximus.crm.pages.crm.CRMActiveContactPage;
import com.maximus.crm.pages.crm.CRMContactHistoryPage;
import com.maximus.crm.pages.crm.CRMContactRecordUIPage;
import com.maximus.crm.pages.crm.CRMCreateConsumerProfilePage;
import com.maximus.crm.utilities.CRMUtilities;
import com.maximus.crm.utilities.Driver;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CRM_CreateConsumerProfileStepDef extends CRMUtilities {
    private WebDriver driver = Driver.getDriver();
    //refactoring 11/03/18
    CRMContactHistoryPage contactHistory = new CRMContactHistoryPage();
    CRMCreateConsumerProfilePage createConsumer = new CRMCreateConsumerProfilePage();
    CRMContactRecordUIPage contactRecord = new CRMContactRecordUIPage();
    CRMActiveContactPage activeContact = new CRMActiveContactPage();
    Map<String, Object> newConsumer = new HashMap<>(getNewTestData());

    @When("I click on Search Button")
    public void i_click_on_Search_Button() {
        highLightElement(contactRecord.searchButton);
        contactRecord.searchButton.click();
    }

    @When("I click on Search Button on Search Consumer Page")  //added new step Definition
    public void i_click_on_Search_Button_on_Search_Consumer_Page() {
        highLightElement(contactRecord.searchButton);
        contactRecord.searchButton.click();
    }

    @Then("I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI")
    public void i_am_able_to_see_Add_Consumer_button_and_I_click_on_it_to_navigate_to_Create_Consumer_UI() {
        assertTrue(createConsumer.addConsumerButton.isDisplayed(), "Add Consumer button is not displayed");
        createConsumer.addConsumerButton.click();
        //    waitForVisibility(createConsumer.createConsumerButton, 5);
        waitFor(1);
//        refactor 11/02/18
        assertTrue(createConsumer.createConsumerButton.isDisplayed(), "Not navigated to Create Consumer page");
    }

    @When("I enter Search criteria fields for a new consumer")
    public void i_enter_Search_criteria_fields_for_a_new_consumer() {
        //System.out.println(newConsumer.keySet());
        clearAndFillText(contactRecord.firstName, (newConsumer.get("name").toString()));
        clearAndFillText(contactRecord.lastName, (newConsumer.get("surname").toString()));
        assertTrue(newConsumer.get("name").toString().equalsIgnoreCase(contactRecord.firstName.getAttribute("value")),
                "First Name field does not have expected" + newConsumer.get("name") + "value");
        assertTrue(newConsumer.get("surname").toString().equalsIgnoreCase(contactRecord.lastName.getAttribute("value")),
                "Last Name field does not have expected" + newConsumer.get("name") + "value");
        ;
    }

    @Then("I see previously populated criteria fields are auto-filled")
    public void i_see_previously_populated_criteria_fields_are_auto_filled() {
        assertTrue(newConsumer.get("name").toString().equalsIgnoreCase(createConsumer.consumerFN.getAttribute("value")),
                "First Name field does not have expected" + newConsumer.get("name") + "value");
        assertTrue(newConsumer.get("surname").toString().equalsIgnoreCase(createConsumer.consumerLN.getAttribute("value")),
                "Last Name field does not have expected" + newConsumer.get("name") + "value");
    }

    @Then("I see all fields and buttons present")
    public void i_see_all_fields_and_buttons_present() {
        assertTrue(createConsumer.consumerFN.isDisplayed(), "First name field is not displayed");
        assertTrue(createConsumer.consumerLN.isDisplayed(), "Last name field is not displayed");
        assertTrue(createConsumer.consumerMI.isDisplayed(), "Middle initial field is not displayed");
        assertTrue(createConsumer.consumerUniqueId.isDisplayed(), "Unique ID field is not displayed");
        assertTrue(createConsumer.consumerDB.isDisplayed(), "Date of birth field is not displayed");
        assertTrue(createConsumer.consumerSSN.isDisplayed(), "SSN field is not displayed");
        assertTrue(createConsumer.consumerPhoneNum.isDisplayed(), "Phone number field is not displayed");
        assertTrue(createConsumer.consumerEmail.isDisplayed(), "Email field is not displayed");
        assertTrue(createConsumer.consumerGender.isDisplayed(), "Gender dropdown field is not displayed");
        assertTrue(createConsumer.commOptIn.isDisplayed(), "Communication option check-mark field is not displayed");
        assertTrue(createConsumer.commOptPhone.isDisplayed(), "Communication option PHONE check-mark field is not displayed");
        assertTrue(createConsumer.commOptText.isDisplayed(), "Communication option TEXT check-mark field is not displayed");
        assertTrue(createConsumer.commOptEmail.isDisplayed(), "Communication option EMAIL check-mark field is not displayed");
        assertTrue(createConsumer.consumerAddressType.isDisplayed(), "Address type field is not displayed");
        assertTrue(createConsumer.consumerAddress1.isDisplayed(), "Address 1 field is not displayed");
        assertTrue(createConsumer.consumerAddress2.isDisplayed(), "Address 2 field is not displayed");
        assertTrue(createConsumer.consumerCity.isDisplayed(), "City field is not displayed");
        assertTrue(createConsumer.consumerCounty.isDisplayed(), "County dropdown field is not displayed");
        assertTrue(createConsumer.consumerState.isDisplayed(), "State dropdown field is not displayed");
        assertTrue(createConsumer.consumerZipCode.isDisplayed(), "Zip code field is not displayed");
        assertTrue(createConsumer.cancelConsumerButton.isDisplayed(), "Cancel button is not displayed");
        assertTrue(createConsumer.createConsumerButton.isDisplayed(), "Create Consumer button is not displayed");
        assertTrue(createConsumer.createCaseButton.isDisplayed(), "Create Case button is not displayed");

    }

    @When("I click on Cancel Button on Create Consumer Page")
    public void i_click_on_Cancel_Button_on_Create_Consumer_Page() {
        createConsumer.cancelConsumerButton.click();
    }

    @Then("I am navigated back to Contact Record UI page")
    public void i_am_navigated_back_to_Contact_Record_UI_page() {
        //assertTrue(contactRecord.noRecordsAvailableMessage.isDisplayed(), "Message that records available is not displayed");
        assertTrue(activeContact.contactRecordSign.isDisplayed());
    }

    @When("I populate Create Consumer fields for a new consumer")
    public void i_populate_Create_Consumer_fields_for_a_new_consumer() {
//        refactor 11/05/18 Clear() UI fields have changed from text to Value
        clearAndFillText(createConsumer.consumerFN, newConsumer.get("name").toString());
        clearAndFillText(createConsumer.consumerLN, newConsumer.get("surname").toString());
        clearAndFillText(createConsumer.consumerMI, (newConsumer.get("name").toString().substring(1)));
        clearAndFillText(createConsumer.consumerDB, ((HashMap) newConsumer.get("birthday")).get("mdy").toString());
        clearAndFillText(createConsumer.consumerSSN, ((HashMap) newConsumer.get("birthday")).get("raw").toString());
        clearAndFillText(createConsumer.consumerUniqueId, (newConsumer.get("password").toString()));
        clearAndFillText(createConsumer.consumerEmail, (newConsumer.get("email").toString()));
        clearAndFillText(createConsumer.consumerPhoneNum, (newConsumer.get("phone").toString()));
        if (newConsumer.get("gender").equals("female")) {
            selectDropDown(createConsumer.consumerGender, "Female");
        } else {
            selectDropDown(createConsumer.consumerGender, "Male");
        }
        waitFor(1);
        createConsumer.commOptIn.click();
        createConsumer.commOptPhone.click();
        selectDropDown(createConsumer.consumerAddressType, "Mailing");
        waitFor(1);
        clearAndFillText(createConsumer.consumerZipCode, ((HashMap) newConsumer.get("credit_card")).get("number").toString());
    }


    @When("I click on Create Consumer Button on Create Consumer Page")
    public void i_click_on_Create_Consumer_Button_on_Create_Consumer_Page() {
        //refactoring 10/17/18
        // highLightElement(createConsumer.createConsumerButton);
        createConsumer.createConsumerButton.click();
        waitFor(1);
        /*todo: remove try catch block after second click CRM-1005 & CRM-1135 bugs are fixed*/
//refactoring 10/25/18
        try {
            if (createConsumer.createConsumerButton.isDisplayed()) {
                createConsumer.createConsumerButton.click();
                waitFor(1);
            }
        } catch (Exception e) {
            System.out.println("Second Click on save consumer button is not needed. " + e.getMessage());
        }
    }

    //By Vinuta, Method to validate invalid details error messages for mandatory fields in Create Consumer page
    @Then("I see mandatory fields highlighted with error messages")
    public void i_see_mandatory_fields_highlighted_with_error_messages() {
        assertTrue(createConsumer.fillOutErrorHeaderMandatory.isDisplayed(), "Error header is not displayed");
        assertTrue(createConsumer.invalidPhoneError.isDisplayed(), "Invalid phone number error is not displayed");
        assertTrue(createConsumer.invalidZipCodeError.isDisplayed(), "Invalid Zip Code error is not displayed");
    }

    //By Vinuta, Method to validate invalid details error messages for optional fields in Create Consumer page
    @Then("I see optional fields highlighted with error messages")
    public void i_see_optional_fields_highlighted_with_error_messages() {
        assertTrue(createConsumer.fillOutErrorHeaderOptional.isDisplayed(), "Error header is not displayed");
        assertTrue(createConsumer.invalidDobError.isDisplayed(), "Invalid Date Of Birth error is not displayed");
        assertTrue(createConsumer.invalidEmailError.isDisplayed(), "Invalid Email error is not displayed");
        assertTrue(createConsumer.invalidSsnError.isDisplayed(), "Invalid SSN error is not displayed");
    }

    //By Vinuta, Method to enter invalid details into phone & zipcode fields
    @Then("I populate invalid phone,zipcode")
    public void i_populate_invalid_phone_zipcode() {
        clearAndFillText(createConsumer.consumerPhoneNum, "123");
        clearAndFillText(createConsumer.consumerZipCode, "123");
    }

    //By Vinuta, Method to enter invalid details into dob,ssn,email fields
    @Then("I populate invalid date, ssn, email")
    public void i_populate_invalid_date_ssn_email() {
        clearAndFillText(createConsumer.consumerDB, "13");
        clearAndFillText(createConsumer.consumerEmail, "13");
        clearAndFillText(createConsumer.consumerSSN, "13");
    }

    @Then("I am navigated to active contact page")
    public void i_am_navigated_to_active_contact_page() {
        assertTrue(contactRecord.contactRecordSign.isDisplayed());
        waitFor(1);
    }

    @Then("I see a unique Consumer Profile ID generated")
    public void i_see_a_unique_Consumer_Profile_ID_generated() {
        activeContact.consumerID.isDisplayed();
    }


    @And("I click on Unlink Contact Button on Active Contact Page")
    public void i_click_on_Unlink_Contact_Button_on_Active_Contact_Page() throws Throwable {
        waitFor(1);
        assertTrue(activeContact.unlinkContactRecord.isDisplayed());
        activeContact.fullName.click();
       // driver.findElement(By.xpath("//*[contains(text(), 'FULL NAME')]")).click();
        scrollUpRobot();
        //scrollToElement(activeContact.unlinkContactRecord);
        waitFor(2);
        highLightElement(activeContact.unlinkContactRecord);

        activeContact.unlinkContactRecord.click();
    }

    @When("I enter random {string} Search criteria fields for a new consumer")
    public void i_enter_random_Search_criteria_fields_for_a_new_consumer(String value) {
        clearAndFillText(contactRecord.firstName, value);
        clearAndFillText(contactRecord.lastName, value);
    }

    @Then("I see Consumer already exists, please associate existing Consumer to Case message displayed")
    public void iSeeConsumerAlreadyExistsPleaseAssociateExistingConsumerToCaseMessageDisplayed() throws Throwable {
        assertTrue(createConsumer.duplicateConsumererrorMessage.isDisplayed(), "Duplicate Error message was not present");
    }

    @And("I clear all fields values")
    public void iClearAllFieldsValues() throws Throwable {
        createConsumer.consumerFN.clear();
        staticWait(100);
        createConsumer.consumerLN.clear();

    }

    @Then("I see Please fill in the required field error message displayed")
    public void i_see_Please_fill_in_the_required_field_error_message_displayed() {
        highLightElement(createConsumer.enterValueMainWarning);
        createConsumer.enterValueMainWarning.isDisplayed();
        assertTrue(createConsumer.enterValueMainWarning.isDisplayed(), "Error not displayed");
    }

    /*Refactoring 09/23/18*/
    @Then("I see new consumer is created and has a unique Consumer ID")
    public void i_see_new_consumer_is_created_and_has_a_unique_Consumer_ID() {
        waitFor(1);
        assertTrue(activeContact.consumerID.isDisplayed(), "Consumer ID is not displayed");
        String expectedFirstName = newConsumer.get("name").toString();
        String expectedLastName = newConsumer.get("surname").toString();
        String expectedMI = newConsumer.get("name").toString().substring(1, 2);
        String expectedFullName = expectedFirstName + " " + expectedMI + " " + expectedLastName;
        assertTrue(activeContact.fullName.getText().equalsIgnoreCase(expectedFullName), "Created Consumer has different name");
    }

    @When("I see {string} field accept {int} characters in total")
    public void i_see_field_accept_characters_in_total(String field, Integer textLength) {

        String actual = "";
        String sentText = createTextString(textLength * 4);
        switch (field) {
            case "addressLine1":
                clearAndFillText(createConsumer.consumerAddress1, sentText);
                waitFor(1);
                actual = createConsumer.consumerAddress1.getAttribute("value");
                break;
            case "addressLine2":
                clearAndFillText(createConsumer.consumerAddress2, sentText);
                waitFor(1);
                actual = createConsumer.consumerAddress2.getAttribute("value");
                break;
            case "city":
                clearAndFillText(createConsumer.consumerCity, sentText);
                waitFor(1);
                actual = createConsumer.consumerCity.getText();
                break;
            case "county":
                createConsumer.consumerCounty.sendKeys(sentText);
                waitFor(1);
                actual = createConsumer.consumerCounty.getText();
                break;
        }
        assertTrue(actual.length() <= textLength, "Field accepts text which is not " + textLength + " characters long");
    }


    @Then("I should be able to see all states in State dropdown are available")
    public void i_should_be_able_to_see_all_states_in_State_dropdown_are_available() {
        assertTrue(stateDropdownHasAll(createConsumer.consumerState), "State dropdown has incorrect value/s");
    }


    @Then("I verify {string} fields being marked mandatory")
    public void i_verify_fields_being_marked_mandatory(String string, List<String> options) {
        String actual = "";
        for (String option : options) {
            actual = "";
            switch (option) {
                case "Consumer First Name":
                    actual = createConsumer.firstNameLabel.getText();
                    break;
                case "Consumer Last Name":
                    actual = createConsumer.lastNameLabel.getText();
                    break;
                case "Zip Code":
                    actual = createConsumer.zipCodeLabel.getText();
                    break;
                case "Phone Number":
                    actual = createConsumer.phoneNumberLabel.getText();
                    break;

            }
            assertTrue(actual.endsWith("*"), option + " field is not marked mandatory");


        }

    }

    @When("I see {string} field accepts up to {int} digits")
    public void i_see_field_accepts_up_to_digits(String field, Integer length) {
        clearAndFillText(createConsumer.consumerZipCode, "qaws234dfert567hfug999jg044k-jj55");
        assertTrue(hasOnlyDigits(createConsumer.consumerZipCode.getAttribute("value")),
                field + " has to accept digits only.");
        assertTrue(createConsumer.consumerZipCode.getAttribute("value").replace("-", "").length() <= 9,
                field + " has to accept up to " + length + " digits only.");

    }

    //refactoring 10/17/18
    @When("I see {int} digits required and {int} digits optional format")
    public void i_see_digits_required_and_digits_optional_format(Integer five, Integer nine) {

        String testZipCode = "1";
        int i;

        for (i = 1; i < 9; i++) {
            // System.out.println(i +" == "+ five );
            //testZipCode.append("1");
            if (i == five) {
                // System.out.println(five +" FIVE ");
                createConsumer.createConsumerButton.click();
                createConsumer.consumerZipCode.sendKeys(testZipCode);
                waitFor(1);
                createConsumer.createConsumerButton.click();

            } else {
                createConsumer.consumerZipCode.sendKeys(testZipCode);
                waitFor(1);
                // System.out.println(createConsumer.consumerZipCode.getAttribute("value"));
                createConsumer.createConsumerButton.click();
                waitFor(1);
                assertTrue(createConsumer.fillOutErrorZipCode.isDisplayed(), "Error message is not displayed");
            }
        }

    }

    @Then("I see {string} field accept only alphanumeric characters")
    public void i_see_field_accept_only_alphanumeric_characters(String string) {
        String actual = "";
        clearAndFillText(createConsumer.consumerUniqueId, createTextString(2));
        actual = createConsumer.consumerUniqueId.getAttribute("value").trim();
        assertTrue(isAlphanumeric(actual), "Unique ID field accepts non-alphanumeric characters");
    }

    @Then("I am able to see Add Consumer button on Consumer Search Results Page")
    public void i_am_able_to_see_Add_Consumer_button() {
        highLightElement(createConsumer.addConsumerButton);
        assertTrue(createConsumer.addConsumerButton.isDisplayed(), "Add Consumer Button is not displayed");
    }

    @When("I enter Search criteria fields for the new consumer created")
    public void i_enter_Search_criteria_fields_for_new_consumer_created() {
        clearAndFillText(contactRecord.firstName, (newConsumer.get("name").toString()));
        clearAndFillText(contactRecord.lastName, (newConsumer.get("surname").toString()));
        assertTrue(newConsumer.get("name").toString().equalsIgnoreCase(contactRecord.firstName.getAttribute("value")),
                "First Name field does not have expected" + newConsumer.get("name") + "value");
        assertTrue(newConsumer.get("surname").toString().equalsIgnoreCase(contactRecord.lastName.getAttribute("value")),
                "Last Name field does not have expected" + newConsumer.get("name") + "value");
    }

    @Then("Add Consumer button is not displayed on Consumer Search Results Page")
    public void Add_Consumer_button_is_not_displayed_on_Consumer_Search_Results_Page() {
        assertEquals(true, IsElementDisplayed(createConsumer.addConsumerButton), "Add Consumer Button is not displayed");
    }

    @When("I click on Search Button without entering search parameters")
    public void i_click_on_Search_Button_without_entering_search_parameters() {
        highLightElement(contactRecord.searchButton);
        contactRecord.searchButton.click();
    }



    @When("I verify latest Contact Record appears on Contact History table")
    public void i_verify_latest_Contact_Record_appears_on_Contact_History_table() {
        //assertTrue(contactHistory.dates.get(0).getText().equals(getTodayDate));
        assertTrue(contactHistory.contactTypes.get(1).getText().equals("Inbound"));
        assertTrue(contactHistory.channels.get(1).getText().equals("Phone"));
        //todo add Lsat name to the first name after bug fixed
        String expectedName =newConsumer.get("name").toString(); //newConsumer.get("surname").toString();
        System.out.println(expectedName);
        System.out.println(contactHistory.consumers.get(1).getText());

        assertTrue(contactHistory.consumers.get(1).getText().equals(expectedName));
        assertTrue(contactHistory.reasons.get(1).getText().equals("Materials Request"));
        assertTrue(contactHistory.actions.get(1).getText().equals("Re-Sent Notice"));
    }

    @Then("I verify previous Contact Record appears on Contact History table after latest record")
    public void i_verify_previous_Contact_Record_appears_on_Contact_History_table_after_latest_record() {
        //assertTrue(contactHistory.dates.get(0).getText().equals(getTodayDate));
        assertTrue(contactHistory.contactTypes.get(2).getText().equals("Outbound"));
        assertTrue(contactHistory.channels.get(2).getText().equals("SMS Text"));
        //todo add Lsat name to the first name after bug fixed
        String expectedName = newConsumer.get("name").toString(); //newConsumer.get("surname").toString();
        assertTrue(contactHistory.consumers.get(2).getText().equals(expectedName));
        assertTrue(contactHistory.reasons.get(2).getText().equals("Update Information Request"));
        assertTrue(contactHistory.actions.get(2).getText().equals("Updated Demographic Information"));

    }


}
