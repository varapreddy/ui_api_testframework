Feature: Contact Record Header UI

 @CRM-280 @CRM-280-01 @regression @shilpa @crm @crm-regression
  Scenario: Validate the Create Task button
    Given I logged into the CRM Application
    When I click on Hamburger Menu I should see the create Task button and I should be able to click

  @CRM-280 @CRM-280-02 @regression  @shilpa @crm @crm-regression
  Scenario: Validate the options are all displayed in the Hamburger Menu
    Given I logged into the CRM Application
    When I click on the Hamburger Menu
    Then I should see all the options present

  @CRM-280 @CRM-280-03 @regression @shilpa @crm @crm-regression
  Scenario:Validate the Username
    Given I logged into the CRM Application
    And I should see the Username should be present

  @CRM-280 @CRM-280-04 @regression @shilpa @crm  @crm-regression
  Scenario: Validate the Role
    Given I logged into the CRM Application
    And I should see the Role present in the header

  @CRM-280 @CRM-280-05 @shilpa @crm @crm-regression @smoke
  Scenario: Validate the Current Date and Time
    Given I logged into the CRM Application
    And I should see the Current Date and Time Displayed

  @CRM-280 @CRM-280-06 @regression @shilpa @crm @crm-regression
  Scenario: validate the office Address in the footer
    Given I logged into the CRM Application
    And I should see the office Address displayed in the bottom


