package com.maximus.crm.step_definitions;

import com.maximus.crm.pages.crm.CRMCaseContactDetailsPage;
import com.maximus.crm.pages.crm.CRMContactRecordUIPage;
import com.maximus.crm.utilities.BrowserUtils;
import com.maximus.crm.utilities.CRMUtilities;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;

import static org.testng.Assert.assertTrue;

public class CRM_LinkConsumerStepDef extends BrowserUtils {
    CRMContactRecordUIPage crmContactRecordUIPage = new CRMContactRecordUIPage();
    CRMCaseContactDetailsPage caseContactDetails = new CRMCaseContactDetailsPage();

    @And("I click on the Case Contact Details Tab")
    public void i_click_on_the_case_contact_details_tab() {
        highLightElement(crmContactRecordUIPage.caseContactDetailsTab);
        crmContactRecordUIPage.caseContactDetailsTab.click();


    }

    @Then("I should see Contact Id Present")
    public void i_should_see_contact_id_present() {
        assertTrue(caseContactDetails.contactId.isDisplayed());
    }

    @And("I should see the first name and Last name {string}")
    public void i_should_see_the_first_name(String expected) {
        assertTrue(crmContactRecordUIPage.getFirstNameLinkedContactRecord(expected).isDisplayed());
        highLightElement(crmContactRecordUIPage.getFirstNameLinkedContactRecord(expected));
    }

    @Then("I see the Contact Record linked to the new Case\\/Consumer Profile")
    public void i_see_the_Contact_Record_linked_to_the_new_Case_Consumer_Profile() {
        assertTrue(crmContactRecordUIPage.linked.isDisplayed());

    }


}
