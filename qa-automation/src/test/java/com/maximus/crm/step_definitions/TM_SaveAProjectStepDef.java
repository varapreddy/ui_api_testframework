package com.maximus.crm.step_definitions;

import com.maximus.crm.pages.LoginPage;
import com.maximus.crm.pages.tm.TMProjectDetailsPage;
import com.maximus.crm.pages.tm.TMListOfProjectsPage;
import com.maximus.crm.utilities.Driver;
import com.maximus.crm.utilities.TMUtilities;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class TM_SaveAProjectStepDef extends TMUtilities {

    private WebDriver driver= Driver.getDriver();

    LoginPage loginPage=new LoginPage();
    TMProjectDetailsPage TMProjectDetailsPage =new TMProjectDetailsPage();
    TMListOfProjectsPage tmListOfProjectsPage=new TMListOfProjectsPage();


    //String excelLoc="C:\\crm-qa-automation-framework\\src\\test\\java\\com\\maximus\\crm\\utilities\\TestData.xlsx";
   // ExcelReader excelReader=new ExcelReader(excelLoc);


    public String userNamedatatExcel= getCellValueBySheetRowAndColoumn("Login","ValidData","UserName");
    public String passExcel= getCellValueBySheetRowAndColoumn("Login","ValidData","password");

    public String projectName= getCellValueBySheetRowAndColoumn("Project","Valid","projectName");
    public String programName= getCellValueBySheetRowAndColoumn("Project","Valid","programName");
    public String contractId=  getCellValueBySheetRowAndColoumn("Project","Valid","contractId");
    public String clientName=  getCellValueBySheetRowAndColoumn("Project","Valid","contractId");

    public String startDate= getCurrentDateWithFormat();



    
    @Then("the elements  should be displayed in the Project Contact Page")
    public void the_elements_should_be_displayed_in_the_project_contact_page()
    {
        staticWait(100);
        Assert.assertTrue(TMProjectDetailsPage.addProjectLogo.isDisplayed());
        Assert.assertTrue(TMProjectDetailsPage.projectName.isDisplayed());
        Assert.assertTrue(TMProjectDetailsPage.state.isDisplayed());
        getStateNames();
        staticWait(100);
        Assert.assertTrue(TMProjectDetailsPage.programName.isDisplayed());
        Assert.assertTrue(TMProjectDetailsPage.contractId.isDisplayed());
        Assert.assertTrue(TMProjectDetailsPage.stateAgencyName.isDisplayed());
        Assert.assertTrue(TMProjectDetailsPage.contractStartDate.isDisplayed());
        Assert.assertTrue(TMProjectDetailsPage.contractEndDate.isDisplayed());
        Assert.assertTrue(TMProjectDetailsPage.provisioningStatus.isDisplayed());
        TMProjectDetailsPage.provisioningStatus.click();
        hover(TMProjectDetailsPage.provisioningStatus);
        Assert.assertTrue(TMProjectDetailsPage.activeStatus.isDisplayed());
        Assert.assertTrue(TMProjectDetailsPage.inactiveStatus.isDisplayed());
        TMProjectDetailsPage.inactiveStatus.click();
        Assert.assertTrue(TMProjectDetailsPage.saveButton.isDisplayed());
    }

    @And("I enter valid Details and save the Project")
    public void i_enter_valid_details_and_save_the_project(){
        createAndSave(projectName,programName,contractId,clientName);
    }

    @Then("User should be navigated to the Home Page")
    public void should_be_navigated_to_the_home_page(){
        Assert.assertTrue(tmListOfProjectsPage.projectList.isDisplayed());
        highLightElement(tmListOfProjectsPage.projectList);

    }

    @Then("I should get a Error Message")
    public void i_should_get_a_error_message(){
        staticWait(10);
        Assert.assertTrue(TMProjectDetailsPage.errorMessage.isDisplayed());

    }

    @And("I enter all the Details Contract Start Date and End Date as Same")
    public void i_enter_all_the_details_contract_start_date_and_end_date_as_same(){
        addContractDateAndSaveCurrentDate(projectName,programName,contractId,clientName,startDate);


    }
    @Then("It should throw an Error message")
    public void it_should_throw_an_error_message()
    {

        waitForVisibility(TMProjectDetailsPage.contractDateErrorMessage,10);
        Assert.assertTrue(TMProjectDetailsPage.contractDateErrorMessage.isDisplayed());
    }

}
