Feature:Create a Unique Project Record ID

   @CRM-291 @CRM-291-01 @shilpa @tm @tm_muted
Scenario:Validate all the Elements in the Project Contact Details
   Given I logged into Tenant Manager Project list page
   When I click on Create a New Project
   Then the elements  should be displayed in the Project Contact Page

   @CRM-291 @CRM-291-02 @shilpa @tm @tm_muted
Scenario:Validate the duplicate records in the Create a project Record
Given I post the Request
When I pass the duplicate values in Project Name and other Attributes
Then I should get a Error Message














