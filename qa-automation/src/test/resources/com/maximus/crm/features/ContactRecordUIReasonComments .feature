Feature: Contact Record UI Reasons and Comments

  @CRM-636 @CRM-636-01 @crm @shilpa
  Scenario:Validate the Reasons and Comments Section
    Given I logged into CRM and click on initiate contact
    And I click on Reasons dropdown
    Then I should see all the attributes present in the Reasons Section


  @CRM-636 @CRM-636-02 @crm @shilpa
 Scenario: Validate the Reasons and Comments Section by providing valid comments
    Given I logged into CRM and click on initiate contact
    When  I should see following dropdown options for "contact reason" field displayed
      | Materials Request |
    Then  I should see following dropdown options for "contact action" field displayed
      | Sent Program Materials |
      | Re-Sent Notice         |
   And I Enter valid data
   And I click on the save button

  @CRM-636 @CRM-636-03 @crm @shilpa @crm-regression
  Scenario: Validate the Reasons and Comments Section by providing valid comments and click on cancel button
    Given I logged into CRM and click on initiate contact
    When  I should see following dropdown options for "contact reason" field displayed
      | Materials Request |
    Then  I should see following dropdown options for "contact action" field displayed
      | Sent Program Materials |
      | Re-Sent Notice         |
    And I Enter valid data and click on the cancel button
    Then I should see a new  window with Information Lost message

    @CRM-636 @CRM-636-04 @crm @shilpa @crm-regression
    Scenario: Validate the Reasons and Comments Time Stamp should be displayed
      Given I logged into CRM and click on initiate contact
      When  I should see following dropdown options for "contact reason" field displayed
        | Materials Request |
      Then  I should see following dropdown options for "contact action" field displayed
        | Sent Program Materials |
        | Re-Sent Notice         |
      And I Enter valid data and click on the save button
      Then I should see the Time stamp should be displayed with the comments

      @CRM-636 @CRM-636-05 @crm @shilpa
      Scenario:Validate the Additional Comments a
        Given I logged into CRM and click on initiate contact
        And I click on the Additional Comments
        And I Enter Valid  additional Comments
        Then I should be able to save the additional comments

  @CRM-636 @CRM-636-06 @crm @shilpa
  Scenario:Validate the Additional Comments and Time Stamp and Comments should be displayed
    Given I logged into CRM and click on initiate contact
    And I click on the Additional Comments
    And I Enter Valid  additional Comments
    Then I should be able to save the additional comments
    Then I should see the Time stamp should be displayed with the comments

  @CRM-636 @CRM-636-07 @crm @shilpa
  Scenario: Validate the Reasons and Comments Section by providing valid comments and click on cancel button
    Given I logged into CRM and click on initiate contact
    When  I should see following dropdown options for "contact reason" field displayed
      | Materials Request |
    Then  I should see following dropdown options for "contact action" field displayed
      | Sent Program Materials |
      | Re-Sent Notice         |
    And I Enter valid data and click on the cancel button
    Then I should see a new  window with Information Lost message
    And I click on Continue button
    Then I should see empty field in comments


  @CRM-636 @CRM-636-08 @crm @shilpa
  Scenario: Validate the Reasons and Comments Section by providing valid comments and click on cancel button
    Given I logged into CRM and click on initiate contact
    When  I should see following dropdown options for "contact reason" field displayed
      | Materials Request |
    Then  I should see following dropdown options for "contact action" field displayed
      | Sent Program Materials |
      | Re-Sent Notice         |
    And I Enter valid data and click on the cancel button
    Then I should see a new  window with Information Lost message
    And I click on Cancel button
    Then I should see the text present in comments

  @CRM-636 @CRM-636-09 @crm @shilpa @smoke
  Scenario:Validate the Additional Comments and Time Stamp and Comments should be displayed
    Given I logged into CRM and click on initiate contact
    And I click on the Additional Comments
    And I Enter Valid  additional Comments
    Then I should be able to save the additional comments
    Then I should see the Time stamp should be displayed with the additional  comments
    When I expand the button of the saved comments
    Then I should see the Additional comments displayed


  @CRM-636 @CRM-636-10 @crm @shilpa @smoke
  Scenario: Validate the Reasons and Comments Time Stamp should be displayed
    Given I logged into CRM and click on initiate contact
    When  I should see following dropdown options for "contact reason" field displayed
      | Materials Request |
    Then  I should see following dropdown options for "contact action" field displayed
      | Sent Program Materials |
      | Re-Sent Notice         |
    And I Enter valid data
    And I click on the save button
    When I expand the button of the saved comments
    Then I should see the comments displayed




















