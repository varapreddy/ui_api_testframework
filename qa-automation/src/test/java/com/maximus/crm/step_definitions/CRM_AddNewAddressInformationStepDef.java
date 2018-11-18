package com.maximus.crm.step_definitions;

import com.maximus.crm.pages.crm.CRMAddContactInfoPage;
import com.maximus.crm.pages.crm.CRMDemographicContactInfoPage;
import com.maximus.crm.utilities.CRMUtilities;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;

public class CRM_AddNewAddressInformationStepDef extends CRMUtilities {

    CRMDemographicContactInfoPage demographicContactInfoPage=new CRMDemographicContactInfoPage();
    CRMAddContactInfoPage crmAddContactInfoPage=new CRMAddContactInfoPage();
     public String addressLineOnExpected;
     public String addressLineTwoExpected;
     public String cityFieldExpected;

    @And("I should see the Demographic Tab")
    public void i_should_see_the_demographic_tab(){
        Assert.assertTrue(demographicContactInfoPage.demographicInfoTab.isDisplayed());

    }

    @Then("I click on the Demographic Info Tab")
    public void i_click_on_the_demographic_info_tab(){
        waitForVisibility(demographicContactInfoPage.demographicInfoTab,10);
        demographicContactInfoPage.demographicInfoTab.click();
    }

    @And("I should see contact Info Tab")
    public void i_should_see_contact_info_tab(){
        waitForVisibility(demographicContactInfoPage.contactInfoTab,2);
        Assert.assertTrue(demographicContactInfoPage.contactInfoTab.isDisplayed());
    }

    @Then("I click on the Contact Info Tab")
    public void i_click_on_the_contact_info_tab(){
        demographicContactInfoPage.contactInfoTab.click();
    }

    @And("I should see the Address label should be displayed")
     public void i_should_see_the_address_label_should_be_displayed(){
        Assert.assertTrue(demographicContactInfoPage.addressLabel.isDisplayed());
    }

    @And("I should see the Add button displayed")
    public void i_should_see_the_add_button_displayed(){
        Assert.assertTrue(demographicContactInfoPage.addAddressButton.isDisplayed());
    }

    @Then("I click on the Add button")
    public void i_click_on_the_add_button(){
       waitForVisibility(demographicContactInfoPage.addAddressButton,2);
        demographicContactInfoPage.addAddressButton.click();
    }

    @Then("I verify that I am in the Address Page")
    public void i_verify_that_i_am_in_the_address_page(){
        Assert.assertTrue(demographicContactInfoPage.addAddressButton.isDisplayed());
    }

    @And("I verify by entering valid data in the address field1 {string}")
    public void i_verify_by_entering_valid_data_in_the_address_field1(String address){
        clearAndFillText(crmAddContactInfoPage.addressLineOne,address);

    }

    @And("I verify by entering valid data in the address field 2 {string}")
    public void i_verify_by_entering_valid_date_in_the_address_feild_2(String addressLine){
        clearAndFillText(crmAddContactInfoPage.addressLineOne,addressLine);
    }

    @And("I verify by entering valid data in the City field {string}")
    public void i_verify_by_entering_valid_data_in_the_city_field(String city ){
        clearAndFillText(crmAddContactInfoPage.addressLineOne,city);
    }


    @And("I verify by entering valid data in the zip code {string}")
    public void i_verify_by_entering_valid_data_in_the_zip_code(String zipcode){
        clearAndFillText(crmAddContactInfoPage.zip,zipcode);
    }

    @Then("I verify by providing a valid Current Date")
    public void i_verify_by_providing_a_valid_current_date(){
       String date=getCurrentDate();
        clearAndFillText(crmAddContactInfoPage.startDate,date);
    }

    @And("I click on Save button")
    public void i_click_on_save_button(){
        crmAddContactInfoPage.saveButton.click();

    }

    @Then("I should be navigated to the Demographic Page")
    public void i_should_be_navigated_to_the_demographic_page(){

        Assert.assertTrue(demographicContactInfoPage.addressLabel.isDisplayed());
    }

    @And("I verify by entering invalid length in the address field1 {string}")
    public void i_verify_by_entering_invalid_length_in_the_address_field1(String invalid){


       addressLineOnExpected=invalid;
        clearAndFillText(crmAddContactInfoPage.addressLineOne,invalid);

    }

    @Then("The Address Field one should not accept more than 50 characters")
    public void it_should_not_accept_more_than_50_characters(){

        String actual=crmAddContactInfoPage.addressLineOne.getAttribute("value");
        Assert.assertNotEquals(addressLineOnExpected,actual);

    }

    @And("I verify by entering invalid length in the address field2 {string}")
    public void i_verify_by_entering_invalid_length_in_the_address_field2(String expected){
        addressLineTwoExpected=expected;
        clearAndFillText(crmAddContactInfoPage.addressLineTwo,expected);

    }

    @Then("The Address field two should not accept more than 50 characters")
    public void the_address_field_two_should_not_accept_more_than_50_characters(){
        String actual=crmAddContactInfoPage.addressLineTwo.getAttribute("value");
        Assert.assertNotEquals(addressLineTwoExpected,actual);
    }

    @And("I provide more than thirty characters in the City field {string}")
    public void i_provide_more_than_thirty_characters_in_the_city_field(String expected){
        cityFieldExpected=expected;
        clearAndFillText(crmAddContactInfoPage.city,expected);

    }

    @Then("The City field should not accept more than 30 characters")
    public void the_city_field_should_accept_more_than_30_characters(){
         String actual=crmAddContactInfoPage.city.getAttribute("value");
         Assert.assertNotEquals(cityFieldExpected,actual);

    }





}
