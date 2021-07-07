Feature: Add owners

  Scenario: Add new owner with correct date
    Given I go to the new-owner page
    When I fill the field named "firstName" with value "Andi"
    And I fill the field named "lastName" with value "Betty"
    And I fill the field named "address" with value "26 Commerce St."
    And I fill the field named "city" with value "McFarland"
    And I fill the field named "telephone" with value "6085557689"
    And I click the button Add Owner
    Then I should see the "Owner Information" page

  Scenario: Add new owner with empty date
    Given I go to the new-owner page
    When I fill the field named "firstName" with value ""
    And I fill the field named "lastName" with value ""
    And I fill the field named "address" with value ""
    And I fill the field named "city" with value ""
    And I fill the field named "telephone" with value ""
    And I click the button Add Owner
    Then I should see the "Owner" page
    And I should see the "must not be empty" under all field without telephone
    And I should see the "numeric value out of bounds (<10 digits>.<0 digits> expected)" under telephone field
  # Add scenarios covering all cases.
