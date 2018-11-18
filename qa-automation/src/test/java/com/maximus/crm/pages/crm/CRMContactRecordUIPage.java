package com.maximus.crm.pages.crm;

import com.maximus.crm.utilities.BrowserUtils;
import com.maximus.crm.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CRMContactRecordUIPage {


    private WebDriver driver;
    private BrowserUtils browserUtils = new BrowserUtils();

    public CRMContactRecordUIPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(), 'Case & Contact Details')]")
    public WebElement caseContactDetailsTab;

    @FindBy(xpath = "(//div[@class='jss143'])[2]")
    public WebElement clickWrapBoxElement;

    @FindBy(xpath = "//*[@id='formatted-text-mask-input']")
    public WebElement ssnSearch;

    @FindBy(xpath = "//*[@placeholder='MM/DD/YYYY']")
    public WebElement dobSearch;

    @FindBy(name = "consumerIdentificationNo")
    public WebElement uniqueIDSearch;

    @FindBy(name = "consumerMiddleName")
    public WebElement middleInitialSearch;


    @FindBy(xpath = "//*[@id='client-snackbar']/..")
    public WebElement enterSearchParametersWarning;

    @FindBy(xpath = "//*[contains(text(), 'sms')]")
    public WebElement initContact;

    @FindBy(xpath = "(//*[@class='m-0 text-right'])[1]")
    public WebElement headerDate;

    @FindBy(xpath = "(//*[@class='m-0 text-right'])[2]")
    public WebElement headerTime;

    public WebElement getHeaderDate(String date)
    {
        return driver.findElement(By.xpath("//*[contains(p,'"+date+"')]/*"));
    }


    @FindBy(xpath = "//p[.='ROLE']")
    public WebElement headerRole;

    @FindBy(xpath = "//p[.='INSTANCE']")
    public WebElement headerInstance;

    @FindBy(xpath = "//*[@class='text-right mx-header-username']")
    public WebElement headerUsername;

    @FindBy(xpath = "//*[@class='text-right mx-header-userid']")
    public WebElement headerID;

    @FindBy(xpath = "//*[@class='mx-top-drawer-recordicon float-left pt-2 px-3 mdl-color-text--green-400']")
    public WebElement contactInProgressGreenSign;

    @FindBy(xpath = "//*[@class='mx-top-drawer-recordicon float-left pt-2 px-3 mdl-color-text--grey-400']")
    public WebElement noContactInProgressGraySign;

    @FindBy(xpath = "//h5[contains(text(), 'CONTACT RECORD')]")
    public WebElement contactRecordSign;

    @FindBy(xpath = "//p[.='START']/..")
    public WebElement contactStart;

    @FindBy(xpath = "//p[.='DURATION']/..")
    public WebElement contactDuration;

    @FindBy(xpath = "//p['DURATION']/following-sibling::h6")
    public WebElement contactDurationValue;
//refactored 11/02/18
    @FindBy(xpath = "(//span[.='close'])[1]")
    public WebElement stopContact;

    // refactored 10-24-18   //FindBy(xpath = "(//span[@class='jss59'])[4]/..")
    @FindBy(xpath = "(//span[contains(text(),'search')])[2]")
    public WebElement searchButton;

//todo check 
    @FindBy(xpath = "(//*[@class='jss56 jss30 jss32 jss35 mx-btn mx-btn-border mr-2 mx-color-text-primary'])[1]")
    public WebElement resetButton;

    @FindBy(xpath = "//h5[contains(text(), 'No Records Available ')]")
    public WebElement noRecordsAvailableMessage;

    @FindBy(xpath = "//span[contains(text(), 'CANCEL')]/..")
    public WebElement cancelButton;

    @FindBy(xpath = "//button[contains(text(), ' Cancel ')]/.")
    public WebElement cancelWarningCancelButton;

    @FindBy(xpath = "//a[contains(text(), ' Continue ')]/.")
    public WebElement cancelWarningContinueButton;

    @FindBy(xpath = "//a[contains(@*,'mx-btn-primary')]") //text(), ' Close ')]")
    public WebElement closeButton;

    @FindBy(xpath = "//*[contains(text(), 'SEARCH RESULT')]")
    public WebElement searchResultSign;
	
	 @FindBy(xpath = "//*[@class='jss88 jss92 jss89']")
    public WebElement searchResultTable;

    //    @FindBy(xpath = "//*[contains(@class ,'mx-table table mt-4 mb-0 mdl-color--grey-50')]//tr[*]//td[10]")
    @FindBy(xpath = "//*[contains(text(), link)]//tr[*]//td[10]")
    public WebElement linkRecordButton;

    @FindBy(xpath = "//h6[contains(text(), 'LINKED')]")
    public WebElement linked;

    //todo
    @FindBy(xpath = "//*[.='link_off']/../..")
    public WebElement unLink;


    @FindBy(name = "consumerFirstName")
    public WebElement firstName;

    @FindBy(name = "consumerLastName")
    public WebElement lastName;

    @FindBy(name="consumerMiddleName")
    public WebElement middleName;

    @FindBy(xpath = "//input[@id='formatted-text-mask-input']")
    public WebElement ssnTextbox;

    @FindBy(xpath = "//label[contains(text(),'Date Of Birth')]//following::input[1]")
    public WebElement dobTextbox;

    @FindBy(name = "consumerIdentificationNo")
    public WebElement uniqueIDTextbox;

    @FindBy(xpath = "//*[@id='contact-reason']/..") //input[@name='contactReason']
    public WebElement contactReason;

    @FindBy(xpath = "//*[@id='contact-action']/..") //input[@name='contactReason']
    public WebElement contactAction;

    @FindBy(name = "otherReason")
    public WebElement otherReasonComments;

    @FindBy(name = "notes")
    public WebElement reasonsComments;

    //@FindBy(css = ".mx-comments-reason:nth-of-type(1)")
    @FindBy(xpath= "//*[contains(text(), 'REASONS')]/..")
    public WebElement expendReasonButton;

    @FindBy(xpath="//p[contains(text(), 'Comments')]")
    public WebElement savedCommentsText;

    @FindBy(xpath = "//p[contains(text(), 'Additional Comments')]")
    public WebElement savedAdditionalComments;

    @FindBy(xpath = "//h6[contains(text(), 'ADDITIONAL COMMENTS')]")
    public WebElement expendAdditionalCommentsButton;

    @FindBy(xpath="//*[@name='comments']")
    public WebElement additionalCommentsTextBox;

    @FindBy(xpath="(//*[@class='material-icons'])[4]")
    public WebElement saveAdditionalComments;


    public WebElement getElementsContactReason(int i) {
        return driver.findElement(By.xpath("//*[@id='menu-contactReason']/div[2]/ul/li[" + i + "]"));
    }


    public WebElement getElementsContactAction(int i) {
        return driver.findElement(By.xpath("//*[@id='menu-contactAction']/div[2]/ul/li[" + i + "]"));

    }

    public WebElement getElementsConsumerType(int i) {
        return driver.findElement(By.xpath("//*[@id='menu-consumerType']/div[2]/ul/li[" + i + "]"));
    }

    public WebElement getElementsContactType(int i) {
        return driver.findElement(By.xpath("//*[@id='menu-contactType']/div[2]/ul/li[" + i + "]"));
    }

    public WebElement getElementsInBoundCall(int i) {
        return driver.findElement(By.xpath("//*[@id='menu-callQueueType']/div[2]/ul/li[" + i + "]"));
    }

    public WebElement getElementContactChannelType(int i) {
        return driver.findElement(By.xpath("//*[@id='menu-contactChannelType']/div[2]/ul/li[" + i + "]"));
    }

    public WebElement getElementsPrefferedLangauge(int i) {
        return driver.findElement(By.xpath("//*[@id='menu-preferredLanguage']/div[2]/ul/li[" + i + "]"));
    }

    public WebElement getElementsContactDispostions(int i) {
        return driver.findElement(By.xpath("//*[@id='menu-contactDispositions']/div[2]/ul/li[" + i + "]"));
    }

    @FindBy(xpath = "(//*[@class='mx-section-header float-left'])[7]")
    public WebElement timeStamp;

    @FindBy(xpath = "//*[@class='mt-3 mb-0 mx-color-text-primary text-capitalize']")
    public WebElement reasonsDisplayed;

    @FindBy(xpath = "//p[contains(text(), 'If you continue, all the captured information will be lost')]")
    public WebElement informationLostMessage;

    @FindBy(xpath = "//a[contains(text(), 'Continue')]")
    public WebElement continueButtonReasons;

    @FindBy(xpath = "//*[contains(text(), 'Cancel')]")
    public WebElement cancelButtonReasons;

    @FindBy(xpath = "(//*[@class='jss131'])[7]")
    public WebElement expandSavedReason;

    @FindBy(xpath = "//p[contains(text(), 'Comments')]")
    public WebElement commentsDisplayedBottom;

    @FindBy(xpath = "(//*[@class='material-icons'])[3]")
    public WebElement saveReasonButton;

    @FindBy(xpath = "(//*[@class='material-icons jss75'])[11]")
    public WebElement cancelButtonResason;

    @FindBy(xpath = "//input[@name='consumerType']/..")
    public WebElement consumerType;

    @FindBy(xpath = "//input[@name='contactType']/..")
    public WebElement contactType;

    @FindBy(xpath = "//input[@name='callQueueType']/..")
    public WebElement callQueueType;

    @FindBy(xpath = "//input[@name='contactChannelType']/..")
    public WebElement contactChannelType;

    @FindBy(xpath = "//input[@name='preferredLanguage']/..")
    public WebElement preferredLanguage;

    @FindBy(name = "consumerAuthenticated")
    public WebElement consumerAuthenticate;

    @FindBy(xpath = "//input[@name='contactDispositions']/..")
    public WebElement contactDispositions;

    @FindBy(xpath = "//input[@name='callCampaignReference']/..")
    public WebElement callCampaignReference;

    @FindBy(xpath = "//input[@name='outcomeOfContact']/..")
    public WebElement outcomeOfContact;

    //Error message locators
    @FindBy(xpath = "//span[contains(text(), 'Please enter the search parameters')]")
    public WebElement blankSearchError;

    @FindBy(xpath = "//span[contains(text(),'Please resolve the following:')]")
    public WebElement headerError;

    @FindBy(xpath = "//label[contains(text(),'Social Security Number')]/following::p[contains(text(),'SSN should be 9 char')]")
    public WebElement ssnError;

    @FindBy(xpath = "//label[contains(text(),'Date Of Birth')]/following::p[contains(text(),'Invalid date format')]")
    public WebElement dobError;

    @FindBy(xpath = "//input[@name='contactReason']/following::p[contains(text(),'Fill out all Required Fields')]")
    public WebElement contactReasonError;

    @FindBy(xpath = "//input[@name='contactAction']/following::p[contains(text(),'Fill out all Required Fields')]")
    public WebElement contactActionError;

    @FindBy(xpath = "//textarea[@name='comments']/following::p[contains(text(),'Fill out the Required Field')]")
    public WebElement additionalCommentsError;

    //    @FindBy(xpath="//span[@class='jss29']//span[@class='material-icons jss71']")
    //    public WebElement hamBurgerMenu;
    @FindBy(xpath = "//span[contains(text(), 'menu')]")
    public WebElement hamBurgerMenu;

    @FindBy(xpath = "//span[contains(text(), 'CREATE TASK')]")
    public WebElement createATask;

    @FindBy(xpath = "//span[contains(text(), 'MORE OPTION')]")
    public WebElement moreOption;

    @FindBy(xpath = "//h6[contains(text(), 'CONSUMER ID')]")
    public WebElement consumerID;

    public WebElement getFirstNameLinkedContactRecord(String name) {
        return driver.findElement(By.xpath("//h6[contains(text(),'" + name + "')]"));
    }
//refactoring 10/25/18
    //    @FindBy(xpath="(//*[contains(@*, 'mx-color-text-primary text-capitalize')])[1]")
    @FindBy(xpath = "(//p[contains(@*, 'mx-color-text-primary')])[1]")
    public WebElement savedReason;

    @FindBy(xpath = "//p[@class='my-0']/..")
    public WebElement savedAction;

    @FindBy(xpath = "(//*[contains(@*, 'mx-color-text-primary text-capitalize')])[2]")
    public WebElement savedAddComments;

    @FindBy(xpath = "//*[contains(text(),'Phone')]")
    public WebElement phoneText;

    @FindBy(xpath = "//*[contains(text(),'Inbound')]")
    public WebElement InboundText;

}
