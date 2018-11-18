package com.maximus.crm.step_definitions;

import com.maximus.crm.pages.tm.TMProjectRolePage;
import com.maximus.crm.pages.tm.TMSearchRolePage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Assert.*;
import static org.testng.Assert.assertTrue;
import com.maximus.crm.utilities.BrowserUtils;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TM_ProjectRoleDetailsStepDef {

    TMProjectRolePage tmProjectRolePage = new TMProjectRolePage();
    TM_CreateProjectStepDef tmCreateProjectStepDef = new TM_CreateProjectStepDef();
    TM_EditProjectInformationStepDef tmEditProjectInformationStepDef = new TM_EditProjectInformationStepDef();
    TMSearchRolePage tmSearchRolePage = new TMSearchRolePage();
    BrowserUtils util = new BrowserUtils();

    public boolean verifyNotEditable(WebElement el) {
        try {
            util.clearAndFillText(el, "editing");
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Given("I navigate to project role details page")
    public void i_navigate_to_project_role_details_page() {
        tmCreateProjectStepDef.i_logged_into_Tenant_Manager_Project_list_page();
        tmEditProjectInformationStepDef.i_expend_a_Project_to_view_the_details();
        tmCreateProjectStepDef.i_click_on_Role_List_Menu();
        tmSearchRolePage.addRoleButton.click();
        Assert.assertTrue(tmProjectRolePage.addRoleHeader.isDisplayed(), "Role Details page is not displayed");
    }

    @Then("I see project name, project ID, role name, role description, start date, end date fields displayed")
    public void i_see_project_name_project_ID_role_name_role_description_start_date_end_date_fields_displayed() {
        assertTrue(tmProjectRolePage.addRoleHeader.isDisplayed(), "Add Role not displayed on the header");
        assertTrue(tmProjectRolePage.roleDetailsPageTitle.isDisplayed(), "Role details is not displayed as page title");
        assertTrue(tmProjectRolePage.roleName.isDisplayed(), "Role name text field is not displayed");
        assertTrue(tmProjectRolePage.roleDesc.isDisplayed(), "Role description text field is not displayed ");
        assertTrue(tmProjectRolePage.roleStartDate.isDisplayed(), "Role start date is not displayed");
        assertTrue(tmProjectRolePage.roleEndDate.isDisplayed(), "Role end date is not displayed");
        assertTrue(tmProjectRolePage.projectName.getAttribute("value").equalsIgnoreCase(tmEditProjectInformationStepDef.currentProjectName));
        assertTrue(tmEditProjectInformationStepDef.currentProjectId.contains(tmProjectRolePage.projectId.getAttribute("value")));
        assertTrue(verifyNotEditable(tmProjectRolePage.projectId));
        assertTrue(verifyNotEditable(tmProjectRolePage.projectName));
    }

    @When("I click on Save button on project role page")
    public void i_click_on_Save_button_on_project_role_page() {
        util.staticWait(10000);
        tmProjectRolePage.saveButton.click();
    }

    @When("I enter End Date {string} to Start Date")
    public void i_enter_End_Date_to_Start_Date(String value) {
        util.clearAndFillText(tmProjectRolePage.roleStartDate, util.getCurrentDate());
        switch (value) {
            case "prior":
                util.clearAndFillText(tmProjectRolePage.roleEndDate, util.getPriorDate(2));
                break;

            case "equal":
                util.clearAndFillText(tmProjectRolePage.roleEndDate, util.getCurrentDate());
                break;

        }
    }

    @Then("I see pop-up that role is created")
    public void i_see_pop_up_that_role_is_created() {
        BrowserUtils.waitForVisibility(tmProjectRolePage.roleCreatedPopUp, 10);
        assertTrue(tmProjectRolePage.roleCreatedPopUp.isDisplayed(), "Role created pop-up is not displayed");
        tmProjectRolePage.okButton.click();
    }

    @When("I populate data on project role page {string},{string},{string},{string}")
    public void i_populate_data_on_project_role_page(String roleName, String roleDesc, String startDate, String endDate) {
        if (roleName.equals("{random}")) roleName = "Role" + util.getRandomString(5);
        util.clearAndFillText(tmProjectRolePage.roleName, roleName);

        if (roleDesc.equals("{random}")) roleDesc = "RoleDesc " + util.getRandomString(7);
        util.clearAndFillText(tmProjectRolePage.roleDesc, roleDesc);

        if (startDate.equals("today")) startDate = util.getCurrentDate();
        if (startDate.equals("-1")) startDate = BrowserUtils.getPriorDate(1);
        if (startDate.equals("+1")) startDate = BrowserUtils.getGreaterDate(1);
        if (startDate.equals("+2")) startDate = BrowserUtils.getGreaterDate(2);
        util.clearAndFillText(tmProjectRolePage.roleStartDate, startDate);

        if (endDate.equals("today")) endDate = util.getCurrentDate();
        if (endDate.equals("-1")) endDate = BrowserUtils.getPriorDate(1);
        if (endDate.equals("+1")) endDate = BrowserUtils.getGreaterDate(1);
        if (endDate.equals("+2")) endDate = BrowserUtils.getGreaterDate(2);
        util.clearAndFillText(tmProjectRolePage.roleEndDate, endDate);
    }

    @Then("I click on add role button on role list page")
    public void i_click_on_add_role_button_on_role_list_page() {
        tmSearchRolePage.addRoleButton.click();
        BrowserUtils.waitForVisibility(tmProjectRolePage.addRoleHeader,10);
    }

    @Then("I see error that role already exists")
    public void i_see_error_that_role_already_exists() {
        assertTrue(tmProjectRolePage.duplicateRoleError.isDisplayed(),"Role already exists error is not displayed");
    }

    @When("I click on Cancel button on Add Role Page")
    public void i_click_on_Cancel_button_on_Add_Role_Page() {
        tmProjectRolePage.cancelButton.click();
    }

    @When("I click on No and I am navigated back to Add Role page and see all previously entered unsaved data")
    public void i_click_on_No_and_I_am_navigated_back_to_Add_Role_page_and_see_all_previously_entered_unsaved_data() {
        tmProjectRolePage.warningPopUpNoButton.click();
        assertTrue(tmProjectRolePage.roleName.getAttribute("value").equals("Test"),
                "First Name did not remain the same");
        assertTrue(tmProjectRolePage.roleDesc.getAttribute("value").equals("Testing cancel button"),
                "Test description did not remain the same");
        assertTrue(tmProjectRolePage.roleStartDate.getAttribute("value").equals(util.getCurrentDate()),
                "Start date did not remain the same");
        assertTrue(tmProjectRolePage.roleEndDate.getAttribute("value").equals(util.getCurrentDate()),
                "End date did not remain the same");
    }


    @Then("I click on Yes and I am navigated back to Role List Page")
    public void i_click_on_Yes_and_I_am_navigated_back_to_Role_List_Page() {
        tmProjectRolePage.warningPopUpYesButton.click();
        assertTrue(tmSearchRolePage.addRoleButton.isDisplayed(), "Add User Button id not displayed");
    }
}