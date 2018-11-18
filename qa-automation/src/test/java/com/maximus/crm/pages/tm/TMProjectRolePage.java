package com.maximus.crm.pages.tm;

import com.maximus.crm.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TMProjectRolePage {

    private WebDriver driver;

    public TMProjectRolePage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }
    //Headers & Titles
    @FindBy(xpath = "//div[@class='mx-layout-top-drawer']/div/h4[contains(text(),'Add Role')]")
    public WebElement addRoleHeader;

    @FindBy(xpath = "//h5[contains(text(),'ROLE DETAILS')]")
    public WebElement roleDetailsPageTitle;

    //Role Details Locators
    @FindBy(name = "projectName")
    public WebElement projectName;

    @FindBy(name = "projectId")
    public WebElement projectId;

    @FindBy(name = "roleName")
    public WebElement roleName;

    @FindBy(name = "roleDesc")
    public WebElement roleDesc;

    @FindBy(xpath = "(//label[contains(text(),'START DATE')]/following-sibling::div/input)[1]")
    public WebElement roleStartDate;

    @FindBy(xpath = "(//label[contains(text(),'END DATE')]/following-sibling::div/input)[1]")
    public WebElement roleEndDate;

    //Permission Details Locators
    @FindBy(xpath = "//label[contains(text(),'Group permission name')]/following-sibling::div/input")
    public WebElement groupPermissionName;

    @FindBy(xpath = "//label[contains(text(),'Group Permission Description')]/following-sibling::div/input")
    public WebElement groupPermissionDescription;

    @FindBy(xpath = "(//label[contains(text(),'START DATE')]/following-sibling::div/input)[2]")
    public WebElement permissionStartDate;

    @FindBy(xpath = "(//label[contains(text(),'END DATE')]/following-sibling::div/input)[2]")
    public WebElement permissionEndDate;

    //Buttons
    @FindBy(xpath = "//span[text()=\" Save \"]/..")
    public WebElement saveButton;

    @FindBy(xpath = "//span[text()=\" Cancel \"]/..")
    public WebElement cancelButton;

    //Error messages
    @FindBy(xpath = "//p[text()='The Role name is required and cannot be left blank.']")
    public WebElement roleNameBlankError;

    @FindBy(xpath = "//p[text()='The Start date is required and cannot be left blank.']")
    public WebElement roleStartDateBlankError;

    @FindBy(xpath = "//*[contains(text(), 'End date should not be less or equal than start date')]")
    public WebElement lessOrEqualEndDateError;

    @FindBy(xpath = "//*[contains(text(), 'The Start Date cannot be in the past')]")
    public WebElement lessStartDateError;

    @FindBy(xpath = "(//*[contains(text(),'Invalid date format')])[1]")
    public WebElement invalidStartDateError;

    @FindBy(xpath = "(//*[contains(text(),'Invalid date format')])[2]")
    public WebElement invalidEndDateError;

    @FindBy(xpath = "(//*[contains(text(),'The date entered does not exist. Please enter a valid date.')])[1]")
    public WebElement nonExistingStartDate;

    @FindBy(xpath = "(//*[contains(text(),'The date entered does not exist. Please enter a valid date.')])[2]")
    public WebElement nonExistingEndDate;

    @FindBy(xpath = "//span[contains(text(),'Role with this name already exists for this project')]")
    public WebElement duplicateRoleError;

    //Pop-up messages
    @FindBy(xpath = "//p[text()='Project Role has been successfully created']")
    public WebElement roleCreatedPopUp;

    @FindBy(xpath = "//span[text()='Ok']/..")
    public WebElement okButton;

    @FindBy(xpath = "//*[contains(text(), 'All changes will be lost; Do you want to continue?')]")
    public WebElement cancelWarningPopUp;

    @FindBy(xpath = "//*[@class='mdl-button mdl-js-button mdl-js-ripple-effect mx-btn-border mx-btn-cancel']")
    public WebElement warningPopUpNoButton;

    @FindBy(xpath = "//*[@class='mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mx-btn-border mx-btn-primary']")
    public WebElement warningPopUpYesButton;
}