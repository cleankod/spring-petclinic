Feature: Find owners

  Scenario: Find owners page
    Given I go to the main page
    When I click on the link with title "find owners"
    Then I should see the "Find Owners" page

  Scenario: Should find an owner
    When I fill the field named "lastName" with value "Franklin"
    And I submit the form "search-owner-form"
    Then I should see the "Owner Information" page

  Scenario: Should find multiple owners
    Given I go to the find-owners page
    When I fill the field named "lastName" with value "Davis"
    And I submit the form "search-owner-form"
    Then I should see the "Owners" page
    # Add additional checks here.
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
    Then I should see the "Owners" page
    Then I should see "has not been found"

  Scenario: Add new owner with correct date
    Given I go to the new-owner page
    When I fill the field named "firstName" with value "Andi"
    And I fill the field named "lastName" with value "Betty"
    And I fill the field named "address" with value "26 Commerce St."
    And I fill the field named "city" with value "McFarland"
    And I fill the field named "telephone" with value "6085557689"
    And I submit the form "add-owner"
    Then I should see the "Owner Information" page

  Scenario: Add new owner with empty date
    Given I go to the new-owner page
    When I fill the field named "firstName" with value ""
    And I fill the field named "lastName" with value ""
    And I fill the field named "address" with value ""
    And I fill the field named "city" with value ""
    And I fill the field named "telephone" with value ""
    And I submit the form "add-owner"
    Then I should see the "Owner" page
    And I should see the "must not be empty" under all field without telephone
    And I should see the "numeric value out of bounds (<10 digits>.<0 digits> expected)" under telephone field
