Feature: register succes messaga
  Background:
    Given go to  and register page
  Scenario: SSN is valid and nine rigid
    When enter ssn nummer
    Then check ssn valid and nine rigid
  Scenario: first name
    When enter first name
    Then check first name space and value
  Scenario: last name is required and contains chars and valid
    When enter last name
    Then Check it is not empty, contains chars and not contains nummer
