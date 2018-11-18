Feature: API-Tenant Manager: Project Controller

@API-CRM-290 @API-TM @API-Regression @API-Project @Sujoy
Scenario: Create Project with blank fields using API
  Given I initiated Create Project API
  When I can provide all blank project information
  Then I run the create empty project API

@API-CRM-292 @API-TM @API-Regression @API-Project @Sujoy
Scenario: Validate Project Lists using API
  Given I initiated Get Project List API
  When I can provide all blank project information
  And I run the get project list API
  Then I can search the project by project name to validate is success

@API-CRM-291 @API-TM @API-Regression @API-Project @Sujoy
Scenario Outline: Validate Duplicate Project is not created by API
  Given I initiated Create Project API
  When I can provide project information to create a "<Provisioning>" project on "<state>"
  And I run the create project API
  Then I can search the project by project name to validate is "<success>"
  Given I initiated Create Project API
  And I run the create project API again
  Then I can verify the project error message on API response
  Examples:
    |state|Provisioning|success|
    |	MN  |Active      |TRUE	 |
    |	AL  |InActive    |TRUE	 |
    |	ND  |Pending     |TRUE	 |

@API-CRM-289 @API-CRM-290 @API-TM @API-Regression @API-Project @Sujoy
Scenario Outline: Create Project with end date greater than today using API
  Given I initiated Create Project API
  When I can provide project information to create a "<Provisioning>" project on "<state>" with end date greater than today
  And I run the create project API
  Then I can search the project by project name to validate is "<success>"
  Examples:
    |state|Provisioning|success|
    |	MN  |Active      |TRUE	 |
    |	AL  |InActive    |TRUE	 |
    |	ND  |Pending     |TRUE	 |

@API-CRM-289 @API-CRM-290 @API-TM @API-Regression @API-Project @Sujoy
Scenario Outline: Create Project using API
  Given I initiated Create Project API
  When I can provide project information to create a "<Provisioning>" project on "<state>"
  And I run the create project API
  Then I can search the project by project name to validate is "<success>"
  Examples:
    |state|Provisioning|success|
    |	AK  |Active      |TRUE	 |
    |	AL  |InActive    |TRUE	 |
    |	AR  |Pending     |TRUE	 |
    |	AS  |Active      |TRUE	 |
    |	AZ  |InActive    |TRUE	 |
    |	CA  |Pending     |TRUE	 |
    |	CO  |Active      |TRUE	 |
    |	CT  |InActive    |TRUE	 |
    |	DC  |Pending     |TRUE	 |
    |	DE  |Active      |TRUE	 |
    |	FL  |InActive    |TRUE	 |
    |	FM  |Pending     |TRUE	 |
    |	GA  |Active      |TRUE	 |
    |	GU  |InActive    |TRUE	 |
    |	HI  |Pending     |TRUE	 |
    |	IA  |Active      |TRUE	 |
    |	ID  |InActive    |TRUE	 |
    |	IL  |Pending     |TRUE 	 |
    |	IN  |Active      |TRUE	 |
    |	KS  |InActive    |TRUE	 |
    |	KY  |Pending     |TRUE	 |
    |	LA  |Active      |TRUE	 |
    |	MA  |InActive    |TRUE	 |
    |	MD  |Pending     |TRUE   |
    |	ME  |Active      |TRUE	 |
    |	MH  |InActive    |TRUE	 |
    |	MI  |Pending     |TRUE	 |
    |	MN  |Active      |TRUE	 |
    |	MO  |InActive    |TRUE	 |
    |	MP  |Pending     |TRUE	 |
    |	MS  |Active      |TRUE	 |
    |	MT  |InActive    |TRUE	 |
    |	NC  |Pending     |TRUE	 |
    |	ND  |Active      |TRUE	 |
    |	NE  |InActive    |TRUE	 |
    |	NH  |Pending     |TRUE	 |
    |	NJ  |Active      |TRUE	 |
    |	NM  |InActive    |TRUE	 |
    |	NV  |Pending     |TRUE	 |
    |	NY  |Active      |TRUE	 |
    |	OH  |InActive    |TRUE	 |
    |	OK  |Pending     |TRUE	 |
    |	OR  |Active      |TRUE	 |
    |	PA  |InActive    |TRUE	 |
    |	PR  |Pending     |TRUE	 |
    |	PW  |Active      |TRUE	 |
    |	RI  |InActive    |TRUE	 |
    |	SC  |Pending     |TRUE	 |
    |	SD  |Active      |TRUE	 |
    |	TN  |InActive    |TRUE	 |
    |	TX  |Pending     |TRUE	 |
    |	UT  |Active      |TRUE	 |
    |	VA  |InActive    |TRUE	 |
    |	VI  |Pending     |TRUE 	 |
    |	VT  |Active      |TRUE	 |
    |	WA  |InActive    |TRUE	 |
    |	WI  |Pending     |TRUE	 |
    |	WV  |Active      |TRUE	 |
    |	WY  |InActive    |TRUE	 |

@API-CRM-294 @API-TM @API-Regression @API-Project @Sujoy
  Scenario Outline: Search Projects using API
      Given I initiated Search Project API
      When I can Search Project by "<Node>" with value "<value>"
      And I run the search project API
      Then I can verify "<Node>" with value "<value>" on project API response will be "<success>"
      Examples:
          | Node           | value        | success |
          | state          | ND           | True    |
          | state          | Nn           | True    |
          | programName    | test         | True    |
          | programName    | sadklfjsfsk  | True    |
          | projectName    | Child        | True    |
          | projectName    | sdkfljkljsd  | True    |
          | stateAgencyName| CHIPeb       | True    |
          | stateAgencyName| sdkjfsdklfjds| True    |

  @API-CRM-295 @API-TM @API-Regression @API-Project @Sujoy
  Scenario Outline: Search Projects using API
    Given I initiated Search Project API
    When I can Search Project by "<Node1>" with value "<value1>", "<Node2>" with value "<value2>", "<Node3>" with value "<value3>" and "<Node4>" with value "<value4>"
    And I run the search project API
    Then I can verify on project search API response will be "<success>"
    Examples:
      | Node1          | value1| Node2| value2 | Node3          | value3   | Node4         | value4               | success|
      | projectName    | Child | state| MD     | programName    |          |stateAgencyName|                      | True   |
      | projectName    |       | state| CA     | programName    | Medicaid |stateAgencyName|                      | True   |
      | projectName    | Child | state|        | programName    | Medicaid |stateAgencyName|                      | True   |
      | projectName    | Child | state|        | programName    |          |stateAgencyName| Illonis State Agency | True   |
      | projectName    |       | state| CA     | programName    |          |stateAgencyName| Illonis State Agency | True   |
      | projectName    |       | state|        | programName    | Medicaid |stateAgencyName| Illonis State Agency | True   |
      | projectName    | Child | state| CA     | programName    | Medicaid |stateAgencyName|                      | True   |
      | projectName    | Child | state| CA     | programName    |          |stateAgencyName| Illonis State Agency | True   |
      | projectName    | Child | state|        | programName    | Medicaid |stateAgencyName| Illonis State Agency | True   |
      | projectName    |       | state| CA     | programName    | Medicaid |stateAgencyName| Illonis State Agency | True   |
      | projectName    | Child | state| CA     | programName    | Medicaid |stateAgencyName| Illonis State Agency | True   |
      | projectName    | sdsdd | state| CA     | programName    | Medicaid |stateAgencyName| Illonis State Agency | True   |
      | projectName    | Child | state| TT     | programName    | Medicaid |stateAgencyName| Illonis State Agency | True   |
      | projectName    | Child | state| CA     | programName    | Medicare |stateAgencyName| Illonis State Agency | True   |
      | projectName    | Child | state| CA     | programName    | Medicaid |stateAgencyName| Texas State Agency   | True   |


@API-CRM-291 @API-TM @API-Regression @API-Project @Sujoy
Scenario Outline: Search a project by Project ID using API
  Given I initiated Search Project API
  When I can Search Project by "<Node>" with value "<value>"
  And I run the search project API
  And I can get ProjectID
  Given I initiated Search Project API By Project ID ""
  And I run the search project API By ProjectID
  Then I can verify Project ID on project search API response will be "<success>"
  Examples:
    | Node           | value        | success |
    | state          | Az           | True    |


@API-CRM-291 @API-TM @API-Regression @API-Project @Sujoy
Scenario Outline: Get approver detail if a project using API
  Given I initiated project approver API by project name "<projectName>"
  When I can get the project approver detail
  Then I can verify get user approver detail API response will be "<success>"
  Examples:
    | projectName    | success |
    | Test Project 1 | True    |

