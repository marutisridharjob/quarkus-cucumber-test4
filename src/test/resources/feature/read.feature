# Created by Tahar at 02/02/2024
Feature: All crud requests testing

  Background:
    Given The collection "address" exists
    And The collection "address" contains data from address.csv

  Scenario Template: Test CRUD READ (<api>)
    Given The collection "<collection>" exists
    And The collection "<collection>" contains data from <csvFile>
    When Send the request url "<url>" with the method GET
    Then Expect the response status code to be: <status>
    And Expect the response content type to be: <contentType>
    And Expect the response body to be: <jsonFile>
    Examples:
      | api                  | collection | csvFile           | url              | status | contentType | jsonFile             |
      | get all client       | client     | client/client.csv | /api/v1/client   | 200    | JSON        | client/read/all.json |
      | get client with id 1 | client     | client/client.csv | /api/v1/client/1 | 200    | JSON        | client/read/1.json   |
      | get client with id 1 | client     | client/client.csv | /api/v1/client/0 | 404    | JSON        | client/read/0.json   |
