Feature: medicamento feature

  Scenario: Como usuario acceder a la seccion de medicamentos y agregar alguno
    Given I wait for 2 seconds
    When I go back
    And I swipe left
    And I press "Medicines"
    And I press view with id "add_button"
    And I press "Cancel"
    And I enter "dolex" into input field number 1
    And I press the enter button
    And I press view with id "med_presentation_2"
    And I press view with id "add_button"
    And I wait for 2 seconds
    And I swipe left
    And I press "Home"
    And I swipe left
    And I press "Medicines"
    And I wait for 2 seconds
    And I press "dolex"
    And I enter "x" into input field number 1
    And I press the menu hey
    And I press "Remove"
    And I press "Yes"
    And I wait for 2 seconds
    Then I should see "med created!"

