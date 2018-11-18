package com.maximus.crm.step_definitions;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.maximus.crm.pages.crm.CRMAddContactInfoPage;
import com.maximus.crm.pages.crm.CRMAddNewEmailPage;
import com.maximus.crm.pages.crm.CRMDemographicContactInfoPage;
import com.maximus.crm.utilities.*;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;


public class CRM_AddNewEmailInfoStepDef extends BrowserUtils {

    CRMDemographicContactInfoPage demographicContactInfoPage=new CRMDemographicContactInfoPage();
    CRMAddContactInfoPage crmAddContactInfoPage=new CRMAddContactInfoPage();
    CRMAddNewEmailPage newEmailErrors = new CRMAddNewEmailPage();
    Map<String, Object> randomdata = new HashMap<>(getNewTestData());
    String emailIDToBeValidated = new String();
    String newStartDate = new String();
    String newEndDate = new String();
    String inactivationDate = new String();
    String existingMailID = new String();
    HashMap<String,String> emailIdsAndStatus = new HashMap<String,String>();
    public String longEmailAddress;

    @Then("I should see the EmailAddress label displayed")
    public void i_should_see_the_EmailAddress_label_displayed() {
        Assert.assertTrue(demographicContactInfoPage.emailLabel.isDisplayed());
    }

    @Then("I should see the Add button displayed for Email address")
    public void i_should_see_the_Add_button_displayed_for_Email_address() {
        Assert.assertTrue(demographicContactInfoPage.addEmailButton.isDisplayed());
    }

    @When("I click on the Add button for Email Address")
    public void i_click_on_the_Add_button_for_Email_Address() {
     demographicContactInfoPage.addEmailButton.click();
    }

    @When("I verify that I am in the Add Email Address Page")
    public void i_verify_that_I_am_in_the_Add_Email_Address_Page() {
        Assert.assertTrue(crmAddContactInfoPage.emailAddressLabel.isDisplayed());
    }

    @Then("I verify the fields displayed on the Add Email Address Page")
    public void i_verify_the_fields_displayed_on_the_Add_Email_Address_Page() {
        waitFor(1);
       Assert.assertTrue(crmAddContactInfoPage.emailAddressField.isDisplayed());
       Assert.assertTrue(crmAddContactInfoPage.startDate.isDisplayed());
       Assert.assertTrue(crmAddContactInfoPage.associatedCaseMember.isDisplayed());
       Assert.assertTrue(crmAddContactInfoPage.endDate.isDisplayed());
       Assert.assertTrue(crmAddContactInfoPage.saveButton.isDisplayed());
       Assert.assertTrue(crmAddContactInfoPage.cancelButton.isDisplayed());
    }

    @When("I enter the mandatory fields on the add email page and click on save")
    public void i_enter_the_mandatory_fields_on_the_add_email_page_and_click_on_save() {
        clearAndFillText(crmAddContactInfoPage.emailAddressField, (randomdata.get("email").toString()));
        crmAddContactInfoPage.associatedCaseMember.click();
        scrollToElement(crmAddContactInfoPage.dropdownLastItem);
        hover(crmAddContactInfoPage.dropdownLastItem);
        crmAddContactInfoPage.dropdownLastItem.click();
        String date=getCurrentDate();
        clearAndFillText(crmAddContactInfoPage.startDate,date);
        crmAddContactInfoPage.saveButton.click();
    }

    @Then("I am navigated back to Demographic page")
    public void i_am_navigated_back_to_Demographic_page() {
        Assert.assertTrue(demographicContactInfoPage.emailTable.isDisplayed());
    }

    @When("I save the fields and give start date as current and end date in future")
    public void i_save_the_fields_and_give_start_date_as_current_and_end_date_in_future() {
        emailIDToBeValidated=randomdata.get("email").toString() ;
        clearAndFillText(crmAddContactInfoPage.emailAddressField, emailIDToBeValidated);
        crmAddContactInfoPage.associatedCaseMember.click();
        waitFor(1);
        scrollToElement(crmAddContactInfoPage.dropdownLastItem);
        hover(crmAddContactInfoPage.dropdownLastItem);
        crmAddContactInfoPage.dropdownLastItem.click();
        String date=getCurrentDate();
        clearAndFillText(crmAddContactInfoPage.startDate,date);
        clearAndFillText(crmAddContactInfoPage.endDate,getGreaterDate(100));
        crmAddContactInfoPage.saveButton.click();
        scrollToElement(crmAddContactInfoPage.emailAddressHeader);
    }

    @When("I save the fields and give start date in the past and end date as current date")
    public void i_save_the_fields_and_give_start_date_in_the_past_and_end_date_as_current_date() {
        emailIDToBeValidated=randomdata.get("email").toString() ;
        clearAndFillText(crmAddContactInfoPage.emailAddressField, emailIDToBeValidated);
        crmAddContactInfoPage.associatedCaseMember.click();
        waitFor(1);
        scrollToElement(crmAddContactInfoPage.dropdownLastItem);
        hover(crmAddContactInfoPage.dropdownLastItem);
        crmAddContactInfoPage.dropdownLastItem.click();
        String date=getCurrentDate();
        clearAndFillText(crmAddContactInfoPage.startDate,getPriorDate(150));
        clearAndFillText(crmAddContactInfoPage.endDate,date);
        crmAddContactInfoPage.saveButton.click();
    }

    @When("I save the fields and give start date in the future and end date in future")
    public void i_save_the_fields_and_give_start_date_in_the_future_and_end_date_in_future() {
        emailIDToBeValidated=randomdata.get("email").toString() ;
        clearAndFillText(crmAddContactInfoPage.emailAddressField, emailIDToBeValidated);
        crmAddContactInfoPage.associatedCaseMember.click();
        waitFor(1);
        scrollToElement(crmAddContactInfoPage.dropdownLastItem);
        hover(crmAddContactInfoPage.dropdownLastItem);
        crmAddContactInfoPage.dropdownLastItem.click();
        String date=getCurrentDate();
        clearAndFillText(crmAddContactInfoPage.startDate,getGreaterDate(10));
        clearAndFillText(crmAddContactInfoPage.endDate,getGreaterDate(200));
        crmAddContactInfoPage.saveButton.click();
        scrollToElement(crmAddContactInfoPage.emailAddressHeader);
    }

    @When("I save the fields and give start date in future and end date in future")
    public void i_save_the_fields_and_give_start_date_in_future_and_end_date_in_future() {
        emailIDToBeValidated=randomdata.get("email").toString() ;
        clearAndFillText(crmAddContactInfoPage.emailAddressField, emailIDToBeValidated);
        crmAddContactInfoPage.associatedCaseMember.click();
        waitFor(1);
        scrollToElement(crmAddContactInfoPage.dropdownLastItem);
        hover(crmAddContactInfoPage.dropdownLastItem);
        crmAddContactInfoPage.dropdownLastItem.click();
        String date=getCurrentDate();
        clearAndFillText(crmAddContactInfoPage.startDate,getGreaterDate(10));
        clearAndFillText(crmAddContactInfoPage.endDate,getGreaterDate(100));
        crmAddContactInfoPage.saveButton.click();
        scrollToElement(crmAddContactInfoPage.emailAddressHeader);
    }

    @When("I save the fields and give start date in past and end date in future")
    public void i_save_the_fields_and_give_start_date_in_past_and_end_date_in_future() {
        emailIDToBeValidated=randomdata.get("email").toString() ;
        clearAndFillText(crmAddContactInfoPage.emailAddressField, emailIDToBeValidated);
        crmAddContactInfoPage.associatedCaseMember.click();
        waitFor(1);
        scrollToElement(crmAddContactInfoPage.dropdownLastItem);
        hover(crmAddContactInfoPage.dropdownLastItem);
        crmAddContactInfoPage.dropdownLastItem.click();
        String date=getCurrentDate();
        clearAndFillText(crmAddContactInfoPage.startDate,getPriorDate(100));
        clearAndFillText(crmAddContactInfoPage.endDate,getGreaterDate(100));
        crmAddContactInfoPage.saveButton.click();
        scrollToElement(crmAddContactInfoPage.emailAddressHeader);

    }
    @When("I save the fields and give start date in the past and end date as past")
    public void i_save_the_fields_and_give_start_date_in_the_past_and_end_date_as_past() {
        emailIDToBeValidated=randomdata.get("email").toString() ;
        clearAndFillText(crmAddContactInfoPage.emailAddressField, emailIDToBeValidated);
        crmAddContactInfoPage.associatedCaseMember.click();
        waitFor(1);
        scrollToElement(crmAddContactInfoPage.dropdownLastItem);
        hover(crmAddContactInfoPage.dropdownLastItem);
        crmAddContactInfoPage.dropdownLastItem.click();
        String date=getCurrentDate();
        clearAndFillText(crmAddContactInfoPage.startDate,getPriorDate(100));
        clearAndFillText(crmAddContactInfoPage.endDate,getPriorDate(2));
        crmAddContactInfoPage.saveButton.click();
        scrollToElement(crmAddContactInfoPage.emailAddressHeader);
    }

    @Then("^I verify status of email as \"(.*)\"$")
    public void verifyEmailStatus(String status){
        WebElement emailStatus=Driver.getDriver().findElement(By.xpath("//td[.='"+emailIDToBeValidated+"']/following-sibling::td[2]"));
        Assert.assertEquals(emailStatus.getText(),status);
    }

    @When("I provide more than thirty characters in email address field {string}")
    public void i_provide_more_than_thirty_characters_in_email_address_field(String invalid) {
        longEmailAddress = invalid;
        clearAndFillText(crmAddContactInfoPage.emailAddressField,invalid);
    }

    @Then("I verify email address more than (\\d+) characters is not allowed")
    public void i_verify_email_address_more_than_characters_is_not_allowed(int arg1) throws Throwable {

        String actual=crmAddContactInfoPage.emailAddressField.getAttribute("value");
        Assert.assertNotEquals(longEmailAddress,actual);

    }
    @When("I click on save without entering the mandatory fields")
    public void i_click_on_save_without_entering_the_mandatory_fields() {
        crmAddContactInfoPage.saveButton.click();

    }

    @Then("I see error message populated below each field")
    public void i_see_error_message_populated_below_each_field() {
       newEmailErrors.emptyEmailAddressError.isDisplayed();
       newEmailErrors.emptyAssociatedCaseMemberError.isDisplayed();
       newEmailErrors.emptyStartDateError.isDisplayed();
    }

    @When("I provide invalid data for emailAddress,StartDate and EndDate")
    public void i_provide_invalid_data_for_emailAddress_StartDate_and_EndDate() {
        clearAndFillText(crmAddContactInfoPage.emailAddressField,"test@abc.co" );
        clearAndFillText(crmAddContactInfoPage.startDate,"02/32/2109");
        clearAndFillText(crmAddContactInfoPage.endDate,"09/31/2018");
        crmAddContactInfoPage.saveButton.click();
    }

    @Then("I see valid field error message populated below each field")
    public void i_see_valid_field_error_message_populated_below_each_field() {
        Assert.assertTrue(newEmailErrors.invalidEmailFieldError.isDisplayed(),"Invalid Email Address Error not displayed");
        Assert.assertTrue(newEmailErrors.incorrectDateError.isDisplayed(),"Incorrect Date Error not displayed");
        Assert.assertTrue(newEmailErrors.invalidDateError.isDisplayed(),"invalid Date Error not displayed");

    }
    @When("I add three emailId's with start date as current and end date in future")
    public void i_add_three_emailId_s_with_start_date_as_current_and_end_date_in_future() {
        String emailID1 = randomEmailId();
        clearAndFillText(crmAddContactInfoPage.emailAddressField, emailID1);
        crmAddContactInfoPage.associatedCaseMember.click();
        waitFor(1);
        scrollToElement(crmAddContactInfoPage.dropdownLastItem);
        hover(crmAddContactInfoPage.dropdownLastItem);
        crmAddContactInfoPage.dropdownLastItem.click();
        clearAndFillText(crmAddContactInfoPage.startDate,getPriorDate(90));
        clearAndFillText(crmAddContactInfoPage.endDate,getGreaterDate(90));
        crmAddContactInfoPage.saveButton.click();
        emailIdsAndStatus.put(emailID1,3+"ACTIVE");
        demographicContactInfoPage.addEmailButton.click();
        //Adding Email 2
        String emailID2 = randomEmailId();
        clearAndFillText(crmAddContactInfoPage.emailAddressField, emailID2);
        crmAddContactInfoPage.associatedCaseMember.click();
        waitFor(1);
        scrollToElement(crmAddContactInfoPage.dropdownLastItem);
        hover(crmAddContactInfoPage.dropdownLastItem);
        crmAddContactInfoPage.dropdownLastItem.click();
        clearAndFillText(crmAddContactInfoPage.startDate,getPriorDate(60));
        clearAndFillText(crmAddContactInfoPage.endDate,getGreaterDate(60));
        crmAddContactInfoPage.saveButton.click();
        emailIdsAndStatus.put(emailID2,2+"ACTIVE");
        demographicContactInfoPage.addEmailButton.click();
        //Adding Email 3
        String emailID3 = randomEmailId();
        clearAndFillText(crmAddContactInfoPage.emailAddressField, emailID3);
        crmAddContactInfoPage.associatedCaseMember.click();
        waitFor(1);
        scrollToElement(crmAddContactInfoPage.dropdownLastItem);
        hover(crmAddContactInfoPage.dropdownLastItem);
        crmAddContactInfoPage.dropdownLastItem.click();
        clearAndFillText(crmAddContactInfoPage.startDate,getPriorDate(10));
        clearAndFillText(crmAddContactInfoPage.endDate,getGreaterDate(355));
        crmAddContactInfoPage.saveButton.click();
        emailIdsAndStatus.put(emailID2,1+"ACTIVE");
    }

    @Then("I verify the order of emailID's and the respective status")
    public void i_verify_the_order_of_emailID_s_and_the_respective_status() {
        scrollToElement(crmAddContactInfoPage.emailAddressHeader);
        HashMap<String,String> actualEmailOrder = getEmailIdsWithStatus();
        Assert.assertEquals(compareHashMaps(actualEmailOrder,emailIdsAndStatus),true);
    }

    @When("I add three emailId's with start date and end date in past")
    public void i_add_three_emailId_s_with_start_date_as_current_and_end_date_in_past() {
        //Adding first emailID
        String emailID1=randomdata.get("email").toString();
        clearAndFillText(crmAddContactInfoPage.emailAddressField, emailID1);
        crmAddContactInfoPage.associatedCaseMember.click();
        waitFor(1);
        scrollToElement(crmAddContactInfoPage.dropdownLastItem);
        hover(crmAddContactInfoPage.dropdownLastItem);
        crmAddContactInfoPage.dropdownLastItem.click();
        clearAndFillText(crmAddContactInfoPage.startDate,"10122016");
        clearAndFillText(crmAddContactInfoPage.endDate,getPriorDate(2));
        crmAddContactInfoPage.saveButton.click();
        emailIdsAndStatus.put(emailID1, 1+"INACTIVE");
        demographicContactInfoPage.addEmailButton.click();

        // Adding second EmailID
        String emailID2=randomdata.get("email").toString();
        clearAndFillText(crmAddContactInfoPage.emailAddressField, emailID2);
        crmAddContactInfoPage.associatedCaseMember.click();
        waitFor(1);
        scrollToElement(crmAddContactInfoPage.dropdownLastItem);
        hover(crmAddContactInfoPage.dropdownLastItem);
        crmAddContactInfoPage.dropdownLastItem.click();
        clearAndFillText(crmAddContactInfoPage.startDate,"10022016");
        clearAndFillText(crmAddContactInfoPage.endDate,getPriorDate(30));
        crmAddContactInfoPage.saveButton.click();
        emailIdsAndStatus.put(emailID2, 2+"INACTIVE");
        demographicContactInfoPage.addEmailButton.click();

        // Adding third EmailID
        String emailID3=randomdata.get("email").toString();
        clearAndFillText(crmAddContactInfoPage.emailAddressField, emailID3);
        crmAddContactInfoPage.associatedCaseMember.click();
        waitFor(1);
        scrollToElement(crmAddContactInfoPage.dropdownLastItem);
        hover(crmAddContactInfoPage.dropdownLastItem);
        crmAddContactInfoPage.dropdownLastItem.click();
        clearAndFillText(crmAddContactInfoPage.startDate,"10042016");
        clearAndFillText(crmAddContactInfoPage.endDate,getPriorDate(100));
        crmAddContactInfoPage.saveButton.click();
        emailIdsAndStatus.put(emailID3, 3+"INACTIVE");
    }

    @When("I add three emailId's with active and inactive status")
    public void i_add_three_emailId_s_with_active_and_inactive_status() {
        // Adding first EmailID
        String emailID1=randomdata.get("email").toString();
        clearAndFillText(crmAddContactInfoPage.emailAddressField, emailID1);
        crmAddContactInfoPage.associatedCaseMember.click();
        waitFor(1);
        scrollToElement(crmAddContactInfoPage.dropdownLastItem);
        hover(crmAddContactInfoPage.dropdownLastItem);
        crmAddContactInfoPage.dropdownLastItem.click();
        String date=getCurrentDate();
        waitFor(1);
        clearAndFillText(crmAddContactInfoPage.startDate,"10122017");
        clearAndFillText(crmAddContactInfoPage.endDate,getGreaterDate(30));
        crmAddContactInfoPage.saveButton.click();
        emailIdsAndStatus.put(emailID1, 2+"ACTIVE");
        demographicContactInfoPage.addEmailButton.click();

        // Adding second EmailID
        String emailID2=randomdata.get("email").toString();
        clearAndFillText(crmAddContactInfoPage.emailAddressField, emailID2);
        crmAddContactInfoPage.associatedCaseMember.click();
        scrollToElement(crmAddContactInfoPage.dropdownLastItem);
        hover(crmAddContactInfoPage.dropdownLastItem);
        crmAddContactInfoPage.dropdownLastItem.click();

        clearAndFillText(crmAddContactInfoPage.startDate,"10122018");
        clearAndFillText(crmAddContactInfoPage.endDate,getGreaterDate(365));
        crmAddContactInfoPage.saveButton.click();
        emailIdsAndStatus.put(emailID2, 1+"ACTIVE");
        demographicContactInfoPage.addEmailButton.click();

        // Adding third EmailID
        String emailID3=randomdata.get("email").toString();
        clearAndFillText(crmAddContactInfoPage.emailAddressField, emailID3);
        crmAddContactInfoPage.associatedCaseMember.click();
        waitFor(1);
        scrollToElement(crmAddContactInfoPage.dropdownLastItem);
        hover(crmAddContactInfoPage.dropdownLastItem);
        crmAddContactInfoPage.dropdownLastItem.click();
        clearAndFillText(crmAddContactInfoPage.startDate,"10042016");
        clearAndFillText(crmAddContactInfoPage.endDate,getPriorDate(100));
        crmAddContactInfoPage.saveButton.click();
        emailIdsAndStatus.put(emailID3, 3+"INACTIVE");
    }

    @Then("^Then I verify the order of emailIDs and the respective status$")
    public void then_I_verify_the_order_of_emailIDs_and_the_respective_status() throws Throwable {
        scrollToElement(crmAddContactInfoPage.emailAddressHeader);
        HashMap<String, String> actualEmailIdOrder= getEmailIdsWithStatus();
        Assert.assertEquals(compareHashMaps(actualEmailIdOrder, emailIdsAndStatus), true);
    }

    @Then("^I click on added emailID$")
    public void i_click_on_added_emailID() throws Throwable {
        clickOnFirstEmaiId();
    }

    @Then("I update the emailID with {string}")
    public void i_update_the_emailID_with(String updatedemail) throws Throwable {
       existingMailID = getText(crmAddContactInfoPage.emailAddressField);
       /* updateEmailID(crmAddContactInfoPage.emailAddressField);
        scrollToElement(crmAddContactInfoPage.dropdownLastItem);
        hover(crmAddContactInfoPage.dropdownLastItem);
        crmAddContactInfoPage.dropdownLastItem.click();*/
       clearAndFillText(crmAddContactInfoPage.emailAddressField,updatedemail);
       waitFor(10);
        crmAddContactInfoPage.saveButton.click();
    }

    @When("I hower over status of the newly email added")
    public void i_hower_over_status_of_the_newly_email_added() {
        hover(crmAddContactInfoPage.firstEmailIDStatus);
    }

    @Then("I verify that the start and end dates are displayed for email address")
    public void i_verify_that_the_start_and_end_dates_are_displayed_for_email_address() {
        Assert.assertTrue(getHoverText().containsKey("START DATE"));
        Assert.assertTrue(getHoverText().containsKey("END DATE"));
    }

    @Then("I verify that emailID has been updated")
    public void i_verify_that_emailID_has_been_updated() throws Throwable {
        Assert.assertNotEquals(getText(crmAddContactInfoPage.firstEmailID), existingMailID);
    }


    @Then("I verify that deactivate immediately checkbox is available")
    public void i_verify_that_deactivate_immediately_checkbox_is_available() throws Throwable {
        Assert.assertTrue(crmAddContactInfoPage.inactiveImmediatelyOption.isDisplayed());
    }

    @Then("I verify that on clicking the deactivate immediately button end date goes blank")
    public void i_verify_that_on_clicking_the_deactivate_immediately_button_end_date_goes_blank() throws Throwable {
        click(crmAddContactInfoPage.inactiveImmediatelyCheckbox);
        System.out.println("value of email id field is "+ getText(crmAddContactInfoPage.endDate));
        Assert.assertEquals(getText(crmAddContactInfoPage.endDate), "");
    }


    @Then("^I update the end date of emailID$")
    public void i_update_the_emd_date_of_emailID() throws Throwable {
        newEndDate= getGreaterDate(300);
        clearAndFillText(crmAddContactInfoPage.endDate, newEndDate);
        crmAddContactInfoPage.saveButton.click();
    }

    @Then("I verify that end date has been updated")
    public void i_verify_that_end_date_has_been_updated() throws Throwable {
        hover(crmAddContactInfoPage.firstEmailIDStatus);
        System.out.println("hover TExt "+getHoverText().get("END DATE").replaceAll("/",""));
        Assert.assertEquals(getHoverText().get("END DATE").replaceAll("/",""), newEndDate);
    }

    @Then("I update the start date of emailID")
    public void i_update_the_start_date_of_emailID() throws Throwable {
        newStartDate= getPriorDate(300);
        clearAndFillText(crmAddContactInfoPage.startDate, newStartDate);
        crmAddContactInfoPage.saveButton.click();
    }

    @Then("I verify that start date has been updated")
    public void i_verify_that_end_start_has_been_updated() throws Throwable {
        hover(crmAddContactInfoPage.firstEmailIDStatus);
        System.out.println("hover TExt "+getHoverText().get("START DATE").replaceAll("/",""));
        Assert.assertEquals(getHoverText().get("START DATE").replaceAll("/",""), newStartDate);
    }

    @Then("I verify that the active emails are displayed on the top")
    public void i_verify_that_the_active_emails_are_displayed_on_the_top() {
        //Adding first emailID with inactive status
        String emailID1=randomdata.get("email").toString();
        clearAndFillText(crmAddContactInfoPage.emailAddressField, emailID1);
        crmAddContactInfoPage.associatedCaseMember.click();
        waitFor(1);
        scrollToElement(crmAddContactInfoPage.dropdownLastItem);
        hover(crmAddContactInfoPage.dropdownLastItem);
        crmAddContactInfoPage.dropdownLastItem.click();
        clearAndFillText(crmAddContactInfoPage.startDate,"10122016");
        clearAndFillText(crmAddContactInfoPage.endDate,getPriorDate(2));
        crmAddContactInfoPage.saveButton.click();
        demographicContactInfoPage.addEmailButton.click();

        // Adding second EmailID with active status
        String emailID2=randomdata.get("email").toString();
        clearAndFillText(crmAddContactInfoPage.emailAddressField, emailID2);
        crmAddContactInfoPage.associatedCaseMember.click();
        waitFor(1);
        scrollToElement(crmAddContactInfoPage.dropdownLastItem);
        hover(crmAddContactInfoPage.dropdownLastItem);
        crmAddContactInfoPage.dropdownLastItem.click();
        clearAndFillText(crmAddContactInfoPage.startDate,"10122018");
        clearAndFillText(crmAddContactInfoPage.endDate,getGreaterDate(365));
        crmAddContactInfoPage.saveButton.click();
        demographicContactInfoPage.addEmailButton.click();

        // Adding third EmailID with active status
        String emailID3=randomdata.get("email").toString();
        clearAndFillText(crmAddContactInfoPage.emailAddressField, emailID3);
        crmAddContactInfoPage.associatedCaseMember.click();
        waitFor(1);
        scrollToElement(crmAddContactInfoPage.dropdownLastItem);
        hover(crmAddContactInfoPage.dropdownLastItem);
        crmAddContactInfoPage.dropdownLastItem.click();
        clearAndFillText(crmAddContactInfoPage.startDate,"10122018");
        clearAndFillText(crmAddContactInfoPage.endDate,getGreaterDate(365));
        crmAddContactInfoPage.saveButton.click();

        //verify that Active mails are on top
        scrollToElement(crmAddContactInfoPage.emailAddressHeader);
        TreeMap<String, String> recordsOnUI = getRecordsOrder();
        int i=1;
        for(Map.Entry<String,String> entry : recordsOnUI.entrySet()) {
            if(i==1 || i==2)
                Assert.assertEquals(entry.getValue(),"ACTIVE");
            else
                Assert.assertEquals(entry.getValue(),"INACTIVE");
            i++;
        }
    }

    @When("I add four emailId's with active and inactive status")
    public void i_add_four_emailId_s_with_active_and_inactive_status() {
        //Adding first emailID
        String emailID1=randomdata.get("email").toString();
        clearAndFillText(crmAddContactInfoPage.emailAddressField, emailID1);
        crmAddContactInfoPage.associatedCaseMember.click();
        waitFor(1);
        scrollToElement(crmAddContactInfoPage.dropdownLastItem);
        hover(crmAddContactInfoPage.dropdownLastItem);
        crmAddContactInfoPage.dropdownLastItem.click();
        clearAndFillText(crmAddContactInfoPage.startDate,"10122016");
        clearAndFillText(crmAddContactInfoPage.endDate,getPriorDate(2));
        crmAddContactInfoPage.saveButton.click();
        demographicContactInfoPage.addEmailButton.click();

        // Adding second EmailID
        String emailID2=randomdata.get("email").toString();
        clearAndFillText(crmAddContactInfoPage.emailAddressField, emailID2);
        crmAddContactInfoPage.associatedCaseMember.click();
        waitFor(1);
        scrollToElement(crmAddContactInfoPage.dropdownLastItem);
        hover(crmAddContactInfoPage.dropdownLastItem);
        crmAddContactInfoPage.dropdownLastItem.click();
        clearAndFillText(crmAddContactInfoPage.startDate,"10122018");
        clearAndFillText(crmAddContactInfoPage.endDate,getGreaterDate(365));
        crmAddContactInfoPage.saveButton.click();
        demographicContactInfoPage.addEmailButton.click();

        // Adding third EmailID
        String emailID3=randomdata.get("email").toString();
        clearAndFillText(crmAddContactInfoPage.emailAddressField, emailID3);
        crmAddContactInfoPage.associatedCaseMember.click();
        waitFor(1);
        scrollToElement(crmAddContactInfoPage.dropdownLastItem);
        hover(crmAddContactInfoPage.dropdownLastItem);
        crmAddContactInfoPage.dropdownLastItem.click();
        clearAndFillText(crmAddContactInfoPage.startDate,"10122018");
        clearAndFillText(crmAddContactInfoPage.endDate,getGreaterDate(365));
        crmAddContactInfoPage.saveButton.click();
        demographicContactInfoPage.addEmailButton.click();


        // Adding fourth EmailID
        String emailID4=randomdata.get("email").toString();
        clearAndFillText(crmAddContactInfoPage.emailAddressField, emailID4);
        crmAddContactInfoPage.associatedCaseMember.click();
        waitFor(1);
        scrollToElement(crmAddContactInfoPage.dropdownLastItem);
        hover(crmAddContactInfoPage.dropdownLastItem);
        crmAddContactInfoPage.dropdownLastItem.click();
        clearAndFillText(crmAddContactInfoPage.startDate,"10012018");
        clearAndFillText(crmAddContactInfoPage.endDate,getGreaterDate(365));
        crmAddContactInfoPage.saveButton.click();
    }

    @Then("I verify that three emails are display at first glance")
    public void i_verify_that_three_emails_are_display_at_first_glance() {
        scrollToElement(crmAddContactInfoPage.emailAddressHeader);
        TreeMap<String, String> recordsOnUI = new TreeMap<String, String>();
        recordsOnUI = getRecordsOrder();
        Assert.assertEquals(recordsOnUI.size(), 3);
    }

    @Then("I verify pagination is available when more than three records are added")
    public void validatePaginationOnRecordsPage(){
        Assert.assertEquals(getPaginationSize("email"), 3);
    }

}
