package com.maximus.crm.pages.crm;

import com.maximus.crm.utilities.BrowserUtils;
import com.maximus.crm.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CRMConsumerSearchResultPage {

    private WebDriver driver;
    private BrowserUtils browserUtils = new BrowserUtils();
    public CRMConsumerSearchResultPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindAll({
            @FindBy(xpath = "//*[contains(@class ,'mx-table table mt-4 mb-0 mdl-color--grey-50')]//tr[*]//td[7]")
    })
    public List<WebElement> SSNs;

    @FindAll({
            @FindBy(xpath = "//*[contains(@class ,'mx-table table mt-4 mb-0 mdl-color--grey-50')]//tr[*]//td[2]")
    })
    public List<WebElement> CaseIDs;

    @FindAll({
            @FindBy(xpath = "//*[contains(@class ,'mx-table table mt-4 mb-0 mdl-color--grey-50')]//tr[*]//td[3]")
    })
    public List<WebElement> ConsumerIDs;

    @FindAll({
            @FindBy(xpath = "//*[contains(@class ,'mx-table table mt-4 mb-0 mdl-color--grey-50')]//tr[*]//td[4]")
    })
    public List<WebElement> FirstNames;

    @FindAll({
            @FindBy(xpath = "//*[contains(@class ,'mx-table table mt-4 mb-0 mdl-color--grey-50')]//tr[*]//td[5]")
    })
    public List<WebElement> LastNames;

    @FindAll({
            @FindBy(xpath = "//*[contains(@class ,'mx-table table mt-4 mb-0 mdl-color--grey-50')]//tr[*]//td[6]")
    })
    public List<WebElement> DOBs;

    @FindAll({
            @FindBy(xpath = "//*[contains(@class ,'mx-table table mt-4 mb-0 mdl-color--grey-50')]//tr[*]//td[8]")
    })
    public List<WebElement> PhoneNums;

    @FindAll({
            @FindBy(xpath = "//*[contains(@class ,'mx-table table mt-4 mb-0 mdl-color--grey-50')]//tr[*]//td[9]")
    })
    public List<WebElement> Emails;

    @FindAll({
            @FindBy(xpath = "//*[contains(@class ,'mx-table table mt-4 mb-0 mdl-color--grey-50')]//tr[*]//td[10]")
    })
    public List<WebElement> LinkButtons;

    /*refactoring 11/15/18*/
    @FindBy(xpath="(//span[contains(text(), 'visibility')])[3]")
    public WebElement ssnEyeUnMaskButton;

    @FindBy (xpath ="col-12 my-4")
    public WebElement searchResultTable;

    public String EyeUnMaskButton ="//span[contains(text(), 'visibility')]";

    /*refactoring 11/15/18*/
   @FindBy(xpath="(//span[contains(text(), 'visibility_off')])[2]")
    public WebElement ssnEyeMaskButton;


}
