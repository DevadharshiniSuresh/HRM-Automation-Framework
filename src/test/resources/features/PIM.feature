Feature: PIM Module - Employee Management

  Background: 
    Given user launches browser
    When user enters username "Admin"
    And user enters password "admin123"
    And user clicks login button

  Scenario Outline: Add a new employee
    When user navigates to PIM module
    And user clicks on Add Employee
    And user enters first name "<Firstname>"
    And user enters last name "<Lastname>"
    And user clicks Save button
    Then employee should be added successfully

    Examples: 
      | Firstname | Lastname |
      | John      | Smith    |
      | Martin    | joe      |
