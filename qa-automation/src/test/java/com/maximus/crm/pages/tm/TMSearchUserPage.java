package com.maximus.crm.pages.tm;

import com.maximus.crm.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TMSearchUserPage {

    private WebDriver driver;

    public TMSearchUserPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }
//todo: this element will be moved to TMViewProjectPage as first appears there
    @FindBy (xpath = "//*[@*='mdl-color-text--white mt-3 pl-2 float-left']")
    public WebElement projectName;

    //todo: this element will be moved to TMViewProjectPage as first appears there
    @FindBy (xpath = "//a[.='ballot']")
    public WebElement editProjectTab;

    //todo: this element will be moved to TMViewProjectPage as first appears there
    @FindBy (xpath = "//a[.='group']")
    public WebElement searchUserTab;

    @FindBy (name = "firstName")
    public WebElement searchFirstName;

    @FindBy (name = "lastName")
    public WebElement searchLastName;

    @FindBy (name = "maximusId")
    public WebElement searchMaxID;

    @FindBy (name = "email")
    public WebElement searchEmail;

   //todo please check for dropdown function
   // @FindBy (xpath="//*[@id='accountType']/..")
   // public WebElement searchAccountType;

    @FindBy (id="accountType")
    public WebElement searchAccountType;

    //todo please check for dropdown function
    // @FindBy (xpath="//*[@id='statusType']/..")
    // public WebElement searchStatusType;

    @FindBy(xpath = "//input[@id='statusType']/..") //(id="statusType")
    public WebElement searchStatusType;

    @FindBy (xpath = "//*[contains(text(), ' Search ')]")
    public WebElement searchButton;


    @FindBy (xpath = "//*[contains(text(), ' Clear ')]")
    public WebElement clearButton;

    @FindBy (xpath = "//*[contains(text(), 'delete')]")
    public WebElement discardButton;

   @FindBy (xpath = "//span[contains(text(), 'add')]/..")
    public WebElement addUserButton;

   @FindBy (xpath = "//span[contains(text(), 'keyboard_backspace')]/..")
    public WebElement backToProjectListButton;

   @FindBy (name = "itemsPerPage")
   public WebElement itemsPerPage;

   @FindBy (xpath = "//*[@class='modal-content']")
   public WebElement warningSign;

    @FindAll({
            @FindBy(xpath = "//*[contains(@class ,'table-responsive mt-0')]//tr[*]//td[1]")
    })
    public List<WebElement> resultCheckBoxes;

    @FindAll({
            @FindBy(xpath = "//*[contains(@class ,'table-responsive mt-0')]//tr[*]//td[2]")
    })
    public List<WebElement> resultFirstNames;

    @FindAll({
            @FindBy(xpath = "//*[contains(@class ,'table-responsive mt-4')]//tr[*]//td[3]")
    })
    public List<WebElement> resultLastNames;

    @FindAll({
            @FindBy(xpath = "//*[contains(@class ,'table-responsive mt-0')]//tr[*]//td[4]")
    })
    public List<WebElement> resultMaxIDs;

    @FindAll({
            @FindBy(xpath = "//*[contains(@class ,'table-responsive mt-4')]//tr[*]//td[5]")
    })
    public List<WebElement> resultEmails;

    @FindAll({
            @FindBy(xpath = "//*[contains(@class ,'table-responsive mt-4')]//tr[*]//td[6]")
    })
    public List<WebElement> resultStartDays;

    @FindAll({
            @FindBy(xpath = "//*[contains(@class ,'table-responsive mt-4')]//tr[*]//td[7]")
    })
    public List<WebElement> resultEndDays;

    @FindAll({
            @FindBy(xpath = "//*[contains(@class ,'table-responsive mt-0')]//tr[*]//td[8]")
    })
    public List<WebElement> resultStatuses;

    @FindBy(xpath = "//th[contains(@class,'mdl-data-table__cell--non-numeric ')]//input")
    public WebElement selectAllUserCheckbox;

    //By Vinuta
    //This method returns the checkbox against any column value that is unique on User List page
    public WebElement getCheckboxBy(String value){
        return  driver.findElement(By.xpath("//td[text()='"+value+"']/parent::tr//preceding-sibling::td//input[@type='checkbox']"));
    }

    //By Vinuta
    //This method returns true if checkbox is checked for Inactive user,false if checkbox is not checked for Active user
    public boolean getCheckboxValue(String value){
        String xpath="//td[text()='" +value+ "']/parent::tr//preceding-sibling::td//input[@checked]";
        WebElement checkboxValue = driver.findElement(By.xpath(xpath));
        if(checkboxValue.isSelected()) return true;
        else return false;
    }
}
