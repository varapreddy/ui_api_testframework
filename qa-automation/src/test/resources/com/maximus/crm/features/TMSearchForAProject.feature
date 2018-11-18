Feature: Tenant Manager Search a Project

  @CRM-294 @CRM-294-01 @regression @muhabbat @tm @tm_regression
  Scenario: Invalid State name search
    Given I logged into Tenant Manager Project list page
    When I search for a project by "state" value "QB"
    Then I should see "No records found" message



  @CRM-294 @CRM-294-02 @regression @muhabbat @tm @tm_regression
  Scenario: Invalid Project name search
    Given I logged into Tenant Manager Project list page
    When I search for a project by "project" value "Invalid"
    Then I should see "No records found" message


  @CRM-294  @CRM-294-03 @regression @muhabbat @tm @tm_regression
  Scenario: Invalid Program name search
    Given I logged into Tenant Manager Project list page
    When I search for a project by "program" value "NotExisting"
    Then I should see "No records found" message


  @CRM-294 @CRM-294-04 @regression @muhabbat @tm @tm_regression
  Scenario: Invalid value ClientAgency search
    Given I logged into Tenant Manager Project list page
    When I search for a project by "client" value "NoAgensy"
    Then I should see "No records found" message


  @CRM-294 @CRM-294-05 @regression @muhabbat @tm @tm_regression @smoke
  Scenario: Valid State name search
    Given I logged into Tenant Manager Project list page
    When I search for a project by "state" value "CO"
    Then I should see all projects with "state" value "CO"

  @CRM-294 @CRM-294-06 @regression @muhabbat @tm @tm_regression @smoke
  Scenario: Valid Project name search
    Given I logged into Tenant Manager Project list page
    When I search for a project by "project" value "test"
    Then I should see all projects with "project" value "Test"

  @CRM-294 @CRM-294-07 @regression @muhabbat @tm @tm_regression @smoke
  Scenario: Valid Program name search
    Given I logged into Tenant Manager Project list page
    When I search for a project by "program" value "test"
    Then I should see all projects with "program" value "Test"

  @CRM-294 @CRM-294-08 @regression @muhabbat @tm @tm_regression @smoke
  Scenario: Valid Client/Agency name search
    Given I logged into Tenant Manager Project list page
    When I search for a project by "client" value "Child Benifit"
    Then I should see all projects with "Client-Agency" value "Child Benifit"

  @CRM-294 @CRM-294-09 @regression @muhabbat @tm @tm_regression
  Scenario: Project name Wild card search
    Given I logged into Tenant Manager Project list page
    When I search for a project by "project" value "W"
    Then I should see the projects according to "project" "W" wild card

  @CRM-294 @CRM-294-10 @regression @muhabbat @tm @tm_regression
  Scenario: Wild card search
    Given I logged into Tenant Manager Project list page
    When I search for a project by "state" value "C"
    Then I should see the projects according to "state" "C" wild card

  @CRM-294 @CRM-294-11 @muhabbat @tm @tm_muted
  Scenario: Program name Wild card search
    Given I logged into Tenant Manager Project list page
    When I search for a project by "program" value "Me"
    Then I should see the projects according to "program" "Me" wild card

  @CRM-294 @CRM-294-12  @muhabbat @tm @tm_muted
  Scenario: ClientAgency Wild card search
    Given I logged into Tenant Manager Project list page
    When I search for a project by "client" value "b"
    Then I should see the projects according to "client" "b" wild card

  @CRM-294 @CRM-294-13 @regression @muhabbat @tm @tm_regression @smoke
  Scenario: No value provided search
    Given I logged into Tenant Manager Project list page
    When I search for a project with empty search fields
    Then I should see "Please provide search criteria" message


  @CRM-294 @CRM-294-14 @regression @muhabbat @tm @tm_regression
  Scenario: Autocomplete Project name search
    Given I logged into Tenant Manager Project list page
    When I search for a project by "project" value "M"
    Then I should see potential "project" with "M" autocomplete
    And I should see all projects with "project" value "M"

  @CRM-294 @CRM-294-15 @regression @muhabbat @tm @tm_regression
  Scenario: Autocomplete Program name search
    Given I logged into Tenant Manager Project list page
    When I search for a project by "program" value "m"
    Then I should see potential "program" with "m" autocomplete
    And I should see all projects with "program" value "m"

  @CRM-294 @CRM-294-16 @regression @muhabbat @tm @tm_regression
  Scenario: Autocomplete State search
    Given I logged into Tenant Manager Project list page
    When I search for a project by "state" value "c"
    Then I should see potential "state" with "c" autocomplete
    And I should see all projects with "state" value "c"


  @CRM-294 @CRM-294-16 @regression @muhabbat @tm @tm_regression
  Scenario: Autocomplete Client/State Agency search
    Given I logged into Tenant Manager Project list page
    When I search for a project by "client" value "m"
    Then I should see potential "client" with "m" autocomplete
    And I should see all projects with "client" value "m"