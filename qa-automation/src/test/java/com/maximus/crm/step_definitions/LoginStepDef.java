package com.maximus.crm.step_definitions;

import com.maximus.crm.pages.LoginPage;
import com.maximus.crm.pages.tm.TMProjectDetailsPage;
import com.maximus.crm.pages.tm.TMListOfProjectsPage;
import com.maximus.crm.utilities.ConfigurationReader;
import com.maximus.crm.utilities.Driver;
import com.maximus.crm.utilities.TMUtilities;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginStepDef extends TMUtilities {
    private WebDriver driver= Driver.getDriver();

    LoginPage loginPage=new LoginPage();
    TMProjectDetailsPage TMProjectDetailsPage =new TMProjectDetailsPage();
    TMListOfProjectsPage tmListOfProjectsPage=new TMListOfProjectsPage();
    public String userNamedata = ConfigurationReader.getProperty("login");
    public String password = ConfigurationReader.getProperty("password");


    public String invalidUserName=getCellValueBySheetRowAndColoumn("Login","InValidData","UserName");
    public String invalidPassword=getCellValueBySheetRowAndColoumn("Login","InValidData","password");

    public String projectName=getCellValueBySheetRowAndColoumn("Search","Valid ","Project");
    public String programName=getCellValueBySheetRowAndColoumn("Search","Valid","Program");

    @Given("I  navigate to the Tenant Manager Url")
    public void i_navigate_to_the_tenant_manager_url(){
        driver.get(ConfigurationReader.getProperty("tmPageURL"));
    }
    @When("I  navigate it should redirect to the login Page")
    public void i_navigate_it_should_redirect_to_the_login_page(){
        String actualTitle="https://mars-tenant-manager-webapp-dev.cfapps.maxng-dev.maximus.com/login";
       // Assert.assertEquals(driver.getTitle(),actualTitle);
    }
    @Then("I should see the login fields present in the login Page")
    public void i_should_see_the_login_fields_present_in_the_login_page(){
        Assert.assertTrue(loginPage.userName.isDisplayed());
        Assert.assertTrue(loginPage.password.isDisplayed());
        Assert.assertTrue(loginPage.submitButton.isDisplayed());
        //login(userNamedata,password);

    }
    @When("I navigate it should redirect to the login Page and provide valid login credentials")
    public void i_navigate_it_should_redirect_to_the_login_page_and_provide_valid_data(){
     //   i_navigate_it_should_redirect_to_the_login_page();
        login(userNamedata,password);
    }
    @Then("the system should allow me to see the Home page")
    public void the_system_should_allow_me_to_see_the_home_page(){
        highLightElement(tmListOfProjectsPage.projectList);
    }

    @When("I  navigate it should redirect to the login Page  and I provide invalid data in the login page")
    public void i_navigate_it_should_redirect_to_the_login_page_and_i_provide_invalid_data_in_the_login_page(){
        login(invalidUserName,invalidPassword);

    }

    @Then("the system should throw an error message of invalid username and invalid password")
    public void the_system_should_throw_an_error_message_of_invalid_username_and_invalid_password(){
        Assert.assertTrue(loginPage.invalidUserNameError.isDisplayed());
        highLightElement(loginPage.invalidUserNameError);

    }

    @Then("the system should allow me to see the Home page and search for the project")
    public  void the_system_should_allow_me_to_see_the_Home_page_and_search_for_the_project(){
        highLightElement(tmListOfProjectsPage.projectList);
        search(projectName,programName);
    }






}
