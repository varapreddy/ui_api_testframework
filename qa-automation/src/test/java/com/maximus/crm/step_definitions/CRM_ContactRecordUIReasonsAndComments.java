package com.maximus.crm.step_definitions;

import com.maximus.crm.pages.crm.CRMContactRecordUIPage;
import com.maximus.crm.utilities.CRMUtilities;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import static org.testng.Assert.assertTrue;

public class CRM_ContactRecordUIReasonsAndComments extends CRMUtilities {

    CRMContactRecordUIPage crmContactRecordUIPage=new CRMContactRecordUIPage();
    public  String comments= "This is a valid Test Comments";


    @And("I click on Reasons dropdown")
    public void i_click_on_reasons_dropdown(){
        Assert.assertTrue(crmContactRecordUIPage.expendReasonButton.isDisplayed());
        crmContactRecordUIPage.expendReasonButton.click();
        waitFor(5);
    }

    @And("I should see all the attributes present in the Reasons Section")
    public void i_should_see_all_the_attributes_present_in_the_reasons_section(){
        Assert.assertTrue(crmContactRecordUIPage.contactReason.isDisplayed());
        Assert.assertTrue(crmContactRecordUIPage.contactAction.isDisplayed());
        Assert.assertTrue(crmContactRecordUIPage.reasonsComments.isDisplayed());
    }


    @And("I Enter valid data")
    public void i_enter_valid_data_and_click_on_the_save_button() {

        //waitForVisibility(crmContactRecordUIPage.reasonsComments,2);
        //Wait for Visibility Did not work
        staticWait(1000);
        crmContactRecordUIPage.reasonsComments.click();
        sendKeyToTextField(crmContactRecordUIPage.reasonsComments,comments);
        waitForVisibility(crmContactRecordUIPage.saveReasonButton,1000);


    }

    @And("I click on the save button")
            public void i_click_on_the_save_button(){
        crmContactRecordUIPage.saveReasonButton.click();
    }





    @And("I Enter valid data and click on the cancel button")
    public void i_enter_valid_data_and_click_on_the_cancel_button(){
        staticWait(1000);
        crmContactRecordUIPage.reasonsComments.click();
        sendKeyToTextField(crmContactRecordUIPage.reasonsComments,comments);
        waitForVisibility(crmContactRecordUIPage.saveReasonButton,1000);
        crmContactRecordUIPage.cancelButtonResason.click();

    }


    @Then("I should see the Time stamp should be displayed with the comments")
    public void i_should_see_the_time_stamp_should_be_displayed_with_the_comments(){
        String actualTime= "chat "+ getCurrentDate();
        waitForVisibility(crmContactRecordUIPage.timeStamp,10);
        highLightElement(crmContactRecordUIPage.timeStamp);
        assertTrue(crmContactRecordUIPage.timeStamp.isDisplayed(), "Contact Reason dropdown is not displayed");
        String expectedTime=crmContactRecordUIPage.timeStamp.getText();
        System.out.print(expectedTime);
        System.out.println(actualTime);
        highLightElement(crmContactRecordUIPage.reasonsDisplayed);
        Assert.assertTrue(crmContactRecordUIPage.reasonsDisplayed.isDisplayed());

    }

    @Then("I should see the Time stamp should be displayed with the additional  comments")
    public void i_should_see_the_time_stamp_should_be_displayed_with_the_additional_comments(){
        String actualTime= "chat "+ getCurrentDate();
        waitForVisibility(crmContactRecordUIPage.timeStamp,10);
        highLightElement(crmContactRecordUIPage.timeStamp);
        assertTrue(crmContactRecordUIPage.timeStamp.isDisplayed(), "Contact Reason dropdown is not displayed");
        String expectedTime=crmContactRecordUIPage.timeStamp.getText();
        System.out.print(expectedTime);
        System.out.println(actualTime);
        highLightElement(crmContactRecordUIPage.savedAdditionalComments);
        assertTrue(crmContactRecordUIPage.savedAdditionalComments.isDisplayed());

    }

    @And("I click on the Additional Comments")
    public void i_click_on_the_additional_comments(){
        waitFor(1);
        crmContactRecordUIPage.expendAdditionalCommentsButton.click();
        waitFor(1);
    }
    @And("I click on Continue button")
    public void i_click_on_continue_button(){
        staticWait(100);
        crmContactRecordUIPage.continueButtonReasons.click();


    }
    @And("I click on Cancel button")
    public void i_click_on_cancel_button(){
        staticWait(100);
        crmContactRecordUIPage.cancelButtonReasons.click();

    }

    @Then("I should see empty field in comments")
     public void i_should_see_empty_field_in_comments(){
        waitForVisibility(crmContactRecordUIPage.reasonsComments,10);
        assertTrue(textIsNotPresent(comments),"Text is not present");

    }

    @Then("I should see the text present in comments")
    public void i_should_see_the_text_present_in_comments(){
        waitForVisibility(crmContactRecordUIPage.reasonsComments,10);
        Assert.assertFalse(textIsNotPresent(comments),"Text is present");
    }



    @Then("I should be able to save the additional comments")
    public void i_should_be_able_to_save_the_additonal_comments(){

        crmContactRecordUIPage.saveAdditionalComments.click();


    }
    @And("I Enter Valid  additional Comments")
            public void i_enter_valid_comments(){
        staticWait(1000);
        crmContactRecordUIPage.additionalCommentsTextBox.click();
        clearAndFillText(crmContactRecordUIPage.additionalCommentsTextBox,comments);

    }
    @Then("I should see a new  window with Information Lost message")
    public void i_should_see_a_new_window_with_information_lost_message(){
        staticWait(100);
        highLightElement(crmContactRecordUIPage.informationLostMessage);
        assertTrue(crmContactRecordUIPage.informationLostMessage.isDisplayed(),"The Information Lost Message is not Displayed ");

    }
    @When("I expand the button of the saved comments")
    public void i_expand_the_button_of_the_saved_comments(){
        crmContactRecordUIPage.expandSavedReason.click();

    }
    @Then("I should see the comments displayed")
    public void i_should_see_the_comments_displayed(){
        assertTrue(crmContactRecordUIPage.savedCommentsText.isDisplayed(),"Comments are Displayed");
    }

    @Then("I should see the Additional comments displayed")
    public void i_should_see_the_additional_comments_displayed(){
        assertTrue(crmContactRecordUIPage.savedAdditionalComments.isDisplayed());
    }



}
