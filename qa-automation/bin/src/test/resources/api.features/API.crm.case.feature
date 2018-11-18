Feature: API-CRM: Case Controller

    @API-CRM-231 @API-CRM-482 @API-CRM @API-Regression @API-Case @Sujoy
    Scenario Outline: Create Case using API
        Given I initiated create case API
        When I can input "<caseFirstName>", "<caseLastName>", "<phoneNumber>" and "<addressZip>"
        And I can run create case API
        Then I can verify case exists by "<caseLastName>" is "<success>" on API response
        Examples:
            | caseFirstName      | caseLastName | phoneNumber| addressZip|success|
            | Paul               | Peca         | 9999999991 | 78746     | True  |
            | Paul               | Peca         | 0000000000 | 78746     | True  |
            | Paul               | Peca         | 9999999991 | 99999     | True  |
            | paul               | peca         | 9999999991 | 99999     | False |


    @API-CRM-231 @API-CRM-482 @API-CRM @API-Regression @API-Case @Sujoy
    Scenario Outline: Search Cases using API
        Given I initiated Search Case API
        When I can search case by "<Node>" with value "<value>"
        And I run the case search API
        Then I can verify case "<Node>" with value "<value>" on API response
        Examples:
            | Node                      | value         |
            | consumerSSN               | 102901290     |
            | consumerDateOfBirth       | 1197397800000 |
            | consumerFirstName         | nilasini      |
            | consumerLastName          | bangar        |
            | consumerIdentificationNo  | NI2092039     |

    @API-CRM-231 @API-CRM-482 @API-CRM @API-Regression @API-Case @Sujoy
    Scenario Outline: Search Cases using API
        Given I initiated Search Case API
        When I can Search Case by "<Node1>" with value "<value1>", "<Node2>" with value "<value2>", "<Node3>" with value "<value3>", "<Node4>" with value "<value4>" and "<Node5>" with value "<value5>"
        And I run the case search API
        Then I can verify on case search API response will be "<success>"
        Examples:
            | Node1           | value1   | Node2           | value2 | Node3              | value3        | Node4     | value4    | Node5                   | value5    |success|
            |consumerFirstName| nilasini | consumerLastName| bangar | consumerDateOfBirth|               |consumerSSN|           | consumerIdentificationNo|           | True  |
            |consumerFirstName|          | consumerLastName| bangar | consumerDateOfBirth| 1197397800000 |consumerSSN|           | consumerIdentificationNo|           | True  |
            |consumerFirstName| nilasini | consumerLastName|        | consumerDateOfBirth| 1197397800000 |consumerSSN|           | consumerIdentificationNo|           | True  |
            |consumerFirstName| nilasini | consumerLastName|        | consumerDateOfBirth|               |consumerSSN| 102901290 | consumerIdentificationNo|           | True  |
            |consumerFirstName|          | consumerLastName| bangar | consumerDateOfBirth|               |consumerSSN| 102901290 | consumerIdentificationNo|           | True  |
            |consumerFirstName|          | consumerLastName|        | consumerDateOfBirth| 1197397800000 |consumerSSN| 102901290 | consumerIdentificationNo|           | True  |
            |consumerFirstName| nilasini | consumerLastName|        | consumerDateOfBirth|               |consumerSSN|           | consumerIdentificationNo| NI2092039 | True  |
            |consumerFirstName|          | consumerLastName| bangar | consumerDateOfBirth|               |consumerSSN|           | consumerIdentificationNo| NI2092039 | True  |
            |consumerFirstName|          | consumerLastName|        | consumerDateOfBirth| 1197397800000 |consumerSSN|           | consumerIdentificationNo| NI2092039 | True  |
            |consumerFirstName|          | consumerLastName|        | consumerDateOfBirth|               |consumerSSN| 102901290 | consumerIdentificationNo| NI2092039 | True  |
            |consumerFirstName| nilasini | consumerLastName| bangar | consumerDateOfBirth| 1197397800000 |consumerSSN|           | consumerIdentificationNo|           | True  |
            |consumerFirstName| nilasini | consumerLastName| bangar | consumerDateOfBirth|               |consumerSSN| 102901290 | consumerIdentificationNo|           | True  |
            |consumerFirstName| nilasini | consumerLastName|        | consumerDateOfBirth| 1197397800000 |consumerSSN| 102901290 | consumerIdentificationNo|           | True  |
            |consumerFirstName|          | consumerLastName| bangar | consumerDateOfBirth| 1197397800000 |consumerSSN| 102901290 | consumerIdentificationNo|           | True  |
            |consumerFirstName| nilasini | consumerLastName| bangar | consumerDateOfBirth| 1197397800000 |consumerSSN|           | consumerIdentificationNo|           | True  |
            |consumerFirstName|          | consumerLastName|        | consumerDateOfBirth| 1197397800000 |consumerSSN| 102901290 | consumerIdentificationNo| NI2092039 | True  |
            |consumerFirstName|          | consumerLastName| bangar | consumerDateOfBirth|               |consumerSSN| 102901290 | consumerIdentificationNo| NI2092039 | True  |
            |consumerFirstName| nilasini | consumerLastName|        | consumerDateOfBirth| 1197397800000 |consumerSSN|           | consumerIdentificationNo| NI2092039 | True  |
            |consumerFirstName| nilasini | consumerLastName| bangar | consumerDateOfBirth| 1197397800000 |consumerSSN| 102901290 | consumerIdentificationNo| NI2092039 | True  |
            |consumerFirstName| nilasini | consumerLastName| bangar | consumerDateOfBirth|               |consumerSSN| 102901290 | consumerIdentificationNo| NI2092099 | False  |
            |consumerFirstName| nilasini | consumerLastName|        | consumerDateOfBirth| 1197397800000 |consumerSSN| 102901990 | consumerIdentificationNo| NI2092039 | False  |
            |consumerFirstName|          | consumerLastName| bangar | consumerDateOfBirth| 1197398800000 |consumerSSN| 102901290 | consumerIdentificationNo| NI2092039 | False  |
            |consumerFirstName| nilasini | consumerLastName| xyghts | consumerDateOfBirth| 1197397800000 |consumerSSN| 102901290 | consumerIdentificationNo| NI2092039 | False  |
            |consumerFirstName| abcdefgh | consumerLastName| bangar | consumerDateOfBirth| 1197397800000 |consumerSSN| 102901290 | consumerIdentificationNo| NI2092039 | False  |
            |consumerFirstName| nilasini | consumerLastName|        | consumerDateOfBirth| 1197398800000 |consumerSSN| 102901290 | consumerIdentificationNo| NI2092039 | False  |
            |consumerFirstName| nilasini | consumerLastName| bangar | consumerDateOfBirth| 1197397800000 |consumerSSN|           | consumerIdentificationNo| NI2092099 | False  |
            |consumerFirstName|          | consumerLastName| bangar | consumerDateOfBirth| 1197397800000 |consumerSSN| 102901990 | consumerIdentificationNo| NI2092039 | False  |

    @API-CRM-778 @API-CRM-778-01 @API-CRM @API-Regression @API-Case @Shruti @1101
    Scenario: Verify the relationship status of the case member
        Given I initiated Create case member API
        When I can provide case member information
            |consumerType | consumerFirstName  | consumerLastName |consumerDateOfBirth| consumerSSN |consumerGenderCode |effectiveStartDate |caseId |relationShip|
            |Member       |    {random}        | {random}         |today-9000         |  {random}   | Male              |today-50           |105    |Child|
            |Member       |    {random}        | {random}         |today-3000         |  {random}   | Male              |today-50           |105    |Spouse|
            |Member       |    {random}        | {random}         |today-15000        |  {random}   | Male              |today-50           |105    |Guardian|
        Given I initiated get case member API for case "105"
        Then I verify the case member details using API
            |relationShip |consumerType |
            |Child        |Member       |
            |Spouse       |Member       |
            |Guardian     |Member       |

    @API-CRM-778 @API-CRM-778-03 @API-CRM @API-Regression @API-Case @Shruti @1102
    Scenario: Verify that case member will not be created if mandatory data missed in the request
        Given I initiated Create case member API
        When I can provide case member information
            |consumerType | consumerFirstName  | consumerLastName |consumerDateOfBirth| consumerGenderCode |effectiveStartDate |effectiveEndDate|caseId |relationShip|consumerStatus|
            |Member       |    {random}        | {random}         |today-9000         | Male              |today-10           |today+100       |105    |Child       |Active        |
        Given I initiated get case member API for case "105"
        Then I verify the case member details not fetched using API
            |consumerStatus |consumerType |
            |Active         |Member       |

    @API-CRM-394  @API-CRM-394-01 @API-CRM @API-Regression @API-Case @Shruti @1102
    Scenario: Verify that Active case members are displayed first followed by inactive
        Given I initiated Create case member API
        When I can provide case member information
            |consumerType | consumerFirstName  | consumerLastName |consumerDateOfBirth| consumerSSN |consumerGenderCode |effectiveStartDate |effectiveEndDate|caseId |relationShip|consumerStatus|
            |Member       |    {random}        | {random}         |today-9000         |  {random}   | Male              |today-10           |today+100       |105    |Child       |Active        |
            |Member       |    {random}        | {random}         |today-3000         |  {random}   | Male              |today-100          |today           |105    |Spouse      |Active        |
            |Member       |    {random}        | {random}         |today-15000        |  {random}   | Male              |today+50           |today+200       |105    |Guardian    |Inactive      |
        Given I initiated get case member API for case "105"
        Then I verify active case members fetched followed by inactive for consumerType "Member" using API











