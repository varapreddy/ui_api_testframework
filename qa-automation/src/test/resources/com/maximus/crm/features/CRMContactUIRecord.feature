Feature: Contact Record UI

  @CRM-229 @CRM-229-01 @regression @muhabbat @crm @crm-regression
  Scenario: Auto captured information on Contact Record UI Page
    Given I logged into CRM and click on initiate contact
    When I verify Contact Start Date is captured
    And I verify Contact Start Time is captured
    And I verify Contact Duration has no value while in progress
    Then I verify Contact Duration Time is captured

  @CRM-229 @CRM-229-02 @muhabbat @crm #Can't be verified, implementation in process
  Scenario: Verification mandatory fields on Contact Record UI
    Given I logged into CRM and click on initiate contact
    When I do not enter values to the contact record mandatory fields
    And I click on save button
    Then I see notification to fill mandatory fields


  @CRM-229 @CRM-229-03 @muhabbat @crm #Can't be verified, implementation in process
  Scenario: Verification optional fields on Contact Record UI
    Given I logged into CRM and click on initiate contact
    When I enter values to the contact record mondatory fields
    And I do not enter value to the contact record non-mandatory field
    And I click on save button
    Then I see contact record saved

  @CRM-229 @CRM-229-04 @muhabbat @crm @smoke #refactored 09/23/18
  Scenario: Existing Case/Consumer linking
    Given I logged into CRM and click on initiate contact
    And I search for an existing contact by criteria
    Then I link the contact to an existing Case or Consumer Profile

  @CRM-229 @CRM-229-05 @muhabbat @crm @smoke #Can't be verified, implementation in process
  Scenario: New Case/Consumer linking
    Given I logged into CRM and click on initiate contact
    When I enter Search criteria fields for a new consumer
    And I click on Search Button
    And I am able to see Add Consumer button and I click on it to navigate to Create Consumer UI
    When I populate Create Consumer fields for a new consumer
    When I click on Create Consumer Button on Create Consumer Page
    And I am navigated to active contact page
    Then I see the Contact Record linked to the new Case/Consumer Profile

  @CRM-229  @CRM-229-06 @muhabbat @crm #CRM-229-04 has to be implemented Can't be verified, implementation in process
  Scenario: View Case/Consumer Unique ID After Linking
    Given I logged into CRM and click on initiate contact
    And I search for an existing contact by criteria
    Then I link the contact to an existing Case or Consumer Profile
    Then I view the Case/Consumer Unique ID to Contact Record


  @CRM-229 @CRM-229-07 @regression @muhabbat @crm @crm-regression
  Scenario: Verification of Consumer Name fields accept up to 15 characters of alphabet only
    Given I logged into CRM and click on initiate contact
    And I see "firstName" field accept only letters
      | 3!@#$%H%^&*()_+-=e[{]}]\|;:'"r<>?,./mione |
    Then I see "firstName" field accept only 15 characters
    And I see "lastName" field accept only letters
      | 3!@#$%He%^&*()_+-=e[{]}]\|;:'"r<>?,./mione |
    Then I see "lastName" field accept only 15 characters

  @CRM-229 @CRM-229-08 @regression @muhabbat @crm @crm-regression
  Scenario: Verification of values in Consumer Type field
    Given I logged into CRM and click on initiate contact
    When  I should see following dropdown options for "consumer type" field displayed
      | Authorized representative |
      | Member                    |
      | Non-Member                |
      | Primary Individual        |
      | Unverified Contact        |


  @CRM-229 @CRM-229-09 @regression @muhabbat @crm @crm-regression
  Scenario: Verification of values in Contact Type field
    Given I logged into CRM and click on initiate contact
    When  I should see following dropdown options for "contact type" field displayed
      | Inbound  |
      | Outbound |

  @CRM-229 @CRM-229-10 @regression @muhabbat @crm @crm-regression
  Scenario: Verification of values in Contact Channel field
    Given I logged into CRM and click on initiate contact
    When  I should see following dropdown options for "contact channel" field displayed
      | Email    |
      | Phone    |
      | SMS Text |
      | Web Chat |


  @CRM-229 @CRM-229-11 @regression @muhabbat @crm @crm-regression
  Scenario: Verification of values in Preferred Language field
    Given I logged into CRM and click on initiate contact
    When  I should see following dropdown options for "preferred language" field displayed
      | English |
      | Spanish |

  @CRM-229  @CRM-229-12 @regression @muhabbat @crm @crm-regression
  Scenario: Verification of values in Reason section
    Given I logged into CRM and click on initiate contact
    When  I should see following options for "reason" section displayed

  @CRM-229 @CRM-229-12-02 @regression @muhabbat @crm @crm-regression
  Scenario: Verification of values in Contact Reason field
    Given I logged into CRM and click on initiate contact
    And I click on "Inbound" type of call option in "Contact Type" dropdown
    When  I should see following dropdown options for "contact reason" field displayed
      | Other                       |
      | Information Request         |
      | Materials Request           |
      | Update Information Request  |

  @CRM-229 @CRM-229-14 @regression @muhabbat @crm @crm-regression
  Scenario: Checking Contact Action values when Contact Reason is Request for Information
    Given I logged into CRM and click on initiate contact
    When I should see following dropdown options for "contact reason" field displayed
      | Information Request |
    Then I should see following dropdown options for "contact action" field displayed
      | Provided Case Status/Information        |
      | Provided Eligibility Status/Information |
      | Provided Enrollment Status/Information  |
      | Provided Financial Information          |

  @CRM-229 @CRM-229-15 @regression @muhabbat @crm @crm-regression
  Scenario: Contact Action values when Contact Reason is Request to Update Information
    Given I logged into CRM and click on initiate contact
    When  I should see following dropdown options for "contact reason" field displayed
      | Update Information Request |
    Then  I should see following dropdown options for "contact action" field displayed
      | Updated Demographic Information |
      | Updated Eligibility Information |
      | Updated Enrollment Information  |


  @CRM-229 @CRM-229-16 @regression @muhabbat @crm @crm-regression
  Scenario: Contact Action values when Contact Reason is Request Materials
    Given I logged into CRM and click on initiate contact
    When  I should see following dropdown options for "contact reason" field displayed
      | Materials Request |
    Then  I should see following dropdown options for "contact action" field displayed
      | Sent Program Materials |
      | Re-Sent Notice         |

  @CRM-229 @CRM-229-16-2 @muhabbat @vinuta @CRM-653 @CRM-653-06
  Scenario: Contact Action values when Contact Reason is Missing Information Request
    Given I logged into CRM and click on initiate contact
    And I click on "Outbound" type of call option in "Contact Type" dropdown
    When  I should see following dropdown options for "contact reason" field displayed
      | Missing Information Request |
    Then  I should see following dropdown options for "contact action" field displayed
      | Requested and Updated Authorized Representative Information |
      | Requested and Updated Case Member Information               |
      | Requested and Updated Demographic Information               |

  @CRM-229 @CRM-229-17 @regression @muhabbat @crm @crm-regression
  Scenario: Verification of text filed when Contact Reason is Other
    Given I logged into CRM and click on initiate contact
    When  I should see following dropdown options for "contact reason" field displayed
      | Other |
    And I should see Contact Action Dropdown disabled
    Then I see "otherReason" field accept only 50 characters

  @CRM-229 @CRM-229-18 @regression @muhabbat @crm @crm-regression @smoke
  Scenario: Reset Button Functionality
    Given I logged into CRM and click on initiate contact
    When I populate Search criteria fields for a new consumer
    And I click on reset button
    Then I see Search criteria fields have no value

  @CRM-229 @CRM-229-19 @regression @muhabbat @crm @crm-regression
  Scenario: Cancel Button Functionality
    Given I logged into CRM and click on initiate contact
    When I enter Consumer details "Minnie" on contact record UI
    And I click on cancel button and see a message
    And I confirm cancellation on the message
    Then I should be navigated to dashboard

  @CRM-229  @CRM-229-20 @muhabbat @crm #blocked by CRM-645 Close Button is not implemented
  Scenario: Close Button Functionality
    Given I logged into CRM and click on initiate contact
    When I enter Consumer details "Minnie" on contact record UI
    And I click on close button
    And I confirm cancellation on the message
    And Contact record is saved and closed
    Then I should be navigated to dashboard
    
 
  #By Vinuta, Validate Error Message when no search criteria given
  @CRM-575-01 @regression @CRM-575 @crm @vinuta @crm-regression
  Scenario: Validate Error Message when no search criteria given
    Given I logged into CRM and click on initiate contact
    When I do not enter any search parameters
    Then I see notification to fill search criteria

  #By Vinuta, Validate field level errors
  @CRM-575-02 @regression @CRM-575 @crm @vinuta @crm-regression
  Scenario Outline: Validate search section for field-level validations on ssn field
    Given I logged into CRM and click on initiate contact
    When I enter First Name as <firstName>, Middle Initial as <middleName>, Last Name as <lastName>, SSN as <ssn>, Date Of Birth as <DOB>, Unique ID as <ID>  on Contact Record
    And I click on Search Button
    Then I see SSN field validation displayed
    Examples:
      | firstName | middleName | lastName | ssn   | DOB | ID |
      | ''        | ''         | ''       | '123' | ''  | '' |

  #By Vinuta, Validate field level errors
  @CRM-575-03 #@CRM-575 #@regression @crm @vinuta #implementation completed # blocked by bug# 682
  Scenario Outline: Validate search section for field-level validations on DB field
    Given I logged into CRM and click on initiate contact
    When I enter First Name as <firstName>, Middle Initial as <middleName>, Last Name as <lastName>, SSN as <ssn>, Date Of Birth as <DOB>, Unique ID as <ID>  on Contact Record
    And I click on Search Button
    Then I see DOB field validation displayed
    Examples:
      | firstName | middleName | lastName | ssn | DOB  | ID |
      | ''        | ''         | ''       | ''  | '13' | '' |

  #By Vinuta, Validate errors for reason & comments section
  @CRM-575-04 @regression @CRM-575 @crm @vinuta @crm-regression
  Scenario: Validate Contact Reason,Action, Comments error message
    Given I logged into CRM and click on initiate contact
    When I click on Reasons dropdown
    And I click on the save button
    And I click on the Additional Comments
    And I should be able to save the additional comments
    Then I should receive error to fill out required fields on reasons and comments

  @CRM-653-01-a @CRM-653 @CRM-1045-01-a @CRM-1045 @regression @vinuta @crm @crm-regression
  Scenario: Validate Call Campaign Reference values for Outbound Contact Type
    Given I logged into CRM and click on initiate contact
    When I click on "Outbound" type of call option in "Contact Type" dropdown
    Then I should see following dropdown options for "call campaign reference" field displayed
    |Enrollment Reminder|
    |Payment Reminder   |
    |Program Information|

  @CRM-653-01-b @CRM-653 @CRM-1045-01-b @CRM-1045 @regression @vinuta @crm @crm-regression
  Scenario: Validate Outcome of Contact values for Outbound Contact Type
    Given I logged into CRM and click on initiate contact
    When I click on "Outbound" type of call option in "Contact Type" dropdown
    Then I should see following dropdown options for "outcome of contact" field displayed
      |Did Not Reach/Left Voicemail|
      |Did Not Reach/No Voicemail  |
      |Invalid Phone Number        |
      |Reached Successfully        |

  @CRM-653-02 @CRM-653 @regression @vinuta @crm @crm-regression
  Scenario: Validate Outcome of Contact values for Outbound Contact Type
    Given I logged into CRM and click on initiate contact
    When I click on "Outbound" type of call option in "Contact Type" dropdown
    Then I should see following dropdown options for "contact disposition" field displayed
      |Complete           |
      |Dropped            |
      |Escalate           |
      |Outbound Incomplete|
      |Requested Call Back|
      |Transfer           |

  @CRM-653-05 @CRM-653 @vinuta
  Scenario: Validate Contact Reason values for Outbound Contact Type
    Given I logged into CRM and click on initiate contact
    When I click on "Outbound" type of call option in "Contact Type" dropdown
    Then I should see following dropdown options for "contact reason" field displayed
      | Information Request         |
      | Materials Request           |
      | Missing Information Request |
      | Other                       |
      | Update Information Request  |

  @CRM-635-03 @CRM-635 @CRM-1045-02-a @CRM-1045 @regression @vinuta @crm @crm-regression
  Scenario: Updates to outcome of contact values when contact disposition is Complete
    Given I logged into CRM and click on initiate contact
    When I click on "Outbound" type of call option in "Contact Type" dropdown
    And I should see following dropdown options for "contact disposition" field displayed
    |Complete|
    Then I should see following dropdown options for "outcome of contact" field displayed
    |Reached Successfully|

  @CRM-635-04 @CRM-635 @CRM-1045-02-b @CRM-1045 @regression @vinuta @crm @crm-regression
  Scenario: Updates to outcome of contact values when contact disposition is Outbound Incomplete
    Given I logged into CRM and click on initiate contact
    When I click on "Outbound" type of call option in "Contact Type" dropdown
    And I should see following dropdown options for "contact disposition" field displayed
      |Outbound Incomplete|
    Then I should see following dropdown options for "outcome of contact" field displayed
      |Did Not Reach/Left Voicemail|
      |Did Not Reach/No Voicemail  |
      |Invalid Phone Number        |

  @CRM-1045-02-c @CRM-1045 @regression @vinuta @crm @crm-regression
  Scenario: Updates to outcome of contact values when contact disposition is Dropped
    Given I logged into CRM and click on initiate contact
    When I click on "Outbound" type of call option in "Contact Type" dropdown
    And I should see following dropdown options for "contact disposition" field displayed
      |Dropped|
    Then I should see following dropdown options for "outcome of contact" field displayed
      |Did Not Reach/Left Voicemail|
      |Did Not Reach/No Voicemail  |
      |Invalid Phone Number        |
      |Reached Successfully        |

  @CRM-1045-02-d @CRM-1045 @regression @vinuta @crm @crm-regression
  Scenario: Updates to outcome of contact values when contact disposition is Escalate
    Given I logged into CRM and click on initiate contact
    When I click on "Outbound" type of call option in "Contact Type" dropdown
    And I should see following dropdown options for "contact disposition" field displayed
      |Escalate|
    Then I should see following dropdown options for "outcome of contact" field displayed
      |Did Not Reach/Left Voicemail|
      |Did Not Reach/No Voicemail  |
      |Invalid Phone Number        |
      |Reached Successfully        |

  @CRM-1045-02-e @CRM-1045 @regression @vinuta @crm @crm-regression
  Scenario: Updates to outcome of contact values when contact disposition is Requested Call Back
    Given I logged into CRM and click on initiate contact
    When I click on "Outbound" type of call option in "Contact Type" dropdown
    And I should see following dropdown options for "contact disposition" field displayed
      |Requested Call Back|
    Then I should see following dropdown options for "outcome of contact" field displayed
      |Did Not Reach/Left Voicemail|
      |Did Not Reach/No Voicemail  |
      |Invalid Phone Number        |
      |Reached Successfully        |

  @CRM-1045-02-f @CRM-1045 @regression @vinuta @crm @crm-regression
  Scenario: Updates to outcome of contact values when contact disposition is Transfer
    Given I logged into CRM and click on initiate contact
    When I click on "Outbound" type of call option in "Contact Type" dropdown
    And I should see following dropdown options for "contact disposition" field displayed
      |Transfer|
    Then I should see following dropdown options for "outcome of contact" field displayed
      |Did Not Reach/Left Voicemail|
      |Did Not Reach/No Voicemail  |
      |Invalid Phone Number        |
      |Reached Successfully        |

  @CRM-1045-03-a @CRM-1045 @regression @vinuta @crm @crm-regression
  Scenario: Updates to contact disposition values when outcome of contact is Reached Successfully
    Given I logged into CRM and click on initiate contact
    When I click on "Outbound" type of call option in "Contact Type" dropdown
    And I should see following dropdown options for "outcome of contact" field displayed
      |Reached Successfully|
    Then I should see following dropdown options for "contact disposition" field displayed
      |Complete           |
      |Dropped            |
      |Escalate           |
      |Requested Call Back|
      |Transfer           |

  @CRM-1045-03-b @CRM-1045 @regression @vinuta @crm @crm-regression
  Scenario: Updates to contact disposition values when outcome of contact is Did Not Reach/Left Voicemail
    Given I logged into CRM and click on initiate contact
    When I click on "Outbound" type of call option in "Contact Type" dropdown
    And I should see following dropdown options for "outcome of contact" field displayed
      |Did Not Reach/Left Voicemail|
    Then I should see following dropdown options for "contact disposition" field displayed
      |Outbound Incomplete|

  @CRM-1045-03-b @CRM-1045 @regression @vinuta @crm @crm-regression
  Scenario: Updates to contact disposition values when outcome of contact is Did Not Reach/No Voicemail
    Given I logged into CRM and click on initiate contact
    When I click on "Outbound" type of call option in "Contact Type" dropdown
    And I should see following dropdown options for "outcome of contact" field displayed
      |Did Not Reach/No Voicemail|
    Then I should see following dropdown options for "contact disposition" field displayed
      |Outbound Incomplete|

  @CRM-1045-03-c @CRM-1045 @regression @vinuta @crm @crm-regression
  Scenario: Updates to contact disposition values when outcome of contact is Did Not Reach/No Voicemail
    Given I logged into CRM and click on initiate contact
    When I click on "Outbound" type of call option in "Contact Type" dropdown
    And I should see following dropdown options for "outcome of contact" field displayed
      |Invalid Phone Number|
    Then I should see following dropdown options for "contact disposition" field displayed
      |Outbound Incomplete|