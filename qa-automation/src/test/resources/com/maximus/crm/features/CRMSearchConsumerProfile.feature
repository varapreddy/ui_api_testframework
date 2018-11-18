Feature: Search for Consumer Profile

  @CRM-234 @CRM-234-01 @CRM-236-02 @regression @muhabbat @crm  @crm-regression @smoke
  Scenario: Search for Consumer by first name
    Given I logged into CRM and click on initiate contact
    When I search for an existing contact by first name "Ethan"
    And I click on Search Button
    Then I see all search results according to the first name "Ethan"

  @CRM-234 @CRM-234-08 @CRM-236-02 @regression @muhabbat @crm @crm-regression @smoke
  Scenario: Search for Consumer by last name
    Given I logged into CRM and click on initiate contact
    When I search for an existing contact by last name "Hunt"
    And I click on Search Button
    Then I see all search results according to the last name "Hunt"

 @CRM-236 @CRM-236-02 @regression @crm-regression
  Scenario: Verify no record is available
    Given I logged into CRM and click on initiate contact
    When I search for an existing contact by last name "jkshdfkhsdjkh"
    And I click on Search Button
    Then I see No Records Available is displayed

  @CRM-234 @CRM-234-02 @CRM-236-02 @regression @muhabbat @crm @crm-regression @smoke
  Scenario: Search for Consumer by SSN
    Given I logged into CRM and click on initiate contact
    When I search for an existing contact by SSN "222-33-3665"
    And I click on Search Button
    When I and click on unmasking button
    Then I see all search results according to SSN "222-33-3665"


  @CRM-234 @CRM-234-03 @CRM-236-02 @muhabbat @crm @crm-regression @smoke
  Scenario: Search for Consumer by DOB
    Given I logged into CRM and click on initiate contact
    When I search for an existing contact by DOB "12/31/1974"
    And I click on Search Button
    Then I see all search results according to DOB "12/31/1974"


  @CRM-234 @CRM-234-04 @CRM-236-02 @muhabbat #refactoring 10/25/18 - removing crm, regression, crm-regression, smoke tags till <Unique ID>not being displayed on consumer UI clarified
  Scenario: Search for Consumer by Unique ID
    Given I logged into CRM and click on initiate contact
    When I search for an existing contact by Unique ID "56701968"
    And I click on Search Button
    Then I see all search results according to Unique ID "Hunt"

  @CRM-234 @CRM-234-05 @CRM-236-02 @CRM-236-02 @regression @muhabbat @crm @crm-regression
  Scenario: Search for Consumer with blank fields
    Given I logged into CRM and click on initiate contact
    When I search for a Consumer with blank fields
    And I click on Search Button
    Then I see warning to enter search parameter


  @CRM-234 @CRM-234-06 @regression @muhabbat @crm @crm-regression
  Scenario: Search for Consumer not in database
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button
    Then I see no results are displayed

  @CRM-234 @CRM-234-07 @CRM-236-03 @muhabbat @crm @crm-regression
  Scenario: Hit Create Consumer Button (Duplicate of CRM-300-01)
    Given I logged into CRM and click on initiate contact
    When I populate Search criteria fields for a new consumer
    And I click on Search Button
    Then I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI