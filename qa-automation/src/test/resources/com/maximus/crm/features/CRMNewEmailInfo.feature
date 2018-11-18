Feature:DemographicPage-Contact Info- Add,View &Update New Email Address Information

  @CRM-763  @CRM-763-01 @shruti @crm  @crm-regression @regression
  Scenario: Verify Add Email Address- Required Information Validation
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    Then I am navigated to active contact page
    When I should see the Demographic Tab
    Then I click on the Demographic Info Tab
    And I should see contact Info Tab
    Then I click on the Contact Info Tab
    And I should see the EmailAddress label displayed
    And I should see the Add button displayed for Email address
    When I click on the Add button for Email Address
    And I verify that I am in the Add Email Address Page
    Then I verify the fields displayed on the Add Email Address Page


  @CRM-763 @CRM-763-02 @shruti @crm @regression @crm-regression
  Scenario:Verify new email can be added to case member
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    Then I am navigated to active contact page
    When I should see the Demographic Tab
    Then I click on the Demographic Info Tab
    And I should see contact Info Tab
    Then I click on the Contact Info Tab
    And I should see the EmailAddress label displayed
    And I should see the Add button displayed for Email address
    When I click on the Add button for Email Address
    And I verify that I am in the Add Email Address Page
    And I enter the mandatory fields on the add email page and click on save
    Then I am navigated back to Demographic page

  @CRM-763 @CRM-763-03 @shruti @crm @regression @crm-regression
  Scenario:Verify the new email address added
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    Then I am navigated to active contact page
    When I should see the Demographic Tab
    Then I click on the Demographic Info Tab
    And I should see contact Info Tab
    Then I click on the Contact Info Tab
    And I should see the EmailAddress label displayed
    And I should see the Add button displayed for Email address
    When I click on the Add button for Email Address
    And I verify that I am in the Add Email Address Page
    And I enter the mandatory fields on the add email page and click on save
    Then I am navigated back to Demographic page

  @CRM-763 @CRM-763-04 @shruti @crm @regression @crm-regression
  Scenario:Verify Status of the Email with Current Start date and future end date
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    Then I am navigated to active contact page
    Then I click on the Demographic Info Tab
    Then I click on the Contact Info Tab
    When I click on the Add button for Email Address
    And I save the fields and give start date as current and end date in future
    Then I verify status of email as "ACTIVE"


  @CRM-763 @CRM-763-05 @shruti @crm @regression @crm-regression
  Scenario:Verify Status of the Email with Start date in past and future end date
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    Then I am navigated to active contact page
    Then I click on the Demographic Info Tab
    Then I click on the Contact Info Tab
    When I click on the Add button for Email Address
    And I save the fields and give start date in past and end date in future
    Then I verify status of email as "ACTIVE"

  @CRM-763 @CRM-763-06 @shruti @crm @regression @crm-regression
  Scenario:Verify Status of the Email with Start date and end date in future
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    Then I am navigated to active contact page
    Then I click on the Demographic Info Tab
    Then I click on the Contact Info Tab
    When I click on the Add button for Email Address
    And I save the fields and give start date in the future and end date in future
    Then I verify status of email as "INACTIVE"


  @CRM-763 @CRM-763-07 @shruti @crm @regression @crm-regression
  Scenario:Verify Status of the Email start date in the past and end date as current date
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    Then I am navigated to active contact page
    Then I click on the Demographic Info Tab
    Then I click on the Contact Info Tab
    When I click on the Add button for Email Address
    And I save the fields and give start date in the past and end date as current date
    Then I verify status of email as "ACTIVE"

  @CRM-763 @CRM-763-08 @shruti @crm @regression @crm-regression
  Scenario:Verify Status of the Email start date in the past and end date in past
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    Then I am navigated to active contact page
    Then I click on the Demographic Info Tab
    Then I click on the Contact Info Tab
    When I click on the Add button for Email Address
    And I save the fields and give start date in the past and end date as past
    Then I verify status of email as "INACTIVE"

  @CRM-763 @CRM-763-09 @shruti @crm @regression @crm-regression
  Scenario:Verify Email Address doesn't allow more than 30 characters
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    Then I am navigated to active contact page
    Then I click on the Demographic Info Tab
    Then I click on the Contact Info Tab
    When I click on the Add button for Email Address
    And I provide more than thirty characters in email address field "testlongemail!#$%^*/@sample.com"
    Then I verify email address more than 30 characters is not allowed

  @CRM-763 @CRM-763-10 @shruti @crm @regression @crm-regression
  Scenario:Verify Error is displayed for Mandatory fields on Add Email Page
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    Then I am navigated to active contact page
    Then I click on the Demographic Info Tab
    Then I click on the Contact Info Tab
    When I click on the Add button for Email Address
    And I click on save without entering the mandatory fields
    Then I see error message populated below each field

  @CRM-763 @CRM-763-11 @shruti @crm @regression @crm-regression
  Scenario:Verify Error is displayed for Invalid /Incorrect Format for the fields on Add Email Page
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    Then I am navigated to active contact page
    Then I click on the Demographic Info Tab
    Then I click on the Contact Info Tab
    When I click on the Add button for Email Address
    And I provide invalid data for emailAddress,StartDate and EndDate
    Then I see valid field error message populated below each field

    # failing due to bug execute once Bug 1185 is fixed
  #@CRM-764 @CRM-764-01 @shruti @crm @regression @crm-regression @1019
  Scenario:Verify that Active Emails are displayed in descending order
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    And I am navigated to active contact page
    And I click on the Demographic Info Tab
    Then I click on the Contact Info Tab
    When I click on the Add button for Email Address
    And I add three emailId's with start date as current and end date in future
    Then I verify the order of emailID's and the respective status
# failing due to bug execute once Bug 1185 is fixed
#  @CRM-764 @CRM-764-02 @shruti @crm  @1019
  Scenario:Verify that InActive Emails are displayed in descending order
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    Then I am navigated to active contact page
    Then I click on the Demographic Info Tab
    Then I click on the Contact Info Tab
    When I click on the Add button for Email Address
    And I add three emailId's with start date and end date in past
    Then I verify the order of emailID's and the respective status


  @CRM-764 @CRM-764-03 @shruti  @1022
  Scenario:Verify that Active Emails are displayed on the top followed by inactive
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    And I click on Create Consumer Button on Create Consumer Page
    And I click on the Demographic Info Tab
    When I click on the Contact Info Tab
    And I click on the Add button for Email Address
    And I add three emailId's with active and inactive status
    Then I verify that the active emails are displayed on the top


  @CRM-764 @CRM-764-04 @shruti @crm @1024
  Scenario:Verify Pagination for email address - 3 emails displayed at first glance
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    Then I am navigated to active contact page
    Then I click on the Demographic Info Tab
    Then I click on the Contact Info Tab
    When I click on the Add button for Email Address
    And I add four emailId's with active and inactive status
    Then I verify that three emails are display at first glance


  @CRM-764 @CRM-764-05 @shruti @crm @1024
  Scenario:Verify Pagination for email address - 3 emails displayed at first glance
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    Then I am navigated to active contact page
    Then I click on the Demographic Info Tab
    Then I click on the Contact Info Tab
    When I click on the Add button for Email Address
    And I add four emailId's with active and inactive status
    Then I verify pagination is available when more than three records are added


  @CRM-764 @CRM-764-06 @shruti  @1023
  Scenario:Verify that Hower over status start and end dates are displayed
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    Then I am navigated to active contact page
    Then I click on the Demographic Info Tab
    Then I click on the Contact Info Tab
    When I click on the Add button for Email Address
    And I save the fields and give start date in the past and end date as current date
    And I verify status of email as "ACTIVE"
    And I hower over status of the newly email added
    Then I verify that the start and end dates are displayed for email address


































