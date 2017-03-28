Feature: person endpoint
  Scenario: client submits POST request at /person
    When the client submits POST request at /person following data {"name":"Bolat"}
    Then after POST request the client receives status code of 200
    And the client receives id of added person 11

  Scenario: client submits empty POST request at /person
      When the client submits POST request at /person following data -
      Then after POST request the client receives status code of 400

  Scenario: client gets all people
    When the client submits GET request at /person
    Then after GET request the client receives status code of 200
    And the client receives json [{"id":1,"name":"Max"},{"id":2,"name":"John"}]

  Scenario: client gets person id=1
      When the client submits GET request at /person with id 1
      Then after GET request the client receives status code of 200
      And the client receives json {"id":1,"name":"Max"}

  Scenario: client gets person id=4 which doesn't exist
      When the client submits GET request at /person with id 4
      Then after GET request the client receives status code of 404