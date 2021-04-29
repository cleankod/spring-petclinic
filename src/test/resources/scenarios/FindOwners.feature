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
    And I should see results relative to "Davis" query

  Scenario: Should find all owners
    Given I go to the find-owners page
    When I fill the field named "lastName" with value ""
    And I submit the form "search-owner-form"
    Then I should see the "Owners" page
    Then I should see all existing users found

  Scenario: Should find all owners with last name staring of the same letters
    Given I go to the find-owners page
    When I fill the field named "lastName" with value "Es"
    And I submit the form "search-owner-form"
    Then I should see the "Owners" page
    And I should see results relative to "Es" query

  Scenario: Should not find owner by first name
    Given I go to the find-owners page
    When I fill the field named "lastName" with value "George"
    And I submit the form "search-owner-form"
    Then I should see the "Find Owners" page
    Then I should see 'has not been found' alert

  Scenario: Should not find owner by pet name
    Given I go to the find-owners page
    When I fill the field named "lastName" with value "Leo"
    And I submit the form "search-owner-form"
    Then I should see the "Find Owners" page
    Then I should see 'has not been found' alert
