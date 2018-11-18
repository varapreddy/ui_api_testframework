Feature: API-CRM: Consumer Controller Health Check

  @API-HealthCheck @Sujoy
  Scenario Outline: Create Consumer Health Check -- PUT com/app/crm/consumer
    Given I initiated Create Consumer API
    When I can provide consumer information with "<consumerFirstName>" "<consumerLastName>" "<phoneNumber>" and "<addressZip>"
    And I can run create consumer API
    Then I can verify consumer consumerLastName with value "<consumerLastName>" on API response
    Examples:
      | consumerFirstName  | consumerLastName |phoneNumber| addressZip |
      |    {random}        | {random}         |{random}   |  {random}  |

  @API-HealthCheck @Sujoy
  Scenario Outline: Search Consumer Health Check -- POST app/crm/consumers
    Given I initiated Consumer Search API
    When I can search consumer by "<Node>" with value "<value>"
    And I run the consumer search API
    Then I can verify consumer "<Node>" with value "<value>" on API response
    Examples:
      | Node                      | value         |
      | consumerLastName          | Bellary       |
