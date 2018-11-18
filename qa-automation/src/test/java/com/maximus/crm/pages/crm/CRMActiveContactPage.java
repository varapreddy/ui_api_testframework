package com.maximus.crm.pages.crm;

import com.maximus.crm.utilities.BrowserUtils;
import com.maximus.crm.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CRMActiveContactPage {

    private WebDriver driver;
      public CRMActiveContactPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }
    @FindBy ( xpath = "//h5[contains(text(), 'CONTACT RECORD')]")
    public WebElement contactRecordSign;

      @FindBy (xpath = "//h6[contains(text(), 'CASE ID')]")
    public WebElement caseID;
//refactoring 11/02/18
      @FindBy (xpath = "//p[contains(text(), 'CONSUMER ID')]")
    public WebElement consumerID;

    @FindBy (xpath = "//h6[contains(text(), 'LINKED CONTACT')]")
    public WebElement linkedContactSign;

    @FindBy (xpath = "//*[contains(text(), 'UNLINK CONTACT RECORD')]")
    public WebElement unlinkContactRecord;

    //refactoring 11-06-18
//    @FindBy (xpath = "(//h6[@class='m-0'])[1]")
    @FindBy (xpath = "//h6[@class='m-0 mx-text-capitalize']")
    public WebElement fullName;

    @FindBy (xpath = "(//h6[@class='m-0'])[1]")
    public WebElement ssn;

    @FindBy (xpath = "(//h6[@class='m-0'])[2]")
    public WebElement dob;

    @FindBy (xpath = "(//h6[@class='m-0'])[3]")
    public WebElement uniqueID;

 @FindBy (xpath ="(//h6)[1]")
    public WebElement headerConsumerName;

 @FindBy (xpath = "(//p)[6]")
    public WebElement headerConsumerID;
}
