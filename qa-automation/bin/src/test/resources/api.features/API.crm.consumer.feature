Feature: API-CRM: Consumer Controller

  @API-CRM-300 @API-CRM @API-Regression @API-Consumer @Sujoy
  Scenario Outline: Create Consumer with mandatory fields using API
    Given I initiated Create Consumer API
    When I can provide consumer information with "<consumerFirstName>" "<consumerLastName>" "<phoneNumber>" and "<addressZip>"
    And I can run create consumer API
    Then I can verify consumer consumerLastName with value "<consumerLastName>" on API response
    Examples:
      | consumerFirstName  | consumerLastName |phoneNumber| addressZip |
      |    {random}        | {random}         |{random}   |  {random}  |

  @API-CRM-300 @API-CRM @API-Regression @API-Consumer @Sujoy
  Scenario Outline: Verify there is only one unique customer id using API
    Given I initiated Consumer Search API
    When I can search consumer by "<Node>" with value "<value>"
    And I run the consumer search API
    Then I can verify consumer consumerId on API response
    Examples:
      | Node                      | value   |
      | consumerFirstName         | prathap |

  @API-CRM-482 @API-CRM @API-Regression @API-Consumer @Sujoy
  Scenario Outline: Search Consumer using API
    Given I initiated Consumer Search API
    When I can search consumer by "<Node>" with value "<value>"
    And I run the consumer search API
    Then I can verify consumer "<Node>" with value "<value>" on API response
    Examples:
      | Node                      | value         |
      | consumerSSN               | 787878878	  |
      | consumerFirstName         | Prathap       |
      | consumerLastName          | Bellary       |
      | consumerIdentificationNo  | 654356789     |

  @API-CRM-482 @API-CRM @API-Regression @API-Consumer @Sujoy
  Scenario: Validate duplicate Consumer is not created using API
    Given I created a consumer using API
    And I initiated Create Consumer API
    When I can provide same consumer information that is created earlier
    And I can run try to create duplicate consumer via API
    Then I can verify on consumer search API response


  @API-CRM-482 @API-CRM @API-Regression @API-Consumer @Sujoy
  Scenario Outline: Create Consumer for all US states using API
    Given I initiated Create Consumer API
    When I can provide consumer address State with "<State>"
    And I can run create consumer API
    Then I can verify on consumer search API response
    Examples:
      |State|
      |	AK  |
      |	AL  |
      |	AR  |
      |	AS  |
      |	AZ  |
      |	CA  |
      |	CO  |
      |	CT  |
      |	DC  |
      |	DE  |
      |	FL  |
      |	FM  |
      |	GA  |
      |	GU  |
      |	HI  |
      |	IA  |
      |	ID  |
      |	IL  |
      |	IN  |
      |	KS  |
      |	KY  |
      |	LA  |
      |	MA  |
      |	MD  |
      |	ME  |
      |	MH  |
      |	MI  |
      |	MN  |
      |	MO  |
      |	MP  |
      |	MS  |
      |	MT  |
      |	NC  |
      |	ND  |
      |	NE  |
      |	NH  |
      |	NJ  |
      |	NM  |
      |	NV  |
      |	NY  |
      |	OH  |
      |	OK  |
      |	OR  |
      |	PA  |
      |	PR  |
      |	PW  |
      |	RI  |
      |	SC  |
      |	SD  |
      |	TN  |
      |	TX  |
      |	UT  |
      |	VA  |
      |	VI  |
      |	VT  |
      |	WA  |
      |	WI  |
      |	WV  |
      |	WY  |
