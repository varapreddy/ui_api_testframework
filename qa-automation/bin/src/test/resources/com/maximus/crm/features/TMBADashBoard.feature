Feature: Verify Tenant Manager Dashboard

 @CRM-396 @CRM-396-01 @regression @shilpa @tm @tm_regression
Scenario: Validate the Create a new Project button
Given I logged into Tenant Manager Project list page
When I click on Create a New Project
Then I should be navigated to the Add Project Page

 @CRM-396 @CRM-396-02 @regression @shilpa @tm @tm_regression
Scenario:Validate the search in the Home Page
 Given I logged into Tenant Manager Project list page
  When I search for a project by "state" value "NY"
  Then I should see all projects with "state" value "NY"

 @CRM-396 @CRM-396-03 @regression @shilpa @tm @tm_regression @smoke
 Scenario: Validate the List of projects displayed
Given I logged into Tenant Manager Project list page
Then the system displays a list of all projects that have been entered into the CRM system

 @CRM-396* @CRM-396-04 @shilpa @tm @tm_muted
 Scenario:Validate the Date in the Home Page
Given I logged into Tenant Manager Project list page
Then The System should display the Current Date in the Header

 @CRM-396 @CRM-396-05 @regression @shilpa @tm @tm_regression
Scenario:Validate the Date in the Home Page
Given I logged into Tenant Manager Project list page
Then The System should display the Role

 @CRM-396 @CRM-396-06 @regression @shilpa @tm @tm_regression
Scenario:Validate the Date in the Home Page
Given I logged into Tenant Manager Project list page
Then The System should display the UserName

 @CRM-396 @CRM-396-07 @regression @shilpa @tm @tm_regression
Scenario:Validate the Date in the Home Page
Given I logged into Tenant Manager Project list page
And I scroll to the bottom of the page
Then The System should display the Office Address
















