package com.maximus.crm.pages.crm;

import com.maximus.crm.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CRMDemographicContactInfoPage {

    private WebDriver driver;

    public CRMDemographicContactInfoPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(text(), 'Demographic Info')]")
    public WebElement demographicInfoTab;

    @FindBy(xpath = "//*[contains(text(), 'CONTACT INFO')]")
    public WebElement contactInfoTab;

    @FindBy(xpath="//*[contains(h5,'ADDRESS')]")
    public WebElement addressLabel;

    @FindBy(xpath="//*[contains(h5,'EMAIL')]")
    public WebElement emailLabel;

    @FindBy(xpath = "(//*[@class='jss31'])[3]")
    public WebElement addAddressButton;

    @FindBy(xpath = "(//*[@class='jss31'])[4]")
    public WebElement addPhoneButton;

    @FindBy(xpath = "(//*[@class='jss31'])[5]")
    public WebElement addEmailButton;

    @FindAll({
            @FindBy(xpath = "(//*[contains(@class , 'mx-table table mt-4 mb-0 mdl-color--grey-50')])[1]//tr[*]//td[1]")
    })
    public List<WebElement> fullAddresses;

    @FindAll({
            @FindBy(xpath = "(//*[contains(@class , 'mx-table table mt-4 mb-0 mdl-color--grey-50')])[1]//tr[*]//td[2]")
    })
    public List<WebElement> addressCounties;

    @FindAll({
            @FindBy(xpath = "(//*[contains(@class , 'mx-table table mt-4 mb-0 mdl-color--grey-50')])[1]//tr[*]//td[3]")
    })
    public List<WebElement> addressTypes;

    @FindAll({
            @FindBy(xpath = "(//*[contains(@class , 'mx-table table mt-4 mb-0 mdl-color--grey-50')])[1]//tr[*]//td[4]")
    })
    public List<WebElement> addressSources;

    @FindAll({
            @FindBy(xpath = "(//*[contains(@class , 'mx-table table mt-4 mb-0 mdl-color--grey-50')])[1]//tr[*]//td[5]")
    })
    public List<WebElement> addressesStatuses;


    @FindAll({
            @FindBy(xpath = "(//*[contains(@class , 'mx-table table mt-4 mb-0 mdl-color--grey-50')])[2]//tr[*]//td[1]")
    })
    public List<WebElement> phoneNumbers;

    @FindAll({
            @FindBy(xpath = "(//*[contains(@class , 'mx-table table mt-4 mb-0 mdl-color--grey-50')])[2]//tr[*]//td[2]")
    })
    public List<WebElement> phoneTypesOne;

    @FindAll({
            @FindBy(xpath = "(//*[contains(@class , 'mx-table table mt-4 mb-0 mdl-color--grey-50')])[2]//tr[*]//td[3]")
    })
    public List<WebElement> phoneTypesTwo;

    @FindAll({
            @FindBy(xpath = "(//*[contains(@class , 'mx-table table mt-4 mb-0 mdl-color--grey-50')])[2]//tr[*]//td[4]")
    })
    public List<WebElement> phoneComments;

    @FindAll({
            @FindBy(xpath = "(//*[contains(@class , 'mx-table table mt-4 mb-0 mdl-color--grey-50')])[2]//tr[*]//td[5]")
    })
    public List<WebElement> phoneStatuses;


    @FindAll({
            @FindBy(xpath = "(//*[contains(@class , 'mx-table table mt-4 mb-0 mdl-color--grey-50')])[3]//tr[*]//td[1]")
    })
    public List<WebElement> emails;

    @FindAll({
            @FindBy(xpath = "(//*[contains(@class , 'mx-table table mt-4 mb-0 mdl-color--grey-50')])[3]//tr[*]//td[2]")
    })
    public List<WebElement> emailCaseMembers;

    @FindAll({
            @FindBy(xpath = "(//*[contains(@class , 'mx-table table mt-4 mb-0 mdl-color--grey-50')])[3]//tr[*]//td[3]")
    })
    public List<WebElement> emailStatuses;

    @FindBy(xpath="(//h6[@class='m-0  float-left'])[1]")
        public WebElement statusStartDate;

    @FindBy(xpath="(//h6[@class='m-0  float-left'])[2]")
    public WebElement statusEndDate;

    @FindBy(xpath = "//div[@id='contact_info']/div[3]/div[2]/div/div/table/thead/tr/th")
    public WebElement emailTable;
}
