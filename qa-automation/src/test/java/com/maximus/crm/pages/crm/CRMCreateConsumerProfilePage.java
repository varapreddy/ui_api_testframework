package com.maximus.crm.pages.crm;

import com.maximus.crm.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CRMCreateConsumerProfilePage {


    private WebDriver driver;

    public CRMCreateConsumerProfilePage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//*[contains(text(), 'ADD CONSUMER')]")
    public WebElement addConsumerButton;

    @FindBy(name = "consumerFN")
    public WebElement consumerFN;

    @FindBy(name = "consumerMI")
    public WebElement consumerMI;

    @FindBy(name = "consumerLN")
    public WebElement consumerLN;

    @FindBy(name = "consumerUniqueId")
    public WebElement consumerUniqueId;

    @FindBy(xpath = "(//*[@placeholder='MM/DD/YYYY'])[2]")
    public WebElement consumerDB;

    @FindBy(xpath = "(//*[@name='fieldValue'])[2]")
    public WebElement consumerSSN;

    /*refactoring 11/15/18*/
    @FindBy(xpath = "(//*[@name='fieldValue'])[4]")
    public WebElement consumerPhoneNum;
    /*refactoring 10/21/18*/
//    @FindBy(xpath = "(//*[@name='consumerEmail'])[2]")
    /*refactoring 10/24/18 back to the locator before 10/19/18 build*/
    @FindBy(xpath = "//*[@name='consumerEmail']")
    public WebElement consumerEmail;

    @FindBy(xpath = "//*[@name='consumerGender']/..")
    public WebElement consumerGender;

    @FindBy(xpath = "//*[@name='commOptIn']/../..")
    public WebElement commOptIn;

    @FindBy(xpath = "//input[@name='Phone']/..")
    public WebElement commOptPhone;

    @FindBy(xpath = "//input[@name='Text']/..")
    public WebElement commOptText;

    @FindBy(xpath = "//input[@name='Email']/..")
    public WebElement commOptEmail;

    @FindBy(xpath = "//input[@name='consumerAddressType']/..")
    public WebElement consumerAddressType;

    @FindBy(name = "consumerAddress1")
    public WebElement consumerAddress1;

    @FindBy(name = "consumerAddress2")
    public WebElement consumerAddress2;
    /**
     * refactoring 10/22/18
     */
    // @FindBy(xpath = "(//*[@name='consumerCity'])[2]")
    /*refactoring 10/24/18 back to the locator before 10/19/18 build*/
    @FindBy(xpath = "//*[@name='consumerCity']")
    public WebElement consumerCity;

    //    @FindBy(xpath = "(//*[@name='consumerState']/..)[2]")
    /*refactoring 10/24/18 back to the locator before 10/19/18 build*/
    @FindBy(xpath = "//*[@name='consumerState']/..")
    public WebElement consumerState;
    //refactoring 10/22/18
//    @FindBy(xpath = "(//*[@name='consumerCounty']/..)[2]")
    /*refactoring 10/24/18 back to the locator before 10/19/18 build*/
    @FindBy(xpath = "//*[@name='consumerCounty']/..")
    public WebElement consumerCounty;

    /*refactoring 11/15/18*/
    //@FindBy(xpath = "(//*[@name='fieldValue'])[4]")
    @FindBy(id = "formatted-zip-input")
    public WebElement consumerZipCode;
    //refactored 10/17/18
    @FindBy(xpath = "(//button[contains(@class ,'mx-btn mx-btn-border mr-2 mx-color-text-primary')])[2]")
    public WebElement cancelConsumerButton;

    // @FindBy(css= "body > div:nth-child(8) > div > div.modal.fade.show > div > div > div.modal-footer > button:nth-child(2)")
    @FindBy(xpath = "(//button[contains(@class ,'mx-btn mx-btn-border mx-btn-primary mdl-shadow--2dp mr-2')])[2]")
    public WebElement createConsumerButton;

    @FindBy(xpath = "(//button[contains(@class ,'mx-btn mx-btn-border mx-btn-primary mdl-shadow--2dp mr-2')])[3]")
    public WebElement createCaseButton;

    @FindBy(xpath = "//span[contains(text(), 'Please fill in the required fields.')]")
    public WebElement enterValueMainWarning;

    @FindBy(xpath = "(//p[contains(text(), 'Fill out all Required Fields')])[1]")
    public WebElement fillOutErrorFN;

    @FindBy(xpath = "(//p[contains(text(), 'Fill out all Required Fields')])[2]")
    public WebElement fillOutErrorLN;

    @FindBy(xpath = "(//p[contains(text(), 'Fill out all Required Fields')])[3]")
    public WebElement fillOutErrorPhone;


    @FindBy(xpath = "//p[contains(text(), 'Please enter a valid Zip Code of either 5 or 9 digits')]")
    public WebElement fillOutErrorZipCode;

    @FindBy(xpath = "//p[text()='Phone Number should be 10 characters']")
    public WebElement invalidPhoneError;

    @FindBy(xpath = "//p[text()='Please enter a valid Zip Code of either 5 or 9 digits']")
    public WebElement invalidZipCodeError;

    @FindBy(xpath = "//span[contains(text(),'Please fill in the required fields.')]")
    public WebElement fillOutErrorHeaderMandatory;

    @FindBy(xpath = "//span[contains(text(),'Please resolve the following:')]")
    public WebElement fillOutErrorHeaderOptional;

    @FindBy(xpath = "//p[contains(text(),'Invalid date format')]")
    public WebElement invalidDobError;

    @FindBy(xpath = "//p[contains(text(),'SSN should be 9 characers')]")
    public WebElement invalidSsnError;

    @FindBy(xpath = "//p[contains(text(),'Please provide valid email')]")
    public WebElement invalidEmailError;

    @FindBy(xpath = "//input[@name='contactReason']/following::p[contains(text(),'Fill out all Required Fields')]")
    public WebElement contactReasonError;

    @FindBy(xpath = "//input[@name='contactAction']/following::p[contains(text(),'Fill out all Required Fields')]")
    public WebElement contactActionError;

    @FindBy(xpath = "//textarea[@name='comments']/following::p[contains(text(),'Fill out the Required Field')]")
    public WebElement additionalCommentsError;

    @FindBy(xpath = "(//label[contains(text(), 'Consumer First Name')])[2]")
    public WebElement firstNameLabel;

    @FindBy(xpath = "(//label[contains(text(), 'Consumer Last Name')])[2]")
    public WebElement lastNameLabel;

    @FindBy(xpath = "//label[contains(text(), 'Phone Number')]")
    public WebElement phoneNumberLabel;
    //refactoring 10/22/18
//    @FindBy(xpath = "(//label[contains(text(), 'Zip Code')])[2]")
    /*refactoring 10/24/18 back to the locator before 10/19/18 build*/
    @FindBy(xpath = "//label[contains(text(), 'Zip Code')]")
    public WebElement zipCodeLabel;


    @FindBy(xpath = "//*[contains(text(),'Consumer already exists, please associate existing Consumer to Case')]")
    public WebElement duplicateConsumererrorMessage;

    @FindBy(xpath = "//h5[contains(text(), 'CREATE CONSUMER')]")
    public WebElement createConsumerHeader;

}

