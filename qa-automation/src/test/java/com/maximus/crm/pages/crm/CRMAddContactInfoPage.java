package com.maximus.crm.pages.crm;

import com.maximus.crm.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CRMAddContactInfoPage {

    private WebDriver driver;

    public CRMAddContactInfoPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(text(), 'keyboard_backspace')]")
    public WebElement backToContactInfo;

    @FindBy(xpath = "//*[@name='address1']")
    public WebElement addressLineOne;

    @FindBy(xpath = "//*[@name='address2']")
    public WebElement addressLineTwo;

    @FindBy(xpath = "//*[@name='city']")
    public WebElement city;

    @FindBy(xpath = "//*[@name='county']")
    public WebElement county;


    @FindBy(xpath = "//*[@name='addressState']//..")
    public WebElement state;

    @FindBy(xpath = "//*[@id='formatted-zip-input']")
    public WebElement zip;

    @FindBy(xpath = "//*[@name='addressType']//..")
    public WebElement addressType;

    @FindBy(xpath = "(//*[@placeholder='MM/DD/YYYY'])[1]")
    public WebElement startDate;

    @FindBy(xpath = "(//*[@placeholder='MM/DD/YYYY'])[2]")
    public WebElement endDate;

    @FindBy(xpath = "//*[contains(@class, 'mx-btn mx-btn-border mx-btn-primary mdl-shadow--2dp mr-2')]")
    public WebElement saveButton;

    @FindBy(xpath = "//*[contains(@class, 'mx-btn mx-btn-border mr-2 mx-color-text-primary')]")
    public WebElement cancelButton;

    @FindBy(xpath="//*[contains(h5,'EMAIL')]")
    public WebElement emailAddressLabel;

    @FindBy(xpath = "//*[@name='emailAddress']")
    public WebElement emailAddressField;

    //@FindBy(xpath = "//*[@name='associatedCaseMember']")
    @FindBy(xpath="//*[@class='jss161 jss162 jss173']")
    public WebElement associatedCaseMember;


    @FindBy(xpath = "//*[@id='formatted-phone-input']")
    public WebElement phoneNumber;

    @FindBy(xpath = "//*[@name='phoneType1']")
    public WebElement phoneTypeOne;

    @FindBy(xpath = "//*[@name='phoneType2']")
    public WebElement phoneTypeTwo;

    @FindBy(xpath = "//*[@name='phoneComments']")
    public WebElement phoneComments;

    @FindBy(xpath="//ul[@role='listbox']/li[last()]")
    public WebElement dropdownLastItem;

    @FindBy(xpath="(//table[starts-with(@class,'jss')])[3]/tr/td[2]")
    public WebElement emailActiveStatus;

    @FindBy(xpath="//th[text()='EMAIL ADDRESS']")
    public WebElement emailAddressHeader;

    @FindBy(xpath="(//th[contains(text(),'EMAIL ADDRESS')]/parent::tr/parent::thead/parent::table/tbody/tr)[2]/td[1]")
    public WebElement firstEmailID;

    @FindBy(xpath="(//th[contains(text(),'EMAIL ADDRESS')]/parent::tr/parent::thead/parent::table/tbody/tr)[2]/td[3]")
    public WebElement firstEmailIDStatus;

    @FindBy(xpath="//input[@value='inactive' and @type='checkbox']")
    public WebElement inactiveImmediatelyCheckbox;

    @FindBy(xpath="//span[text()='INACTIVE IMMEDIATELY']")
    public WebElement inactiveImmediatelyOption;

    @FindBy(xpath="(//ul[@class='pagination'])[3]/li/a")
    public WebElement pagesOnEmailSection;

}
