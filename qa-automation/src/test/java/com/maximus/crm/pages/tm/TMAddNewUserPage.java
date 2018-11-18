package com.maximus.crm.pages.tm;

import com.maximus.crm.utilities.Driver;
import com.maximus.crm.utilities.TMUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class TMAddNewUserPage extends TMUtilities {

    private WebDriver driver;

    public TMAddNewUserPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    //By Vinuta
    @FindBy(xpath = "//div[@class='mx-layout-top-drawer']/div/h4[contains(text(),'Add User')]")
    public WebElement addUserHeader;

    @FindBy(xpath = "//h5[contains(text(),'USER DETAILS')]")
    public WebElement userDetailsPageTitle;

    @FindBy(name = "projectName")
    public WebElement projectName;

    @FindBy(name = "firstName")
    public WebElement addFirstName;

    @FindBy(name = "middleName")
    public WebElement addMiddleName;

    @FindBy(name = "lastName")
    public WebElement addLastName;

    @FindBy(name="email")
    public WebElement addEmail;

    @FindBy(xpath = "//label[contains(text(),'START DATE')]/following-sibling::div/input")
    public WebElement startDate;

    @FindBy(xpath = "//label[contains(text(),'END DATE')]/following-sibling::div/input")
    public WebElement endDate;

    @FindBy(xpath = "//*[@name='accountType']/..")
    public WebElement accountType;

    @FindBy(xpath = "//*[@name='accountAuthType']/..")
    public WebElement accountAuthType;

    @FindBy(xpath = "//label[contains(text(),'AUTHORIZATION DATE')]/following-sibling::div/input")
    public WebElement authDate;

    @FindBy(xpath = "//span[text()=\" Save \"]/..")
    public WebElement saveButton;

    @FindBy(xpath = "//span[text()=\" Cancel \"]/..")
    public WebElement cancelButton;

    @FindBy(xpath = "//*[@name='overrideAuthReasonType']/..")
    public WebElement overrideAuthReasonType;

    @FindBy(xpath = "//*[@name='isOverrideAuth']/..")
    public WebElement overrideAuthCheckbox;

    @FindBy(xpath = "//*[@name='isAccessToPHI']/..")
    public WebElement accessToPHICheckbox;

    @FindBy(xpath = "//*[@name='PHIReasonType']/..")
    public WebElement phiReasonType;

    @FindBy(xpath = "//*[@name='isAccessToPII']/..")
    public WebElement accessToPIICheckbox;

    @FindBy(xpath = "//*[@name='PIIReasonType']/..")
    public WebElement piiReasonType;

    @FindBy(xpath = "//input[@name='isMaximusEmp']")
    public WebElement maximusEmpCheckbox;

    @FindBy(name = "maximusId")
    public WebElement maximusEmpIdTextbox;

    @FindBy(xpath = "//*[text()='add_circle']")
    public WebElement addMaxId;

    @FindBy(xpath = "//p[text()='User Created Successfully']")
    private WebElement userCreatedMessage;

    @FindBy(xpath = "//span[text()=\"Continue\"]/..")
    public WebElement continueBtnOnUserCreation;

    @FindBy(xpath ="//button/span[text()='Ok']" )
    public WebElement OkButton;

    @FindBy(xpath = "//input[@name='AccInactivationReasonType']/..")
    public WebElement accInactivationReason;

    @FindBy(xpath = "//input[@name='AccReactivationReasonType']/..")
    public WebElement accReactivationReason;

    @FindBy(xpath = "//p[text()=\"No employee record exists for a given MAX ID\"]")
    public WebElement noMaxIDFoundError;

    @FindBy(xpath = "//p[text()='Account Manager and Account Approvers must be identified']")
    private WebElement noApproversError;

    @FindBy(xpath = "//span[text()=\"Continue\"]/..")
    private WebElement continueBtnOnApproversError;

    @FindBy(xpath = "(//*[contains(text(), 'is required and cannot be left blank.')])[1]")
    public WebElement blankMaxIdError;

    @FindBy(xpath = "(//*[contains(text(), 'is required and cannot be left blank.')])[2]")
    public WebElement blankFirstNameError;

    @FindBy(xpath = "(//*[contains(text(), 'is required and cannot be left blank.')])[3]")
    public WebElement blankLastNameError;

    @FindBy(xpath = "(//*[contains(text(), 'is required and cannot be left blank.')])[4]")
    public WebElement blankEmailError;

    @FindBy(xpath = "(//*[contains(text(), 'is required and cannot be left blank.')])[5]")
    public WebElement blankStartDateError;

    @FindBy(xpath = "(//*[contains(text(), 'is required and cannot be left blank.')])[6]")
    public WebElement blankAccountTypeError;

    @FindBy(xpath = "//ul/li[contains(text(), 'application')]/preceding-sibling::*[1]")
    public WebElement blankAccountTypeOption;

    @FindBy(xpath = "//*[contains(text(), 'The Start Date cannot be in the past')]")
    public WebElement priorDateError;

    //refactoring 10-19-18
    @FindBy(xpath = "//*[contains(text(), 'The End Date must be greater than the Start Date')]")
    public WebElement lessOrEqualDateError;

    @FindBy(xpath = "//*[contains(text(), 'add_circle')]")
    public WebElement addMaxIdButton;

    @FindBy(xpath="//*[contains(text(),'INACTIVE IMMEDIATELY')]/..//input[@type='checkbox']")
    public WebElement inactiveImmediatelyCheckbox;

    @FindBy(xpath = "//*[contains(text(), 'All changes will be lost; Do you want to continue?')]")
    public WebElement cancelWarningPopUp;

    @FindBy(xpath = "//*[@class='mdl-button mdl-js-button mdl-js-ripple-effect mx-btn-border mx-btn-cancel']")
    public WebElement warningPopUpNoButton;

    @FindBy(xpath = "//*[@class='mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mx-btn-border mx-btn-primary']")
    public WebElement warningPopUpYesButton;

    @FindBy(xpath = "//p[text()='User you are trying to create already is an active user in the system.']")
    private WebElement duplicateUserError;

    @FindBy(xpath = "//p[text()='The user account will be deactivated when you click the Save button']")
    public WebElement inactivateUserMessage;

    @FindBy(xpath = "//p[text()='The user has been inactivated successfully']")
    public WebElement inactivateSuccessMessage;

    @FindBy(xpath = "//p[text()='The user has been reactivated successfully']")
    public WebElement reactivateSuccessMessage;

    @FindBy(xpath = "//p[text()='End date must be empty to reactivate a user']")
    public WebElement endDateReactivateError;

    public WebElement get_duplicateUserError(){return this.duplicateUserError;}

    public WebElement get_noApproversError() {
        return this.noApproversError;
    }

    public WebElement get_continueBtnOnApproversError() {
        return this.continueBtnOnApproversError;
    }

    public WebElement get_userCreatedMessage() {
        return this.userCreatedMessage;
    }

    public boolean isDisabledByDefault(String element) {
        if (driver.findElement(By.name(element)).getAttribute("value").equals("false")) {
            return true;
        }
        return false;
    }

    public String get_MaximusEmp_Status() {
        return maximusEmpCheckbox.getAttribute("value");
    }

    public void set_MaximusEmp_Status(String status) {
        String current_status = maximusEmpCheckbox.getAttribute("value");
        if (status.equalsIgnoreCase("True")) {
            if (current_status.equalsIgnoreCase("False"))
                maximusEmpCheckbox.click();
        }
        if (status.equalsIgnoreCase("False")) {
            if (current_status.equalsIgnoreCase("True"))
                maximusEmpCheckbox.click();
        }
    }

    public void set_MaximusEmp_Id(String maxID) {
        Assert.assertTrue(maximusEmpIdTextbox.isDisplayed(), "Max Id text box is not displayed");
        maximusEmpIdTextbox.clear();
        maximusEmpIdTextbox.sendKeys(maxID);
    }

    public void add_maxId() {

        Assert.assertTrue(addMaxId.isEnabled(), "Add Max ID button is not enabled");
        addMaxId.click();
    }

    public boolean get_maxIDError() {
        return noMaxIDFoundError.isDisplayed();
    }

    public WebElement get_addUserHeader() {
        return this.addUserHeader;
    }

    public WebElement get_userDetailsPageTitle() {
        return this.userDetailsPageTitle;
    }


    public boolean verifyReadOnlyElByValue(String value) {
        try {
            WebElement el = driver.findElement(By.cssSelector("[value='" + value + "']"));
            return el.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyNotEditable(WebElement el) {
        try {
            clearAndFillText(el, "editing");
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}
