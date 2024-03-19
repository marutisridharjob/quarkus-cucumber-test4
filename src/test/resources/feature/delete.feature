# Created by Tahar at 18/03/2024
Feature: Delete Using Crud

  Scenario Template: Delete
    Given The collection "<collection>" exists
    And The collection "<collection>" contains data from <csvFile>
    When Send the request url "<url>" with the method DELETE
    Then Expect the response status code to be: 204
    And Expect the collection "<collection>" to not contains data from <dataCsvFile>
    Examples:
      | collection | csvFile           | url              | dataCsvFile               |
      | client     | client/client.csv | /api/v1/client/1 | client/delete/deleted.csv |