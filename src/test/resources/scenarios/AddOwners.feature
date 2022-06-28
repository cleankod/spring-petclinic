Feature: Add owners

  Scenario: Add owners page
    Given I go to the main page
    When I click on the link with title "find owners"
    And I click on the "Add Owner" button
    Then I should see the "Owner" page
    And I should see "add-owner-form" form

  Scenario: Should add owner
    Given I go to the add-owner page
    When I fill the field named "firstName" with value "John"
    And I fill the field named "lastName" with value "Doe"
    And I fill the field named "address" with value "Sample Street 82/1a"
    And I fill the field named "city" with value "Doe City"
    And I fill the field named "telephone" with value "111222333"
    And I submit the form "add-owner-form"
    Then I should see the "Owner Information" page
    And I should see name with value "John Doe"
    And I should see "Address" with value "Sample Street 82/1a"
    And I should see "City" with value "Doe City"
    And I should see "Telephone" with value "111222333"

  Scenario: Should not add owner with every field empty
    Given I go to the add-owner page
    When I fill the field named "firstName" with value ""
    And I fill the field named "lastName" with value ""
    And I fill the field named "address" with value ""
    And I fill the field named "city" with value ""
    And I fill the field named "telephone" with value ""
    And I submit the form "add-owner-form"
    Then I should see field "firstName" with validation message:
      """
      must not be empty
      """
    And I should see field "lastName" with validation message:
      """
      must not be empty
      """
    And I should see field "address" with validation message:
      """
      must not be empty
      """
    And I should see field "city" with validation message:
      """
      must not be empty
      """
    And I should see field "telephone" with validation message:
      """
      must not be empty
      numeric value out of bounds (<10 digits>.<0 digits> expected)
      """

  Scenario: Should not add owner with no numbers in telephone field
    Given I go to the add-owner page
    When I fill the field named "firstName" with value "John"
    And I fill the field named "lastName" with value "Doe"
    And I fill the field named "address" with value "Sample Street 82/1a"
    And I fill the field named "city" with value "Doe City"
    And I fill the field named "telephone" with value "Sample"
    And I submit the form "add-owner-form"
    Then I should see field "telephone" with validation message:
      """
      numeric value out of bounds (<10 digits>.<0 digits> expected)
      """

  Scenario: Should not add owner with 11 numbers in telephone field
    Given I go to the add-owner page
    When I fill the field named "firstName" with value "John"
    And I fill the field named "lastName" with value "Doe"
    And I fill the field named "address" with value "Sample Street 82/1a"
    And I fill the field named "city" with value "Doe City"
    And I fill the field named "telephone" with value "12345678901"
    And I submit the form "add-owner-form"
    Then I should see field "telephone" with validation message:
      """
      numeric value out of bounds (<10 digits>.<0 digits> expected)
      """

  Scenario: Should add owner with 10 numbers in telephone field
    Given I go to the add-owner page
    When I fill the field named "firstName" with value "John"
    And I fill the field named "lastName" with value "Doe"
    And I fill the field named "address" with value "Sample Street 82/1a"
    And I fill the field named "city" with value "Doe City"
    And I fill the field named "telephone" with value "1234567890"
    And I submit the form "add-owner-form"
    Then I should see the "Owner Information" page
    And I should see name with value "John Doe"
    And I should see "Address" with value "Sample Street 82/1a"
    And I should see "City" with value "Doe City"
    And I should see "Telephone" with value "1234567890"

  Scenario: Should add owner with 30 characters in each field
    Given I go to the add-owner page
    When I fill the field named "firstName" with value "VeryLongNameVeryLongNameVeryLo"
    And I fill the field named "lastName" with value "VeryLongLastNameVeryLongLastNa"
    And I fill the field named "address" with value "VeryLongStreetVeryLongStreetVe"
    And I fill the field named "city" with value "VeryLongCityVeryLongCityVeryLo"
    And I fill the field named "telephone" with value "1234567890"
    And I submit the form "add-owner-form"
    Then I should see the "Owner Information" page
    And I should see name with value "VeryLongNameVeryLongNameVeryLo VeryLongLastNameVeryLongLastNa"
    And I should see "Address" with value "VeryLongStreetVeryLongStreetVe"
    And I should see "City" with value "VeryLongCityVeryLongCityVeryLo"
    And I should see "Telephone" with value "1234567890"
