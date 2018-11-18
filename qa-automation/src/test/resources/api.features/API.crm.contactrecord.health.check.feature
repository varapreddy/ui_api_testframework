Feature: API-CRM: Contact Record Health Check Controller

  @API-HealthCheck @Sujoy
  Scenario Outline: Create Contact Record Health Check -- PUT app/crm/contactrecord
    Given I initiated consumer search API for Contact
    When I get correlationId id from consumer first name search "<consumerFirstName>"
    And I initiated Create Contact Records API
    And I can provide contact records information with "<consumerFirstName>" and "<consumerLastName>"
    And I can provide correlation id
    And I can run create contact records API
    Then I can verify consumer contact by consumerLastName with value "<consumerLastName>" on API response
    Examples:
      | consumerFirstName  | consumerLastName |
      |    Prathap         | Bellary          |

  @API-HealthCheck @Sujoy
  Scenario Outline: Search Contact Record Health Check -- POST app/crm/contactrecords
    Given I initiated search contact records API
    When I can search Contact Record by "<Node>" with value "<value>"
    And I run the search contact records API
    Then I can verify contact records search response success is "<Success>"
    Examples:
      | Node                  | value    | Success |
      | consumerFirstName     | Prathap  | True    |

  @API-HealthCheck @Sujoy
  Scenario Outline: Get Contact Record detail by Id Health Check -- GET app/crm/contactrecord/{contactId}
    Given I initiated consumer search API for Contact
    When I get correlationId id from consumer first name search "<value>"
    And I initiated specific contact Search ""
    And I run Get contact records API
    Then I can verify contact records search response success is "True"
    Examples:
      | Node                  | value     |
      | consumerFirstName     | Prathap   |

