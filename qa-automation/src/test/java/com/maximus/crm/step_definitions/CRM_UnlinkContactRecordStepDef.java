package com.maximus.crm.step_definitions;

import com.maximus.crm.pages.crm.CRMActiveContactPage;
import com.maximus.crm.pages.crm.CRMContactRecordUIPage;
import com.maximus.crm.utilities.CRMUtilities;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.testng.Assert.assertTrue;

public class CRM_UnlinkContactRecordStepDef extends CRMUtilities {

    CRMActiveContactPage activeContact = new CRMActiveContactPage();
    CRMContactRecordUIPage contactRecord = new CRMContactRecordUIPage();

    @Then("I see unlink contact record option is displayed")//
    public void i_see_unlink_contact_record_option_is_displayed() {
        waitFor(1);
        assertTrue(activeContact.unlinkContactRecord.isDisplayed(), "Unlink Contact Record button is not displayed");
    }


    @When("I see no values are displayed on Header for Consumer name and ID")
    public void i_see_no_values_are_displayed_on_Header_for_Consumer_name_and_ID() {
        String expected = "";
        assertTrue(activeContact.headerConsumerID.getText().equals(expected), "Header should not hold Consumer ID value");
        assertTrue(activeContact.headerConsumerName.getText().equals(expected), "Header should not hold Consumer Name value");
    }

    @Then("I see Contact Record Search is displayed")
    public void i_see_Contact_Record_Search_is_displayed() {
        assertTrue(contactRecord.searchButton.isDisplayed(), "Search Button should be displayed");
    }

    @Then("I see entered value for Contact Details and Reason not changed")
    public void i_see_entered_value_for_Contact_Details_and_Reason_not_changed() {
        assertTrue(contactRecord.contactType.getText().equals("Outbound"),
                "Contact Type field value is not Outbound");
        assertTrue(contactRecord.contactChannelType.getText().equals("Web Chat"),
                "Contact Channel Type field value is not Web Chat");
        assertTrue(contactRecord.contactReason.getText().equals("Update Information Request"),
                "Contact Reason field value is not 'Update Information Request'");
        assertTrue(contactRecord.contactAction.getText().equals("Updated Eligibility Information"),
                "Contact Action field value is not 'Updated Eligibility Information'");
    }
}
