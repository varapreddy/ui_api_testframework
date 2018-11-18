Feature: API-CRM: Contact Record Controller

  @API-CRM-300 @API-CRM @API-Regression @API-ContactRecords @Sujoy
  Scenario Outline: Create Consumer Contact Records using API
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

  @API-CRM-300 @API-CRM @API-Regression @API-ContactRecords @Sujoy
  Scenario Outline: Create Consumer Contact Records with varius Consumer Type using API
    Given I initiated consumer search API for Contact
    When I get correlationId id from consumer first name search "<consumerFirstName>"
    And I initiated Create Contact Records API
    And I can provide contact records with "<consumerFirstName>", "<consumerLastName>" and "<consumerType>"
    And I can provide correlation id
    And I can run create contact records API
    Then I can verify consumer contact by consumerLastName with value "<consumerLastName>" on API response
    Examples:
      | consumerFirstName  | consumerLastName | consumerType              |
      |    Prathap         | Bellary          | Member                    |
      |    Prathap         | Bellary          | Non-Member                |
      |    Prathap         | Bellary          | Authorized Representative |


  @API-CRM-231 @API-CRM-234 @API-CRM @API-Regression @API-ContactRecords @Sujoy
    Scenario Outline: Search Contact Record using API
        Given I initiated search contact records API
        When I can search Contact Record by "<Node>" with value "<value>"
        And I run the search contact records API
        Then I can verify contact records search response success is "<Success>"
        Examples:
            | Node                  | value    | Success |
            | consumerFirstName     | Prathap  | True    |
            | consumerLastName      | bangar   | True    |

  @API-CRM-225 @API-CRM @API-Regression @API-ContactRecords @Sujoy
  Scenario Outline: Get Contact Record history using API
    Given I initiated consumer search API for Contact
    When I get correlationId id from consumer first name search "<value>"
    And I initiated Contact Records Customers API
    And I can set link consumer Id as reference Id
    And I run the post contact records API with Query parameters with "<page>","<size>" and "<sort>"
    Then I can verify contact records search response success is "<Success>"
    And I can verify contact record Size is less than or equal of "<size>"
    And I can verify contact record ID are descending
    Examples:
      | Node                  | value     | page | size | sort                 | Success |
      | consumerFirstName     | Prathap   | 0    | 5    | contactRecordId,DESC | True    |

  @API-CRM-231 @API-CRM-234 @API-CRM @API-Regression @API-ContactRecords @Sujoy
  Scenario Outline: Search Blank Contact Record using API
      Given I initiated search contact records API
      When I can search Contact Record by "<Node>" with value "<value>"
      And I run the search contact records API
      Then I can verify contact records search response success is "<Success>"
      Examples:
          | Node                  | value    | Success |
          | consumerFirstName     |          | True    |

  @API-CRM-232 @API-CRM @API-Regression @API-ContactRecords @Sujoy
  Scenario Outline: Search Contact Record Create link using API
      Given I initiated consumer search API for Contact
      When I get correlationId id from consumer first name search "<value>"
      And I initiated Create Contact Records API
      And I can provide contact records information for link
      And I can run update contact records API
      And I initiated specific contact Search ""
      And I run Get contact records API
      Then I can verify contact records search response success is "True"
      Examples:
        | Node                  | value     |
        | consumerFirstName     | Prathap   |


  @API-CRM-232 @API-CRM @API-Regression @API-ContactRecords @Sujoy
  Scenario Outline: Search Contact Record Create Unlink using API
    Given I initiated consumer search API for Contact
    When I get correlationId id from consumer first name search "<value>"
    And I initiated Create Contact Records API
    And I can provide contact records information for unlink
    And I can run update contact records API
    And I initiated correlation contact Search ""
    And I run Get contact records API
    Then I can verify contact records search response success is "True"
    Examples:
      | Node                  | value     |
      | consumerFirstName     | Prathap   |


  @API-CRM-304 @API-CRM @API-Regression @API-ContactRecords @Sujoy
  Scenario Outline: Close Contact Record using API
    Given I initiated search contact records API
    When I can search Contact Record by "consumerFirstName" with value "<consumerFirstName>"
    And I run the search contact records API
    And I initiated Create Contact Records API
    And I can close contact records information
    Then I can verify consumer contact by consumerLastName with value "<consumerLastName>" on API response
    Examples:
      | consumerFirstName | consumerLastName |
      | Prathap           | Bellary          |


  @API-CRM-300 @API-CRM @API-Regression @API-ContactRecords @Sujoy
  Scenario Outline: Create Consumer Contact Records using API
    Given I initiated consumer search API for Contact
    When I get correlationId id from consumer first name search "<consumerFirstName>"
    And I initiated Create Contact Records API
    And I can provide contact records with "<consumerFirstName>", "<consumerLastName>" and "<consumerType>"
    And I can provide correlation id
    And I can run create contact records API
    Then I can verify consumer contact by consumerLastName with value "<consumerLastName>" on API response
    Examples:
      | consumerFirstName  | consumerLastName | consumerType              |
      |    Prathap         | Bellary          | Member                    |
      |    Prathap         | Bellary          | Non-Member                |
      |    Prathap         | Bellary          | Authorized Representative |
