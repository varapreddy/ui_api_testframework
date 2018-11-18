

  Feature:Verify by Linking a Consumer
@CRM-232 @CRM-232-01 @regression @shilpa @crm @crm-regression
Scenario: Validate by Linking the Consumer
  Given I logged into CRM and click on initiate contact
  When I search for an existing contact by first name "Test First"
  When I search for an existing contact by last name "Test Last"
  And I click on Search Button
  Then I see all search results according to the first name "Test First"
  And I click on the "Test First"  record link button
  Then I should see Linked Contact in the Header

    @CRM-232 @CRM-232-02 @regression @shilpa @crm @crm-regression
  Scenario: Validate by Linking the Consumer
    Given I logged into CRM and click on initiate contact
    When I search for an existing contact by first name "Test First"
    When I search for an existing contact by last name "Test Last"
    And I click on Search Button
    Then I see all search results according to the first name "Test First"
    And I click on the "Test First"  record link button
    Then I should see Linked Contact in the Header
    And I click on the Case Contact Details Tab
    Then I should see Contact Id Present

    @CRM-232 @CRM-232-03  @regression @shilpa @crm @crm-regression
    Scenario: Validate by Linking the Consumer
      Given I logged into CRM and click on initiate contact
      When I search for an existing contact by first name "Test First"
      When I search for an existing contact by last name "Test Last"
      And I click on Search Button
      Then I see all search results according to the first name "Test First"
      And I click on the "Test First"  record link button
      Then I should see Linked Contact in the Header
      And I should see the first name and Last name "Test First T Test Last"



