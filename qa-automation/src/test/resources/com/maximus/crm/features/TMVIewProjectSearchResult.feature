Feature: View Project Search Result

  @CRM-295 @CRM-295-03 @regression @muhabbat @tm @tm_regression @smoke
  Scenario: View  All Projects on Project List Page
    When I logged into Tenant Manager Project list page
    Then I can see all Project records with all data elements displayed

  @CRM-295 @CRM-295-01 @regression @muhabbat @tm @tm_regression
  Scenario Outline: View all mandatory Project elements
    Given I logged into Tenant Manager Project list page
    When I search for a project by "<field>" value "<value>"
    And I should see all projects with "<field>" value "<value>"
    Then I can see all Project records with all data elements displayed
    Examples:
      | field   | value       |
      | project | Test        |
      | state   | Al          |
      | program | Medicare    |
      | client  | Az Baseline |

  @CRM-295 @CRM-295-02 @regression @muhabbat @tm @tm_regression @smoke
  Scenario Outline:
    Given I logged into Tenant Manager Project list page
    When I search for a project by "<field>" value "<value>"
    And I should see all projects with "<field>" value "<value>"
    Then I can navigate to see more Projects than shown on current page
    Examples:
      | field   | value       |
      | project | Test        |
      | state   | AR          |
      | program | Medicaid    |
      | client  | Az Baseline |