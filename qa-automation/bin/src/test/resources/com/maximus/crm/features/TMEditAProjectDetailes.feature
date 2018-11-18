Feature: Tenant Manager Editing a Project Details

  @CRM-293 @CRM-293-01 @regression @muhabbat @tm @tm_regression @smoke
  Scenario Outline: Edit project details single field
    Given I logged into Tenant Manager Project list page
    When I expend a Project to view the details
    And I edit and save the project "<detail>" with "<value>" one at the time
    When I confirm the project "<detail>" "<value>"is updated
    Then I change it back to previous value for "<detail>"
    Examples:
      | detail        | value            |
     # | project_name  | ChildSupport     |
      | state         | MD               |
     # | program_name  | PriorityHelp     |
      | contract_id   | WS34             |
     # | client_agency | PriorityPartners |
     # | start_date    | 05/06/2016       |
     # | end_date      | 05/09/2019       |
      | pro_status    | Active           |


  @CRM-293 @CRM-293-02 @regression @muhabbat @tm @tm_regression
  Scenario Outline: Not saving edited project details
    Given I logged into Tenant Manager Project list page
    When I expend a Project to view the details
    And I edit but don't save the project "<detail>" with "<value>" one at the time
    Then I confirm the project "<detail>" "<value>" is not updated
    Examples:
      | detail        | value      |
      | project_name  | HomeCare   |
      | state         | MI         |
      | program_name  | MCHP       |
      | contract_id   | AAAA       |
      | client_agency | BBC        |
      | start_date    | 05/06/2018 |
      | end_date      | 05/09/2020 |
      | pro_status    | Inactive   |


  @CRM-293 @CRM-293-09 @regression @muhabbat @tm @tm_regression @smoke
  Scenario: Edit project contact details
    Given I logged into Tenant Manager Project list page
    When I expend a Project to view the details
    And I edit each role with new project contact details and see updates
    Then I change each role to previous value

