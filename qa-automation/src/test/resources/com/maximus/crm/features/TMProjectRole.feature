Feature: Project Role in Tenant Manager

  @CRM-751 @CRM-751-01 @regression @tm @vinuta @tm_regression
  Scenario: Validate project role fields are displayed as expected
    Given I navigate to project role details page
    Then I see project name, project ID, role name, role description, start date, end date fields displayed

  @CRM-751 @CRM-751-02 @regression @tm @vinuta @tm_regression
  Scenario:Verify error displayed when saved without entering any data
    Given I navigate to project role details page
    When I click on Save button on project role page
    Then I see mandatory fields have error messages
    |Role Name      |
    |Role Start Date|

  #@CRM-751 @CRM-751-03-a @regression @tm @vinuta @tm_regression #execute after CRM-751 is done
  Scenario Outline: Verify role is created when start date = end date
    Given I navigate to project role details page
    When I populate data on project role page "<roleName>","<roleDesc>","<startDate>","<endDate>"
    And I click on Save button on project role page
    Then I see pop-up that role is created
    Examples:
          |roleName|roleDesc|startDate|endDate|
          |{random}|{random}|today    |today  |

  #@CRM-751 @CRM-751-03-b @regression @tm @vinuta @tm_regression #execute after CRM-751 is done
  Scenario Outline: Verify error displayed when start date > end date
    Given I navigate to project role details page
    When I populate data on project role page "<roleName>","<roleDesc>","<startDate>","<endDate>"
    And I click on Save button on project role page
    Then I see "End date should not be less or equal than start date" message under the "roleEndDate" field
    Examples:
         |roleName|roleDesc|startDate|endDate|
         |{random}|{random}|today    |-1     |

  #@CRM-751 @CRM-751-03-c @regression @tm @vinuta @tm_regression  #existing bug
  Scenario Outline: Verify error displayed when start date < current date
    Given I navigate to project role details page
    When I populate data on project role page "<roleName>","<roleDesc>","<startDate>","<endDate>"
    And I click on Save button on project role page
    Then I see "The Start Date cannot be in the past" message under the "roleStartDate" field
    Examples:
      |roleName|roleDesc|startDate|endDate|
      |{random}|{random}|-1       |       |

  #@CRM-751 @CRM-751-03-d @CRM-751-07 @regression @tm @vinuta @tm_regression #execute after CRM-751 is done
  Scenario Outline: Verify role is created when start date = end date
    Given I navigate to project role details page
    When I populate data on project role page "<roleName>","<roleDesc>","<startDate>","<endDate>"
    And I click on Save button on project role page
    Then I see pop-up that role is created
    Examples:
      |roleName|roleDesc|startDate|endDate|
      |{random}|{random}|+1       |       |

  #@CRM-751 @CRM-751-03-e @regression @tm @vinuta @tm_regression #execute after CRM-751 is done
  Scenario Outline: Verify error displayed when start date,end date are invalid
    Given I navigate to project role details page
    When I populate data on project role page "<roleName>","<roleDesc>","<startDate>","<endDate>"
    And I click on Save button on project role page
    Then I see "Invalid date format" message under the "roleStartDate" field
    And I see "Invalid date format" message under the "roleEndDate" field
    Examples:
      |roleName|roleDesc|startDate|endDate|
      |{random}|{random}|09/02    |09/02  |

  #@CRM-751 @CRM-751-03-f @regression @tm @vinuta @tm_regression #execute after CRM-722 is done
  Scenario Outline: Verify error displayed when start date,end date are non-existing dates
    Given I navigate to project role details page
    When I populate data on project role page "<roleName>","<roleDesc>","<startDate>","<endDate>"
    And I click on Save button on project role page
    Then I see "The date entered does not exist. Please enter a valid date." message under the "roleStartDate" field
    And I see "The date entered does not exist. Please enter a valid date." message under the "roleEndDate" field
    Examples:
      |roleName|roleDesc|startDate |endDate     |
      |{random}|{random}|02/32/2019|13/13/3000  |

  #@CRM-751 @CRM-751-04-a @regression @tm @vinuta @tm_regression #execute after CRM-751 is done
  Scenario Outline: Verify error is displayed when duplicate role is created with same dates
    Given I navigate to project role details page
    When I populate data on project role page "<roleName>","<roleDesc>","<startDate>","<endDate>"
    And I click on Save button on project role page
    Then I see pop-up that role is created

    And I click on add role button on role list page

    When I populate data on project role page "<roleName>","<roleDesc>","<startDate>","<endDate>"
    And I click on Save button on project role page
    Then I see error that role already exists
    Examples:
      |roleName     |roleDesc                      |startDate|endDate|
      |DuplicateRole|This is to test duplicate role|+1       |       |

  #@CRM-751 @CRM-751-04-b @regression @tm @vinuta @tm_regression #execute after CRM-751 is done
  Scenario Outline: Verify error is displayed when duplicate role is created with new start date between the time segment
    Given I navigate to project role details page
    When I populate data on project role page "<roleName>","<roleDesc>","<startDate1>","<endDate1>"
    And I click on Save button on project role page
    Then I see pop-up that role is created

    And I click on add role button on role list page

    When I populate data on project role page "<roleName>","<roleDesc>","<startDate2>","<endDate2>"
    And I click on Save button on project role page
    Then I see error that role already exists
    Examples:
      |roleName        |roleDesc                      |startDate1|endDate1|startDate2|endDate2|
      |NewDuplicateRole|This is to test duplicate role|today     |    +2  |  +1      |        |

  #@CRM-751 @CRM-751-04-c @regression @tm @vinuta @tm_regression #execute after CRM-751 is done
  Scenario Outline: Verify error is displayed when duplicate role is created with new start date after first role end date
    Given I navigate to project role details page
    When I populate data on project role page "<roleName>","<roleDesc>","<startDate1>","<endDate1>"
    And I click on Save button on project role page
    Then I see pop-up that role is created

    And I click on add role button on role list page

    When I populate data on project role page "<roleName>","<roleDesc>","<startDate2>","<endDate2>"
    And I click on Save button on project role page
    Then I see pop-up that role is created
    Examples:
      |roleName             |roleDesc                      |startDate1|endDate1|startDate2|endDate2|
      |DuplicateRoleReturns |This is to test duplicate role|today     |    +1  |  +2      |        |

  @new @CRM-751 @CRM-751-05 @regression @tm @vinuta @tm_regression
  Scenario: Cancel button functionality on Add Role page when some data is entered
    Given I navigate to project role details page
    When I populate data on project role page "Test","Testing cancel button","today","today"
    And I click on Cancel button on Add Role Page
    Then I see "All changes will be lost" alert displayed
    And I click on No and I am navigated back to Add Role page and see all previously entered unsaved data

    When I click on Cancel button on Add Role Page
    Then I see "All changes will be lost" alert displayed
    And I click on Yes and I am navigated back to Role List Page

  @new @CRM-751 @CRM-751-06 @regression @tm @vinuta @tm_regression
  Scenario: Cancel button functionality on Add Role page when some data is entered
    Given I navigate to project role details page
    When I click on Cancel button on Add Role Page
    Then I navigate to project role list page