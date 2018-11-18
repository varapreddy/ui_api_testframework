Feature: API-CRM: Contact Controller

  @API-CRM-763 @API-CRM @API-Regression @API-Contacts @Sujoy
  Scenario Outline: Add new Email using API
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

  @API-CRM-763 @CRM-763-02 @API-CRM @API-Regression @API-Contacts @Shruti @1012
  Scenario Outline:Add new random email to a random consumer
    Given I created a consumer for contact using API
    And I collected some consumer related data from consumer Controller
    And I initiated add new Contact using API
    And I added email information "<externalRefType>", "<associatedCaseMember>", "<emailAddress>", "<startDate>" and "<endDate>"
    And I can run add contacts using API
    And I initiated specific contacts detail "<externalRefType>"
    Then I run get contacts detail using API
    Examples:
      | consumerFirstName  | externalRefType | associatedCaseMember | emailAddress         |startDate |endDate |
      |                    |   Consumer      |                      | {random}             |          |        |

  @API-CRM-763 @CRM-763-03 @API-CRM @API-Regression @API-Contacts @Shruti @1012
  Scenario Outline:Validate status as of email address with different start and end dates
    Given I created a consumer for contact using API
    And I collected some consumer related data from consumer Controller
    And I initiated add new Contact using API
    And I added email information "<externalRefType>", "<associatedCaseMember>", "<emailAddress>", "<startDate>" and "<endDate>"
    And I can run add contacts using API
    And I initiated specific contacts detail "<externalRefType>"
    Then I verify "<success>" details using API
    Examples:
      | consumerFirstName  | externalRefType | associatedCaseMember | emailAddress         | addressZip| phoneNumber | startDate   |endDate   | success |
      |    {random}        |  Consumer       |                      | {random}             |{random}   | {random}    |  today      |tomorrow  | True    |
      |    {random}        |  Consumer       |                      | {random}             |{random}   | {random}    |  yesterday  |tomorrow  | True    |

  @API-CRM-763 @CRM-763-06 @API-CRM @API-Regression @API-Contacts @Shruti @1015
  Scenario Outline:Verify Audit Trail for newly added email to a consumer
    Given I created a consumer for contact using API
    And I collected some consumer related data from consumer Controller
    And I initiated add new Contact using API
    And I added email information "<externalRefType>", "<associatedCaseMember>", "<emailAddress>", "<startDate>" and "<endDate>"
    And I can run add contacts using API
    And I initiated specific contacts detail "<externalRefType>"
    Then I verify Email address Audit Trail
    Examples:
      | consumerFirstName  | externalRefType | associatedCaseMember | emailAddress         | addressZip| phoneNumber | startDate |endDate   | success|
      |    {random}        |  Consumer       |                      | {random}             | {random}  | {random}    |  today    | tomorrow | True   |

  @API-CRM-760 @API-CRM @API-Regression @API-Contacts @Shilpa
  Scenario Outline: Add new Phone using API
    Given I initiated consumer search API for Contacts
    When I get uiid and correlationId id from contact first name search "<consumerFirstName>"
    And I initiated add new Contact using API
    And I added phone information "<externalRefType>", "<phoneNumber>", "<phoneType1>", "<phoneType2>", "<comments>", "<startDate>" and "<endDate>"
    And I can run add contacts using API
    And I initiated specific contacts detail "<externalRefType>"
    And I run get contacts detail using API
    Then I verify "<success>" details using API
    Examples:
      | consumerFirstName  | externalRefType  | phoneNumber    | phoneType1 |phoneType2 |comments       | startDate  | endDate   | success |
      |    Prathap         |   Consumer       | 1234567890     | Primary    | Home      |Test Comments1 | today      | tomorrow  | True    |
      |    Prathap         |   Consumer       | 1234567890     | Secondary  | Cell      |Test Comments2 | yesterday  | tomorrow  | True    |
      |    Prathap         |   Consumer       | 1234567890     | Primary    | Work      |Test Comments3c| today      | tomorrow  | True    |

  @API-CRM-760 @Sujoy
  Scenario Outline: Add new Phone blank field using API
    Given I initiated consumer search API for Contacts
    When I get uiid and correlationId id from contact first name search "<consumerFirstName>"
    And I initiated add new Contact using API
    And I added blank phone information
    And I can run add contacts using API
    And I initiated specific contacts detail "<externalRefType>"
    And I run get contacts detail using API
    Then I verify "<success>" details using API
    Examples:
      | consumerFirstName  | externalRefType  |  success |
      |    Prathap         |   Consumer       |  False   |

  @API-CRM-761 @API-CRM @API-Regression @API-Contacts @Sujoy
  Scenario Outline: View phone number(s) as consumer Contact using API
      Given I initiated consumer search API for Contacts
      When I get uiid and correlationId id from contact first name search "<consumerFirstName>"
      And I initiated Consumer Type Search vai API with "<externalRefType>" type and id "<externalRefid>"
      And I run get contacts detail using API
      Then I can verify consumer phone detail "<success>" and count greater than zero using API
      Examples:
          | consumerFirstName  | externalRefType | externalRefid |success|
          |    Prathap         | Consumer        |               | True  |


  @API-CRM-757-02 @API-CRM @API-Regression @API-Contacts @muhabbat
  Scenario Outline: Add new Address using API
    Given I initiated consumer search API for Contacts
    When I get uiid and correlationId id from contact first name search "<consumerFirstName>"
    And I initiated add new Contact using API
    And I added address information "<externalRefType>","<address1>","<address2>","<city>","<county>","<state>","<zip>","<zipFour>","<type>","<startDate>" and "<endDate>"
    And I can run add contacts using API
    And I initiated specific contacts detail "<externalRefType>"
    When I run get contacts detail using API
    Then I verify "<success>" details using API
    Examples:
      | consumerFirstName | externalRefType | address1 | address2 | city          | county      | state   | zip      | zipFour  | type    | startDate | endDate  | success |
      | Eric              | Consumer        | {random} |          | SomeTest City | Test County | Georgia | {random} | {random} | Mailing | today     | tomorrow | True    |
      | Eric              | Consumer        | {random} |          | SomeTest City | Test County | Georgia | {random} | {random} | Mailing | yesterday | tomorrow | True    |
#      | Eric              | Consumer        | {random} |          | SomeTest City | Test County | Georgia | {random} | {random} | Mailing  | tomorrow  | yesterday | False   |
#      | Eric              | Consumer        |          |          | SomeTest City | Test County | Georgia | {random} |          | Physical | today     | tomorrow  | False   |
#      | Eric              | Consumer        | {random} |          | 111111111     | Test County | Georgia | {random} | {random} | Mailing  | today     | tomorrow  | False   |
#      | Eric              | Consumer        | {random} |          | SomeTest City | 22222222    | Georgia | {random} |          | Physical | tomorrow  | today     | False   |
#      | Eric              | Consumer        | {random} |          | SomeTest City | Test County | 33333   | {random} | {random} | Mailing  | today     | tomorrow  | False   |
#      | Eric              | Consumer        | {random} |          | SomeTest City | Test County | Georgia | SomeZIp  |          | Physical | today     | tomorrow  | False   |


  @API-CRM-757-07 @API-CRM @API-Regression @API-Contacts @muhabbat
  Scenario Outline:Validate status as of Address with different start and end dates
    Given I created a consumer for contact using API
    And I collected some consumer related data from consumer Controller
    And I initiated add new Contact using API
    And I added address information "<externalRefType>","<address1>","<address2>","<city>","<county>","<state>","<zip>","<zipFour>","<type>","<startDate>" and "<endDate>"
    And I can run add contacts using API
    And I initiated specific contacts detail "<externalRefType>"
    Then I verify "<success>" details using API
    Examples:
      | consumerFirstName | externalRefType | address1 | address2 | city          | county      | state   | zip      | zipFour  | type    | startDate | endDate   | success |
      | {random}          | Consumer        | {random} |          | SomeTest City | Test County | Georgia | {random} | {random} | Mailing | today     | tomorrow  | True    |
      | {random}          | Consumer        | {random} |          | SomeTest City | Test County | Georgia | {random} | {random} | Mailing | yesterday | tomorrow  | True    |
#      | {random}          | Consumer        | {random} |          | SomeTest City | Test County | Georgia | {random} | {random} | Mailing | tomorrow  | yesterday | False   |

#  @API-CRM-757-09 @API-CRM @API-Regression @API-Contacts @muhabbat
  Scenario Outline:Verify Audit Trail for newly added email to a consumer
    Given I created a consumer for contact using API
    And I collected some consumer related data from consumer Controller
    And I initiated add new Contact using API
    And I added address information "<externalRefType>","<address1>","<address2>","<city>","<county>","<state>","<zip>","<zipFour>","<type>","<startDate>" and "<endDate>"
    And I can run add contacts using API
    And I initiated specific contacts detail "<externalRefType>"
    Then I verify Address Audit Trail
    Examples:
      | consumerFirstName | externalRefType | address1 | address2 | city          | county      | state   | zip      | zipFour  | type    | startDate | endDate   | success |
      | {random}          | Consumer        | {random} |          | SomeTest City | Test County | Georgia | {random} | {random} | Mailing | today     | tomorrow  | True    |

  @API-CRM-758 @API-CRM @API-Regression @API-Contacts @Sujoy
  Scenario Outline: View Consumer Address list using API
    Given I initiated consumer search API for Contacts
    When I get uiid and correlationId id from contact first name search "<consumerFirstName>"
    And I initiated Consumer Type Search vai API with "<externalRefType>" type and id "<externalRefid>"
    And I run get contacts detail using API
    Then I verify "<success>" details using API

    Examples:
      | consumerFirstName | externalRefType |  externalRefid |  success |
      | Prathap           | Consumer        |                |  True    |

  @API-CRM-759 @API-CRM @API-Regression @API-Contacts @Sujoy
  Scenario Outline: Update Existing Address using API
    Given I initiated consumer search API for Contacts
    When I get uiid and correlationId id from contact first name search "<consumerFirstName>"
    And I initiated Consumer Type Search vai API with "<externalRefType>" type and id "<externalRefid>"
    And I run get contacts detail using API
    And I get latest address information of a the consumer
    And I initiated add new Contact using API
    And I updated existing address information "<address1>","<address2>","<city>","<county>","<state>","<zip>","<zipFour>","<type>","<startDate>" and "<endDate>"
    And I can run add contacts using API
    Then I can verify Address Updates
    Examples:
      | consumerFirstName | externalRefType |  externalRefid | address1 | address2 | city    | county  | state   | zip      | zipFour  | type   | startDate | endDate  | success |
      | APIQUV            | Consumer        |                | {random} | {random} | {random}| {random}|{random} | {random} | {random} |        | today     | tomorrow | True    |

  @API-CRM-764 @CRM-764-01 @API-CRM @API-Regression @API-Contacts @Shruti
  Scenario Outline:Verify Active emails are displayed first followed by inactive emails
    Given I created a consumer for contact using API
    And I collected some consumer related data from consumer Controller
    And I initiated add new Contact using API
    And I added email information "<externalRefType>", "<associatedCaseMember>", "<emailAddress1>", "<start date1>" and "<end date1>"
    And I can run add contacts using API
    And I added email information "<externalRefType>", "<associatedCaseMember>", "<emailAddress2>", "<start date2>" and "<end date2>"
    And I can run add contacts using API
    And I added email information "<externalRefType>", "<associatedCaseMember>", "<emailAddress3>", "<start date3>" and "<end date3>"
    And I can run add contacts using API
    And I initiated specific contacts detail "<externalRefType>"
    Then I verify active emails fetched first followed by inactive emails

    Examples:
      | consumerFirstName  | consumerLastName | externalRefType | associatedCaseMember | emailAddress1         | addressZip1| phoneNumber1 | start date1 |end date1|emailAddress2         | addressZip2| phoneNumber2 | start date2 |end date2|emailAddress3         | addressZip3| phoneNumber3 | start date3 |end date3|
      |    {random}        | {random}         |  Consumer       |                      | {random}             |{random}      | {random}    |  past      | past  | {random}             |{random}    | {random}     |  past       | future  | {random}             |{random}    | {random}     |  past       | future  |

  @API-CRM-765 @API-CRM-765-01 @API-CRM @API-Regression @API-Contacts @Shruti @1029
  Scenario Outline:Validate update to an existing email address
    Given I created a consumer for contact using API
    And I collected some consumer related data from consumer Controller
    And I initiated add new Contact using API
    And I added email information "<externalRefType>", "<associatedCaseMember>", "<emailAddress>", "<start date>" and "<end date>"
    And I can run add contacts using API
    And I initiated specific contacts detail "<externalRefType>"
    Then I run get contacts detail using API
    When I initiated add new Contact using API
    And I updated email information "<newEmailAddress>"
    And I can run add contacts using API
    And I initiated specific contacts detail "<externalRefType>"
    Then I verify updated Email address detail using API "<newEmailAddress>"

    Examples:
      | consumerFirstName  | consumerLastName | externalRefType | associatedCaseMember | emailAddress         | addressZip| phoneNumber | start date |end date| status |newEmailAddress|
      |    {random}        | {random}         |  Consumer       |                      | {random}             |{random}   | {random}    |  today      | future | active|updatedEmail@gmail.com|

  @API-CRM-765 @CRM-765-02 @API-CRM @API-Regression @API-Contacts @Shruti @1012-7567123 @To-DO-1030
  Scenario Outline:Verify Audit Trail for Updated Email
    Given I created a consumer for contact using API
    And I collected some consumer related data from consumer Controller
    And I initiated add new Contact using API
    And I added email information "<externalRefType>", "<associatedCaseMember>", "<emailAddress1>", "<start date1>" and "<end date1>"
    And I can run add contacts using API
    And I added email information "<externalRefType>", "<associatedCaseMember>", "<emailAddress2>", "<start date2>" and "<end date2>"
    And I can run add contacts using API
    And I initiated specific contacts detail "<externalRefType>"
    Then I verify Updated on field is populated
    Examples:
      | consumerFirstName  | consumerLastName | externalRefType | associatedCaseMember | emailAddress1         | addressZip1| phoneNumber1 | start date1 |end date1|emailAddress2         | addressZip2| phoneNumber2 | start date2 |end date2|
      |    {random}        | {random}         |  Consumer       |                      | {random}             |{random}      | {random}    |  past      | future  | {random}             |{random}    | {random}     |  today       | future  |

