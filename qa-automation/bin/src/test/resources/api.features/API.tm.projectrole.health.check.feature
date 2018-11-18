Feature: API-Tenant Manager: Project Role Controller

@API-HealthCheck @Sujoy
Scenario Outline: Create Project Role API Health Check PUT mars/tm/project/role
    Given I initiated Create Project Role API
    When I can provide role details with "<projectId>" "<roleName>" "<roleDesc>"
    And I run the create project role API
    Then I can search the project role by role name to validate is "<success>"
    Examples:
        |projectId|roleName |roleDesc    |success|
        |{random} |{random} |{random}    |TRUE   |

