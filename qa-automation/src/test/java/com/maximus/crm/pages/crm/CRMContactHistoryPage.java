package com.maximus.crm.pages.crm;

import com.maximus.crm.utilities.BrowserUtils;
import com.maximus.crm.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CRMContactHistoryPage {

    private WebDriver driver;


    public CRMContactHistoryPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(@class ,'mdl-data-table__cell--non-numeric mx-table-desc')][1]")
    public WebElement contactIdColumn;

    //todo add contact date after bug fixed

     @FindBy(xpath="//*[contains(@class ,'mdl-data-table__cell--non-numeric')][2]")
     public WebElement dateColumn;

    @FindBy(xpath = "//*[contains(@class ,'mdl-data-table__cell--non-numeric')][3]")
// todo change to [3] after above bug fixed
    public WebElement consumerNameColumn;

    @FindBy(xpath = "//*[contains(@class ,'mdl-data-table__cell--non-numeric')][4]")
    public WebElement contactTypeColumn;

    @FindBy(xpath = "//*[contains(@class ,'mdl-data-table__cell--non-numeric')][5]")
    public WebElement channelColumn;

    @FindBy(xpath = "//*[contains(@class ,'mdl-data-table__cell--non-numeric')][6]")
    public WebElement reasonColumn;

    @FindBy(xpath = "//*[contains(@class ,'mdl-data-table__cell--non-numeric')][7]")
    public WebElement actionColumn;

    @FindAll({
            @FindBy(xpath = "//*[contains(@class ,'mx-table table mt-4 mb-0 mdl-color--grey-50')][1]//tr[*]//td[1]")
    })
    public List<WebElement> contactIDs;

// todo uncomment after bug fixed
 @FindAll({
            @FindBy(xpath = "//*[contains(@class ,'mx-table table mt-4 mb-0 mdl-color--grey-50')][1]//tr[*]//td[2]")
    })
    public List<WebElement> dates;

    @FindAll({
            @FindBy(xpath = "//*[contains(@class ,'mx-table table mt-4 mb-0 mdl-color--grey-50')][1]//tr[*]//td[3]")
    })
    public List<WebElement> consumers;

    @FindAll({
            @FindBy(xpath = "//*[contains(@class ,'mx-table table mt-4 mb-0 mdl-color--grey-50')][1]//tr[*]//td[4]")
    })
    public List<WebElement> contactTypes;

    @FindAll({
            @FindBy(xpath = "//*[contains(@class ,'mx-table table mt-4 mb-0 mdl-color--grey-50')][1]//tr[*]//td[5]")
    })
    public List<WebElement> channels;

    @FindAll({
            @FindBy(xpath = "//*[contains(@class ,'mx-table table mt-4 mb-0 mdl-color--grey-50')][1]//tr[*]//td[6]")
    })
    public List<WebElement> reasons;

    @FindAll({
            @FindBy(xpath = "//*[contains(@class ,'mx-table table mt-4 mb-0 mdl-color--grey-50')][1]//tr[*]//td[7]")
    })
    public List<WebElement> actions;

    @FindBy(xpath = "//*[@name='itemsPerPage']/..")
    public WebElement showPerPageDropdown;


    @FindBy(xpath = "//a[contains(text(),'»')]")
    public WebElement lastPaginationItem;

//new WebDriverWait(
//            driver, TIME_TO_WAIT).until(
//            ExpectedConditions.presenceOfElementLocated(
//            By.tagName("a")));
//    List<WebElement> elements = driver.findElements(By.tagName("a"));
//for (int i = 0; i < elements.size(); i++) {
//        String title = elements.get(i).getAttribute("title");
//        if (title.equals("Next Page")) {
//            elements.get(i).click();
//            break;
//        }


}


