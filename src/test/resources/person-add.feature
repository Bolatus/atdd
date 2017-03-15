Feature: person can be added
  Scenario: client submits POST request at /person
    When the client submits POST request at /person following data {"name":"Bolat"}
    Then the client receives status code of 200
    And the client receives id of added person 1