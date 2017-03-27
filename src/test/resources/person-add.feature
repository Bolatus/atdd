Feature: person endpoint
  Scenario: client submits POST request at /person
    When the client submits POST request at /person following data {"name":"Bolat"}
    Then after POST request the client receives status code of 200
    And the client receives id of added person 11

  Scenario: client submits GET request at /person return 200
    When the client submits GET request at /person
    Then after GET request the client receives status code of 200
    And the client receives arraylist json [{"id":1,"name":"Max"},{"id":2,"name":"John"}]