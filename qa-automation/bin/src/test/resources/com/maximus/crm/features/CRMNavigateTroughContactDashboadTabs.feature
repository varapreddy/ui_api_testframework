Feature: Navigation through Active Contact Record Dashboard Tabs

  @CRM-230 @CRM-230-01 @muhabbat @regression @crm-regression
  Scenario: Verify Contact Record is present when moved to different Tabs
    Given I logged into CRM and click on initiate contact
    When I search for an existing contact by first name "Ethan"
    And I click on Search Button
    And I link the contact to an existing Case or Consumer Profile
    And I should see Linked Contact in the Header
    When I click on "Demographic Info" Tab on Contact Dashboard Page
    And I click on "Active Contact" Tab on Contact Dashboard Page
    And I click on "Outbound" type of call option in "Contact Type" dropdown
    And I click on "Case & Contact Details" Tab on Contact Dashboard Page
    When I click on "Active Contact" Tab on Contact Dashboard Page
    When I click on "Task & Service Request" Tab on Contact Dashboard Page
    When I click on "Active Contact" Tab on Contact Dashboard Page
    When I click on "Program & Benefit Info" Tab on Contact Dashboard Page
    When I click on "Active Contact" Tab on Contact Dashboard Page

  @CRM-230 @CRM-230-02 @muhabbat @regression @crm-regression
  Scenario: Verify Contact Reason, Action, Additional Comments are present through all Tabs
    Given I logged into CRM and click on initiate contact
    When  I should see following dropdown options for "contact reason" field displayed
      | Materials Request |
    And  I choose "Sent Program Materials" option for Contact Action field
    And I click on save Reasons Options button
    And I click on the Additional Comments
    And I Enter "Valid Additional Comment Text" as additional Comments
    And I should be able to save the additional comments
    When I search for an existing contact by first name "Ethan"
    And I click on Search Button
    And I link the contact to an existing Case or Consumer Profile
    Then I should see Linked Contact in the Header

    When I click on "Demographic Info" Tab on Contact Dashboard Page
    And I verify values for Reason Action and Additional Comments are present
    And I click on "Case & Contact Details" Tab on Contact Dashboard Page
    And I verify values for Reason Action and Additional Comments are present
    When I click on "Task & Service Request" Tab on Contact Dashboard Page
    And I verify values for Reason Action and Additional Comments are present
    When I click on "Program & Benefit Info" Tab on Contact Dashboard Page
    Then  I verify values for Reason Action and Additional Comments are present
