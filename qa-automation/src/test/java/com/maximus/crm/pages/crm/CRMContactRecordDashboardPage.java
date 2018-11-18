package com.maximus.crm.pages.crm;

import com.maximus.crm.utilities.BrowserUtils;
import com.maximus.crm.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CRMContactRecordDashboardPage {


    private WebDriver driver;
    private BrowserUtils browserUtils = new BrowserUtils();

    public CRMContactRecordDashboardPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath ="(//*[@class='nav-item'])[1]")
    public WebElement activeContactTab;


    @FindBy (xpath ="(//*[@class='nav-item'])[2]")
    public WebElement demographicInfoTab;


    @FindBy (xpath ="(//*[@class='nav-item'])[3]")
    public WebElement caseContactDetailsTab;


    @FindBy (xpath ="(//*[@class='nav-item'])[4]")
    public WebElement taskServiceRequestTab;


    @FindBy (xpath ="(//*[@class='nav-item'])[5]")
    public WebElement programBenefitInfoTab;

    @FindBy (xpath ="//*[@class='nav-link active']")
    public WebElement activeTab;



}
