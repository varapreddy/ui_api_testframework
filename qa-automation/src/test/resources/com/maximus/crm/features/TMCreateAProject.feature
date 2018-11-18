Feature: Tenant Manager  Create a new Project

  @CRM-289 @CRM-289-01  @shilpa @tm @tm_regression
Scenario:Create a new Project
	Given I logged into Tenant Manager Project list page
	When I click on Create a New Project
	Then I should be navigated to the Add Project Page

  @CRM-289 @CRM-289-02  @shilpa @tm @tm_regression
Scenario:Validate all the Elements in the Project Contact Details
  Given I logged into Tenant Manager Project list page
   When I click on Create a New Project
   Then I should see all the elements displayed in the Project Contact Page

  @CRM-289 @CRM-289-03  @shilpa @tm @tm_regression @smoke
 Scenario: Create a new Project and save the Project
  Given I logged into Tenant Manager Project list page
  When I click on Create a New Project
  And I enter valid Details and save the Project
  Then User should be navigated to the Home Page
    











