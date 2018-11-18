Feature: View Contact Record History

  @CRM-225 @CRM-225-01 @muhabbat @crm @crm-regression @regression
  Scenario: Verify the columns on Contact History Page
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    Then I see new consumer is created and has a unique Consumer ID
    When I click on "Case & Contact Details" Tab on Contact Dashboard Page
    Then I verify all the columns on Contact History table are present

  @CRM-225 @CRM-225-02 @muhabbat @crm @crm-regression @regression
  Scenario: Verify Reason/Action displays the first captured reason/action, when there are multiple entries
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    Then I see new consumer is created and has a unique Consumer ID
    When  I should see following dropdown options for "contact reason" field displayed
      | Information Request |
    And  I choose "Provided Financial Information" option for Contact Action field
    And I click on the save contact reason button
    When  I should see following dropdown options for "contact reason" field displayed
      | Update Information Request |
    And  I choose "Updated Demographic Information" option for Contact Action field
    And I click on the save contact reason button
    When  I should see following dropdown options for "contact type" field displayed
      | Outbound |
    When  I should see following dropdown options for "contact channel" field displayed
      | SMS Text |
    When  I should see following dropdown options for "consumer type" field displayed
      | Member |
    And I close the current Contact Record and re-initiate a new Contact Record
    When I enter Search criteria fields for a new consumer
    And I click on Search Button
    And I link the contact to an existing Case or Consumer Profile
    And I should see Linked Contact in the Header
    When I click on "Case & Contact Details" Tab on Contact Dashboard Page
    And I verify Contact History has one record with "Information Request" reason and "Provided Financial Information" action


 # @CRM-225 @CRM-225-03 @muhabbat todo check Outbound contact captured bug
  Scenario: Initial Contact History Display
    Given I logged into CRM and click on initiate contact
    When  I should see following dropdown options for "contact reason" field displayed
      | Update Information Request |
    And  I choose "Updated Demographic Information" option for Contact Action field
    And I click on the save contact reason button
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    And I see new consumer is created and has a unique Consumer ID
    When  I should see following dropdown options for "contact type" field displayed
      | Outbound |
    When  I should see following dropdown options for "contact channel" field displayed
      | SMS Text |
    When  I should see following dropdown options for "consumer type" field displayed
      | Member |
    And I close the current Contact Record and re-initiate a new Contact Record
    When I verify a new contact re-initiated
    When  I should see following dropdown options for "contact reason" field displayed
      | Materials Request |
    And  I choose "Re-Sent Notice" option for Contact Action field
    And I click on the save contact reason button
    When I enter Search criteria fields for a new consumer
    And I click on Search Button
    And I link the contact to an existing Case or Consumer Profile
    And I should see Linked Contact in the Header
    When  I should see following dropdown options for "contact type" field displayed
      | Outbound |
    When  I should see following dropdown options for "contact channel" field displayed
      | Web Chat |
    When  I should see following dropdown options for "consumer type" field displayed
      | Member |
    And I close the current Contact Record and re-initiate a new Contact Record
    When I enter Search criteria fields for a new consumer
    And I click on Search Button
    And I link the contact to an existing Case or Consumer Profile
    And I should see Linked Contact in the Header
    When I click on "Case & Contact Details" Tab on Contact Dashboard Page
    And I verify latest Contact Record appears on Contact History table
    Then I verify previous Contact Record appears on Contact History table after latest record


  @CRM-225 @CRM-225-04 @muhabbat @crm @crm-regression @regression
  Scenario: Sort Contact history Display
    Given I logged into CRM and click on initiate contact
    When  I should see following dropdown options for "contact reason" field displayed
      | Materials Request |
    And  I choose "Re-Sent Notice" option for Contact Action field
    And I click on the save contact reason button
    When  I should see following dropdown options for "contact type" field displayed
      | Outbound |
    When  I should see following dropdown options for "contact channel" field displayed
      | Web Chat |
    When  I should see following dropdown options for "consumer type" field displayed
      | Member |
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    And I see new consumer is created and has a unique Consumer ID
    And I close the current Contact Record and re-initiate a new Contact Record
    When I enter Search criteria fields for a new consumer
    And I click on Search Button
    And I link the contact to an existing Case or Consumer Profile
    And I should see Linked Contact in the Header
    When I click on "Case & Contact Details" Tab on Contact Dashboard Page
    And I click on "Contact ID" column and verify it is in descending or ascending order
    And I click on "Date" column and verify it is in descending or ascending order
    And I click on "Consumer" column and verify it is in descending or ascending order
    And I click on "Contact Type" column and verify it is in descending or ascending order
    And I click on "Channel" column and verify it is in descending or ascending order
    And I click on "Reason" column and verify it is in descending or ascending order
    And I click on "Action" column and verify it is in descending or ascending order


  @CRM-225 @CRM-225-05 @muhabbat
  Scenario: View specified number of records at a time
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button on Search Consumer Page
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    And I see new consumer is created and has a unique Consumer ID
    When I click on "Case & Contact Details" Tab on Contact Dashboard Page
    And I see no more then 5 Contact history records are displayed
    When  I should see following dropdown options for "show items per page" field displayed
      | Show 10 |
    And I see no more then 10 Contact history records are displayed
    When  I should see following dropdown options for "show items per page" field displayed
      | Show 20 |
    And I see no more then 20 Contact history records are displayed
    When  I should see following dropdown options for "show items per page" field displayed
      | Show 5 |
    Then I see no more then 5 Contact history records are displayed