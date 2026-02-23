Feature: Update Employee

  Background: 
    Given user launches browser
    When user enters username "Admin"
    And user enters password "admin123"
    And user clicks login button
    When user navigates to PIM module

  Scenario Outline: Update employee first name
  And user clicks on Add Employee
    And user enters first name "<FirstName>"
    And user enters last name "<LastName>"
    And user clicks Save button
     When user navigates to PIM module
    When user searches employee by name "<FirstName> <LastName>"
   When user opens employee details for "<FirstName> <LastName>"
    And user updates first name to "<UpdatedName>"
    Then employee first name should be updated to "<UpdatedName>"

   Examples:
    | FirstName | LastName | UpdatedName |
    | John      | Smith    | Johnny      |
    | Martin    | Joe      | Rio         | 
