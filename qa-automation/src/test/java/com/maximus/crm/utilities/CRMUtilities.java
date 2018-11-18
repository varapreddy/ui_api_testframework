package com.maximus.crm.utilities;
import com.maximus.crm.pages.crm.CRMContactRecordUIPage;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class CRMUtilities extends BrowserUtils{
    public static final String UINAMESURL = "https://uinames.com/api/?ext";

    // TODO RE: Sujoy - Consider cutting off external application/files dependency
	ExcelReader excelReader2 =new ExcelReader(ConfigurationReader.getProperty("excelLocation"));

    private String firstName;
    private String lastName;
    private String ssn;
    private String dateOfBirth;
    private String phoneNumber;
    private String gender;
    private String email;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String county;
    private String zipCode;

    CRMContactRecordUIPage crmContactRecordUIPage = new CRMContactRecordUIPage();


    /*
      Author:Shilpa P
    * This method is used to get the exact Sheet Value and the cell value
    *
    * */


    public String getCellValueBySheetRowAndColoumn(String sheetName,String rowName,String coloumnName){
        Map<String,String> recordByRowName= excelReader2.getExcelData(sheetName,rowName);
        String cellValue= recordByRowName.get(coloumnName);
        return cellValue;
    }


    /*
      Author:Shilpa P
    * This method is used to click the Hamburger Menu and
    * select the options from the drop down
    * */

    public void navigateToHamBurgerMenu(String linkName)
    {
        crmContactRecordUIPage.hamBurgerMenu.click();
        highLightElement(crmContactRecordUIPage.hamBurgerMenu);
        switch (linkName.toUpperCase()){
            case "CREATEATASK":
                highLightElement(crmContactRecordUIPage.createATask);
                crmContactRecordUIPage.createATask.click();
                break;

            case "MOREOPTION":
                highLightElement(crmContactRecordUIPage.moreOption);
                crmContactRecordUIPage.moreOption.click();

                default:
        }


    }

    /*
    * Author:Shilpa p
    * This method is used to click on the contact drop down  from the
    * contact Reason
    * and Select the contact reason
    *
    * */

    public void  contactReason(String contactReason)
    {
        switch(contactReason){
            case "Information Request":
                highLightElement(crmContactRecordUIPage.getElementsContactReason(2));
                crmContactRecordUIPage.getElementsContactReason(2).click();
                break;
            case "Materials Request":
                highLightElement(crmContactRecordUIPage.getElementsContactReason(3));
                crmContactRecordUIPage.getElementsContactReason(3).click();
                break;
            case "Missing Information Request":
                highLightElement(crmContactRecordUIPage.getElementsContactReason(4));
                crmContactRecordUIPage.getElementsContactReason(4).click();
                 break;

            case "Other":
                highLightElement(crmContactRecordUIPage.getElementsContactReason(5));
                crmContactRecordUIPage.getElementsContactReason(5).click();
                break;

            case "Update Information Request":
                highLightElement(crmContactRecordUIPage.getElementsContactReason(6));

            default:
        }

    }

    /*
     * Author:Shilpa p
     * This method is used to click the contact Action  from the
     * contact Action drop down
     * and Select the contact Action
     *
     * */



    public void  contactAction(String contactAction)
    {
        switch(contactAction){
            case "Requested and Updated Authorized Representative Information":
                waitForVisibility(crmContactRecordUIPage.getElementsContactAction(3),2);
                highLightElement(crmContactRecordUIPage.getElementsContactAction(3));
                crmContactRecordUIPage.getElementsContactAction(3).click();
                break;
            case "Requested and Updated Case Member Information":
                highLightElement(crmContactRecordUIPage.getElementsContactAction(4));
                crmContactRecordUIPage.getElementsContactAction(4).click();
                break;
            case "Requested and Updated Demographic Information":
                highLightElement(crmContactRecordUIPage.getElementsContactAction(5));
                crmContactRecordUIPage.getElementsContactAction(5).click();
                break;


        }

    }

    /*
    * Author:Shilpa P
    * This method is used to create the Reasons and Comments
    * */

//TODO Shilpa this method have to be REFACTORED ASAP
    public boolean createReasonsAndComments(String comments,String contactsReasons,String contactAction){
        //crmContactRecordUIPage.expendReasonButton.click();
       // hover(crmContactRecordUIPage.expendReasonButton);
        staticWait(5000);
        waitForVisibility(crmContactRecordUIPage.contactReason,2);
        crmContactRecordUIPage.contactReason.click();
        hover(crmContactRecordUIPage.contactReason);
        contactReason(contactsReasons);
        System.out.print("Contacts Selected ");
        waitForVisibility(crmContactRecordUIPage.contactAction,10);
        System.out.print(" before Clicked");
        staticWait(100);
        crmContactRecordUIPage.contactAction.click();
        System.out.print("After Clicked");
        hover(crmContactRecordUIPage.contactAction);
        contactAction(contactAction);
        waitForVisibility(crmContactRecordUIPage.reasonsComments,2);
        clearAndFillText(crmContactRecordUIPage.reasonsComments,comments);
        crmContactRecordUIPage.saveReasonButton.click();
        return true;
    }

}
