package com.maximus.crm.step_definitions;

import com.maximus.crm.pages.tm.TMListOfProjectsPage;
import com.maximus.crm.pages.tm.TMProjectListPage;
import com.maximus.crm.utilities.BrowserUtils;
import com.maximus.crm.utilities.TMUtilities;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class TM_BADashboardStepDef extends BrowserUtils {

    TMListOfProjectsPage tenantManagerListOfProjectsPage = new TMListOfProjectsPage();
    TMUtilities tenantManagerUtils = new TMUtilities();
    TMProjectListPage tmProjectListPage = new TMProjectListPage();

    @Then("The System should display the Current Date in the Header")
    public void the_system_should_display_the_current_date_in_the_header() {

        staticWait(100);
        Assert.assertTrue(tenantManagerListOfProjectsPage.date.isDisplayed());
        String actualDate = tenantManagerListOfProjectsPage.date.getText();
        System.out.print(actualDate);
        String expectedDate = getCurrentDate();
        if (expectedDate.matches(actualDate)) {

            highLightElement(tenantManagerListOfProjectsPage.date);
        }
    }

    @Then("The System should display the Role")
    public void the_system_should_display_the_role() {
        Assert.assertTrue(tenantManagerListOfProjectsPage.role.isDisplayed());
        highLightElement(tenantManagerListOfProjectsPage.role);

    }

    @Then("The System should display the UserName")
    public void the_system_should_display_the_username() {
        Assert.assertTrue(tenantManagerListOfProjectsPage.userNameHeader.isDisplayed());
        //highLightElement(tenantManagerListOfProjectsPage.userNameHeader);
        String actualUserName = tenantManagerListOfProjectsPage.userNameHeader.getText();
        String expectedUserName = tenantManagerUtils.getCellValueBySheetRowAndColoumn("Login", "ValidData", "UserName");
        if (actualUserName.matches(expectedUserName)) {
            highLightElement(tenantManagerListOfProjectsPage.userNameHeader);
        }
    }

    @And("I scroll to the bottom of the page")
    public void i_scroll_to_the_bottom_of_the_page() {
        scrollDown();


    }

    @Then("The System should display the Office Address")
    public void the_System_should_display_the_Office_Address() {
        Assert.assertTrue(tenantManagerListOfProjectsPage.addressLine1.isDisplayed());
        highLightElement(tenantManagerListOfProjectsPage.addressLine1);
        Assert.assertTrue(tenantManagerListOfProjectsPage.getAddressLine2.isDisplayed());
        highLightElement(tenantManagerListOfProjectsPage.getAddressLine2);

    }

    @Then("the system displays a list of all projects that have been entered into the CRM system")
    public void the_system_displays_a_list_of_all_projects_that_have_been_entered_into_the_crm_system() {

        for (WebElement eachResult : tmProjectListPage.projects) {
            Assert.assertTrue(eachResult.isDisplayed());
        }
    }


}
