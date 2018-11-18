package com.maximus.crm.step_definitions;

import com.maximus.crm.pages.crm.CRMConsumerSearchResultPage;
import com.maximus.crm.pages.crm.CRMContactRecordUIPage;
import com.maximus.crm.utilities.BrowserUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertTrue;

/*
* Author:Shilpa P
* Created 09/12/2018
* */
public class CRM_Close_ContactRecordStepDef extends BrowserUtils {
    CRMConsumerSearchResultPage searchResult = new CRMConsumerSearchResultPage();
    CRMContactRecordUIPage crmContactRecordUIPage=new CRMContactRecordUIPage();
    private List<String> searchResults = new ArrayList<String>();
     public String expectedTime;
     public String expectedStopDuration;


     /*
     * Author:Shilpa P
     * This method works only for unique First Name and Last Name it clicks on the Link record
     * */
    @And("I click on the {string}  record link button")
    public void i_see_all_search_results_according_to_the_first_name(String expected) {
        waitFor(2);
        for (WebElement e : searchResult.FirstNames) {
            searchResults.add(e.getText());

        }

            Iterator<WebElement> itr = searchResult.LinkButtons.iterator();
            while (itr.hasNext()){
                for (String actualFName : searchResults) {
                    assertTrue(expected.equalsIgnoreCase(actualFName.substring(0, expected.length())), "First name search result is incorrect");

                WebElement element=itr.next();
                highLightElement(element);
                element.click();

            }
        }
    }

    @When("I click on the close button on the Header")
    public void i_click_on_the_close_button_on_the_header(){

        crmContactRecordUIPage.stopContact.click();
          expectedTime= timeNow();

    }
    @Then("The Timer should be stopped")
    public void the_timer_should_be_stopped(){
       String actualTime=crmContactRecordUIPage.contactStart.getText();
       String actaulTimeStandard=actualTime.substring(6,14);
       System.out.print(actaulTimeStandard);
        Assert.assertEquals(expectedTime,actaulTimeStandard);

    }
    @And("I click on the Close button in the bottom")
    public void i_click_on_the_close_button_in_the_bottom(){
      crmContactRecordUIPage.closeButton.click();
    }

    @Then("I should see Contact Dispotions should be present")
    public void i_should_see_contact_dispotions_should_be_present(){
        Assert.assertTrue(crmContactRecordUIPage.contactDispositions.isDisplayed());
    }

    @Then("I should see Linked Contact in the Header")
    public void i_should_see_linked_contact_in_the_header(){
       assertTrue(crmContactRecordUIPage.linked.isDisplayed());
        highLightElement(crmContactRecordUIPage.linked);
    }

    @Then("I click on the Consumer Type {string}")
    public void i_click_on_the_consumer_type(String expected) {
        switch (expected) {
            case "Authorized representative":
                staticWait(1000);
                crmContactRecordUIPage.consumerType.click();
                crmContactRecordUIPage.getElementsConsumerType(2).click();
                break;

            case "Member":
                crmContactRecordUIPage.consumerType.click();
                crmContactRecordUIPage.getElementsConsumerType(3).click();
                break;

            case "Non-Member":
                crmContactRecordUIPage.consumerType.click();
                crmContactRecordUIPage.getElementsConsumerType(4).click();
                break;

            case "Primary Individual":
                crmContactRecordUIPage.consumerType.click();
                crmContactRecordUIPage.getElementsConsumerType(5).click();
                break;

            case "Unverified Contact":
                crmContactRecordUIPage.consumerType.click();
                crmContactRecordUIPage.getElementsConsumerType(6).click();
                break;


        }


    }


    @And("I click on the Contact Type {string}")
    public void i_click_on_the_contact_type(String expected) {
        switch (expected) {
            case "Inbound":
                staticWait(1000);
                crmContactRecordUIPage.contactType.click();
                waitForVisibility(crmContactRecordUIPage.getElementsContactType(2),10);
                crmContactRecordUIPage.getElementsContactType(2).click();
                break;

            case "Outbound":
                crmContactRecordUIPage.contactType.click();
                crmContactRecordUIPage.getElementsContactType(3).click();

        }
    }

    @And("I click on the Inbound Call queue {string}")
    public void i_click_on_the_inbound_call_queue(String expected){
        switch(expected){
            case "Eligibility":
                staticWait(1000);
                crmContactRecordUIPage.callQueueType.click();
                crmContactRecordUIPage.getElementsInBoundCall(2).click();
                break;
            case "Enrollment":
                crmContactRecordUIPage.callQueueType.click();
                crmContactRecordUIPage.getElementsInBoundCall(3).click();
                break;

            case "General Program Questions":
                crmContactRecordUIPage.callQueueType.click();
                crmContactRecordUIPage.getElementsInBoundCall(4).click();
                break;

        }

    }

    @And("I click on the Contact Channel Type {string}")
    public void i_click_on_the_contact_channel_type(String expected){
        switch(expected){
            case "Email":
                staticWait(1000);
                crmContactRecordUIPage.contactChannelType.click();
                crmContactRecordUIPage.getElementContactChannelType(2).click();
                break;

            case "Phone":
                staticWait(1000);
                crmContactRecordUIPage.contactChannelType.click();
                crmContactRecordUIPage.getElementContactChannelType(3).click();
                break;

            case "SMS Text":
                crmContactRecordUIPage.contactChannelType.click();
                crmContactRecordUIPage.getElementContactChannelType(4).click();
                break;

            case "Web Chat":
                crmContactRecordUIPage.contactChannelType.click();
                crmContactRecordUIPage.getElementContactChannelType(5).click();
                break;

        }

    }

     @And("I click on the Preffered lanaguage {string}")
    public void i_click_on_the_preffered_language(String expected){
        switch (expected){
            case "English":
                staticWait(1000);
                crmContactRecordUIPage.preferredLanguage.click();
                crmContactRecordUIPage.getElementsPrefferedLangauge(2).click();
                break;

            case "Spanish":
                crmContactRecordUIPage.preferredLanguage.click();
                crmContactRecordUIPage.getElementsPrefferedLangauge(3).click();
                break;
        }

     }

     @And("I click on Consumer Authenticated")
    public void i_click_on_consumer_authenticated(){
        staticWait(1000);
        crmContactRecordUIPage.consumerAuthenticate.click();

     }

     @Then("I click on the contact dispotions {string}")
    public void i_click_on_the_contact_dispotions(String expected){
        switch(expected){
            case "Complete":
                crmContactRecordUIPage.contactDispositions.click();
                crmContactRecordUIPage.getElementsContactDispostions(2).click();
                break;

            case "Dropped":
                crmContactRecordUIPage.contactDispositions.click();
                crmContactRecordUIPage.getElementsContactDispostions(3).click();
                break;

            case "Escalate":
                crmContactRecordUIPage.contactDispositions.click();
                crmContactRecordUIPage.getElementsContactDispostions(4).click();
                break;

            case "Incomplete":
                crmContactRecordUIPage.contactDispositions.click();
                crmContactRecordUIPage.getElementsContactDispostions(5).click();
                break;

            case "OutboundIncomplete":
                staticWait(1000);
                crmContactRecordUIPage.contactDispositions.click();
                crmContactRecordUIPage.getElementsContactDispostions(6).click();
                break;

            case "Requested Call Back":
                crmContactRecordUIPage.contactDispositions.click();
                crmContactRecordUIPage.getElementsContactDispostions(7).click();
                break;

            case "Transfer":
                crmContactRecordUIPage.contactDispositions.click();
                crmContactRecordUIPage.getElementsContactDispostions(8).click();
                break;
        }
     }

    @Then("I should see the Hamburger Menu Displayed")
    public void i_should_see_the_hamburger_menu_displayed(){
        Assert.assertTrue(crmContactRecordUIPage.hamBurgerMenu.isDisplayed());
        highLightElement(crmContactRecordUIPage.hamBurgerMenu);

    }

    @And("I scroll the Page to Reasons and comments")
    public void i_scroll_the_page_to_reasons_and_comments(){
        staticWait(5000);
       // crmContactRecordUIPage.clickWrapBoxElement.click();
        //scrollUp();
       System.out.print("Clicked");
        //staticWait(100);
    }
    @And("I scroll the Page to the Bottom")
    public void i_scroll_the_page_to_the_bottom(){
        staticWait(100);
        scrollDown();
    }














}





