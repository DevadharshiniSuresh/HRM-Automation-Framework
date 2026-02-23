Feature: Search Employee

  Background: 
    Given user launches browser
    When user enters username "Admin"
    And user enters password "admin123"
    And user clicks login button
    When user navigates to PIM module

  Scenario Outline: Search employee added by name
    And user clicks on Add Employee
    And user enters first name "<FirstName>"
    And user enters last name "<LastName>"
    And user clicks Save button
     When user navigates to PIM module
    When user searches employee by name "<FirstName> <LastName>"
    Then employee "<FirstName> <LastName>" should be displayed in results

    Examples:
    | FirstName | LastName |
    | John      | Smith    |
    | Martin    | Joe      |
