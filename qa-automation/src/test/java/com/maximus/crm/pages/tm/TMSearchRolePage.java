package com.maximus.crm.pages.tm;

import com.maximus.crm.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TMSearchRolePage {
    private WebDriver driver;

    public TMSearchRolePage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()=' add']")
    public WebElement addRoleButton;
}
