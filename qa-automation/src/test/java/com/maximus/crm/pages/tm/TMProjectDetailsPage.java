package com.maximus.crm.pages.tm;

import com.maximus.crm.utilities.Driver;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class TMProjectDetailsPage {

    private WebDriver driver;

    public TMProjectDetailsPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//h4[contains(text(),'Add Project')]")
    public WebElement addProjectLogo;

    @FindBy(name = "projectName")
    public WebElement projectName;

    @FindBy(xpath="//*[@id='root']/div/main/div/div/div/div[2]/div/div[4]/div/div/div/div")
    public WebElement state;

    @FindBy(xpath = "//*[@id='menu-state']/div[2]/ul/li")
    public List<WebElement> stateList;

    public WebElement stateName(int i){
       return  driver.findElement(By.xpath("//*[@id='menu-state']/div[2]/ul/li["+i +"])"));
    }
    @FindBy(xpath = "//*[@id='menu-state']/div[2]/ul/li[2]")
    public WebElement AR;

    @FindBy(name = "programName")
    public WebElement programName;

    @FindBy(name="contractId")
    public WebElement contractId;

    @FindBy(name="stateAgencyName")
    public WebElement stateAgencyName;

    @FindBy(xpath="//*[@id='root']/div/main/div/div/div/div[2]/div/div[8]/div/div/input")
    public WebElement contractStartDate;

    @FindBy(xpath = "//*[@id='root']/div/main/div/div/div/div[2]/div/div[9]/div/div/input")
    public WebElement contractEndDate;

    @FindBy(xpath="//div[contains(text(),'Pending')]")
    public WebElement  provisioningStatus;

    @FindBy(xpath ="//*[@id='menu-provStatus']/div[2]/ul/li[2]")
    public WebElement activeStatus;

    @FindBy(xpath="//*[@id='menu-provStatus']/div[2]/ul/li[3]")
    public WebElement inactiveStatus;

    @FindBy(xpath="//button[contains(@class, 'mx-btn-border mx-btn-primary')]/span[1]")
    public WebElement saveButton;

    @FindBy(xpath="//*[@id=\"root\"]/div/main/div/div/div/div[2]/div/div[15]/div/div/table/tbody/tr[1]/td[1]/div/div/div/div")
    public WebElement role;

    @FindBy(xpath = "//*[@id='menu-contactRole']/div[2]/ul/li[2]")
    public WebElement accountApprover;

    @FindBy(xpath="//*[@id='menu-contactRole']/div[2]/ul/li[3]")
    public WebElement accountManager;

    @FindBy(xpath="//*[@id='menu-contactRole']/div[2]/ul/li[4]")
    public WebElement contact;


    @FindBy(name = "firstName")
    public WebElement firstName;

    @FindBy(name="middleName")
    public  WebElement middleName;

    @FindBy(name="lastName")
    public WebElement lastName;

    @FindBy(xpath="//*[@id='root']/div/main/div/div/div/div[2]/div/div[15]/div/div/table/tbody/tr[1]/td[5]/div/div/div")
    public WebElement phoneNumber;

    @FindBy(name="email")
    public WebElement email;

    @FindBy(xpath="//*[@id='root']/div/main/div/div/div/div[2]/div/div[15]/div/div/table/tbody/tr[1]/td[7]/button")
    public WebElement projectContactSave;

    //ToDo change the xpath to dynamic
    @FindBy(xpath="//*[contains(p, 'An existing Project record already')]/*[last()]")
    public WebElement errorMessage;

    @FindBy(xpath="//*[@id='root']/div/main/div/div/div/div[2]/div/div[9]/div/p")
    public WebElement contractErrorMessage;

    //By Vinuta
    @FindBy(xpath = "//a[@href='/tenant/projectDetails/usersearch']")
    public WebElement viewUserList;

    @FindBy(xpath = "//a[@href='/tenant/projectDetails/role']")
    public WebElement viewRoleList;

    @FindBy(xpath = "//*[text()='PROJECT DETAILS']")
    public WebElement projectDetailsTitle;

 @FindBy(xpath="//*[text()='Contract end date must not be equal or lesser than start date ']")
    public WebElement contractDateErrorMessage;

}
