package com.maximus.crm.pages.tm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.maximus.crm.utilities.Driver;

import java.util.List;

public class TMListOfProjectsPage {

    private WebDriver driver;

    public TMListOfProjectsPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    public final String searchDropDownList = "//*[@id='react-autowhatever-1']//li";
    //@FindBy(xpath="//i[contains(text(),'add')]")
    //TODo We need to change the xpath
    @FindBy(xpath = "//*[@id='root']/div/div/div/div/button")
    public WebElement createProjectButton;

    @FindBy(xpath = "//h4[contains(text(),'Project List')]")
    public WebElement projectList;

    @FindBy(xpath = "//label[starts-with(text(), 'State')]/..//input ")
    public WebElement searchByState;

    @FindBy(xpath = "//*[@id='root']/div/main/div/div/div/div[2]/div/div[2]/div/div/div[1]/div/input")
    public WebElement project;

    @FindBy(xpath = "//*[@id='root']/div/main/div/div/div/div[2]/div/div[3]/div/div/div[1]/div/input")
    public WebElement programName;

    @FindBy(xpath = "//label[starts-with(text(), 'Client/State Agency')]/..//input")
    public WebElement searchByClientStateAgency;

    //TODo change the xpath
    @FindBy(xpath = "//h4[contains(text(),'TEST')]")
    public WebElement elementSearchResults;

    @FindBy(xpath = "//i[contains(text(),'arrow_right_alt')]")
    public WebElement arrowClick;

    @FindBy(xpath = "//div[@class='mdl-color--grey-100 mx-border-t-6dp px-4 py-1 mx-position-r']")
    public WebElement projectCreatedList;
//todo Check why list of elements named as single?
    @FindAll({
            @FindBy(xpath = "//h6[@class='mdl-color-text--grey-700']")
    })
    public List<WebElement> projectId;       //List<WebElement> projects  = driver.findElements(By.cssSelector(".row.mx-0 > div"));

	//@FindBy(xpath = "//h6[@class='mdl-color-text--grey-700']")
    //public WebElement projectId;


    @FindBy(css = "#root > div > header > div > nav > div:nth-child(1) > p:nth-child(1)")
    public WebElement date;

    @FindBy(xpath = "//*[@id='root']/div/header/div/nav/div[1]/p[2]")
    public WebElement time;

    @FindBy(xpath = "//p[contains(text(),'CORPORATE HEADQUARTERS')]")
    public WebElement addressLine1;

    @FindBy(xpath = "//p[contains(text(),'1891 Metro Center Drive Reston, VA 20190')]")
    public WebElement getAddressLine2;

    @FindBy(xpath = "//*[@id='root']/div/header/div/nav/div[2]/p[1]")
    public WebElement role;

    @FindBy(xpath = "//h5[@class='text-right mx-header-username']")
    public WebElement userNameHeader;

    @FindBy(xpath = "//span[contains(text(), ' Search ')]")
    public WebElement search;

    @FindBy(xpath = "//span[contains(text(), ' Clear ')]")
    public WebElement clear;

    @FindAll({
            @FindBy(css = ".row.mx-0 > div")
    })
    public List<WebElement> projects;       //List<WebElement> projects  = driver.findElements(By.cssSelector(".row.mx-0 > div"));

    @FindBy(xpath = "//*[@id='root']//h4")
    public WebElement projectListDashboard;

    public static String expendButtonClass = "button"; //"jss72";
    public static String projectNameCSS = "h4";
    public static String projectStateClass = "pr-2";
    public static String contractIdClass = "pl-2";
    public static String projectIdCSS = "h6";
    public static String provisioningStatusCSS = "h6 span";
    public static String programNameCSS = "div p:nth-of-type(2)";
    public static String clientNameCSS = "div p:nth-of-type(4)";
    public static String startDateCSS = ".row .col-6:nth-of-type(1) p:nth-of-type(2)";
    public static String startDateFieldXpath = "(//div/p[@class='mdl-color-text--grey-500 mb-0'])[3]";
    public static String endDateCSS = ".row .col-6:nth-of-type(2) p:nth-of-type(2)";
    public static String endDateFieldXpath = "(//div/p[@class='mdl-color-text--grey-500 mb-0'])[4]";
}
