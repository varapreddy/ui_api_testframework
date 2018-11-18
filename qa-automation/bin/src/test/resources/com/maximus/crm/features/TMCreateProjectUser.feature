Feature: Create new user account for a project

  @CRM-635 @CRM-635-01  @regression @tm @muhabbat @tm_regression
  Scenario:Validate fields, default values on User Details page
    Given I logged into Tenant Manager Project list page
    When I expend a Project to view the details
    And I click on User List Menu
    And I click on add new user button on add new user page
    When I should be navigated to User Details page
    And I should see all the elements displayed in the User Details Page
    Then I should see default values populated

  @CRM-635 @CRM-635-02 @regression @tm @muhabbat @tm_regression
  Scenario: Validating mandatory fields on User Details page
    Given I logged into Tenant Manager Project list page
    When I expend a Project to view the details
    And I check Account Manager and Approver are added
    And I click on User List Menu
    And I click on add new user button on add new user page
    When I should be navigated to User Details page
    And I click on save a user button
    Then I see mandatory fields have error messages
      | First Name                       |
      | Last Name                        |
      | Internal Maximus Employee? - Y/N |
      | Email Address                    |
      | Start Date                       |
      | Account Type                     |

  @CRM-635 @CRM-635-03 @muhabbat #@tm #@tm_muted
  Scenario: Validating mandatory fields on User Details page
    Given I logged into Tenant Manager Project list page
    When I expend a Project to view the details
   And I check Account Manager and Approver are added
    And I click on User List Menu
    And I click on add new user button on add new user page
    When I should be navigated to User Details page
    And I set maximus employee to yes
    And I enter max id "200341"
    And I click on add max id button
    When I see fields auto-populated with the corresponding values from Active Directory
    Then I see Auto-populated fields are not editable

  #By Vinuta, If the Active Directory does not identify the MAXID
  @CRM-635 @vinuta @CRM-635-04  @regression @tm @tm_regression
  Scenario Outline: If the Active Directory does not identify the MAXID, display error
    Given I logged into Tenant Manager Project list page
    When I expend a Project to view the details
    And I click on User List Menu
    And I click on add new user button on add new user page
    And I set maximus employee to yes
    And I enter max id "<MaxID>"
    And I click on add max id button
    Then I receive an error that max ID does not exist
    Examples:
      | MaxID |
      | 123   |

  #By Vinuta, Creating user when approvers are not added to the project
  @CRM-635 @vinuta @CRM-635-05 @tm @tm_muted @smoke
  Scenario: Creating user when approvers are not added to the project
    Given I logged into Tenant Manager Project list page
    When I click on Create a New Project
    When I create,save and view new project details
    And I click on User List Menu
    And I click on add new user button on add new user page
    Then I should see a pop-up as an overlay on User Details page
    And I click on Continue button on User Details error pop-up
    Then I am navigated to Project Details page

  @CRM-635 @CRM-635-06 @regression @tm @muhabbat @tm_regression
  Scenario: Cancel button functionality on Add User page when some data is entered
    Given I logged into Tenant Manager Project list page
    When I expend a Project to view the details
    And I click on User List Menu
    And I click on add new user button on add new user page
    And I should be navigated to User Details page
    And I populate some data in the fields
    And I click on Cancel button on Add User Page
    And I see "All changes will be lost" alert displayed
    When I click on No and I am navigated back to Add User page and see all previously entered unsaved data
    And I click on Cancel button on Add User Page
    And I see "All changes will be lost" alert displayed
    Then I click on Yes and I am navigated back to User List Page

  @CRM-635 @CRM-635-07 @regression @tm @muhabbat @tm_regression
  Scenario: Cancel button functionality on Add User page when some data is entered
    Given I logged into Tenant Manager Project list page
    When I expend a Project to view the details
    And I click on User List Menu
    And I click on add new user button on add new user page
    And I should be navigated to User Details page
    And I click on Cancel button on Add User Page
    Then I am navigated back to User List Page

  @CRM-635-08 @CRM-635 @tm @vinuta @tm_muted @smoke
  Scenario Outline: User is created successfully with Active status & Verify duplicate active user cannot be created
    Given I logged into Tenant Manager Project list page
    When I expend a Project to view the details
    And I click on User List Menu
    And I click on add new user button on add new user page
    And I create User with given data <isMaxEmp>,<MaxID>,<FN>,<MN>,<LN>,<Email>,<Start Date>,<End Date>,<Acct Type>,<Acct Auth>,<Auth Date>,<Override Auth>,<Override Auth Reason>,<PHI Access>,<PHI Reason>,<PII Access>,<PII Reason>,<Status>
    And I click on save a user button
    Then I should see a pop-up that user is created successfully
    And I click on add new user button on add new user page
    And I create User with given data <isMaxEmp>,<MaxID>,<FN>,<MN>,<LN>,<Email>,<Start Date>,<End Date>,<Acct Type>,<Acct Auth>,<Auth Date>,<Override Auth>,<Override Auth Reason>,<PHI Access>,<PHI Reason>,<PII Access>,<PII Reason>,<Status>
    And I click on save a user button
    Then I should see error that user is already active
    And I click on Cancel button on Add User Page
    And I see "All changes will be lost" alert displayed
    Then I click on Yes and I am navigated back to User List Page
    And I enter search criteria for a user by "maxID" value <MaxID>
    And I click on Search Button on User List Page
    And I should see all users with "status" value <Status>
    Examples:
      | isMaxEmp | MaxID    | FN | MN | LN | Email | Start Date | End Date | Acct Type | Acct Auth | Auth Date | Override Auth | Override Auth Reason | PHI Access | PHI Reason | PII Access | PII Reason | Status   |
      | 'Yes'    | '154255' | '' | '' | '' | ''    | ''         | ''       | ''        | ''        | ''        | ''            | ''                   | ''         | ''         | ''         | ''         | 'Active' |

  #Require clarification on inactive user, blocked with CRM-637 Inactivate user automation
  @CRM-635-09 @CRM-635 @vinuta #@regression @tm  @tm_muted
  Scenario Outline: User is created successfully with Inactive status & verify we can create duplicate user
    Given I logged into Tenant Manager Project list page
    When I expend a Project to view the details
    And I click on User List Menu
    And I click on add new user button on add new user page
    And I create User with given data <isMaxEmp>,<MaxID>,<FN>,<MN>,<LN>,<Email>,<Start Date>,<End Date>,<Acct Type>,<Acct Auth>,<Auth Date>,<Override Auth>,<Override Auth Reason>,<PHI Access>,<PHI Reason>,<PII Access>,<PII Reason>,<Status>
    And I click on save a user button
    Then I should see a pop-up that user is created successfully
    And I click on add new user button on add new user page
    And I create User with given data <isMaxEmp>,<MaxID>,<FN>,<MN>,<LN>,<Email>,<Start Date>,<End Date>,<Acct Type>,<Acct Auth>,<Auth Date>,<Override Auth>,<Override Auth Reason>,<PHI Access>,<PHI Reason>,<PII Access>,<PII Reason>,<Status>
    And I click on save a user button
    Then I should see a pop-up that user is created successfully
    And I enter search criteria for a user by "maxID" value <MaxID>
    And I click on Search Button on User List Page
    And I should see all users with "status" value <Status>
    Examples:
      | isMaxEmp | MaxID    | FN | MN | LN | Email | Start Date | End Date | Acct Type | Acct Auth | Auth Date | Override Auth | Override Auth Reason | PHI Access | PHI Reason | PII Access | PII Reason | Status     |
      | 'Yes'    | '154262' | '' | '' | '' | ''    | ''         | ''       | ''        | ''        | ''        | ''            | ''                   | ''         | ''         | ''         | ''         | 'Inactive' |

  @CRM-635 @CRM-635-10 @muhabbat @tm @tm_regression @regression
  Scenario: Entered Dates Validation
    Given I logged into Tenant Manager Project list page
    When I expend a Project to view the details
    And I click on User List Menu
    And I click on add new user button on add new user page
    And I populate some data in the fields
    And I enter the Start Date prior to the date of creation
    And I see "The Start Date cannot be in the past" message under the "start" field
    When I enter the End Date "prior" to the Start Start
    And I click on save a user button
    And I see "The End Date must be greater than the Start Date" message under the "end" field
    When I enter the End Date "equal" to the Start Start
    And I click on save a user button
    Then I see "The End Date must be greater than the Start Date" message under the "end" field



