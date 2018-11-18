# new feature
# Tags: optional
Feature: Close the Contact Record

   @CRM-304 @CRM-304-01  @regression @shilpa @crm @crm-regression
    Scenario: Validate the Close button by not providing any values in the Contact Record
      Given I logged into CRM and click on initiate contact
     When I click on the close button on the Header
      Then The Timer should be stopped
      And I click on the Close button in the bottom
      Then I should see Contact Dispotions should be present

  @CRM-304 @CRM-304-02  @regression @shilpa @crm @crm-regression @smoke
        Scenario:Validate the Close button by providing all the options
        Given I logged into CRM and click on initiate contact
        When  I should see following dropdown options for "contact reason" field displayed
          | Materials Request |
        Then  I should see following dropdown options for "contact action" field displayed
          | Sent Program Materials |
          | Re-Sent Notice         |
        And I Enter valid data
        And I click on the save button
        When I search for an existing contact by first name "Test First"
        When I search for an existing contact by last name "Test Last"
        And I click on Search Button
        Then I see all search results according to the first name "Test First"
        And I click on the "Test First"  record link button
        Then I click on the Consumer Type "Authorized representative"
         And I click on the Contact Type "Inbound"
          And I click on the Inbound Call queue "Eligibility"
         And I click on the Contact Channel Type "Phone"
          And I click on the Preffered lanaguage "English"
          And I click on Consumer Authenticated
          Then I click on the contact dispotions "OutboundIncomplete"
        And I scroll the Page to Reasons and comments
        When I click on the close button on the Header
        And I scroll the Page to the Bottom
        And I click on the Close button in the bottom
        Then I should see the Hamburger Menu Displayed

  @CRM-304 @CRM-304-03  @regression @shilpa @crm @crm-regression
        Scenario:Validate the Contact Duration after completing the Contact Record
        Given I logged into CRM and click on initiate contact
        When  I should see following dropdown options for "contact reason" field displayed
          | Materials Request |
        Then  I should see following dropdown options for "contact action" field displayed
          | Sent Program Materials |
          | Re-Sent Notice         |
        And I Enter valid data
        And I click on the save button
        When I search for an existing contact by first name "Test First"
        When I search for an existing contact by last name "Test Last"
        And I click on Search Button
        Then I see all search results according to the first name "Test First"
        And I click on the "Test First"  record link button
        Then I click on the Consumer Type "Authorized representative"
        And I click on the Contact Type "Inbound"
        And I click on the Inbound Call queue "Eligibility"
        And I click on the Contact Channel Type "Phone"
        And I click on the Preffered lanaguage "English"
        And I click on Consumer Authenticated
        Then I click on the contact dispotions "OutboundIncomplete"
        And I scroll the Page to Reasons and comments
        When I click on the close button on the Header
        Then The Timer should be stopped

  @CRM-653 @CRM-653-08 @regression @vinuta @crm @crm-regression
  Scenario:Validate the Close button for Outbound contact by providing all the options
    Given I logged into CRM and click on initiate contact
    When  I should see following dropdown options for "contact reason" field displayed
      | Materials Request |
    Then  I should see following dropdown options for "contact action" field displayed
      | Sent Program Materials |
    And I Enter valid data
    And I click on the save button
    When I search for an existing contact by first name "Test First"
    When I search for an existing contact by last name "Test Last"
    And I click on Search Button
    And I click on the "Test First"  record link button
    Then I click on the Consumer Type "Authorized representative"
    And I click on "Outbound" type of call option in "Contact Type" dropdown
    And I should see following dropdown options for "outcome of contact" field displayed
      |Did Not Reach/Left Voicemail|
    Then I should see following dropdown options for "contact disposition" field displayed
      |Outbound Incomplete|
    And I click on the Contact Channel Type "Phone"
    And I click on the Preffered lanaguage "English"
    And I click on Consumer Authenticated
    And I scroll the Page to Reasons and comments
    When I click on the close button on the Header
    And I scroll the Page to the Bottom
    And I click on the Close button in the bottom
    Then I should see the Hamburger Menu Displayed