package com.maximus.crm.pages;

import com.maximus.crm.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    public LoginPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(name="loginName")
    public WebElement userName;

    @FindBy(name="passwordValue")
    public WebElement password;

    @FindBy(className="mdl-button__ripple-container")
    public WebElement submitButton;

    @FindBy(className = "class='jss62 jss63")
    public WebElement invalidUserNameError;


}
