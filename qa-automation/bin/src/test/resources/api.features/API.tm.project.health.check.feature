Feature: API-Tenant Manager: Project Controller Health Check

  @API-HealthCheck @Sujoy
  Scenario Outline: Create Project API Health Check PUT /mars/tm/project
    Given I initiated Create Project API
    When I can provide project information to create a "<Provisioning>" project on "<state>"
    And I run the create project API
    Then I can search the project by project name to validate is "<success>"
    Examples:
      |state|Provisioning|success|
      |	AK  |Active      |TRUE	 |

  @API-HealthCheck @Sujoy
  Scenario Outline: Search Projects by ID - API Health Check GET /mars/tm/project/{projectId}
    Given I initiated Search Project API
    When I can Search Project by "<Node>" with value "<value>"
    And I run the search project API
    And I can get ProjectID
    Given I initiated Search Project API By Project ID ""
    And I run the search project API By ProjectID
    Then I can verify Project ID on project search API response will be "<success>"
    Examples:
      | Node           | value        | success |
      | state          | Az           | True    |

  @API-HealthCheck @Sujoy
  Scenario: Validate Project Lists using API Health Check GET /mars/tm/projects
    Given I initiated Get Project List API
    And I run the get project list API
    Then I can search the project by project name to validate is success

  @API-HealthCheck @Sujoy
  Scenario Outline: Search Projects using API Health Check POST /mars/tm/projects
    Given I initiated Search Project API
    When I can Search Project by "<Node>" with value "<value>"
    And I run the search project API
    Then I can verify "<Node>" with value "<value>" on project API response will be "<success>"
    Examples:
      | Node           | value        | success |
      | state          | Tx           | True    |


  @API-HealthCheck @Sujoy
  Scenario Outline: Get approver detail health check GET mars/tm/project/approver/{projectId}
    Given I initiated project approver API by project name "<projectName>"
    When I can get the project approver detail
    Then I can verify get user approver detail API response will be "<success>"
    Examples:
      | projectName    | success |
      | Test Project 1 | True    |

