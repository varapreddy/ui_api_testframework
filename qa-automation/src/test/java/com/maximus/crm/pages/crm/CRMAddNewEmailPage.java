package com.maximus.crm.pages.crm;

import com.maximus.crm.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CRMAddNewEmailPage {

    private WebDriver driver;

    public CRMAddNewEmailPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath ="//p[.='Please enter an email id']")
    public WebElement emptyEmailAddressError;

    @FindBy(xpath ="//p[.='Please select aAssociated Case Member']")
    public WebElement emptyAssociatedCaseMemberError;

    @FindBy(xpath ="//p[.='Please select start date']")
    public WebElement emptyStartDateError;

    @FindBy(xpath ="//p[.='Please provide valid email']")
    public WebElement invalidEmailFieldError;

    @FindBy(xpath ="//p[.='The date entered does not exist. Please enter a valid date.']")
    public WebElement incorrectDateError;

    @FindBy(xpath ="//p[.='Invalid date format']")
    public WebElement invalidDateError;






}
