package com.maximus.crm.step_definitions;

import com.maximus.crm.pages.crm.CRMContactRecordDashboardPage;
import com.maximus.crm.pages.crm.CRMContactRecordUIPage;
import com.maximus.crm.utilities.CRMUtilities;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import static org.testng.Assert.assertTrue;

public class CRM_NavigateThroughContactDashboardStepDef extends CRMUtilities {

    CRMContactRecordDashboardPage contactRecordDashboard = new CRMContactRecordDashboardPage();
    CRMContactRecordUIPage contactRecord = new CRMContactRecordUIPage();

    public static String additionalCommentText;
    public static String contactReasonText;
    public static String contactActionText;

    @When("I click on {string} Tab on Contact Dashboard Page")
    public void i_click_on_Tab_on_Contact_Dashboard_Page(String tab) {
        waitFor(1);
        switch (tab) {
            case "Demographic Info":
                contactRecordDashboard.demographicInfoTab.click();
                break;
            case "Active Contact":
                contactRecordDashboard.activeContactTab.click();
                break;
            case "Case & Contact Details":
                contactRecordDashboard.caseContactDetailsTab.click();
                break;
            case "Task & Service Request":
                contactRecordDashboard.taskServiceRequestTab.click();
                break;
            case "Program & Benefit Info":
                contactRecordDashboard.programBenefitInfoTab.click();
                break;
        }
        assertTrue(contactRecordDashboard.activeTab.getText().equalsIgnoreCase(tab));
    }

    @When("I Enter {string} as additional Comments")
    public void i_Enter_as_additional_Comments(String comments) {
        additionalCommentText = comments;
        contactRecord.additionalCommentsTextBox.click();
        clearAndFillText(contactRecord.additionalCommentsTextBox, comments);
    }

    @And("I click on save Reasons Options button")
    public void i_click_on_save_Reasons_Options_button() {
        waitFor(1);
        contactRecord.saveReasonButton.click();
    }

    @When("I verify values for Reason Action and Additional Comments are present")
    public void i_verify_values_for_Reason_Action_and_Additional_Comments_are_present() {
        //refactoring 10/25/18
        waitFor(1);
        assertTrue(textIsPresent("Sent Program Materials"));
        assertTrue(textIsPresent("Valid Additional Comment Text"));
    }

}
