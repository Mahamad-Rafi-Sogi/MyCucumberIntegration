# 👩‍💼 Employee Management System

This is a full-stack **Spring Boot** application that provides a REST API for managing employees, with test coverage using **JUnit 5** and **Cucumber BDD**.

---

## 🚀 Features

- Create, Read, Delete Employee
- RESTful API using Spring Boot
- In-memory H2 Database
- BDD Testing with Cucumber
- Unit & Integration Testing using JUnit and Spring Test

---

## 📦 Tech Stack

| Layer         | Technology                |
|---------------|----------------------------|
| Backend       | Java 17, Spring Boot 3     |
| Testing       | JUnit 5, Cucumber, Mockito |
| Database      | H2 (in-memory)             |
| Build Tool    | Maven                      |
| API Client    | RestTemplate               |

---

## 📁 Project Structure

employee-management-system/
├── src/
│ ├── main/
│ │ └── java/com/example/ems/
│ │ ├── controller/
│ │ ├── entity/
│ │ ├── repository/
│ │ ├── service/
│ ├── test/
│ │ ├── java/com/example/ems/
│ │ │ ├── stepdefinitions/
│ │ │ ├── RunCucumberTest.java
│ │ └── resources/features/
│ │ └── employee.feature
├── pom.xml
└── README.md

---

## 🧪 Running the Tests

### 🟢 Run All Unit + Cucumber Tests

```bash
mvn clean test
Make sure your Spring Boot application is running locally on port 8080 before running Cucumber tests.

🛠️ API Endpoints
Method	Endpoint	Description
GET	/employees	Get all employees
POST	/employees	Add a new employee
DELETE	/employees/{id}	Delete employee by ID

🧠 Sample JSON (POST Request)
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com"
}
✅ BDD Feature (Cucumber)
Located at: src/test/resources/features/employee.feature

gherkin
Copy
Edit
Feature: Employee management

  Scenario: Create a new employee
    Given the employee data is prepared
    When the employee is submitted to the API
    Then the employee should be created successfully

  Scenario: Get all employees
    When the client requests all employees
    Then the response should contain a list of employees

  Scenario: Delete an employee
    Given a new employee is created for deletion
    When the employee is deleted using API
    Then the employee should be deleted successfully
📚 Prerequisites
Java 17+

Maven 3.6+

IDE (e.g. IntelliJ IDEA / VS Code)

🤝 Contributing
Feel free to fork this repo, create a new branch, and submit a pull request!
