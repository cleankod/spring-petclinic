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

  Scenario: Should find all owners
    Given I go to the find-owners page
    When I fill the field named "lastName" with value ""
    And I submit the form "search-owner-form"
    Then I should see the "owners" table
    And I Should see all owners found

  Scenario: Should not find any results
    Given I go to the find-owners page
    When I fill the field named "lastName" with value "RandomLastNameThatDoesNotExist"
    And I submit the form "search-owner-form"
    Then I should see "has not been found" message under "lastName" field

  Scenario: Should not find owner by name
    Given I go to the find-owners page
    When I fill the field named "lastName" with value "George"
    And I submit the form "search-owner-form"
    Then I should see "has not been found" message under "lastName" field

  Scenario: Should not find owner by name and surname
    Given I go to the find-owners page
    When I fill the field named "lastName" with value "George Franklin"
    And I submit the form "search-owner-form"
    Then I should see "has not been found" message under "lastName" field

  Scenario: Should redirect to the Owner Information page after clicking on owner in the table
    Given I go to the find-owners page
    When I fill the field named "lastName" with value ""
    And I submit the form "search-owner-form"
    And I click on the owner
    Then I should see the "Owner Information" page

