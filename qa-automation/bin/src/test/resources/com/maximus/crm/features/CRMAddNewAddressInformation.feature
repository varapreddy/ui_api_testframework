Feature:Validate by Adding a New Address Information in the Demographic Page

  @CRM-757  @CRM-757-01 @shilpa #@smoke #@crm
  Scenario: Verify by providing a valid Address can be added
    Given I logged into CRM and click on initiate contact
    When I search for an existing contact by first name "Test First"
    When I search for an existing contact by last name "Test Last"
    And I click on Search Button
    Then I see all search results according to the first name "Test First"
    And I click on the "Test First"  record link button
    And I should see the Demographic Tab
    Then I click on the Demographic Info Tab
    And I should see contact Info Tab
    Then I click on the Contact Info Tab
    And I should see the Address label should be displayed
    And I should see the Add button displayed
    Then I click on the Add button
    Then I verify that I am in the Address Page
    And I verify by entering valid data in the address field1 "Test Address1"
    And I verify by entering valid data in the address field 2 "Test Address2"
    And I verify by entering valid data in the City field "AL"
    And I verify by selecting a valid state
    And I verify by entering valid data in the zip code "675675675"
    And I verify by selecting a valid email address type
    Then I verify by providing a valid Current Date
    And I click on Save button
    Then I should be navigated to the Demographic Page

  @CRM-757 @CRM-757-02 @shilpa @crm @regression @crm-regression
    Scenario:Validate the Address Line one  should not accept more than 50 characters
    Given I logged into CRM and click on initiate contact
    When I search for an existing contact by first name "Test First"
    When I search for an existing contact by last name "Test Last"
    And I click on Search Button
    Then I see all search results according to the first name "Test First"
    And I click on the "Test First"  record link button
    And I should see the Demographic Tab
    Then I click on the Demographic Info Tab
    And I should see contact Info Tab
    Then I click on the Contact Info Tab
    And I should see the Address label should be displayed
    And I should see the Add button displayed
    Then I click on the Add button
    Then I verify that I am in the Address Page
    And I verify by entering invalid length in the address field1 "Like any other social media site Facebook has length."
    Then The Address Field one should not accept more than 50 characters


@CRM-757 @CRM-757-03 @shilpa @crm @regression @crm-regression
  Scenario:Validate the Address Line one  should not accept more than 50 characters
    Given I logged into CRM and click on initiate contact
    When I search for an existing contact by first name "Test First"
    When I search for an existing contact by last name "Test Last"
    And I click on Search Button
    Then I see all search results according to the first name "Test First"
    And I click on the "Test First"  record link button
    And I should see the Demographic Tab
    Then I click on the Demographic Info Tab
    And I should see contact Info Tab
    Then I click on the Contact Info Tab
    And I should see the Address label should be displayed
    And I should see the Add button displayed
    Then I click on the Add button
    Then I verify that I am in the Address Page
    And I verify by entering invalid length in the address field2 "Like any other social media site Facebook has length."
    Then The Address field two should not accept more than 50 characters

    @CRM-757 @CRM-757-04 @shilpa @crm @regression @crm-regression
    Scenario:Validate the City field should not accept more than thirty characters
      Given I logged into CRM and click on initiate contact
      When I search for an existing contact by first name "Test First"
      When I search for an existing contact by last name "Test Last"
      And I click on Search Button
      Then I see all search results according to the first name "Test First"
      And I click on the "Test First"  record link button
      And I should see the Demographic Tab
      Then I click on the Demographic Info Tab
      And I should see contact Info Tab
      Then I click on the Contact Info Tab
      And I should see the Address label should be displayed
      And I should see the Add button displayed
      Then I click on the Add button
      Then I verify that I am in the Address Page
      And I provide more than thirty characters in the City field "CitySocilaDataTestOneTwoThreeTestone"
      Then The City field should not accept more than 30 characters

  @CRM-757 @CRM-757-05 @shilpa #@crm
    Scenario:Validate the county field should accept more than 30 characters
    Given I logged into CRM and click on initiate contact
    When I search for an existing contact by first name "Test First"
    When I search for an existing contact by last name "Test Last"
    And I click on Search Button
    Then I see all search results according to the first name "Test First"
    And I click on the "Test First"  record link button
    And I should see the Demographic Tab
    Then I click on the Demographic Info Tab
    And I should see contact Info Tab
    Then I click on the Contact Info Tab
    And I should see the Address label should be displayed
    And I should see the Add button displayed
    Then I click on the Add button
    Then I verify that I am in the Address Page
    And I provide more than thirty characters in the county field
    Then The county field should not accept more than 30 characters
    

































