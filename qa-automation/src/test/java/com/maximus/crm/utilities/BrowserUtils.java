package com.maximus.crm.utilities;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.maximus.crm.pages.LoginPage;
import com.maximus.crm.pages.tm.TMProjectDetailsPage;
import com.maximus.crm.pages.tm.TMListOfProjectsPage;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.base.Function;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
//refactoring 10/17/18
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class BrowserUtils {
    private static WebDriver driver;

    public BrowserUtils() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    LoginPage loginPage = new LoginPage();
    public TMListOfProjectsPage tmListOfProjectsPage = new TMListOfProjectsPage();
    TMProjectDetailsPage TMProjectDetailsPage = new TMProjectDetailsPage();
    Logger logger = Logger.getLogger(BrowserUtils.class);
    public static final String UINAMESURL = "https://uinames.com/api/?ext";

    public static void scrollDown() {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript("scroll(0, 250);");
    }

    public static void scrollUp() {
        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        jse.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
    }

    public static void scrollUpRobot() {

        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_PAGE_UP);
            robot.keyRelease(KeyEvent.VK_PAGE_UP);

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }


    //refactoring 10-19-18
    public static void scrollToElement(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();

        jse.executeScript("arguments[0].scrollIntoView()", element);
    }

    public static List<String> getElementsText(By locator) {

        List<WebElement> elems = Driver.getDriver().findElements(locator);
        List<java.lang.String> elemTexts = new ArrayList<>();
        for (WebElement el : elems) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }

        return elemTexts;
    }

    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickablility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForClickablility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement fluentWait(final WebElement webElement, int timeinsec) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getDriver())
                .withTimeout(timeinsec, TimeUnit.SECONDS).pollingEvery(timeinsec, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return webElement;
            }
        });
        return element;
    }

    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            System.out.println("Waiting for page to load...");
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println(
                    "Timeout waiting for Page Load Request to complete after " + timeOutInSeconds + " seconds");
        }
    }

    public static void waitFor(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void switchToWindow(String targetTitle) {
        String origin = Driver.getDriver().getWindowHandle();
        for (String handle : Driver.getDriver().getWindowHandles()) {
            Driver.getDriver().switchTo().window(handle);
            if (Driver.getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        Driver.getDriver().switchTo().window(origin);
    }

    public static void hover(WebElement element) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }

    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemText = new ArrayList<>();
        for (WebElement el : list) {
            if (!el.getText().isEmpty()) {
                elemText.add(el.getText());
            }

        }
        return elemText;
    }

    /*
     * This method is created by Shilpa
     * */
    public void highLightElement(WebElement element) {
        staticWait(100);
        logger.info("Started Executing the Java Script ");
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].setAttribute('style','background:yellow;border:2px solid red;');", element);
        try {
            Thread.sleep(1000);
            logger.info("Executed and HighLighted");
        } catch (InterruptedException e) {
            System.out.print(e.getMessage());
        }

    }

    public static void clearAndFillText(WebElement element, String text) {
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), (text));
//        element.clear();
//        staticWait(500);
//        element.sendKeys(text);
    }

    /*
    this is duplicate of line 102 <public static void wait(){}>/
     */
    public void staticWait(int timeInMilliSeconds) {
        try {
            Thread.sleep(timeInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String randomEmailId() {
        String n = UUID.randomUUID().toString().substring(30);
        String s = new SimpleDateFormat("HH:mm:ss.SSS").format(new Date()).toString().replaceAll(":", "").
                replaceAll("", "").replaceAll("-", "").replace(".", "")
                .substring(2);
        String email = "test" + n + s + "@gmail.com";
        return email;
    }

    public void login(String userName, String password) {
        clearAndFillText(loginPage.userName, userName);
        clearAndFillText(loginPage.password, password);
        loginPage.submitButton.click();

    }

    public static String getCurrentDateWithFormat() {
        Date currentDate = new Date();
        String DATE_FORMAT = "MMddyyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        String actualDate = sdf.format(currentDate);
        return actualDate;

    }

    public String getDateTime() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String actualdate = dateFormat.format(cal);
        return actualdate;
    }

    public static String getGreaterDate(int nDays) {
        LocalDate dateAfterNDays = LocalDate.now().plusDays(nDays);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String actualDate = dateAfterNDays.format(formatter);
        return actualDate;
    }

    public static String getPriorDate(int nDays) {
        LocalDate datePriorNDays = LocalDate.now().minusDays(nDays);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String actualDate = datePriorNDays.format(formatter);
        return actualDate;
    }

    public String getCurrentDate() {
        Date currentDate = new Date();
        String DATE_FORMAT = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        String actualDate = sdf.format(currentDate);
        return actualDate;

    }

    public String getFutureYearWithCurrentdate(int year) {
        String DATE_FORMAT = "MMddyyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, year);
        return sdf.format(cal.getTime());

    }


    public void createAndSave(String projectName, String programName, String contractId, String clientName) {
        clearAndFillText(TMProjectDetailsPage.projectName, projectName);
        TMProjectDetailsPage.state.click();
        hover(TMProjectDetailsPage.state);
        TMProjectDetailsPage.AR.click();
        clearAndFillText(TMProjectDetailsPage.programName, programName);
        clearAndFillText(TMProjectDetailsPage.contractId, contractId);
        clearAndFillText(TMProjectDetailsPage.stateAgencyName, clientName);
        TMProjectDetailsPage.provisioningStatus.click();
        hover(TMProjectDetailsPage.provisioningStatus);
        TMProjectDetailsPage.activeStatus.click();
        System.out.print("Clicked");
        //staticWait(5000);
        hover(TMProjectDetailsPage.saveButton);
        highLightElement(TMProjectDetailsPage.saveButton);
        TMProjectDetailsPage.saveButton.click();
        highLightElement(TMProjectDetailsPage.saveButton);
    }

    public void addContractDateAndSaveCurrentDate(String projectName, String programName, String contractId, String
            clientName, String startDate) {
        clearAndFillText(TMProjectDetailsPage.projectName, projectName);
        TMProjectDetailsPage.state.click();
        hover(TMProjectDetailsPage.state);
        TMProjectDetailsPage.AR.click();
        clearAndFillText(TMProjectDetailsPage.programName, programName);
        clearAndFillText(TMProjectDetailsPage.contractId, contractId);
        clearAndFillText(TMProjectDetailsPage.stateAgencyName, clientName);
        clearAndFillText(TMProjectDetailsPage.contractStartDate, startDate);
        clearAndFillText(TMProjectDetailsPage.contractEndDate, startDate);
        TMProjectDetailsPage.provisioningStatus.click();
        hover(TMProjectDetailsPage.provisioningStatus);
        TMProjectDetailsPage.activeStatus.click();
        System.out.print("Clicked");
        //staticWait(5000);
        hover(TMProjectDetailsPage.saveButton);
        highLightElement(TMProjectDetailsPage.saveButton);
        TMProjectDetailsPage.saveButton.click();
        highLightElement(TMProjectDetailsPage.saveButton);
    }

    public void addContractDateAndSaveCurrentDateAndEndDate(String projectName, String programName, String
            contractId, String clientName, String startDate, String endDate) {
        clearAndFillText(TMProjectDetailsPage.projectName, projectName);
        TMProjectDetailsPage.state.click();
        hover(TMProjectDetailsPage.state);
        TMProjectDetailsPage.AR.click();
        clearAndFillText(TMProjectDetailsPage.programName, programName);
        clearAndFillText(TMProjectDetailsPage.contractId, contractId);
        clearAndFillText(TMProjectDetailsPage.stateAgencyName, clientName);
        clearAndFillText(TMProjectDetailsPage.contractStartDate, startDate);
        clearAndFillText(TMProjectDetailsPage.contractEndDate, endDate);
        TMProjectDetailsPage.provisioningStatus.click();
        hover(TMProjectDetailsPage.provisioningStatus);
        TMProjectDetailsPage.activeStatus.click();
        System.out.print("Clicked");
        //staticWait(5000);
        hover(TMProjectDetailsPage.saveButton);
        highLightElement(TMProjectDetailsPage.saveButton);
        TMProjectDetailsPage.saveButton.click();
        highLightElement(TMProjectDetailsPage.saveButton);
    }

    public void search(String projectName, String programName) {
        //clearAndFillText(tenantManagerListOfProjectsPage.state,state);
        clearAndFillText(tmListOfProjectsPage.project, projectName);
        clearAndFillText(tmListOfProjectsPage.programName, programName);
        staticWait(1000);
        hover(tmListOfProjectsPage.search);
        tmListOfProjectsPage.search.click();
        staticWait(100);

    }

    public void selectSearchResults() {
        highLightElement(tmListOfProjectsPage.elementSearchResults);
        //tenantManagerListOfProjectsPage.elementSearchResults.click();
        tmListOfProjectsPage.arrowClick.click();
    }

    public void getStateNames() {
        TMProjectDetailsPage.state.click();
        hover(TMProjectDetailsPage.state);
        List<WebElement> getStates = TMProjectDetailsPage.stateList;
        getStates.size();
        System.out.print("size is " + getStates.size());
        for (int i = 1; i < getStates.size(); i++) {
            System.out.println(getStates.get(i).getText());
        }
        TMProjectDetailsPage.AR.click();

    }

    public void createProjectContactAndSave(String firstName, String middleName, String lastName, String
            phoneNumber, String email) {
        highLightElement(TMProjectDetailsPage.role);
        hover(TMProjectDetailsPage.role);
        TMProjectDetailsPage.accountApprover.click();
        clearAndFillText(TMProjectDetailsPage.firstName, firstName);
        clearAndFillText(TMProjectDetailsPage.middleName, middleName);
        clearAndFillText(TMProjectDetailsPage.lastName, lastName);
        clearAndFillText(TMProjectDetailsPage.phoneNumber, phoneNumber);
        clearAndFillText(TMProjectDetailsPage.email, email);
        TMProjectDetailsPage.projectContactSave.click();

    }

    public static int radomNumber(int range) {
        Random ran = new Random();
        int ranNumb = ran.nextInt(range - 1) + 1;
        if (ranNumb == 0) {
            return ranNumb + 1;
        } else if (ranNumb == range) {
            return ranNumb - 1;
        } else {
            return ranNumb;
        }
    }

    /*
     * Author:Shilpa P
     * @return this method returns the random number from range 1-500
     *
     * */

    public int randomNumberGenartor() {
        Random rand = new Random();
        int randomValue = rand.nextInt(500);
        return randomValue;

    }


    /*this method asserts dropdown element being displayed and selected*/
    public static void selectDropDown(WebElement element, String selector) {
        // for some reason Project Page is loading something, can't find
        // Solution. It waits about 1 sec and then starts selection process
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        element.click();
        BrowserUtils.hover(element);
        WebElement single = Driver.getDriver().findElement(By.xpath("//ul/li[contains(text(), '" + selector + "')]"));
        BrowserUtils.scrollToElement(single);
        BrowserUtils.hover(single);
        single.click();
        BrowserUtils.hover(element);
        Assert.assertEquals(element.getText(), selector, "Selector " + selector + " - wasn't selected");
    }

    /*This method selects value from Account Inactivation reason dropdown*/

    public static void selectInactivateReason(WebElement element, String selector) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        element.click();
        BrowserUtils.hover(element);
        WebElement single = Driver.getDriver().findElement(By.xpath("//ul/li[@data-value='" + selector + "']"));
        BrowserUtils.scrollToElement(single);
        BrowserUtils.hover(single);
        single.click();
        BrowserUtils.hover(element);
        Assert.assertEquals(element.getText(), selector, "Selector " + selector + " - wasn't selected");
    }

    public static void sendKeyToTextField(WebElement element, String value) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        element.click();
        element.clear();
        element.sendKeys(value);
    }

    /*this method returns present local time in 12 hours <hh:mm a> format. Exp: 03:58 pm*/
    public static String timeNow() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm a");
        LocalTime localTimeNow = LocalTime.now();
        return dtf.format(localTimeNow);
    }

    /*this method returns a String of specific length*/
    public static String createTextString(int size) {
        StringBuilder text = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            text.append("abc!1234/'][- ");
            if (text.length() >= size) {
                break;
            }
        }
        return text.toString();
    }
    /*this method returns a string with all non Alphabetical symbols */

    public static String mostSymbolText() {
        StringBuilder text = new StringBuilder().append("3!@#$%H%^&*()_+-=e[{]}]\\|;:'").append("\"").append("r<>?,.+/mione");
        return text.toString();
    }

    /*this method asserts that there is no specific WebElement on the page */

    public static boolean textIsNotPresent(String text) {
        boolean notPresent = false;
        try {
            Driver.getDriver().findElement(By.xpath("//*[contains(text(), '" + text + "')]"));
        } catch (NoSuchElementException e) {
            notPresent = true;
        }
        return notPresent;
    }

    public static boolean textIsNotPresent2(String locator, int index) {
        boolean notPresent = false;
        try {
            Driver.getDriver().findElement(By.xpath("(//*[contains(text(), '" + locator + "')])[index]"));
        } catch (NoSuchElementException e) {
            notPresent = true;
        }
        return notPresent;
    }

    /*this method checks the text consists alphabetical symbols and space */
    public static boolean hasOnlyLettersSpaces(String text) {
        Pattern p = Pattern.compile("^[ A-Za-z]+$");
        Matcher m = p.matcher(text);
        boolean b = m.matches();
        return b;
    }

    //refactoring 10/17/18
    public static boolean stateDropdownHasAll(WebElement element) {
        boolean present = false;
        List<String> allStates = Arrays.asList("District of Columbia", "Alabama", "Alaska", "Arizona", "Arkansas", "California",
                "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa",
                "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi",
                "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina",
                "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota",
                "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming");
        List<String> states = new ArrayList<>();

        states.addAll(allStates);
        for (String state : states) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            element.click();
            BrowserUtils.hover(element);
            WebElement single = Driver.getDriver().findElement(By.xpath("//ul/li[contains(text(), '" + state + "')]"));
            BrowserUtils.scrollToElement(single);
            present = (single.getText().equals(state));
            BrowserUtils.hover(single);
            single.click();
        }
        return present;
    }

    public static boolean hasOnlyDigits(String text) {
        text = text.replace("-", "");
        boolean isaDigit = true;

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            if (!Character.isDigit(character)) {
                isaDigit = false;
                break;
            }
        }
        return isaDigit;
    }

    public static boolean isAlphanumeric(String text) {
        boolean alphanumeric;
        if (text.matches("[A-Za-z0-9]+")) {

            return true;

        }
        return false;

    }

    public static Map getUINameForData() {

        Response response1 =
                given().accept(ContentType.JSON)
                        .and().params("amount", 1, "region", "United States")
                        .when().get(UINAMESURL);
        return response1.body().as(Map.class);
    }

    /**
     * this method gets and stores a new set of test data by api call to uiname.com
     */
    public static Map getNewTestData() {

        Response response2 =
                given().accept(ContentType.JSON)
                        .and().params("amount", 1, "region", "United States")
                        .when().get(UINAMESURL);
        JsonPath json = response2.jsonPath();
//       firstName = json.getString("name");
//       lastName = json.getString("surname");
//       ssn = json.getString("birthday.raw");
//       dateOfBirth =json.getString("birthday.mdy");
//       phoneNumber = json.getString("number");
//       gender = json.getString("gender");
//       email = json.getString("email");
//       zipCode = json.getString("number");
        assertEquals(response2.statusCode(), 200);
//       assertTrue(firstName!=null);
        return response2.body().as(Map.class);
    }


    /*
     * Author:Shruti
     * This method is used to verify that an element is not displayed
     * */
    public boolean IsElementDisplayed(WebElement element) {
        try {
            element.isDisplayed();
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    public static int randomNumberBetweenTwoNumbers(int low, int high) {
        Random ran = new Random();
        int ranNumb = ran.nextInt((high - low) + 1) + low;
        return ranNumb;
    }

    public static boolean getmailingAddress(WebElement element) {
        boolean present = true;
        List<String> allStates = Arrays.asList("District of Columbia", "Alabama", "Alaska", "Arizona", "Arkansas", "California",
                "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa",
                "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi",
                "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina",
                "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota",
                "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming");
        List<String> states = new ArrayList<>();
        states.addAll(allStates);
        for (String state : states) {
            if (present) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                element.click();
                BrowserUtils.hover(element);
                WebElement single = Driver.getDriver().findElement(By.xpath("//ul/li[contains(text(), '" + state + "')]"));
                BrowserUtils.scrollToElement(single);


                BrowserUtils.hover(single);
                single.click();
                present = (element.getText().equals(state));
            }

        }
        return present;
    }

    /*
     * Author:Shilpa P
     * This method is used to generate Random  Strings  with Number combination
     * @returns the String
     * */
    public static String generateRandomNumberChars() {
        String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        int length = 17;
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars
                    .length())));
        }
        return sb.toString();
    }

    /*
     * Author:Shilpa P
     * This method is used to generate Random  Strings  with Number combination
     * @returns the String
     * */
    public static String generateRandomCharacters() {
        String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int length = 17;
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars
                    .length())));
        }
        return sb.toString();
    }

    //refactoring 10/23/18
    public static void singleSelectFromMultipleOptionDropDown(WebElement element, String selector) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        element.click();
        BrowserUtils.hover(element);
        WebElement single = Driver.getDriver().findElement(By.xpath("//ul/li[contains(text(), '" + selector + "')]"));
        BrowserUtils.scrollToElement(single);
        BrowserUtils.hover(single);
        single.click();
        single.sendKeys(Keys.ESCAPE);
        BrowserUtils.hover(element);
        Assert.assertEquals(element.getText(), selector, "Selector " + selector + " - wasn't selected");
        waitFor(1);
        element.click();
        waitFor(1);
        single = Driver.getDriver().findElement(By.xpath("//ul/li[contains(text(), '" + selector + "')]"));
        waitFor(1);
        single.click();
        single.sendKeys(Keys.ESCAPE);

    }

    /*
     * Author:Shruti
     * This method is used to compare hash maps
     * @returns the String
     * */
    public static HashMap<String, String> getEmailIdsWithStatus() {
        List<WebElement> emailIDRows = Driver.getDriver().findElements(By.xpath("//th[contains(text(),'EMAIL ADDRESS')]/parent::tr/parent::thead/parent::table/tr"));
        int numberofRowsInEmailIDSection = emailIDRows.size();
        HashMap<String, String> emailIDsWithStatus = new HashMap<String, String>();
        for (int i = 2; i <= numberofRowsInEmailIDSection; i++) {
            int counter = 1;
            emailIDsWithStatus.put(Driver.getDriver().findElement(By.xpath("(//th[contains(text(),'EMAIL ADDRESS')]/parent::tr/parent::thead/parent::table/tr)[" + i + "]/td[1]")).getText(), counter + Driver.getDriver().findElement(By.xpath("(//th[contains(text(),'EMAIL ADDRESS')]/parent::tr/parent::thead/parent::table/tr)[" + i + "]/td[3]")).getText());
            counter++;
        }
        return emailIDsWithStatus;
    }

    public boolean compareHashMaps(HashMap<String, String> mapA, HashMap<String, String> mapB) {
        try {
            for (String k : mapB.keySet()) {
                System.out.println("mapA" + mapA.get(k) + "and the value of the key is " + k);
                System.out.println("mapB" + mapB.get(k) + "and the value of the key is " + k);
                if (!mapA.get(k).equals(mapB.get(k))) {

                }
            }
            for (String y : mapA.keySet()) {
                if (!mapB.containsKey(y)) {
                    return false;
                }
            }
        } catch (NullPointerException np) {
            return false;
        }
        return true;

    }

    public String getEmailStatus(String emailID) {
        return Driver.getDriver().findElement(By.xpath("//td[.='" + emailID + "']/following-sibling::td[2]")).getText();
    }

    public void clickOnFirstEmaiId() {
        Driver.getDriver().findElement(By.xpath("(//th[contains(text(),'EMAIL ADDRESS')]/parent::tr/parent::thead/parent::table/tbody/tr)[2]/td[1]")).click();
    }

    public void updateEmailID(WebElement emailIDField) {
        clearAndFillText(emailIDField, randomEmailId());
    }

    public String getText(WebElement element) {
        return element.getText();
    }

    public void click(WebElement ele) {
        ele.click();
    }

    public HashMap<String, String> getHoverText() {
        HashMap<String, String> toolTipData = new HashMap<String, String>();
        for (int i = 1; i <= 2; i++) {
            toolTipData.put(getText(Driver.getDriver().findElement(By.xpath("(//div[@role='tooltip']/div/div/div)[" + i + "]/p"))).trim(), getText(Driver.getDriver().findElement(By.xpath("(//div[@role='tooltip']/div/div/div)[" + i + "]/h6"))));
        }
        return toolTipData;
    }

    /*
     * Author:Shruti
     * Implementing treemap to verify the order of records
     * */
    public static TreeMap<String, String> getRecordsOrder() {
        List<WebElement> emailIDRows = Driver.getDriver().findElements(By.xpath("//th[contains(text(),'EMAIL ADDRESS')]/parent::tr/parent::thead/parent::table/tr"));
        int numberofRowsInEmailIDSection = emailIDRows.size();
        TreeMap<String, String> emailIDsWithStatus = new TreeMap<String, String>();
        for (int i = 2; i <= numberofRowsInEmailIDSection; i++) {
            emailIDsWithStatus.put(Driver.getDriver().findElement(By.xpath("(//th[contains(text(),'EMAIL ADDRESS')]/parent::tr/parent::thead/parent::table/tr)[" + i + "]/td[1]")).getText(), Driver.getDriver().findElement(By.xpath("(//th[contains(text(),'EMAIL ADDRESS')]/parent::tr/parent::thead/parent::table/tr)[" + i + "]/td[3]")).getText());
        }
        return emailIDsWithStatus;
    }
    /*
     * Author:Shruti
     * //This method can be extended for rest of the sections
     * */

    public int getPaginationSize(String section) {
        int size = 0;
        if (section.equals("email"))
            size = Driver.getDriver().findElements(By.xpath("(//ul[@class='pagination'])[3]/li/a")).size();
        return size;
    }

    //refactoring 10/25/18
    public static void singleSelectFromDropDown(WebElement element, String selector) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        element.click();
        BrowserUtils.hover(element);
        WebElement single = Driver.getDriver().findElement(By.xpath("//ul/li[contains(text(), '" + selector + "')]"));
        BrowserUtils.scrollToElement(single);
        BrowserUtils.hover(single);
        single.click();
        single.sendKeys(Keys.ESCAPE);
    }

    public static boolean textIsPresent(String text) {
        return Driver.getDriver().getPageSource().contains(text);

    }

    public static boolean ascendingOrderIDs(List<WebElement> IDs) {
        boolean is = false;
        int topId;
        int nextBelowId;
        for (int i = 0; i <= IDs.size() - 1; i++) {
            topId = Integer.parseInt(IDs.get(i).getText());
            if (i == IDs.size() - 1) {
                continue;
            } else {
                nextBelowId = Integer.parseInt(IDs.get(i + 1).getText());

            }
            is = (topId > nextBelowId);
        }
        return is;
    }

    public static boolean descendingOrderIDs(List<WebElement> IDs) {
        boolean is = false;
        int topId;
        int nextBelowId;
        for (int i = 0; i <= IDs.size() - 1; i++) {
            topId = Integer.parseInt(IDs.get(i).getText());
            if (i == IDs.size() - 1) {
                continue;
            } else {
                nextBelowId = Integer.parseInt(IDs.get(i + 1).getText());

            }
            is = (topId < nextBelowId);

        }
        return is;
    }

    public boolean ascendingOrderDates(List<WebElement> dates) {
        boolean is = false;
        String topDate;
        String nextBelowDate;
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        for (int i = 0; i <= dates.size() - 1; i++) {

            topDate = dates.get(i).getText();
            if (i == dates.size() - 1) {
                continue;
            } else {
                nextBelowDate = dates.get(i + 1).getText();
                LocalDate date1 = LocalDate.parse(topDate, sdf);
                LocalDate date2 = LocalDate.parse(nextBelowDate, sdf);
                if (date1.isEqual(date2)) {
                    is = true;
                } else {
                    is = (date1.isAfter(date2));
                }
            }
        }
        return is;
    }

    public boolean descendingOrderDates(List<WebElement> dates) {
        boolean is = false;
        String topDate;
        String nextBelowDate;
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        for (int i = 0; i <= dates.size() - 1; i++) {

            topDate = dates.get(i).getText();
            if (i == dates.size() - 1) {
                continue;
            } else {
                nextBelowDate = dates.get(i + 1).getText();
                LocalDate date1 = LocalDate.parse(topDate, sdf);
                LocalDate date2 = LocalDate.parse(nextBelowDate, sdf);
                if (date1.isEqual(date2)) {
                    is = true;
                } else {
                    is = (date1.isBefore(date2));
                }
            }
        }
        return is;
    }

    public boolean ascendingOrderTexts(List<WebElement> texts) {
        boolean is = false;
        String topText;
        String nextBelowText;

        for (int i = 0; i <= texts.size() - 1; i++) {
            if (i + 1 == texts.size()) {
                is = true;
                continue;
            } else {
                topText = texts.get(i).getText();
                nextBelowText = texts.get(i + 1).getText();
                if (topText.equals("") || nextBelowText.equals("")) {
                    is = true;
                    continue;
                } else {
                    if (topText.charAt(0) == nextBelowText.charAt(0)) {
                        is = true;
                    } else {
                        is = (topText.charAt(0) < nextBelowText.charAt(0));
                    }
                }
            }
        }
        return is;
    }

    public boolean descendingOrderTexts(List<WebElement> texts) {
        boolean is = false;
        String topText;
        String nextBelowText;

        for (int i = 0; i <= texts.size() - 1; i++) {
            if (i + 1 == texts.size()) {
                is = true;
                continue;
            } else {
                topText = texts.get(i).getText();
                nextBelowText = texts.get(i + 1).getText();
                if (topText.equals("") || nextBelowText.equals("")) {
                    is = true;
                    continue;
                } else {
                    if (topText.charAt(0) == nextBelowText.charAt(0)) {
                        is = true;
                    } else {
                        is = (topText.charAt(0) > nextBelowText.charAt(0));
                    }
                }
            }
        }
        return is;
    }

    public String getRandomString(int stringLength) {
        boolean useLetters = true;
        boolean useNumbers = false;
        return RandomStringUtils.random(stringLength, useLetters, useNumbers);
    }

    public void clearAndFillText2(WebElement element, String text) {

        element.clear();
        staticWait(500);
        element.sendKeys(text);
    }
}
