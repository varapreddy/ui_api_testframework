Feature: Viewing Address Information on Demographics Page

  @CRM-758 @CRM-758-01 @muhabbat
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
    And I click on Add new address button on Contact Info Tab Page
    When I add a new active "Physical" address to a consumer profile
    Then I can view full address column has Address Line one, Address Line two, State, Zip in one line displayed
    And I see "County" column has expected value displayed
    And I see "Type" column has expected value displayed
    And I see "Source" column has expected value displayed
    Then I see "Status" column has expected value displayed