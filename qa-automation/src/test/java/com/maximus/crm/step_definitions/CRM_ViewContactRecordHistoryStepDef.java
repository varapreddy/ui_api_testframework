package com.maximus.crm.step_definitions;

import com.maximus.crm.pages.crm.CRMContactHistoryPage;
import com.maximus.crm.pages.crm.CRMContactRecordUIPage;
import com.maximus.crm.utilities.CRMUtilities;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class CRM_ViewContactRecordHistoryStepDef extends CRMUtilities {

    CRMContactHistoryPage contactHistory = new CRMContactHistoryPage();
    CRMContactRecordUIPage contactRecord = new CRMContactRecordUIPage();
    CRM_CreateConsumerProfileStepDef createConsumerStepDef = new CRM_CreateConsumerProfileStepDef();

    @Then("I verify all the columns on Contact History table are present")
    public void i_verify_all_the_columns_on_Contact_History_table_are_present() {
        assertTrue(contactHistory.contactIdColumn.isDisplayed());
//assertTrue(contactHistory.dateColumn.isDisplayed());
        assertTrue(contactHistory.consumerNameColumn.isDisplayed());
        assertTrue(contactHistory.contactTypeColumn.isDisplayed());
        assertTrue(contactHistory.channelColumn.isDisplayed());
        assertTrue(contactHistory.reasonColumn.isDisplayed());
        assertTrue(contactHistory.actionColumn.isDisplayed());
    }

    @Then("I scroll up to Contact Reasons and comments")
    public void i_scroll_up_to_Contact_Reasons_and_comments() {
        //scrollUp();
        waitFor(1);
    }

    @And("I click on the save contact reason button")
    public void i_click_on_the_save_button() {
        waitFor(1);
        contactRecord.saveReasonButton.click();

        //assertTrue(contactRecord.saveReasonButton.isDisplayed());
    }

    @When("I click on initiate a contact button")
    public void i_click_on_initiate_a_contact_button() {
        contactRecord.initContact.click();
        assertTrue(contactRecord.contactInProgressGreenSign.isDisplayed());
    }

    @When("I verify Contact History has one record with {string} reason and {string} action")
    public void i_verify_Contact_History_has_one_record_with_reason_and_action(String expectedReason, String expectedAction) {
        assertTrue(contactHistory.contactIDs.size() == 2, "There should lines of records displayed");
        waitFor(1);
        assertTrue(contactHistory.reasons.get(1).getText().equals(expectedReason), "Non-expected reason is displayed");
        assertTrue(contactHistory.actions.get(1).getText().equals(expectedAction), "Non-expected action is displayed");

    }


    @When("I click on {string} column and verify it is in descending or ascending order")
    public void i_click_on_column_and_verify_it_is_in_descending_or_ascending_order(String column) {
        switch (column) {
            case "Contact ID":
                waitFor(5);
                assertTrue(ascendingOrderIDs(contactHistory.contactIDs), column+" column is not in ascending order");
                contactHistory.contactIdColumn.click();
                waitFor(1);
                assertTrue(descendingOrderIDs(contactHistory.contactIDs), column+" column is not in descending order");
                break;
            case "Date":
                //todo check after bug "Incorrect Date of contacts is fixed"
                contactHistory.dateColumn.click();
                waitFor(1);
                assertTrue(descendingOrderDates(contactHistory.dates), column+" column is not in descending order");
                contactHistory.dateColumn.click();
                waitFor(1);
                assertTrue(ascendingOrderDates(contactHistory.dates),column+" column is not in ascending order");
                break;
            case "Consumer":
                contactHistory.consumerNameColumn.click();
                waitFor(1);
                assertTrue(descendingOrderTexts(contactHistory.consumers), column+" column is not in descending order");
                contactHistory.consumerNameColumn.click();
                waitFor(1);
                assertTrue(ascendingOrderTexts(contactHistory.consumers), column+" column is not in ascending order");
                break;
            case "Contact Type":
                contactHistory.contactTypeColumn.click();
                waitFor(1);
                assertTrue(descendingOrderTexts(contactHistory.contactTypes), column+" column is not in descending order");
                contactHistory.consumerNameColumn.click();
                waitFor(1);
                assertTrue(ascendingOrderTexts(contactHistory.contactTypes), column+" column is not in ascending order");
                break;
            case "Channel":
                contactHistory.channelColumn.click();
                waitFor(1);
                assertTrue(descendingOrderTexts(contactHistory.channels), column+" column is not in descending order");
                contactHistory.channelColumn.click();
                waitFor(1);
                assertTrue(ascendingOrderTexts(contactHistory.channels), column+" column is not in ascending order");
                break;
            case "Reason":
                //todo check after issue is fixed
                contactHistory.reasonColumn.click();
                waitFor(2);
                assertTrue(descendingOrderTexts(contactHistory.reasons), column+" column is not in descending order");
                contactHistory.reasonColumn.click();
                waitFor(1);
                assertTrue(ascendingOrderTexts(contactHistory.reasons), column+" column is not in ascending order");
                break;
            case "Action":
                //todo check after issue is fixed
                contactHistory.actionColumn.click();
                waitFor(1);
                assertTrue(descendingOrderTexts(contactHistory.actions), column+" column is not in descending order");
                contactHistory.actionColumn.click();
                waitFor(1);
                assertTrue(ascendingOrderTexts(contactHistory.actions), column+" column is not in ascending order");
                break;
        }
    }


    @Then("I see no more then {int} Contact history records are displayed")
    public void i_see_no_more_then_Contact_history_records_are_displayed(Integer expected) {
        int records = contactHistory.contactIDs.size();
        assertTrue(records <= expected, "There should not be more than " + records + " records displayed");
    }


    @When("I close the current Contact Record and re-initiate a new Contact Record")
    public void i_close_the_current_Contact_Record_and_re_initiate_a_new_Contact_Record() {
waitFor(1);
        contactRecord.stopContact.click();
        waitFor(1);
        selectDropDown(contactRecord.contactDispositions, "Complete");
        waitFor(5);
        contactRecord.closeButton.click();
        waitFor(1);
        contactRecord.initContact.click();
        assertTrue(contactRecord.contactInProgressGreenSign.isDisplayed());

    }

    @When("I verify a new contact re-initiated")
    public void i_verify_a_new_contact_re_initiated() {
        contactRecord.initContact.click();
        assertTrue(contactRecord.contactInProgressGreenSign.isDisplayed());
    }

//    @Then("I verify previous Contact Record appears on Contact History table after latest record")
//    public void i_verify_previous_Contact_Record_appears_on_Contact_History_table_after_latest_record() {
//        //assertTrue(contactHistory.dates.get(0).getText().equals(getTodayDate));
//        assertTrue(contactHistory.contactTypes.get(2).getText().equals("Outbound"));
//        assertTrue(contactHistory.channels.get(2).getText().equals("SMS Text"));
//        String expectedName = createConsumerStepDef.newConsumer.get("name").toString();
//        assertTrue(contactHistory.consumers.get(2).getText().equals(expectedName));
//        assertTrue(contactHistory.reasons.get(2).getText().equals("Update Information Request"));
//        assertTrue(contactHistory.actions.get(2).getText().equals("Updated Demographic Information"));
//
//    }
}
