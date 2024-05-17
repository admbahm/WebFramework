Feature: My Feature
  As a user,
  I want to perform some actions on a website,
  So that I can verify the expected results.

  Scenario Outline: Verify login functionality
    Given I am on the login page
    When I enter "<username>" and "<password>"
    And I click the "Login" button
    Then I should see the message "<expected_message>"

    Examples: Login Credentials
      | username   | password   | expected_message     |
      | testuser   | testpass   | Welcome, testuser!       |
