Feature:Save a Project

  @CRM-290 @CRM-290-01 @shilpa @tm @tm_muted
Scenario:Validate all the Elements in the Project Contact Details
   	Given I logged into Tenant Manager Project list page
   When I click on Create a New Project
   Then the elements  should be displayed in the Project Contact Page

  @CRM-290 @CRM-290-02 @regression @shilpa @tm @tm_regression @smoke
Scenario: Create a new Project and save the Project
  Given I logged into Tenant Manager Project list page
   When I click on Create a New Project
   And I enter valid Details and save the Project
   Then User should be navigated to the Home Page

  @CRM-290 @CRM-290-03 @regression @shilpa @tm @tm_regression @smoke
Scenario:Validate the submit button after entering the existing project
   Given I logged into Tenant Manager Project list page
   When I click on Create a New Project
   And I enter existing Project Details and save the Project
   Then I should get a Error Message

  @CRM-290 @CRM-290-04 @regression @shilpa @tm @tm_regression
Scenario:Validate the Contract Start Date and End Date
  Given I logged into Tenant Manager Project list page
  When I click on Create a New Project
  And  I enter all the Details Contract Start Date and End Date as Same
  Then It should throw an Error message














