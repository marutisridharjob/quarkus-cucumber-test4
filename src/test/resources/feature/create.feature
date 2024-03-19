# Created by Tahar at 18/03/2024
Feature: Create Using Crud

  Background:
    Given The collection "address" exists
    And The collection "address" contains data from address.csv

  Scenario Template: Create
    Given The collection "<collection>" exists
    When Send the request url "<url>" with the method POST
    And The request body is <requestBodyJsonFile>
    Then Expect the response status code to be: 201
    And Expect the response body to be: <responseBodyJsonFile>
    And Expect the collection "<collection>" to contains data from <dataCsvFile>
    Examples:
      | collection | url            | requestBodyJsonFile        | responseBodyJsonFile        | dataCsvFile               |
      | client     | /api/v1/client | client/create/request.json | client/create/response.json | client/create/created.csv |