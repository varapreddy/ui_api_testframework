package com.maximus.crm.pages.crm;

import com.maximus.crm.utilities.BrowserUtils;
import com.maximus.crm.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CRMCaseContactDetailsPage {
    private WebDriver driver;
    private BrowserUtils browserUtils = new BrowserUtils();

    public CRMCaseContactDetailsPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//th[contains(text(),'CONTACT ID')]")
    public WebElement contactId;


}
