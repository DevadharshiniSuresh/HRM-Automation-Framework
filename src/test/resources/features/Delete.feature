Feature: Delete employee

Background:
  Given user launches browser
  When user enters username "Admin"
  And user enters password "admin123"
  And user clicks login button
  And user navigates to PIM module

Scenario Outline: Verify employee deleted
  And user clicks on Add Employee
    And user enters first name "<FirstName>"
    And user enters last name "<LastName>"
    And user clicks Save button
     When user navigates to PIM module
    When user searches employee by name "<FirstName> <LastName>"
  When user deletes employee "<FirstName> <LastName>"
  Then employee "<FirstName> <LastName>" should be deleted

  Examples:
    | FirstName | LastName |
    | John      | Smith    |
    | Martin    | Joe      |