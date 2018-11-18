package com.maximus.crm.step_definitions;

import com.maximus.crm.pages.tm.*;
import com.maximus.crm.utilities.Driver;
import com.maximus.crm.utilities.ExcelReader;
import com.maximus.crm.utilities.TMUtilities;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.maximus.crm.utilities.BrowserUtils.waitForVisibility;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TM_AddNewUserToProjectStepDef extends TMUtilities {
    private WebDriver driver = Driver.getDriver();

   // String excelLoc="C:\\Git\\maximus-crm-qa-automation-framework\\qa-automation\\src\\test\\resources\\testData\\TestData.xlsx";
   // ExcelReader excelReader=new ExcelReader(excelLoc);


    static int randomMaxID = randomNumberBetweenTwoNumbers(154000,154700);

    TMViewProjectPage viewProject = new TMViewProjectPage();
    TMAddNewUserPage tmAddNewUserPage = new TMAddNewUserPage();
    TM_SaveAProjectStepDef tmSaveAProjectStepDef = new TM_SaveAProjectStepDef();
    TMProjectListPage tmProjectListPage = new TMProjectListPage();
    TM_EditProjectInformationStepDef tmEditProjectInformationStepDef = new TM_EditProjectInformationStepDef();
    TM_SearchProjectStepDefs tmSearchProjectStepDefs = new TM_SearchProjectStepDefs();
    TMSearchUserPage searchUser = new TMSearchUserPage();
    TMProjectDetailsPage tmProjectDetailsPage = new TMProjectDetailsPage();
    Map<String, Object> newTestData = new HashMap<>(getNewTestData());
    TM_CreateProjectStepDef tm_createProjectStepDef=new TM_CreateProjectStepDef();
    TMProjectRolePage tmProjectRolePage=new TMProjectRolePage();

    public int get_random_max_ID() {
        return randomMaxID;
    }


    @When("I set maximus employee to yes")
    public void i_set_maximus_employee_to_yes() {
        tmAddNewUserPage.set_MaximusEmp_Status("True");
    }

    @When("I enter max id {string}")
    public void i_enter_max_id(String string) {
        tmAddNewUserPage.set_MaximusEmp_Id(string);
    }

    @When("I click on add max id button")
    public void i_click_on_add_max_id_button() {
        tmAddNewUserPage.addMaxIdButton.click();
        tmAddNewUserPage.add_maxId();
    }

    @Then("I receive an error that max ID does not exist")
    public void i_receive_an_error_that_max_ID_does_not_exist() {
        assertTrue(tmAddNewUserPage.get_maxIDError(), "Error that Max ID is not found, is not displayed");
    }

    @When("I create,save and view new project details")
    public void i_create_save_and_view_new_project_details() {
        tmSaveAProjectStepDef.i_enter_valid_details_and_save_the_project();
        tmSearchProjectStepDefs.i_search_for_a_project_by_value("project", tmSaveAProjectStepDef.projectName);
        waitFor(10);
        tmProjectListPage.getProjectDetailsButton(tmSaveAProjectStepDef.projectName).click();
    }

    @Then("I should be navigated to User Details page")
    public void i_should_be_navigated_to_User_Details_page() {
        assertTrue(tmAddNewUserPage.get_addUserHeader().isDisplayed(), "Project-Add User is not displayed on Header");
        assertTrue(tmAddNewUserPage.get_userDetailsPageTitle().isDisplayed(), "User Details title is not displayed");
    }

    @Then("I should see all the elements displayed in the User Details Page")
    public void i_should_see_all_the_elements_displayed_in_the_User_Details_Page() {
        waitForVisibility(tmAddNewUserPage.addFirstName,5);
        assertTrue(tmAddNewUserPage.addFirstName.isDisplayed(), "First Name field is not displayed.");
        assertTrue(tmAddNewUserPage.addLastName.isDisplayed(), "Last Name field is not displayed.");
        assertTrue(tmAddNewUserPage.addMiddleName.isDisplayed(), "Middle Initial field is not displayed.");
        //assertTrue(tmAddNewUserPage.maximusEmpCheckbox.isDisplayed(), "Maximus ID Status checkbox is not displayed");
        tmAddNewUserPage.maximusEmpCheckbox.click();
        assertTrue(tmAddNewUserPage.maximusEmpIdTextbox.isDisplayed(), "Maximus ID Field is not displayed.");
        tmAddNewUserPage.maximusEmpCheckbox.click();
        assertTrue(tmAddNewUserPage.addEmail.isDisplayed(), "Email field is not displayed.");
        assertTrue(tmAddNewUserPage.startDate.isDisplayed(), "Start Date field is not displayed.");
        assertTrue(tmAddNewUserPage.endDate.isDisplayed(), "End Date field is not displayed.");
        assertTrue(tmAddNewUserPage.accountType.isDisplayed(), "Account Type field is not displayed.");
        assertTrue(tmAddNewUserPage.accountAuthType.isDisplayed(), "Account Authorisation field is not displayed.");
        assertTrue(tmAddNewUserPage.authDate.isDisplayed(), "Authorisation Date field is not displayed.");
        assertTrue(tmAddNewUserPage.overrideAuthCheckbox.isDisplayed(), "Override Authorisation checkbox is not displayed.");
        tmAddNewUserPage.overrideAuthCheckbox.click();
        assertTrue(tmAddNewUserPage.overrideAuthReasonType.isDisplayed(), "Override Authorisation Reason field is not displayed.");
        tmAddNewUserPage.overrideAuthCheckbox.click();
        assertTrue(tmAddNewUserPage.accessToPHICheckbox.isDisplayed(), "Access to PHI checkbox is not displayed");
        tmAddNewUserPage.accessToPHICheckbox.click();
        assertTrue(tmAddNewUserPage.phiReasonType.isDisplayed(), "PHI Reason dropdown is not displayed");
        tmAddNewUserPage.accessToPHICheckbox.click();
        assertTrue(tmAddNewUserPage.accessToPIICheckbox.isDisplayed(), "Access to PII checkbox is not displayed");
        tmAddNewUserPage.accessToPIICheckbox.click();
        assertTrue(tmAddNewUserPage.piiReasonType.isDisplayed(), "PII Reason dropdown is not displayed");
        tmAddNewUserPage.accessToPIICheckbox.click();
        assertTrue(tmAddNewUserPage.saveButton.isDisplayed());
        assertTrue(tmAddNewUserPage.cancelButton.isDisplayed());


    }

    @Then("I should see default values populated")
    public void i_should_see_default_values_populated() {
        assertTrue(tmAddNewUserPage.isDisabledByDefault("isMaximusEmp"), "Maximus ID Status checkbox has to be disabled by default.");
        assertFalse(tmAddNewUserPage.maximusEmpIdTextbox.isEnabled(), "Maximus ID field has to be disabled by default.");
        assertTrue(tmAddNewUserPage.isDisabledByDefault("isOverrideAuth"), "Authorisation Date field is not displayed.");
        assertTrue(tmAddNewUserPage.accountType.getText().equals("Individual - maximus"),
                "Account Type dropdown option has to be 'Individual - maximus' by default.");
        assertTrue(tmAddNewUserPage.isDisabledByDefault("isAccessToPHI"), "Access to PHI checkbox has to be disabled by default.");
        assertTrue(tmAddNewUserPage.isDisabledByDefault("isAccessToPII"), "Access to PII checkbox has to be disabled by default.");
        assertFalse(tmAddNewUserPage.projectName.getAttribute("value").equals(tmEditProjectInformationStepDef.currentProjectName),
                "Project Name is not displayed by default");
        assertTrue(tmAddNewUserPage.authDate.getAttribute("value").replace("/", "").equals(getCurrentDateWithFormat()));
    }

    @Then("I should see a pop-up as an overlay on User Details page")
    public void i_should_see_a_pop_up_as_an_overlay_on_User_Details_page() {
        waitFor(5);
        assertTrue(tmAddNewUserPage.get_noApproversError().isDisplayed(), "Pop-up is not displayed when approvers are not added");
    }

    @Then("I click on Continue button on User Details error pop-up")
    public void i_click_on_Continue_button_on_User_Details_error_pop_up() {
        assertTrue(tmAddNewUserPage.get_continueBtnOnApproversError().isDisplayed(), "Continue button is not displayed");
        tmAddNewUserPage.get_continueBtnOnApproversError().click();

    }

    @Then("I am navigated to Project Details page")
    public void i_am_navigated_to_Project_Details_page() {
        waitFor(5);
        assertTrue(tmProjectDetailsPage.projectDetailsTitle.isDisplayed(), "Project Details page is not displayed");
    }

    /* By Vinuta
    This method creates a user inside the project
    Parameters: isMaxEmp(Yes, No),maxID(if not given, takes a random maxID),first name, middle initial, last name, email, startDate(is an integer to be added to current date, if not given, takes current date),end date(integer),
                acctType,acctAuth,authDate,overrideAuthReason,phiAccess,phiReason,piiAccess,piiReason,statusverrideAuth - Not implemented into the method as they are not required at this point
                status(Active, Inactive)
     */
    @When("I create User with given data {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string}")
    public void i_create_User_with_given_data(String isMaxEmp, String maxID, String fn, String mn, String ln, String email, String startDate, String endDate, String acctType, String acctAuth, String authDate, String overrideAuth, String overrideAuthReason, String phiAccess, String phiReason, String piiAccess, String piiReason, String status) {

        //If Maximus Employee is Yes
        if(isMaxEmp.equalsIgnoreCase("Yes")) {
            tmAddNewUserPage.set_MaximusEmp_Status("True");

            //If max ID is not provided, enter random max ID
            if(maxID.isEmpty())
                tmAddNewUserPage.set_MaximusEmp_Id(Integer.toString(randomMaxID));
            else
                tmAddNewUserPage.set_MaximusEmp_Id(maxID);

            tmAddNewUserPage.addMaxId.click();
            waitFor(5);
            assertTrue(!(tmAddNewUserPage.addFirstName.getAttribute("value").isEmpty()), "Max ID employee details are not populated");
        } else {
            //to implement when not max employee
        }

        //If start date is not given:If status is active:enter today's date as start date,end date as today+1
        //If start date is not given:If status is inactive:enter today+1 as start date, end date as today+2

        if(startDate.isEmpty() && endDate.isEmpty() && status.equalsIgnoreCase("active")) {
            clearAndFillText(tmAddNewUserPage.startDate, getCurrentDate());
            clearAndFillText(tmAddNewUserPage.endDate, getGreaterDate(1));
        }

        if(startDate.isEmpty() && endDate.isEmpty() && status.equalsIgnoreCase("inactive")){
            clearAndFillText(tmAddNewUserPage.startDate,getGreaterDate(1));
            clearAndFillText(tmAddNewUserPage.endDate,getGreaterDate(2));
        }

        if(!startDate.isEmpty() && endDate.isEmpty()) {
            clearAndFillText(tmAddNewUserPage.startDate, getGreaterDate(Integer.parseInt(startDate)));
            clearAndFillText(tmAddNewUserPage.startDate, getGreaterDate((Integer.parseInt(startDate)) + 1));
        }

        if(!startDate.isEmpty() && !endDate.isEmpty()){
            clearAndFillText(tmAddNewUserPage.startDate,getGreaterDate(Integer.parseInt(startDate)));
            clearAndFillText(tmAddNewUserPage.endDate,getGreaterDate(Integer.parseInt(endDate)));
        }

    }

    @When("I click on save a user button")
    public void i_click_on_save_a_user_button() {
        waitFor(1);
        tmAddNewUserPage.saveButton.click();
    }
    //refactoring 10-19-18
    @Then("I see mandatory fields have error messages")
    public void i_see_mandatory_fields_have_error_messages(List<String> fields) {

        for (String field : fields) {
            switch (field) {
                case "First Name":
                    scrollToElement(tmAddNewUserPage.userDetailsPageTitle);
                    assertTrue(tmAddNewUserPage.blankFirstNameError.isDisplayed(), "Blank " + field + " Error has to be displayed");
                    //waitFor(10);
                    break;
                case "Last Name":
                    scrollToElement(tmAddNewUserPage.userDetailsPageTitle);
                    assertTrue(tmAddNewUserPage.blankLastNameError.isDisplayed(), "Blank " + field + " Error has to be displayed");
                    //waitFor(10);
                    break;
                case "Email Address":
                    scrollToElement(tmAddNewUserPage.userDetailsPageTitle);
                    assertTrue(tmAddNewUserPage.blankEmailError.isDisplayed(), "Blank " + field + " Error has to be displayed");
                    break;
                case "Start Date":
                    scrollToElement(tmAddNewUserPage.userDetailsPageTitle);
                    assertTrue(tmAddNewUserPage.blankStartDateError.isDisplayed(), "Blank " + field + " Error has to be displayed");
                    break;
					//todo confirm account type is not mandatory
                /*case "Account Type":
                    tmAddNewUserPage.accountType.click();
                    hover(tmAddNewUserPage.blankAccountTypeOption);
                    tmAddNewUserPage.blankAccountTypeOption.click();
                    waitFor(1);
                    tmAddNewUserPage.saveButton.click();
                    assertTrue(tmAddNewUserPage.blankAccountTypeError.isDisplayed(), "Blank " + field + " Error has to be displayed");
                    break;*/
                case "Internal Maximus Employee? - Y/N":
                    scrollToElement(tmAddNewUserPage.userDetailsPageTitle);
//                    JavascriptExecutor jse = (JavascriptExecutor)driver;
//
//                    jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
                    // scrollDownToElement(tmAddNewUserPage.maximusEmpCheckbox);
                    tmAddNewUserPage.maximusEmpCheckbox.click();
                    tmAddNewUserPage.saveButton.click();
                    assertTrue(tmAddNewUserPage.blankMaxIdError.isDisplayed(), "Blank Max Id Error has to be displayed");
                    break;
                case "Role Name":
                    assertTrue(tmProjectRolePage.roleNameBlankError.isDisplayed(),"The Role Name Is Required And Cannot Be Left Blank. error is not displayed");
                    break;
                case "Role Start Date":
                    assertTrue(tmProjectRolePage.roleStartDateBlankError.isDisplayed(),"The Start Date Is Required And Cannot Be Left Blank. error is not displayed ");
                    break;
            }
        }
    }

    /*@When("I select Yes for Maximus Employee")
    public void i_select_Yes_for_Maximus_Employee() {
        tmAddNewUserPage.maximusEmpCheckbox.click();
        assertFalse(tmAddNewUserPage.isDisabledByDefault("isMaximusEmp"), "Maximus ID Status checkbox is not set to 'Yes'");
        waitFor(1);
    }

    @When("I enter valid {string} Max ID in the Maximus Id field and click on plus button")
    public void i_enter_valid_Max_ID_in_the_Maximus_Id_field_and_click_on_plus_button(String id) {
       tmAddNewUserPage.addMaxId.sendKeys(id);
       tmAddNewUserPage.addMaxIdButton.click();
       assertTrue(tmAddNewUserPage.addMaxId.getAttribute("value").equals(id));
    }*/

    @When("I see fields auto-populated with the corresponding values from Active Directory")
    public void i_see_fields_auto_populated_with_the_corresponding_values_from_Active_Directory() {
           assertTrue(tmAddNewUserPage.verifyReadOnlyElByValue("Muhabbat"), "First Name does not match");
           assertTrue(tmAddNewUserPage.verifyReadOnlyElByValue("Khaydarova"), "Last Name does not match");
           assertTrue(tmAddNewUserPage.verifyReadOnlyElByValue("MuhabbatKhaydarova@maximus.com"), "Email does not match");

    }

    @Then("I see Auto-populated fields are not editable")
    public void i_see_Auto_populated_fields_are_not_editable() {
        assertTrue(tmAddNewUserPage.verifyNotEditable(tmAddNewUserPage.addFirstName), "Auto-populated First Name field has can't be editable");
        assertTrue(tmAddNewUserPage.verifyNotEditable(tmAddNewUserPage.addLastName), "Auto-populated Last Name field has can't be editable");
        assertTrue(tmAddNewUserPage.verifyNotEditable(tmAddNewUserPage.addEmail), "Auto-populated Email field has can't be editable");
    }

    @When("I populate some data in the fields")
    public void i_populate_some_data_in_the_fields() {
       clearAndFillText(tmAddNewUserPage.addFirstName,  (newTestData.get("name").toString()));
       clearAndFillText(tmAddNewUserPage.addLastName, (newTestData.get("surname").toString()));
       clearAndFillText(tmAddNewUserPage.addEmail, (newTestData.get("email").toString()));

    }

    @When("I click on Cancel button on Add User Page")
    public void i_click_on_Cancel_button_on_Add_User_Page() {
        tmAddNewUserPage.cancelButton.click();
    }

    @When("I see {string} alert displayed")
    public void i_see_alert_displayed(String string) {
        waitFor(1);
        assertTrue(tmAddNewUserPage.cancelWarningPopUp.isDisplayed(), "Warning Pop Up is not displayed");

    }

    @When("I click on No and I am navigated back to Add User page and see all previously entered unsaved data")
    public void i_click_on_No_and_I_am_navigated_back_to_Add_User_page_and_see_all_previously_entered_unsaved_data() {
       tmAddNewUserPage.warningPopUpNoButton.click();
       assertTrue(tmAddNewUserPage.addFirstName.getAttribute("value").equals(newTestData.get("name").toString()),
               "First Name did not remain the same");
       assertTrue(tmAddNewUserPage.addLastName.getAttribute("value").equals(newTestData.get("surname").toString()),
               "Last Name did not remain the same");
       assertTrue(tmAddNewUserPage.addEmail.getAttribute("value").equals(newTestData.get("email").toString()),
               "Email did not remain the same");
    }

    @Then("I click on Yes and I am navigated back to User List Page")
    public void i_click_on_Yes_and_I_am_navigated_back_to_User_List_Page() {
       tmAddNewUserPage.warningPopUpYesButton.click();
        assertTrue(searchUser.addUserButton.isDisplayed(), "Add User Button id not displayed");
        assertTrue(searchUser.projectName.isDisplayed(), "Project Name is not displayed on Header");

    }

    @Then("I am navigated back to User List Page")
    public void i_am_navigated_back_to_User_List_Page() {
        assertTrue(searchUser.addUserButton.isDisplayed(), "Add User Button id not displayed");
        assertTrue(searchUser.projectName.isDisplayed(), "Project Name is not displayed on Header");
    }
    @When("I click on add new user button on add new user page")
    public void i_click_on_add_new_user_button_on_add_new_user_page() {

        searchUser.addUserButton.click();
    }

    @When("I enter the Start Date prior to the date of creation")
    public void i_enter_the_Start_Date_prior_to_the_date_of_creation() {
       clearAndFillText(tmAddNewUserPage.startDate, getPriorDate(2));
    }
//refactoring 10-19-18
    @When("I see {string} message under the {string} field")
    public void i_see_message_under_the_field(String message, String field) {
        if (field.equals("start")) {
            scrollToElement(tmAddNewUserPage.userDetailsPageTitle);
            assertTrue(tmAddNewUserPage.priorDateError.isDisplayed());
            assertTrue(tmAddNewUserPage.priorDateError.getText().equals(message));
        }
        if (field.equals("end")) {
            scrollToElement(tmAddNewUserPage.userDetailsPageTitle);
            assertTrue(tmAddNewUserPage.lessOrEqualDateError.isDisplayed());
            assertTrue(tmAddNewUserPage.lessOrEqualDateError.getText().equals(message));
        }
        if(field.equals("roleEndDate")) {
            if (message.equals("End date should not be less or equal than start date")) {
                assertTrue(tmProjectRolePage.lessOrEqualEndDateError.isDisplayed());
                assertTrue(tmProjectRolePage.lessOrEqualEndDateError.getText().equals(message));
            }
            if (message.equals("Invalid date format")) {
                assertTrue(tmProjectRolePage.invalidEndDateError.isDisplayed());
                assertTrue(tmProjectRolePage.invalidEndDateError.getText().equals(message));
            }
            if(message.equals("The date entered does not exist. Please enter a valid date.")){
                assertTrue((tmProjectRolePage.nonExistingEndDate.isDisplayed()));
                assertTrue(tmProjectRolePage.nonExistingEndDate.getText().equals(message));
            }
        }
        if(field.equals("roleStartDate")){
            if(message.equals("The Start Date cannot be in the past")) {
                assertTrue(tmProjectRolePage.lessStartDateError.isDisplayed());
                assertTrue(tmProjectRolePage.lessStartDateError.getText().equals(message));
            }
            if(message.equals("Invalid date format")){
                assertTrue(tmProjectRolePage.invalidStartDateError.isDisplayed());
                assertTrue(tmProjectRolePage.invalidStartDateError.getText().equals(message));
            }
            if(message.equals("The date entered does not exist. Please enter a valid date.")){
                assertTrue((tmProjectRolePage.nonExistingStartDate.isDisplayed()));
                assertTrue(tmProjectRolePage.nonExistingStartDate.getText().equals(message));
            }
        }
    }

    @When("I enter the End Date {string} to the Start Start")
    public void i_enter_the_End_Date_to_the_Start_Start(String value) {
        clearAndFillText(tmAddNewUserPage.startDate, getCurrentDate());
        switch(value){
            case "prior":
                clearAndFillText(tmAddNewUserPage.endDate,getPriorDate(2));
                break;

            case "equal":
                clearAndFillText(tmAddNewUserPage.endDate, getCurrentDate());
                break;

        }
    }

    @Then("I should see a pop-up that user is created successfully")
    public void i_should_see_a_pop_up_that_user_is_created_successfully() {
        waitForVisibility(tmAddNewUserPage.continueBtnOnUserCreation,10);
        assertTrue(tmAddNewUserPage.get_userCreatedMessage().isDisplayed(),"User created successfully pop-up is not displayed");
        assertTrue(tmAddNewUserPage.continueBtnOnUserCreation.isDisplayed(),"Continue button is not displayed on user created pop-up");
        tmAddNewUserPage.continueBtnOnUserCreation.click();
        waitForVisibility(searchUser.addUserButton,10);
    }


    @Then("I should see error that user is already active")
    public void i_should_see_error_that_user_is_already_active() {
        assertTrue(tmAddNewUserPage.get_duplicateUserError().isDisplayed(),"Duplicate user error is not displayed");
    }


    //By Vinuta, TODO when add project contact confusion is resolved
    /*@When("I add project contacts if not added")
    public void i_add_project_contacts_if_not_added() {
        waitFor(5);
        if(tmAddNewUserPage.get_noApproversError().isDisplayed())
            i_click_on_Continue_button_on_User_Details_error_pop_up();
            tm_createProjectStepDef.i_should_be_navigated_to_the_add_project_page();
            tmSaveAProjectStepDef.createProjectContactAndSave("Test","T","Test","1234567890","test@test.com");
    }
    */

    @Then("I set Inactive Immediately to Yes")
    public void i_set_Inactive_Immediately_to_Yes() {
        tmAddNewUserPage.inactiveImmediatelyCheckbox.click();
        assertTrue(tmAddNewUserPage.inactiveImmediatelyCheckbox.isSelected(), "Inactive Immediately checkbox is not checked");
    }

    @Then("I should see pop-up with message {string}")
    public void i_should_see_pop_up_with_message(String string) {
        waitForVisibility(tmAddNewUserPage.inactivateUserMessage, 5);
        assertTrue(tmAddNewUserPage.inactivateUserMessage.isDisplayed(), string + "error is not displayed");
    }

    @Then("I click on ok button to inactivate user")
    public void i_click_on_ok_button_to_inactivate_user() {
        tmAddNewUserPage.OkButton.click();
    }

    @Then("I verify end date is set to current date & not editable")
    public void i_verify_end_date_is_set_to_current_date_not_editable() {
        assertTrue(tmAddNewUserPage.verifyNotEditable(tmAddNewUserPage.endDate), "End date cannot be edited");
    }

    @Then("I verify values in account inactivation {string} dropdown")
    public void i_verify_values_in_account_inactivation_dropdown(String string, List<String> options) {
        for (String option : options) {
            selectInactivateReason(tmAddNewUserPage.accInactivationReason,option);
            waitFor(3);
        }

    }

    @Then("I verify values in account reactivation {string} dropdown")
    public void i_verify_values_in_account_reactivation_dropdown(String string, List<String> options) {
        for (String option : options) {
            selectInactivateReason(tmAddNewUserPage.accReactivationReason,option);
            waitFor(3);
        }

    }

    @Then("I should see message that user has been inactivated successfully")
    public void i_should_see_message_that_user_has_been_inactivated_successfully() {
        waitForVisibility(tmAddNewUserPage.inactivateSuccessMessage,5);
        assertTrue(tmAddNewUserPage.inactivateSuccessMessage.isDisplayed(),"User is not inactivated");
    }

    @Then("I see the error that End date must be empty to reactivate a user")
    public void i_see_the_error_that_End_date_must_be_empty_to_reactivate_a_user() {
            assertTrue(tmAddNewUserPage.endDateReactivateError.isDisplayed(),"End Date should be blank for reactivation");
        }

    @Then("I nullify the end date")
    public void i_nullify_the_end_date() {
        clearAndFillText(tmAddNewUserPage.endDate,"");
    }

    @Then("I should see message that user has been reactivated successfully")
    public void i_should_see_message_that_user_has_been_reactivated_successfully() {
        waitForVisibility(tmAddNewUserPage.reactivateSuccessMessage,5);
        assertTrue(tmAddNewUserPage.reactivateSuccessMessage.isDisplayed(),"User is not inactivated");
    }

    @Then("I click on ok button to reactivate user")
    public void i_click_on_ok_button_to_reactivate_user() {
        tmAddNewUserPage.OkButton.click();
    }

    @And("I select value {string} in account inactivation dropdown")
    public void i_select_value_in_account_inactivation_dropdown(String value) {
        selectDropDown(tmAddNewUserPage.accInactivationReason, value);
        waitFor(3);
    }
// added to handle If the Project does not have any Project Role Like Account Approver and Account Manager
    @When("I check Account Manager and Approver are added")
    public void i_check_Account_Manager_and_Approver_are_added() {
        try{
        selectDropDown(viewProject.projectRoleDropdown, "Account Approver");
        waitFor(1);
        viewProject.createRoleFirstName.sendKeys("Test");
        viewProject.createRoleLastName.sendKeys("Test");
        viewProject.createRolePhone.sendKeys("2222222222");
        viewProject.createRoleEmail.sendKeys("test@email.com");
        viewProject.createRoleSaveButton.click();
        tmEditProjectInformationStepDef.i_expend_a_Project_to_view_the_details();
        selectDropDown(viewProject.projectRoleDropdown, "Account Manager");
        waitFor(1);
        viewProject.createRoleFirstName.sendKeys("Test");
        viewProject.createRoleLastName.sendKeys("Test");
        viewProject.createRolePhone.sendKeys("3333333332");
        viewProject.createRoleEmail.sendKeys("test@email.com");
        viewProject.createRoleSaveButton.click();
        tmEditProjectInformationStepDef.i_expend_a_Project_to_view_the_details();
        }
        catch (Exception e)
        {
            System.out.print("Exception accrued :");
           e.printStackTrace();
        }

    }

    @When("I inactivate user in project with data {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string}")
    public void i_inactivate_user_in_project_with_data(String isMaxEmp, String maxID, String fn, String mn, String ln, String email, String startDate, String endDate, String acctType, String acctAuth, String authDate, String overrideAuth, String overrideAuthReason, String phiAccess, String phiReason, String piiAccess, String piiReason, String status) {
        this.i_set_Inactive_Immediately_to_Yes();
        this.i_should_see_pop_up_with_message("The user account will be deactivated when you click the Save button");
        this.i_click_on_ok_button_to_inactivate_user();
        this.i_select_value_in_account_inactivation_dropdown("Resigned");
        this.i_click_on_save_a_user_button();
        this.i_should_see_message_that_user_has_been_inactivated_successfully();
        this.i_click_on_ok_button_to_inactivate_user();
    }
}
