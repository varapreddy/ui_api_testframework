package com.maximus.crm.step_definitions;

import com.maximus.crm.pages.crm.CRMAddContactInfoPage;
import com.maximus.crm.pages.crm.CRMDemographicContactInfoPage;
import com.maximus.crm.utilities.CRMUtilities;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertTrue;

public class CRM_ViewAddressInformationStepDef extends CRMUtilities {

    CRMDemographicContactInfoPage contactInfo = new CRMDemographicContactInfoPage();
    CRMAddContactInfoPage addContactInfo = new CRMAddContactInfoPage();
    Map<String, Object> newConsumer = new HashMap<>(getNewTestData());
    String expectedAddressOne;
    String expectedAddressTwo;
    String expectedCity;
    String expectedState;
    String expectedZip;
    String expectedCounty;
    String expectedType;
    String expectedFullAddress;

    @And("I click on Add new address button on Contact Info Tab Page")
    public void i_click_on_add_new_address_button_on_contact_info_tab_page() {
        contactInfo.addAddressButton.click();
        waitFor(1);
        assertTrue(addContactInfo.backToContactInfo.isDisplayed(), "Add new Addres page Back to Contact Info Page button is not displayed");
    }


    @When("I add a new active {string} address to a consumer profile")
    public void i_add_a_new_active_address_to_a_consumer_profile(String type) {
        expectedAddressOne = newConsumer.get("name").toString() + " St.";
        expectedAddressTwo = "Apt. #" + ((HashMap) newConsumer.get("birthday")).get("mdy").toString().charAt(1);
        expectedCity = newConsumer.get("surname").toString() + " City";
        expectedState = "Florida";
        expectedZip = ((HashMap) newConsumer.get("credit_card")).get("number").toString().substring(8);
        System.out.println(expectedZip);
        expectedCounty = newConsumer.get("surname").toString();
        expectedType = type;
        expectedFullAddress = expectedAddressOne + ", " + expectedAddressTwo + ", " + expectedCity + ", " + expectedState + ", " + expectedZip;

        clearAndFillText(addContactInfo.addressLineOne, expectedAddressOne);
        clearAndFillText(addContactInfo.addressLineTwo, expectedAddressTwo);
        clearAndFillText(addContactInfo.city, expectedCity);
        waitFor(3);
        selectDropDown(addContactInfo.state, expectedState);
        waitFor(1);
        clearAndFillText(addContactInfo.zip, expectedZip);
        clearAndFillText(addContactInfo.county, expectedCounty);
        clearAndFillText(addContactInfo.startDate, getCurrentDate());
        clearAndFillText(addContactInfo.endDate, getGreaterDate(2));
        selectDropDown(addContactInfo.addressType, type);
        waitFor(1);
        addContactInfo.saveButton.click();
        assertTrue(contactInfo.addressLabel.isDisplayed(), "Contact Info Page Address label is not displayed");
    }

    @Then("I can view full address column has Address Line one, Address Line two, State, Zip in one line displayed")
    public void i_can_view_full_address_column_has_Address_Line_one_Address_Line_two_State_Zip_in_one_line_displayed() {
        System.out.println(contactInfo.fullAddresses.get(1).getText());
        System.out.println(expectedFullAddress);
        //todo uncomment assertion when Bug CRM-1271 is fixed
        // assertTrue(expectedFullAddress.equalsIgnoreCase(contactInfo.fullAddresses.get(1).getText()),
        //     "Full Address does not match expected value");
    }

    @Then("I see {string} column has expected value displayed")
    public void i_see_column_has_expected_value_displayed(String column) {

        switch (column) {
            case "County":
                assertTrue(expectedCounty.equalsIgnoreCase(contactInfo.addressCounties.get(1).getText()),
                        "County name does not match expected value");
                break;
            case "Type":
                assertTrue(expectedType.equalsIgnoreCase(contactInfo.addressTypes.get(1).getText()),
                        "Address Type does not match expected value");
                break;
            case "Source":
                assertTrue(contactInfo.addressSources.get(1).getText().equals("Manual User Captured"),
                        "Address Source does not match expected value");
                break;
            case "Status":
                System.out.println(contactInfo.addressesStatuses.get(1).getText());
                assertTrue(contactInfo.addressesStatuses.get(1).getText().equalsIgnoreCase("Active"),
                        "Address Status does not match expected value");
                break;
        }
    }

//

}
