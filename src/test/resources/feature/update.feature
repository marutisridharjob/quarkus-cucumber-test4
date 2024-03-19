# Created by Tahar at 18/03/2024
Feature: Update Using Crud

  Background:
    Given The collection "address" exists
    And The collection "address" contains data from address.csv

  Scenario Template: Create
    Given The collection "<collection>" exists
    And The collection "<collection>" contains data from <dataCsv>
    When Send the request url "<url>" with the method PUT
    And The request body is <requestBodyJsonFile>
    Then Expect the response status code to be: 200
    And Expect the response body to be: <responseBodyJsonFile>
    And Expect the collection "<collection>" to contains data from <dataCsvFile>
    Examples:
      | collection | dataCsv           | url            | requestBodyJsonFile        | responseBodyJsonFile        | dataCsvFile               |
      | client     | client/client.csv | /api/v1/client | client/update/request.json | client/update/response.json | client/update/updated.csv |