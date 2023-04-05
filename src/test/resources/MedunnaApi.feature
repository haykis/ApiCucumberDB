Feature:Validate all data performing database testing
  Scenario: All users' info (user, employee, manager and admin) should be retrieved
  by database and validated
    Given make a new register with Api
    When verify my registration with Api
    Then verify my registration with DB