Feature: Employee management

  Scenario: Add a new employee
    Given the employee data is prepared
    When the employee is submitted to the API
    Then the employee should be created successfully

  Scenario: Retrieve all employees
    When the client requests all employees
    Then the response should contain a list of employees


  Scenario: Delete an employee
    Given a new employee is created for deletion
    When the employee is deleted using API
    Then the employee should be deleted successfully