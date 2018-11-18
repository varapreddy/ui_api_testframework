Feature: Search User page in a project, including 'Add' User option, 'Remove' User option, ' Inactivate' User option

  @CRM-637-11 @CRM-637 @CRM-628 @tm @regression @vinuta @tm_regression
  Scenario Outline: Validate End date is set to current date & is not editable when inactivate immediately is selected
    Given I logged into Tenant Manager Project list page
    When I expend a Project to view the details
    Then I check Account Manager and Approver are added
    When I click on User List Menu
    And I click on add new user button
    Then I create User with given data <isMaxEmp>,<MaxID>,<FN>,<MN>,<LN>,<Email>,<Start Date>,<End Date>,<Acct Type>,<Acct Auth>,<Auth Date>,<Override Auth>,<Override Auth Reason>,<PHI Access>,<PHI Reason>,<PII Access>,<PII Reason>,<Status>
    When I click on save a user button
    Then I should see a pop-up that user is created successfully
    When I enter search criteria for a user by "maxID" value <MaxID>
    And I enter search criteria for a user by "status" value <Status>
    And I click on Search Button on User List Page
    Then I should see all users with "status" value <Status>
    When I click on first user to open User Details
    And I set Inactive Immediately to Yes
    Then I should see pop-up with message "The user account will be deactivated when you click the Save button"
    When I click on ok button to inactivate user
    Then I verify end date is set to current date & not editable
    Examples:
      | isMaxEmp | MaxID    | FN | MN | LN | Email | Start Date | End Date | Acct Type | Acct Auth | Auth Date | Override Auth | Override Auth Reason | PHI Access | PHI Reason | PII Access | PII Reason | Status   |
      | 'Yes'    | '154265' | '' | '' | '' | ''    | ''         | ''       | ''        | ''        | ''        | ''            | ''                   | ''         | ''         | ''         | ''         | 'Active' |

  @CRM-637-09 @CRM-637 @CRM-628 @tm @regression @vinuta @tm_regression
  Scenario Outline: Validate account inactivation reason dropdown values & inactivate user immediately
    Given I logged into Tenant Manager Project list page
    When I expend a Project to view the details
    Then I check Account Manager and Approver are added
    When I click on User List Menu
    And I click on add new user button
    Then I create User with given data <isMaxEmp>,<MaxID>,<FN>,<MN>,<LN>,<Email>,<Start Date>,<End Date>,<Acct Type>,<Acct Auth>,<Auth Date>,<Override Auth>,<Override Auth Reason>,<PHI Access>,<PHI Reason>,<PII Access>,<PII Reason>,<Status>
    When I click on save a user button
    Then I should see a pop-up that user is created successfully
    When I enter search criteria for a user by "maxID" value <MaxID>
    And I enter search criteria for a user by "status" value <Status>
    And I click on Search Button on User List Page
    Then I should see all users with "status" value <Status>
    And I click on first user to open User Details
    Then I verify values in account inactivation "reason" dropdown
      | Change of Job Functions |
      | Leave (Other)           |
      | Parental Leave          |
      | Resigned                |
      | Terminated              |
    And I set Inactive Immediately to Yes
    Then I should see pop-up with message "The user account will be deactivated when you click the Save button"
    And I click on ok button to inactivate user
    And I click on save a user button
    Then I should see message that user has been inactivated successfully
    And I click on ok button to inactivate user
    Then I enter search criteria for a user by "maxID" value <MaxID>
    And I enter search criteria for a user by "status" value "Inactive"
    And I click on Search Button on User List Page
    Then I should see all users with "maxID" value <MaxID>
    And I should see all users with "status" value "Inactive"

  Examples:
  | isMaxEmp | MaxID    | FN | MN | LN | Email | Start Date | End Date | Acct Type | Acct Auth | Auth Date | Override Auth | Override Auth Reason | PHI Access | PHI Reason | PII Access | PII Reason | Status   |
  | 'Yes'    | '124635' | '' | '' | '' | ''    | ''         | ''       | ''        | ''        | ''        | ''            | ''                   | ''         | ''         | ''         | ''         | 'Active' |

  @CRM-628 @CRM-634 @CRM-634-01 @CRM-634-02 @CRM-634-03 @tm @regression @vinuta @tm_regression
  Scenario Outline: Remove users from project
    Given I logged into Tenant Manager Project list page
    When I expend a Project to view the details
    Then I check Account Manager and Approver are added
    When I click on User List Menu
    And I click on add new user button
    Then I create User with given data <isMaxEmp>,<MaxID>,<FN>,<MN>,<LN>,<Email>,<Start Date>,<End Date>,<Acct Type>,<Acct Auth>,<Auth Date>,<Override Auth>,<Override Auth Reason>,<PHI Access>,<PHI Reason>,<PII Access>,<PII Reason>,<Status>
    When I click on save a user button
    Then I should see a pop-up that user is created successfully
    When I enter search criteria for a user by "maxID" value <MaxID>
    And I enter search criteria for a user by "status" value <Status>
    And I click on Search Button on User List Page
    Then I should see all users with "status" value <Status>
    And I click on first user to open User Details
    Then I verify values in account inactivation "reason" dropdown
      | Change of Job Functions |
      | Leave (Other)           |
      | Parental Leave          |
      | Resigned                |
      | Terminated              |
    And I set Inactive Immediately to Yes
    Then I should see pop-up with message "The user account will be deactivated when you click the Save button"
    And I click on ok button to inactivate user
    And I click on save a user button
    Then I should see message that user has been inactivated successfully
    And I click on ok button to inactivate user
    Then I enter search criteria for a user by "maxID" value <MaxID>
    And I enter search criteria for a user by "status" value "Inactive"
    And I click on Search Button on User List Page
    Then I should see all users with "maxID" value <MaxID>
    And I should see all users with "status" value "Inactive"
    When I click on select all checkbox
    Then I can select only Inactive users but not active users
    And  I click on Remove User button
    Then I should not see user with "maxID" <MaxID> on User List page
    Examples:
      | isMaxEmp | MaxID    | FN | MN | LN | Email | Start Date | End Date | Acct Type | Acct Auth | Auth Date | Override Auth | Override Auth Reason | PHI Access | PHI Reason | PII Access | PII Reason | Status   |
      | 'Yes'    | '124458' | '' | '' | '' | ''    | ''         | ''       | ''        | ''        | ''        | ''            | ''                   | ''         | ''         | ''         | ''         | 'Active' |

  @CRM-628 @CRM-638 @CRM-638-01 @CRM-638-04 @vinuta
  Scenario Outline: Reactivate a user immediately
    Given I logged into Tenant Manager Project list page
    When I expend a Project to view the details
    And I click on User List Menu
    And I click on add new user button
    And I create User with given data <isMaxEmp>,<MaxID>,<FN>,<MN>,<LN>,<Email>,<Start Date>,<End Date>,<Acct Type>,<Acct Auth>,<Auth Date>,<Override Auth>,<Override Auth Reason>,<PHI Access>,<PHI Reason>,<PII Access>,<PII Reason>,<Status>
    And I click on save a user button
    Then I should see a pop-up that user is created successfully
    And I enter search criteria for a user by "maxID" value <MaxID>
    And I enter search criteria for a user by "status" value <Status>
    And I click on Search Button on User List Page
    Then I should see all users with "status" value <Status>
    And I click on first user to open User Details
    And I set Inactive Immediately to Yes
    Then I should see pop-up with message "The user account will be deactivated when you click the Save button"
    And I click on ok button to inactivate user
    And I select value "Resigned" in account inactivation dropdown
    And I click on save a user button
    Then I should see message that user has been inactivated successfully
    And I click on ok button to inactivate user
    And I enter search criteria for a user by "maxID" value <MaxID>
    And I click on Search Button on User List Page
    And I click on first user to open User Details
    Then I verify values in account reactivation "reason" dropdown
      | Change of Job Functions      |
      | Returned from Leave (Other)  |
      | Returned from Parental Leave |
      | Rehire                       |
    And I click on save a user button
    Then I see the error that End date must be empty to reactivate a user
    And I enter the End Date "equal" to the Start Start
    And I nullify the end date
    And I click on save a user button
    Then I should see message that user has been reactivated successfully
    And I click on ok button to reactivate user
    And I enter search criteria for a user by "maxID" value <MaxID>
    And I enter search criteria for a user by "status" value <Status>
    And I click on Search Button on User List Page
    Then I should see all users with "status" value <Status>
    Examples:
      | isMaxEmp | MaxID    | FN | MN | LN | Email | Start Date | End Date | Acct Type | Acct Auth | Auth Date | Override Auth | Override Auth Reason | PHI Access | PHI Reason | PII Access | PII Reason | Status   |
      | 'Yes'    | '152369' | '' | '' | '' | ''    | ''         | ''       | ''        | ''        | ''        | ''            | ''                   | ''         | ''         | ''         | ''         | 'Active' |
