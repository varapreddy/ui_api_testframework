package com.maximus.crm.pages.tm;

import com.maximus.crm.utilities.BrowserUtils;
import com.maximus.crm.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class TMViewProjectPage {

    private WebDriver driver;

    public TMViewProjectPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id='root']//h4")
    public WebElement viewProjectDashboard;

    @FindBy(name = "projectName")
    public WebElement editProjectName;

    @FindBy(xpath = "//input[@id='state']/../div")
    public WebElement editState;

    @FindBy(name = "programName")
    public WebElement editProgramName;

    @FindBy(name = "contractId")
    public WebElement editContractId;

    @FindBy(name = "stateAgencyName")
    public WebElement editStateAgencyName;

    @FindBy(xpath = "//*[contains(text(), 'Contract Start Date')]/..//input")
    public WebElement editStartDate;

    @FindBy(xpath = "//*[contains(text(), 'Contract End Date')]/..//input")
    public WebElement editEndDate;

    @FindBy(xpath = "//input[@name='provStatus']/..")
    public WebElement editProvStatus;

    public static String provStatusValueXpath = "//li[@class='jss95 jss542 jss545 jss549 jss550 jss540']";
    public String stateNameValueXpath = "//li[@class='jss95 jss560 jss563 jss567 jss568 jss558'])";

    @FindBy(xpath = "//span[contains(text(), 'Save')]")
    public WebElement saveButton;

    @FindBy(xpath = "//div[@class='float-right pr-5']")
    public WebElement backToProjectsButton;

    @FindAll(
            @FindBy(css = ".mx-table tbody tr")
    )
    public List<WebElement> projectContactRows;

    public final String contactRole = "td:nth-of-type(1)";
    public final String contactFirstName = "td:nth-of-type(2) input";
    public final String contactMiddleName = "td:nth-of-type(3) input";
    public final String contactLastName = "td:nth-of-type(4) input";

    public final String contactPhoneNumber = "formatted-ssn-input"; //"td:nth-of-type(5) input";
    public final String contactEmail = "td:nth-of-type(6) input";
    public final String contactEditButton = "td:nth-of-type(7) button:nth-of-type(1)";
    public final String contactCancelButton = "td:nth-of-type(7) button:nth-of-type(2)";


    public void clickSaveButton() {
        BrowserUtils.waitFor(1);
        saveButton.click();
    }

    public void clickBackButton() {
        BrowserUtils.waitFor(1);
        backToProjectsButton.click();
    }

    public String getCurrentProjectName() {
        return editProjectName.getAttribute("value");
    }

    @FindBy (xpath = "//*[@name='contactRole']/..")
    public WebElement projectRoleDropdown;

    @FindBy (xpath = "//*[@name='firstName']")
    public WebElement createRoleFirstName;

    @FindBy (xpath = "//*[@name='lastName']")
    public WebElement createRoleLastName;

    @FindBy (xpath = "//*[@name='fieldValue']")
    public WebElement createRolePhone;

    @FindBy (xpath = "//*[@name='email']")
    public WebElement createRoleEmail;

    @FindBy (xpath = "(//*[contains(text(), 'check')])[2]")
    public WebElement createRoleSaveButton;
}
