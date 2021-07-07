Feature: Find owners

  Scenario: Find owners page
    Given I go to the main page
    When I click on the link with title "find owners"
    Then I should see the "Find Owners" page

  Scenario: Should find an owner
    Given I go to the find-owners page
    When I fill the field named "lastName" with value "Franklin"
    And I submit the form "search-owner-form"
    Then I should see the "Owner Information" page

  Scenario: Should find multiple owners
    Given I go to the find-owners page
    When I fill the field named "lastName" with value "Davis"
    And I submit the form "search-owner-form"
    Then I should see the "Owners" page

  Scenario: Should find all owners
    Given I go to the find-owners page
    When I fill the field named "lastName" with value ""
    And I submit the form "search-owner-form"
    Then I should see the "Owners" page
    Then I should see all owners

  Scenario: Incorrect data
    Given I go to the find-owners page
    When I fill the field named "lastName" with value "123"
    And I submit the form "search-owner-form"
    Then I should see text "has not been found"
