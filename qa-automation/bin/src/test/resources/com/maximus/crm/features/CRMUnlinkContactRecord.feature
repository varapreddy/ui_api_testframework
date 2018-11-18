Feature: Unlink Contact Record - Active Contact

  @CRM-297 @CRM-297-01 @regression @muhabbat @crm @crm-regression
  Scenario: Unlink Button on UI for Consumer
    Given I logged into CRM and click on initiate contact
    When I search for an existing contact by last name "Hunt"
    When I click on Search Button
    And I link the contact to an existing Case or Consumer Profile
    Then I see unlink contact record option is displayed

  @CRM-297 @CRM-297-02 @muhabbat  @crm #as for 08/31/2018 is duplicate of CRM-297-01 (linking to Case is not implemented)
  Scenario: Unlink Button on UI for Case
    Given I logged into CRM and click on initiate contact
    When I search for an existing contact by last name "Hunt"
    When I click on Search Button
    And I link the contact to an existing Case or Consumer Profile
    Then I see unlink contact record option is displayed

  @CRM-297 @CRM-297-03 @regression @muhabbat @crm @crm-regression
  Scenario: Unlink - Initial Creation for Consumer
    Given I logged into CRM and click on initiate contact
    When I search for an existing contact by last name "Hunt"
    When I click on Search Button
    And I link the contact to an existing Case or Consumer Profile
    And I see unlink contact record option is displayed
    And I click on Unlink Contact Button on Active Contact Page
    And I see no values are displayed on Header for Consumer name and ID
    Then I see Contact Record Search is displayed


  @CRM-297 @CRM-297-04 @muhabbat @crm #as for 08/31/2018 is duplicate of CRM-297-01 (linking to Case is not implemented)
  Scenario: Unlink - Initial Creation for Case
    Given I logged into CRM and click on initiate contact
    When I search for an existing contact by last name "Hunt"
    When I click on Search Button
    And I link the contact to an existing Case or Consumer Profile
    And I see unlink contact record option is displayed
    And I click on Unlink Contact Button on Active Contact Page
    And I see no values are displayed on Header for Consumer name and ID
    Then I see Contact Record Search is displayed

  @CRM-297 @CRM-297-05 @regression @muhabbat @crm @crm-regression
  Scenario: Unlink - Information Captured on Contact Record for Consumer
    Given I logged into CRM and click on initiate contact
    When I search for an existing contact by last name "Hunt"
    When I click on Search Button
    And I link the contact to an existing Case or Consumer Profile
    And I see unlink contact record option is displayed
    When  I should see following dropdown options for "contact type" field displayed
      | Outbound |
    When  I should see following dropdown options for "contact channel" field displayed
      | Web Chat |
    When  I should see following dropdown options for "contact reason" field displayed
      | Update Information Request |
    And  I should see following dropdown options for "contact action" field displayed
      | Updated Eligibility Information |
    And I click on Unlink Contact Button on Active Contact Page
    And I see no values are displayed on Header for Consumer name and ID
    Then I see entered value for Contact Details and Reason not changed

  @CRM-297 @CRM-297-06 @muhabbat @crm #as for 08/31/2018 is duplicate of CRM-297-01 (linking to Case is not implemented)
  Scenario: Unlink - Information Captured on Contact Record for Case
    Given I logged into CRM and click on initiate contact
    When I search for an existing contact by last name "Hunt"
    When I click on Search Button
    And I link the contact to an existing Case or Consumer Profile
    And I see unlink contact record option is displayed
    When  I should see following dropdown options for "contact type" field displayed
      | Outbound |
    When  I should see following dropdown options for "contact channel" field displayed
      | Web Chat |
    When  I should see following dropdown options for "contact reason" field displayed
      | Update Information Request |
    And  I should see following dropdown options for "contact action" field displayed
      | Updated Eligibility Information |
    And I click on Unlink Contact Button on Active Contact Page
    And I see no values are displayed on Header for Consumer name and ID
    Then I see entered value for Contact Details and Reason not changed

  @CRM-297 @CRM-297-07 @regression @muhabbat @crm @crm-regression
  Scenario: Unlink - Initial Creation - Standard User
    Given I logged into CRM and click on initiate contact
    When I search for an existing contact by last name "Hunt"
    When I click on Search Button
    And I link the contact to an existing Case or Consumer Profile
    And I see unlink contact record option is displayed
    When  I should see following dropdown options for "contact type" field displayed
      | Outbound |
    When  I should see following dropdown options for "contact channel" field displayed
      | Web Chat |
    When  I should see following dropdown options for "contact reason" field displayed
      | Update Information Request |
    And  I should see following dropdown options for "contact action" field displayed
      | Updated Eligibility Information |
    And I click on Unlink Contact Button on Active Contact Page
    And I see no values are displayed on Header for Consumer name and ID
    And I see entered value for Contact Details and Reason not changed
    When I search for an existing contact by last name "Hadid"
    When I click on Search Button
    And I link the contact to an existing Case or Consumer Profile
    And I see unlink contact record option is displayed
    Then I see entered value for Contact Details and Reason not changed

  @CRM-297 @CRM-297-08 @regression @muhabbat @crm @crm-regression
    Scenario: Unlink - Initial Creation - Standard User
      Given I logged into CRM and click on initiate contact
      When I enter Search criteria fields for a new consumer
      And I click on Search Button
      And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
      When I populate Create Consumer fields for a new consumer
      When I click on Create Consumer Button on Create Consumer Page
      And I am navigated to active contact page
      Then I see unlink contact record option is displayed