Feature: API-Tenant Manager: Project Role Controller

@API-CRM-751 @API-TM @API-Regression @API-Project-role @Vinuta
Scenario Outline: Create Project Role API
    Given I initiated Create Project Role API
    When I can provide role details with "<projectId>" "<roleName>" "<roleDesc>"
    And I run the create project role API
    Then I can search the project role by role name to validate is "<success>"
    Examples:
        |projectId|roleName |roleDesc    |success|
        |{random} |{random} |{random}    |TRUE   |

@API-CRM-751 @API-TM @API-Regression @API-Project-role @Vinuta
Scenario Outline: Validate Duplicate Project Role is not created by API
    Given I initiated Create Project Role API
    When I can provide role details with "<projectId>" "<roleName>" "<roleDesc>"
    And I run the create project role API
    Then I can search the project role by role name to validate is "<success>"
    Given I initiated Create Project Role API
    And I run the create project role API
    Then I can verify the duplicate role error message on API response
    Examples:
        |projectId|roleName |roleDesc    |success|
        |1        |{random} |{random}    |TRUE   |

