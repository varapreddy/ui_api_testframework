package com.maximus.crm.step_definitions;

import com.maximus.crm.pages.tm.TMListOfProjectsPage;
import com.maximus.crm.pages.tm.TMProjectDetailsPage;
import com.maximus.crm.pages.tm.TMProjectListPage;
import com.maximus.crm.pages.tm.TMViewProjectPage;
import com.maximus.crm.utilities.BrowserUtils;
import com.maximus.crm.utilities.Driver;
import com.maximus.crm.utilities.TMUtilities;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.*;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TM_EditProjectInformationStepDef extends TMUtilities{

    private WebDriver driver = Driver.getDriver();
    private TMProjectListPage tmProjectListPage = new TMProjectListPage();
    TMListOfProjectsPage tmListOfProjectsPage=new TMListOfProjectsPage();
    private TMProjectDetailsPage tmProjectDetailsPage = new TMProjectDetailsPage();
    TMViewProjectPage tmViewProjectPage = new TMViewProjectPage();
    //private JavascriptExecutor js = (JavascriptExecutor) driver;
    private List<String> currentProjectInfo = new ArrayList<String>();
    private List<String> returnProjectInfo = new ArrayList<String>();
    private static int currentProjIndex;
    private List<List<String>> allOriginalRowsOfContact = new ArrayList<>();
    Select dropdown;
    String currentProjectName;
    String currentProjectId;

    @When("I expend a Project to view the details")
    public void i_expend_a_Project_to_view_the_details() {

            // TODO Change Currents index to random after bug fixed
            currentProjIndex = 3; //BrowserUtils.radomNumber(tmProjectListPage.projects.size());
            //for (int i=0; i<tmProjectListPage.projects.size(); i++){
            hover(tmProjectListPage.projects.get(currentProjIndex));
            currentProjectInfo = tmProjectListPage.projectInfo(currentProjIndex);
            currentProjectId=tmListOfProjectsPage.projectId.get(currentProjIndex).getText();
            // Click on Expend Button on Project
            tmProjectListPage.expendProject(tmProjectListPage.projects.get(currentProjIndex));
            assertTrue(tmViewProjectPage.viewProjectDashboard.isDisplayed());
            waitFor(3);
            currentProjectName = tmViewProjectPage.getCurrentProjectName();
        }

    @When("I expand a random project to view the details")
    public void i_expand_a_random_project_to_view_the_details() {

        // TODO Change Currents index to random after bug fixed
        currentProjIndex=BrowserUtils.radomNumber(tmProjectListPage.projects.size());
        System.out.println(tmProjectListPage.projects.size());
        System.out.println(currentProjIndex);
        //for (int i=0; i<tmProjectListPage.projects.size(); i++){
        hover(tmProjectListPage.projects.get(currentProjIndex));
        currentProjectInfo = tmProjectListPage.projectInfo(currentProjIndex);
        // Click on Expend Button on Project
        tmProjectListPage.expendProject(tmProjectListPage.projects.get(currentProjIndex));
        assertTrue(tmViewProjectPage.viewProjectDashboard.isDisplayed());
        currentProjectName = tmViewProjectPage.getCurrentProjectName();
    }




    @When("I edit and save the project {string} with {string} one at the time")
    public void i_edit_and_save_the_project_with_one_at_the_time(String field, String value) {
        /* update project info on IU..... Method accepts data from feature file.
         */
       swichMetodForEditProjectPage(field, value);
        tmViewProjectPage.clickSaveButton();
    }

    @When("I confirm the project {string} {string}is updated")
    public void i_confirm_the_project_is_updated(String field, String value) {
        returnProjectInfo = tmProjectListPage.projectInfo(currentProjIndex);
        Assert.assertTrue(returnProjectInfo.contains(value), "Didn't update field " + field + " with value " + value);

    }

    @Then("I change it back to previous value for {string}")
    public void i_change_it_back_to_previous_value_for(String field) {
        waitFor(1);
        tmProjectListPage.expendProject(tmProjectListPage.projects.get(currentProjIndex));

        switch (field) {
            case "project_name":
                clearAndFillText(tmViewProjectPage.editProjectName, currentProjectInfo.get(0));
                break;
            case "program_name":
                clearAndFillText(tmViewProjectPage.editProgramName, currentProjectInfo.get(5));
                break;
            case "contract_id":
                clearAndFillText(tmViewProjectPage.editContractId, currentProjectInfo.get(2));
                break;
            case "client_agency":
                clearAndFillText(tmViewProjectPage.editStateAgencyName, currentProjectInfo.get(6));
                break;
            case "start_date":
                clearAndFillText(tmViewProjectPage.editStartDate, currentProjectInfo.get(7));
                break;
            case "end_date":
                clearAndFillText(tmViewProjectPage.editEndDate, currentProjectInfo.get(8));
                break;
            case "state":
                selectDropDown(tmViewProjectPage.editState, currentProjectInfo.get(1));
                break;
            case "pro_status":
                selectDropDown(tmViewProjectPage.editProvStatus, currentProjectInfo.get(4));
                break;
        }
        tmViewProjectPage.clickSaveButton();
        returnProjectInfo = tmProjectListPage.projectInfo(currentProjIndex);
        waitFor(2);
        
        Assert.assertTrue(currentProjectInfo.containsAll(returnProjectInfo), "Restore Project details back failed");


    }

    @Then("I should see the project details updated by example")
    public void i_should_see_the_project_details_updated_by_example(List<String> exampleInfo) {
        // Method should accept data from feature file.
        // Assertions from UI project and feature file project details for NEW Just entered data.
        tmViewProjectPage.saveButton.click();
        List<String> editedProject = tmProjectListPage.projectInfo(0);
        System.out.println(editedProject);
        System.out.println(exampleInfo);
        // Assert.assertTrue(editedProject.equals(exampleInfo), "not equals"); //exp how to compare

    }


    @Then("I edit all the project details at one step")
    public void i_edit_all_the_project_details_at_one_step() {

        // TODO replace project info from List "currentProjectInfo" to UI.
        // TODO add saved info to the List "returnProjectInfo"
        tmProjectListPage.expendProject(tmProjectListPage.projects.get(0));

        tmViewProjectPage.editProjectName.clear();
//        timer

        tmViewProjectPage.editProjectName.sendKeys(currentProjectInfo.get(0));
        /// .....

        tmViewProjectPage.saveButton.click();
//        returnProjectInfo = tmListOfProjectsPage.projectInfo(workingProjNumber);
//        System.out.println(returnProjectInfo);
//        Assert.assertTrue(currentProjectInfo.equals(returnProjectInfo), "not equals"); //exp how to compare
    }

    @When("I edit but don't save the project {string} with {string} one at the time")
    public void i_edit_but_don_t_save_the_project_with_one_at_the_time(String field, String value) {
        /* update project info on IU..... Method accepts data from feature file.
         */
       swichMetodForEditProjectPage(field, value);
        tmViewProjectPage.clickBackButton();

    }

    @When("I confirm the project {string} {string} is not updated")
    public void i_confirm_the_project_is_not_updated(String field, String value) {
        returnProjectInfo = tmProjectListPage.projectInfo(currentProjIndex);
        // TODO Add logic for Pending status, if project has same ProvStatus as Example
        assertFalse(returnProjectInfo.contains(value), field + " field was updated by value " + value);

    }

    @When("I edit each role with new project contact details and see updates")
    public void i_edit_each_role_with_new_project_contact_details_and_see_updates() {
        // TODO change to random dependency
        List<String> tempInfo = new ArrayList<>();
        tempInfo.add("Contact");
        tempInfo.add("Smith");
        tempInfo.add("D");
        tempInfo.add("Hunt");
        tempInfo.add("404-505-4545");
        tempInfo.add("test@gmail.com");

        //TODO add : if projectContactRows is 0 try again.
        // some projects do not have any contacts persons. and test just pass.

        for (int i = 1; i < tmViewProjectPage.projectContactRows.size(); i++) {

            WebElement currentRow = tmViewProjectPage.projectContactRows.get(i);

            List<String> currentOriginalRowInfo = TMUtilities.getContactInfo(currentRow);

            while (currentOriginalRowInfo.get(4).equals("")) {
                currentOriginalRowInfo = TMUtilities.getContactInfo(currentRow);
            }
            allOriginalRowsOfContact.add(currentOriginalRowInfo);

            currentRow.findElement(By.cssSelector(tmViewProjectPage.contactEditButton)).click();
            updateContactInfo(currentRow, tempInfo);
            currentRow.findElement(By.cssSelector(tmViewProjectPage.contactEditButton)).click();
            Assert.assertEquals(TMUtilities.getContactInfo(tmViewProjectPage.projectContactRows.get(i)), tempInfo,
                    "New Contact Information weren't save correctly");
        }
    }

    @Then("I change each role to previous value")
    public void i_change_each_role_to_previous_value() {
        for (int i = 1; i < tmViewProjectPage.projectContactRows.size(); i++) {

            WebElement currentRow = tmViewProjectPage.projectContactRows.get(i);
            currentRow.findElement(By.cssSelector(tmViewProjectPage.contactEditButton)).click();
            updateContactInfo(currentRow, allOriginalRowsOfContact.get(i - 1));
            currentRow.findElement(By.cssSelector(tmViewProjectPage.contactEditButton)).click();
            Assert.assertEquals(getContactInfo(tmViewProjectPage.projectContactRows.get(i)), allOriginalRowsOfContact.get(i - 1),
                    "Restore Contact Information weren't save correctly");
        }
    }
}
