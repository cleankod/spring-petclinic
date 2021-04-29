Feature: Add owners

  Scenario: Add owner with valid data
    Given I go to the new-owner page
    When I fill the field named "firstName" with value "test"
    And I fill the field named "lastName" with value "test"
    And I fill the field named "address" with value "knowhere, 1"
    And I fill the field named "city" with value "nowhere"
    And I fill the field named "telephone" with value "1234567890"
    And I click the button with text "Add Owner"
    Then I should see the "Owner Information" page
    And I should see owner with the "Name" as "test test"
    And I should see owner with the "Address" as "knowhere, 1"
    And I should see owner with the "City" as "nowhere"
    And I should see owner with the "Telephone" as "1234567890"

  Scenario: Add owner with empty fields
    Given I go to the new-owner page
    When I fill the field named "firstName" with value ""
    And I fill the field named "lastName" with value ""
    And I fill the field named "address" with value ""
    And I fill the field named "city" with value ""
    And I fill the field named "telephone" with value ""
    And I click the button with text "Add Owner"
    Then I should see the "Owner" page
    And I should see "firstName" field with "must not be empty" alert
    And I should see "lastName" field with "must not be empty" alert
    And I should see "address" field with "must not be empty" alert
    And I should see "city" field with "must not be empty" alert
    And I should see "telephone" field with "numeric value out of bounds (<10 digits>.<0 digits> expected)\nmust not be empty" alert

  Scenario: Add owner with only spaces fields
    Given I go to the new-owner page
    When I fill the field named "firstName" with value "          "
    And I fill the field named "lastName" with value "          "
    And I fill the field named "address" with value "          "
    And I fill the field named "city" with value "          "
    And I fill the field named "telephone" with value "          "
    And I click the button with text "Add Owner"
    Then I should see the "Owner" page
    And I should see "firstName" field with "must not be empty" alert
    And I should see "lastName" field with "must not be empty" alert
    And I should see "address" field with "must not be empty" alert
    And I should see "city" field with "must not be empty" alert
    And I should see "telephone" field with "numeric value out of bounds (<10 digits>.<0 digits> expected)\nmust not be empty" alert

  Scenario: Add owner with the first name with over 30 characters
    Given I go to the new-owner page
    When I fill the field named "firstName" with value "DavisDavisDavisDavisDavisDavisDavis"
    And I fill the field named "lastName" with value "lname"
    And I fill the field named "address" with value "knowhere, 1"
    And I fill the field named "city" with value "nowhere"
    And I fill the field named "telephone" with value "1234567890"
    And I click the button with text "Add Owner"
    Then I should see the "Owner" page
    And I should see "firstName" field with "numeric value out of bounds (<30 digits>.<0 digits> expected)\nmust not be empty" alert

  Scenario: Add owner with the last name with over 30 characters
    Given I go to the new-owner page
    When I fill the field named "firstName" with value "fname"
    And I fill the field named "lastName" with value "DavisDavisDavisDavisDavisDavisDavis"
    And I fill the field named "address" with value "knowhere, 1"
    And I fill the field named "city" with value "nowhere"
    And I fill the field named "telephone" with value "1234567890"
    And I click the button with text "Add Owner"
    Then I should see the "Owner" page
    And I should see "lastName" field with "numeric value out of bounds (<30 digits>.<0 digits> expected)\nmust not be empty" alert

  Scenario: Add owner with the telephone number with over 10 characters
    Given I go to the new-owner page
    When I fill the field named "firstName" with value "fname"
    And I fill the field named "lastName" with value "lanme"
    And I fill the field named "address" with value "knowhere, 1"
    And I fill the field named "city" with value "nowhere"
    And I fill the field named "telephone" with value "12345678901011"
    And I click the button with text "Add Owner"
    Then I should see the "Owner" page
    And I should see "lastName" field with "numeric value out of bounds (<10 digits>.<0 digits> expected)" alert

  Scenario: Add existing owner
    Given I go to the new-owner page
    When I fill the field named "firstName" with value "George"
    And I fill the field named "lastName" with value "Franklin"
    And I fill the field named "address" with value "110 W. Liberty St."
    And I fill the field named "city" with value "Madison"
    And I fill the field named "telephone" with value "6085551023"
    And I click the button with text "Add Owner"
    Then I should see the "Owner" page
    And I should see "firstName" field with "user with the first name already exist" alert
    And I should see "lastName" field with "user with the last name already exist" alert
    And I should see "address" field with "user with the address already exist" alert
