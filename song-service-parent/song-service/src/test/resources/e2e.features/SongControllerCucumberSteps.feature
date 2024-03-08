Feature: End to End tests for song-service
  As a user I wanna perform http requests to manage audio metadata
  Scenario: Adding song metadata
    Given I sent POST request with valid song metadata in body to /songs
    Then I got response status code: 200
    And I received saved song metadata id
    Then I able to GET /songs/id