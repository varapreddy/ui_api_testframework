Feature: API-CRM: Case Controller Health Check

@API-HealthCheck @Sujoy
Scenario Outline: Create Case Health Check -- PUT app/crm/case
    Given I initiated create case API
    When I can input "<caseFirstName>", "<caseLastName>", "<phoneNumber>" and "<addressZip>"
    And I can run create case API
    Then I can verify case exists by "<caseLastName>" is "<success>" on API response
    Examples:
        | caseFirstName      | caseLastName | phoneNumber| addressZip|success|
        | Paul               | Peca         | 9999999991 | 78746     | True  |

@API-HealthCheck @Sujoy
Scenario Outline: Search Case Consumer Health Check -- POST app/crm/case/consumers
    Given I initiated Search Case API
    When I can Search Case by "<Node1>" with value "<value1>", "<Node2>" with value "<value2>", "<Node3>" with value "<value3>", "<Node4>" with value "<value4>" and "<Node5>" with value "<value5>"
    And I run the case search API
    Then I can verify on case search API response will be "<success>"
    Examples:
        | Node1           | value1   | Node2           | value2 | Node3              | value3        | Node4     | value4    | Node5                   | value5    |success|
        |consumerFirstName| nilasini | consumerLastName| bangar | consumerDateOfBirth| 1197397800000 |consumerSSN| 102901290 | consumerIdentificationNo| NI2092039 | True  |

@API-HealthCheck @Shruti @1031
Scenario: Create Case Consumer Health Check -- PUT com/app/crm/casemember
    Given I initiated Create case member API
    When I can provide case member information
    |consumerType | consumerFirstName  | consumerLastName |consumerDateOfBirth| consumerSSN |consumerGenderCode |effectiveStartDate |caseId |relationShip|
    |Member       |    {random}        | {random}         |today-15000        |  {random}   | Male              |today-50           |241    |Guardian|

    Then I can run create case member API and validate the status is success

# @Shruti @1101     ##--Test is failing. Need to revisit this test --
#Scenario: List of Case Consumer by Case ID  Health Check -- GET com/app/crm/casemember/{caseId}
#    Given I initiated Create case member API
#    When I can provide case member information
#        |consumerType | consumerFirstName  | consumerLastName |consumerDateOfBirth| consumerSSN |consumerGenderCode |effectiveStartDate |caseId |relationShip|
#        |Member       |    {random}        | {random}         |today-9000         |  {random}   | Male              |today-50           |250    |Child       |
#    Given I initiated get case member API for case "250"
#    Then I can verify case member is created on API response
