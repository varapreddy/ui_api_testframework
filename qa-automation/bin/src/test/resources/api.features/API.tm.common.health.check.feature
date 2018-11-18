Feature: API-Tenant Manager: Common Controller

@API-HealthCheck @Sujoy
Scenario Outline: Common Health Check - GET mars/tm/common/activedirectory/{maxId}
    Given I initiated get employee detail by MaxID "<maximusID>"
    When I run the employee detail API by MaxID
    Then I can verify ge user approver detail API response will be "<success>"
    Examples:
        | maximusID    | success |
        | 102040       | True    |
