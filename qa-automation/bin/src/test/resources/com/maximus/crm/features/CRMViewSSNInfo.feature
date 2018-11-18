Feature: Viewing Consumer's SSN Information

  @CRM-281 @CRM-281-01 @regression @muhabbat @crm @crm-regression
  Scenario: Validating existing Consumer's SSN is masked by default
    Given I logged into CRM and click on initiate contact
    And I search for an existing contact by criteria
    Then I see all Search results have SSN field value masked in "***-**-****" format

  @CRM-281 @CRM-281-02 @regression @muhabbat @crm @crm-regression
  Scenario: Validating unmasking function
    Given I logged into CRM and click on initiate contact
    And I search for an existing contact by criteria
    Then I see unmasking button displayed

  @CRM-281 @CRM-281-03 @regression @muhabbat @crm @crm-regression
  Scenario: Validating unmasking function
    Given I logged into CRM and click on initiate contact
    And I search for an existing contact by criteria
    And I see unmasking button displayed
    When I and click on unmasking button
    Then I see SSN 9 digits value unmasked
