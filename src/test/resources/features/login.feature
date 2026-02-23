Feature: Login Functionality

  Background: 
    Given user launches browser

  Scenario: Verify valid login
    Given user launches browser
    When user enters username "Admin"
    And user enters password "admin123"
    And user clicks login button
    Then user should see dashboard

  Scenario: Login with invalid password
    Given user launches browser
    When user enters username "Admin"
    And user enters password "wrong123"
    And user clicks login button
    Then user should see error message "Invalid credentials"
