Feature: API-Tenant Manager: Common Controller


@API-CRM-291 @API-TM @API-Project @Sujoy
Scenario Outline: Get employee detail using API
    Given I initiated get employee detail by MaxID "<maximusID>"
    When I run the employee detail API by MaxID
    Then I can verify ge user approver detail API response will be "<success>"
    Examples:
        | maximusID    | success |
        | 102040       | True    |
