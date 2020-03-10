Feature: Login

  Scenario: User login
    Given I load SET Forms portal
    When I login with the following credentials:
      | email                      | password   |
      | pablo.sahonero@dharbor.com | Password1! |
    Then the user should be logged in
      And the Home page should be displayed