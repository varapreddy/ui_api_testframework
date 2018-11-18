Feature: API-CRM: Contacts Controller Health Check


  @API-HealthCheck @Sujoy
  Scenario Outline: Contacts Health Check -- PUT mars/crm/contact
      Given I initiated consumer search API for Contacts
      Given I initiated consumer search API for Contacts
      When I get uiid and correlationId id from contact first name search "<consumerFirstName>"
      And I initiated add new Contact using API
      And I added email information "<externalRefType>", "<associatedCaseMember>", "<emailAddress>", "<startDate>" and "<endDate>"
      And I can run add contacts using API
      And I initiated specific contacts detail "<externalRefType>"
      Then I run get contacts detail using API
      Examples:
          | consumerFirstName  | externalRefType | associatedCaseMember | emailAddress         | startDate  | endDate|
          |    Prathap         |   Consumer      |  Prathap Bellary     | abc@test.com         |            |        |

  @API-HealthCheck @Sujoy
  Scenario Outline: Get Contacts Details By ExternalRefType and ExternalRefId Health Check -- GET mars/crm/contacts/{externalType}/{externalRefid}
      Given I initiated consumer search API for Contacts
      When I get uiid and correlationId id from contact first name search "<consumerFirstName>"
      And I initiated Consumer Type Search vai API with "<externalRefType>" type and id "<externalRefid>"
      And I run get contacts detail using API
      Then I can verify consumer phone detail "<success>" and count greater than zero using API
      Examples:
          | consumerFirstName  | externalRefType | externalRefid |success|
          |    APIQUV          | Consumer        |               | True  |

