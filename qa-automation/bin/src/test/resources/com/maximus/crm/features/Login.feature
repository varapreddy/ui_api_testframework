Feature: Tenant Manager -- Verify Login

  @CRM-395 @CRM-395-01  @regression @shilpa @tm_regression
Scenario:Validate the Login fields
	Given I  navigate to the Tenant Manager Url
	When I  navigate it should redirect to the login Page
	Then I should see the login fields present in the login Page

  @CRM-395 @CRM-395-02 @regression @shilpa @tm_regression @smoke
Scenario:Validate the Login Page by providing valid data
Given I  navigate to the Tenant Manager Url
 When I navigate it should redirect to the login Page and provide valid login credentials
 Then the system should allow me to see the Home page

  @CRM-395 @CRM-395-03  @shilpa @tm_muted
Scenario:Validate the Login Page by providing invalid data
 Given I  navigate to the Tenant Manager Url
  When I  navigate it should redirect to the login Page  and I provide invalid data in the login page
  Then  the system should throw an error message of invalid username and invalid password

  @CRM-395 @CRM-395-04 @shilpa @tm_muted
Scenario: Validate the Tenant Manger Home Page after logging
Given I  navigate to the Tenant Manager Url
 When I navigate it should redirect to the login Page and provide valid login credentials
 Then the system should allow me to see the Home page and search for the project













