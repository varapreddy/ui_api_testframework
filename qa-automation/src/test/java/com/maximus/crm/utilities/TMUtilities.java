package com.maximus.crm.utilities;

import com.maximus.crm.pages.LoginPage;
import com.maximus.crm.pages.tm.TMProjectDetailsPage;
import com.maximus.crm.pages.tm.TMViewProjectPage;
import com.maximus.crm.pages.tm.TMListOfProjectsPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TMUtilities extends BrowserUtils{

   
    LoginPage loginPage = new LoginPage();
    TMListOfProjectsPage tmListOfProjectsPage = new TMListOfProjectsPage();
    TMProjectDetailsPage TMProjectDetailsPage = new TMProjectDetailsPage();
    Logger logger = Logger.getLogger(BrowserUtils.class);
   // String excelLocation="C:\\crm-qa-automation-framework\\src\\test\\java\\com\\maximus\\crm\\utilities\\TestData.xlsx";

    ExcelReader excelReader2 =new ExcelReader(ConfigurationReader.getProperty("excelLocation"));

    /*
     *  Author: Shilpa P
     *
     * This method is used to clear the text and pass  the username and password
     * and click on the submit button
     * */

    public void login(String userName, String password) {
        clearAndFillText(loginPage.userName, userName);
        clearAndFillText(loginPage.password, password);
        loginPage.submitButton.click();
    }

    /*
    * Author : Shilpa P
    * This method is used to create and save the project
    *
    * */

    public void createAndSave(String projectName, String programName, String contractId, String clientName) {
        clearAndFillText(TMProjectDetailsPage.projectName, projectName);
        TMProjectDetailsPage.state.click();
        hover(TMProjectDetailsPage.state);
        staticWait(1600);
        TMProjectDetailsPage.AR.click();
        //waitForClickablility(TMProjectDetailsPage.programName,2);
        System.out.print("Element clicked ");
        clearAndFillText(TMProjectDetailsPage.programName, programName);
        clearAndFillText(TMProjectDetailsPage.contractId, contractId);
        clearAndFillText(TMProjectDetailsPage.stateAgencyName, clientName);
        TMProjectDetailsPage.provisioningStatus.click();
        hover(TMProjectDetailsPage.provisioningStatus);
        TMProjectDetailsPage.activeStatus.click();
        System.out.print("Clicked");
        highLightElement(TMProjectDetailsPage.saveButton);
        hover(TMProjectDetailsPage.saveButton);
        staticWait(7000);
        TMProjectDetailsPage.saveButton.click();
        //highLightElement(TMProjectDetailsPage.saveButton);
    }

   /*
   * Author: Shilpa P
   * This method is used to create a project with adding a contract  current
   * date and saving the project details
   *
   * */

    public void addContractDateAndSaveCurrentDate(String projectName, String programName, String contractId, String clientName, String startDate) {
        clearAndFillText(TMProjectDetailsPage.projectName, projectName);
        TMProjectDetailsPage.state.click();
        hover(TMProjectDetailsPage.state);
        TMProjectDetailsPage.AR.click();
        clearAndFillText(TMProjectDetailsPage.programName, programName);
        clearAndFillText(TMProjectDetailsPage.contractId, contractId);
        clearAndFillText(TMProjectDetailsPage.stateAgencyName, clientName);
        clearAndFillText(TMProjectDetailsPage.contractStartDate, startDate);
        clearAndFillText(TMProjectDetailsPage.contractEndDate, startDate);
        TMProjectDetailsPage.provisioningStatus.click();
        hover(TMProjectDetailsPage.provisioningStatus);
        TMProjectDetailsPage.activeStatus.click();
        System.out.print("Clicked");
        //staticWait(5000);
        hover(TMProjectDetailsPage.saveButton);
        highLightElement(TMProjectDetailsPage.saveButton);
        TMProjectDetailsPage.saveButton.click();
        highLightElement(TMProjectDetailsPage.saveButton);
    }
    /*
     * Author: Shilpa P
     * This method is used to create a project with adding a contract  current
     * date  and add the future date and saving the project details
     *
     * */
    public void addContractDateAndSaveCurrentDateAndEndDate(String projectName, String programName, String contractId, String clientName, String startDate, String endDate) {
        clearAndFillText(TMProjectDetailsPage.projectName, projectName);
        TMProjectDetailsPage.state.click();
       // hover(TMProjectDetailsPage.state);
        TMProjectDetailsPage.AR.click();
        clearAndFillText(TMProjectDetailsPage.programName, programName);
        clearAndFillText(TMProjectDetailsPage.contractId, contractId);
        clearAndFillText(TMProjectDetailsPage.stateAgencyName, clientName);
        clearAndFillText(TMProjectDetailsPage.contractStartDate, startDate);
        clearAndFillText(TMProjectDetailsPage.contractEndDate, endDate);
        TMProjectDetailsPage.provisioningStatus.click();
        hover(TMProjectDetailsPage.provisioningStatus);
        TMProjectDetailsPage.activeStatus.click();
        System.out.print("Clicked");
        //staticWait(5000);
        hover(TMProjectDetailsPage.saveButton);
        highLightElement(TMProjectDetailsPage.saveButton);
        TMProjectDetailsPage.saveButton.click();
       // highLightElement(TMProjectDetailsPage.saveButton);
    }

    /*
    * Author:Shilpa P
    * This method is used to search for the project Name
    * and Program Name
    * */

    public void search(String projectName,String programName){
        //clearAndFillText(tenantManagerListOfProjectsPage.state,state);
        clearAndFillText(tmListOfProjectsPage.project,projectName);
        clearAndFillText(tmListOfProjectsPage.programName,programName);
        staticWait(1000);
        hover(tmListOfProjectsPage.search);
        tmListOfProjectsPage.search.click();
        staticWait(100);

    }
    /*
     * Author:Shilpa P
     * This method is used to select for the specific  project Name
     * */

    public void selectSearchResults(){
        highLightElement(tmListOfProjectsPage.elementSearchResults);
        //tenantManagerListOfProjectsPage.elementSearchResults.click();
        tmListOfProjectsPage.arrowClick.click();
    }

    /*
    * Author:Shilpa P
    * This method is used to get all the state names displayed in the state Name drop down
    * */

    public void getStateNames(){
        TMProjectDetailsPage.state.click();
        hover(TMProjectDetailsPage.state);
        List<WebElement> getStates= TMProjectDetailsPage.stateList;
        getStates.size();
        System.out.print("size is " + getStates.size());
        for(int i=1;i<getStates.size();i++){
            System.out.println(getStates.get(i).getText());
        }
        TMProjectDetailsPage.AR.click();

    }

    /*
     * Author:Shilpa P
     * This method is used to create the project contact details and save
     * */


    public void createProjectContactAndSave(String firstName,String middleName,String lastName,String phoneNumber,String email){
        highLightElement(TMProjectDetailsPage.role);
        hover(TMProjectDetailsPage.role);
        TMProjectDetailsPage.accountApprover.click();
        clearAndFillText(TMProjectDetailsPage.firstName,firstName);
        clearAndFillText(TMProjectDetailsPage.middleName,middleName);
        clearAndFillText(TMProjectDetailsPage.lastName,lastName);
        clearAndFillText(TMProjectDetailsPage.phoneNumber,phoneNumber);
        clearAndFillText(TMProjectDetailsPage.email,email);
        TMProjectDetailsPage.projectContactSave.click();

    }
    /*
     *  This method is used to get the cell value by sheet Name Row Name and coloumn Name
     * */

    public String getCellValueBySheetRowAndColoumn(String sheetName,String rowName,String coloumnName){
        Map<String,String> recordByRowName= excelReader2.getExcelData(sheetName,rowName);
        String cellValue= recordByRowName.get(coloumnName);
        return cellValue;
    }


    public static void swichMetodForEditProjectPage(String field, String value) {
        /* update project info on IU..... Method accepts data from feature file.
         */
        TMViewProjectPage tmViewProjectPage = new TMViewProjectPage();
        switch (field) {
            case "project_name":
                clearAndFillText(tmViewProjectPage.editProjectName, value);
                break;
            case "program_name":
                clearAndFillText(tmViewProjectPage.editProgramName, value);
                break;
            case "contract_id":
                clearAndFillText(tmViewProjectPage.editContractId, value);
                break;
            case "client_agency":
                clearAndFillText(tmViewProjectPage.editStateAgencyName, value);
                break;
            case "start_date":
                clearAndFillText(tmViewProjectPage.editStartDate, value);
                break;
            case "end_date":
                clearAndFillText(tmViewProjectPage.editEndDate, value);
                break;
            case "state":
                selectDropDown(tmViewProjectPage.editState, value);
                break;
            case "pro_status":
                selectDropDown(tmViewProjectPage.editProvStatus, value);
                break;
        }
    }

    public static List<String> getContactInfo(WebElement element) {
        TMViewProjectPage tmViewProjectPage = new TMViewProjectPage();
        List<String> info = new ArrayList<>();
        info.add(element
                .findElement(By.cssSelector(tmViewProjectPage.contactRole)).getText());
        info.add(element
                .findElement(By.cssSelector(tmViewProjectPage.contactFirstName)).getAttribute("value"));
        info.add(element
                .findElement(By.cssSelector(tmViewProjectPage.contactMiddleName)).getAttribute("value"));
        info.add(element
                .findElement(By.cssSelector(tmViewProjectPage.contactLastName)).getAttribute("value"));
        info.add(element
                .findElement(By.id(tmViewProjectPage.contactPhoneNumber)).getAttribute("value"));
        info.add(element
                .findElement(By.cssSelector(tmViewProjectPage.contactEmail)).getAttribute("value"));
        return info;
    }

    public static void updateContactInfo(WebElement element, List<String> newInfo) {
        TMViewProjectPage tmViewProjectPage = new TMViewProjectPage();
        BrowserUtils.selectDropDown(element.findElement(By.cssSelector(tmViewProjectPage.contactRole)), newInfo.get(0));
        BrowserUtils.sendKeyToTextField(element.findElement(By.cssSelector(tmViewProjectPage.contactFirstName)), newInfo.get(1));
        BrowserUtils.sendKeyToTextField(element.findElement(By.cssSelector(tmViewProjectPage.contactMiddleName)), newInfo.get(2));
        BrowserUtils.sendKeyToTextField(element.findElement(By.cssSelector(tmViewProjectPage.contactLastName)), newInfo.get(3));
        BrowserUtils.sendKeyToTextField(element.findElement(By.id(tmViewProjectPage.contactPhoneNumber)), newInfo.get(4));
        BrowserUtils.sendKeyToTextField(element.findElement(By.cssSelector(tmViewProjectPage.contactEmail)), newInfo.get(5));
    }

}
