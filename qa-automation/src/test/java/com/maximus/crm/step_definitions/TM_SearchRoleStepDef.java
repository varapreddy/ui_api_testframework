package com.maximus.crm.step_definitions;

import com.maximus.crm.pages.tm.TMSearchRolePage;
import cucumber.api.java.en.Given;
import org.testng.Assert;


public class TM_SearchRoleStepDef {
    @Given("I navigate to project role list page")
    public void i_navigate_to_project_role_details_page() {

        TMSearchRolePage tmSearchRolePage = new TMSearchRolePage();
        Assert.assertTrue(tmSearchRolePage.addRoleButton.isDisplayed(), "Role list page is not displayed");
    }
}
