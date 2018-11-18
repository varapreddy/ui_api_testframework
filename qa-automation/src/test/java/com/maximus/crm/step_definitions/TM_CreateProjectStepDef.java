package com.maximus.crm.step_definitions;

import com.maximus.crm.pages.tm.TMProjectDetailsPage;
import com.maximus.crm.pages.tm.TMListOfProjectsPage;
import com.maximus.crm.utilities.ConfigurationReader;
import com.maximus.crm.utilities.Driver;
import com.maximus.crm.utilities.TMUtilities;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class TM_CreateProjectStepDef extends TMUtilities {

    private WebDriver driver= Driver.getDriver();
    TMProjectDetailsPage tmProjectDetailsPage =new TMProjectDetailsPage();
    TMListOfProjectsPage tmListOfProjectsPage=new TMListOfProjectsPage();

    public String userNamedata = ConfigurationReader.getProperty("login");
    public String password = ConfigurationReader.getProperty("password");

    public String projectName= getCellValueBySheetRowAndColoumn("Project","Valid","projectName");
    public String programName= getCellValueBySheetRowAndColoumn("Project","Valid","programName");


    @Given("I logged into Tenant Manager Project list page")
    public void i_logged_into_Tenant_Manager_Project_list_page(){
        driver.get(ConfigurationReader.getProperty("tmPageURL"));
        login(userNamedata,password);
    }


    //Todo check with TM_SaveAProjectStepDef line 48
    @When("I click on Create a New Project")
    public void i_click_on_create_a_new_project(){
        highLightElement(tmListOfProjectsPage.createProjectButton);
        staticWait(100);
        tmListOfProjectsPage.createProjectButton.click();

    }

    @Then("I should be navigated to the Add Project Page")
    public void i_should_be_navigated_to_the_add_project_page(){
        Assert.assertTrue(tmProjectDetailsPage.addProjectLogo.isDisplayed());
        highLightElement(tmProjectDetailsPage.addProjectLogo);

    }

//    /*following step is using and duplicating an existing Step/s
//     * the original feature line/s should be used*/
//    @Given("I am on the Home Page")
//    public void i_am_on_the_home_page(){
//        i_logged_into_Tenant_Manager_Project_list_page();
//
//    }
    /*following step is using and duplicating an existing Step/s
     * the original feature line/s should to be used*/
//    @When("I click on the Create new Project")
//    public void i_click_on_the_create_new_project(){
//
//        i_click_on_create_a_new_project();
//    }

    @Then("I should see all the elements displayed in the Project Contact Page")
    public void i_should_see_all_the_elements_displayed_in_the_project_contact_page(){
        staticWait(100);
        Assert.assertTrue(tmProjectDetailsPage.addProjectLogo.isDisplayed());
        Assert.assertTrue(tmProjectDetailsPage.projectName.isDisplayed());
        Assert.assertTrue(tmProjectDetailsPage.state.isDisplayed());
        Assert.assertTrue(tmProjectDetailsPage.programName.isDisplayed());
        Assert.assertTrue(tmProjectDetailsPage.contractId.isDisplayed());
        Assert.assertTrue(tmProjectDetailsPage.stateAgencyName.isDisplayed());
        Assert.assertTrue(tmProjectDetailsPage.contractStartDate.isDisplayed());
        Assert.assertTrue(tmProjectDetailsPage.contractEndDate.isDisplayed());
        Assert.assertTrue(tmProjectDetailsPage.provisioningStatus.isDisplayed());
        Assert.assertTrue(tmProjectDetailsPage.saveButton.isDisplayed());

    }

   /* *//*following step is using and duplicating an existing Step/s
     * the original feature line/s need/s to be used*//*
    @Given("I am on the Project Contact Details Page")
    public void i_am_on_the_project_contact_details_page(){
        i_logged_into_Tenant_Manager_Project_list_page();
        i_click_on_create_a_new_project();

    }*/

   /* @When("I enter all the Details and save the Project")
    public void i_enter_all_the_details_and_save_the_project(){
        staticWait(100);
        browserUtils.createAndSave(projectName,programName,contractId,clientAgency);

    }*/

    @And("I search for the project in Home Page")
     public void i_search_for_the_project_in_the_home_page(){
        staticWait(3000);
        search(projectName,programName);
        selectSearchResults();
        staticWait(100);

     }

    @Then("I should be navigated to the Home Page")
    public void i_should_be_navigated_to_the_home_page(){
        Assert.assertTrue(tmListOfProjectsPage.projectList.isDisplayed());
        highLightElement(tmListOfProjectsPage.projectList);

    }

    //By Vinuta, Method to click on User List option on left navigation menu on Project Details Page
    @When("I click on User List Menu")
    public void i_click_on_User_List_Menu() {
        Assert.assertTrue(tmProjectDetailsPage.viewUserList.isDisplayed());
        tmProjectDetailsPage.viewUserList.click();
    }

    //By Vinuta, Method to click on Role List option on left navigation menu on Project Details Page
    @When("I click on Role List Menu")
    public void i_click_on_Role_List_Menu() {
        Assert.assertTrue(tmProjectDetailsPage.viewRoleList.isDisplayed());
        tmProjectDetailsPage.viewRoleList.click();
    }

}
