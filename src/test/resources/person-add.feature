Feature: person can be added
  Scenario: client submits GET request at /person return 200
    When the client submits GET request at /person
    Then the client receives status code of 200