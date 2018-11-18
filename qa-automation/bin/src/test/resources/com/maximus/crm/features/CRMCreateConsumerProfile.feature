Feature: Create a Consumer Profile

  @CRM-300 @CRM-300-01 @regression @muhabbat @crm @CRM-905-01 @crm-regression
  Scenario: Verification Add Consumer button is present and functions
    Given I logged into CRM and click on initiate contact
    When I populate Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    Then I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI


  @CRM-300 @CRM-300-02 @regression @muhabbat @crm @crm-regression
  Scenario: Create consumer fields auto-filled with search values
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    Then I see previously populated criteria fields are auto-filled


  @CRM-300 @CRM-300-03 @regression @muhabbat @crm @crm-regression
  Scenario: Verification of fields on Create Consumer UI
    Given I logged into CRM and click on initiate contact
    When I populate Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    Then I see all fields and buttons present


  @CRM-300 @CRM-300-04 @regression @muhabbat @crm @crm-regression
  Scenario: Verification of mandatory fields on Create Consumer UI
    Given I logged into CRM and click on initiate contact
    When I populate Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    Then I verify "following" fields being marked mandatory
      | Consumer First Name |
      | Consumer Last Name  |
      | Zip Code            |
      | Phone Number        |

  @CRM-300 @CRM-300-05 @regression @muhabbat @crm @crm-regression
  Scenario: Verification of Cancel Button functionality
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    #refactored 10/17/18
    When I click on Cancel Button on Create Consumer Page
    Then I am navigated back to Contact Record UI page

  @CRM-300 @CRM-300-06 @regression @muhabbat @crm @crm-regression @smoke
  Scenario: Verification of Save Consumer Button functionality
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    Then I see new consumer is created and has a unique Consumer ID

  @CRM-300 @CRM-300-07 @regression @muhabbat @crm @crm-regression
   Scenario: Verification Address fields accept up to 50 characters
    Given I logged into CRM and click on initiate contact
    When I populate Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    And I see "addressLine1" field accept 50 characters in total
    And I see "addressLine2" field accept 50 characters in total


  @CRM-300 @CRM-300-10 @regression @muhabbat @crm @crm-regression
  Scenario: Verification City field accept up to 30 alphabetic characters
    Given I logged into CRM and click on initiate contact
    When I populate Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    And I see "city" field accept 30 characters in total



  @CRM-300 @CRM-300-11 @muhabbat @crm @crm-regression @regression
  Scenario: Verification all states in State Dropdown are available
    Given I logged into CRM and click on initiate contact
    When I populate Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    Then I should be able to see all states in State dropdown are available

    #refactoring 10/17/18
  @CRM-300 @CRM-300-12 @muhabbat @crm @crm-regression @regression
  Scenario: Verification Zip consist out of Numbers only
    Given I logged into CRM and click on initiate contact
    When I populate Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    And I see "<Zip Code>" field accepts up to 9 digits


  @CRM-300 @CRM-300-13 @regression @muhabbat @crm @crm-regression 
  Scenario: Verification Unique ID field accept up to 30 alphanumeric characters
    Given I logged into CRM and click on initiate contact
    When I populate Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    And I see "uniqueID" field accept only 30 characters
    Then I see "uniqueID" field accept only alphanumeric characters


  @CRM-575 @CRM-300 @CRM-482 @CRM-300-08 @CRM-482-02 @CRM-575-06 @crm @regression @muhabbat @shruti @vinuta @crm-regression
  Scenario: Validating Error message is displayed for the mandatory fields
    Given I logged into CRM and click on initiate contact
    When I populate Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    And I clear all fields values
    And I click on Create Consumer Button on Create Consumer Page
    Then I see Please fill in the required field error message displayed


  @CRM-300 @CRM-300-09 @muhabbat @crm @crm-regression @smoke #duplicate of CRM-482-05 by Shruti
  Scenario: Validating Error message is displayed when consumer details match an existing consumer
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    And I am navigated to active contact page
    And I click on Unlink Contact Button on Active Contact Page
    When I enter random "xyz" Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    And I click on Create Consumer Button on Create Consumer Page
    Then I see Consumer already exists, please associate existing Consumer to Case message displayed

    #refactoring 10/17/18
  @CRM-300 @CRM-300-14 @muhabbat @crm @crm-regression
  Scenario: Verification Zip field has 5 digits required and 9 digits optional format
    Given I logged into CRM and click on initiate contact
    When I populate Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    And I see 5 digits required and 9 digits optional format

  @CRM-482-03 @regression @shruti @crm-regression
  Scenario: Verification of Save Consumer Button functionality
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    Then I am navigated to active contact page

  @CRM-482-04 @regression @shruti @crm-regression
  Scenario: Verification of unique Consumer ID is generated functionality
    Given I logged into CRM and click on initiate contact
    When I populate Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    And I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    And I am navigated back to Contact Record UI page
    Then I see a unique Consumer Profile ID generated

  @CRM-575 @CRM-482 @CRM-482-05 @CRM-575-12 @regression @shruti @vinuta @crm-regression
  Scenario: Verification of Duplicate consumer functionality
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    Then I am navigated to active contact page
    And I click on Unlink Contact Button on Active Contact Page
    When I enter random "xyz" Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    Then I see Consumer already exists, please associate existing Consumer to Case message displayed

#By Vinuta, Check for field-level validations on mandatory fields
  @CRM-575-10 @regression @CRM-575 @vinuta @crm @crm-regression
  Scenario: Validating error message on mandatory fields when input is in incorrect format
    Given I logged into CRM and click on initiate contact
    When I populate Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    Then I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    And I populate invalid phone,zipcode
    And I click on Create Consumer Button on Create Consumer Page
    Then I see mandatory fields highlighted with error messages

  #By Vinuta, Check for field-level validations on optional fields
  @CRM-575-07 @regression @CRM-575 @vinuta @crm @crm-regression
  Scenario: Validating error message on optional fields when input is in incorrect format
    Given I logged into CRM and click on initiate contact
    When I populate Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    Then I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    And I populate Create Consumer fields for a new consumer
    And I populate invalid date, ssn, email
    And I click on Create Consumer Button on Create Consumer Page
    Then I see optional fields highlighted with error messages

  @crm @CRM-905-01 @shruti @0925 @CRM-905 @crm-regression
  Scenario: Verification Add Consumer button is displayed when consumer is not found
    Given I logged into CRM and click on initiate contact
    When I populate Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    Then I am able to see Add Consumer button on Consumer Search Results Page

  @CRM-905-02 @regression @CRM-905 @shruti @0925 @crm @CRM-905 @crm-regression @smoke
  Scenario: Verify Add Consumer Button Availability All the time (even when consumer is displayed in search result)
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    Then I am navigated to active contact page
    And I click on Unlink Contact Button on Active Contact Page
    When I enter Search criteria fields for the new consumer created
    And I click on Search Button on Search Consumer Page
    Then I am able to see Add Consumer button on Consumer Search Results Page

  @crm @CRM-905-03 @shruti @0927 @regression @CRM-905 @crm-regression
  Scenario: Verify Add Consumer button is not displayed when consumer search is not performed
    Given I logged into CRM and click on initiate contact
    Then  Add Consumer button is not displayed on Consumer Search Results Page

  @crm @CRM-905-04 @shruti @0927 @CRM-905 @crm-regression
  Scenario: Verify Add Consumer button is not displayed when blank consumer search is performed
    Given I logged into CRM and click on initiate contact
    When I click on Search Button without entering search parameters
    Then  Add Consumer button is not displayed on Consumer Search Results Page





