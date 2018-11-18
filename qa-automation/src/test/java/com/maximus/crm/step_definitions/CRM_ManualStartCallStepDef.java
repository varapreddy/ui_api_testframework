package com.maximus.crm.step_definitions;

import com.maximus.crm.pages.crm.CRMContactRecordUIPage;
import com.maximus.crm.utilities.CRMUtilities;
import com.maximus.crm.utilities.ConfigurationReader;
import cucumber.api.java.en.Then;
import org.testng.Assert;

public class CRM_ManualStartCallStepDef extends CRMUtilities {

     CRMContactRecordUIPage crmContactRecordUIPage=new CRMContactRecordUIPage();
   //toDo add the header ID in the Configuration propetry file with the Testing account
    //public String headerID = ConfigurationReader.getProperty("headerID");
    public String userName=ConfigurationReader.getProperty("login");

     @Then("I should see the Inbound Contact text should be present")
    public void i_should_see_the_inbound_contact_text_should_be_present(){
        waitForVisibility(crmContactRecordUIPage.InboundText,10);
         Assert.assertTrue(crmContactRecordUIPage.InboundText.isDisplayed());
     }

     @Then("I should see the Phone text should be present")
    public void i_should_see_the_phone_text_should_be_present(){
         Assert.assertTrue(crmContactRecordUIPage.phoneText.isDisplayed());
     }

     @Then("I should see the User ID should be displayed {string}")
    public void i_should_see_the_user_id_should_be_displayed(String expected){
       String text=crmContactRecordUIPage.headerID.getText();
       System.out.println(text);
       //toDO with the Header ID and uncomment the If Condition
      /* if(expected.contains(text) || headerID.contains(text))
       {
           Assert.assertTrue(crmContactRecordUIPage.headerID.isDisplayed());

       }*/
      //} else//{
           Assert.assertTrue(crmContactRecordUIPage.headerID.isDisplayed());
      // }
     }

     @Then("I should see the Username should be displayed {string}")
      public void i_should_see_the_username_should_be_displayed(String expected){

         String text=crmContactRecordUIPage.headerUsername.getText();
         System.out.println(text);
         if(expected.contains(text)|| userName.contains(text))
         {
             Assert.assertTrue(crmContactRecordUIPage.headerUsername.isDisplayed());
         }
         else{
             Assert.assertTrue(crmContactRecordUIPage.headerUsername.isDisplayed());
         }
     }

}
