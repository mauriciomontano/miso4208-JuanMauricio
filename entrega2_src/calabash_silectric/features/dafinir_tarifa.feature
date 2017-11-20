Feature: tarifa feature

  Scenario: Como usuario quiero definir una tarifa por watt
    Given I wait for 2 seconds
    When I clear input field number 1
    And I enter "99999999999999999999999999999999999999999999999999999" into input field number 1
    And I press view with id "saveElectricFeeButton"
    And I wait for 2 seconds
    And I press the menu key
    And I press "Preferences"
    And I enter text "5" into field with id "usage_fee_per_kwh_edit_text"
    And I press view with id "saveElectricFeeButton"
    Then I should see "Resultados"
