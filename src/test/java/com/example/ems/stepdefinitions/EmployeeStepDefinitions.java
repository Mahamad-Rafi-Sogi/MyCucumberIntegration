package com.example.ems.stepdefinitions;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class EmployeeStepDefinitions {

    private final String baseUrl = "http://localhost:8080/employees";
    private final RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<String> response;
    private Map<String, Object> employee;

    @Given("the employee data is prepared")
    public void the_employee_data_is_prepared() {
        employee = new HashMap<>();
        employee.put("firstName", "John");
        employee.put("lastName", "Doe");
        employee.put("email", "john.doe@example.com");
    }

    @When("the employee is submitted to the API")
    public void the_employee_is_submitted_to_the_api() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(employee);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        response = restTemplate.postForEntity(baseUrl, entity, String.class);
    }

    @Then("the employee should be created successfully")
    public void the_employee_should_be_created_successfully() {
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody().contains("id"));
    }

    @When("the client requests all employees")
    public void get_all_employees() {
        response = restTemplate.getForEntity(baseUrl, String.class);
    }

    @Then("the response should contain a list of employees")
    public void verify_list_of_employees() {
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("id")); // simplistic check
    }

    private Long deleteEmployeeId;

    @Given("a new employee is created for deletion")
    public void a_new_employee_is_created_for_deletion() throws Exception {
        Map<String, Object> newEmployee = new HashMap<>();
        newEmployee.put("firstName", "ToDelete");
        newEmployee.put("lastName", "User");
        newEmployee.put("email", "delete.user@example.com");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(newEmployee);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> postResponse = restTemplate.postForEntity(baseUrl, entity, String.class);

        // Extract ID from response
        String body = postResponse.getBody();
        if (body != null && body.contains("id")) {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> responseMap = mapper.readValue(body, Map.class);
            deleteEmployeeId = Long.valueOf(responseMap.get("id").toString());
        } else {
            throw new RuntimeException("Failed to create employee for deletion");
        }
    }

    @When("the employee is deleted using API")
    public void the_employee_is_deleted_using_api() {
        String deleteUrl = baseUrl + "/" + deleteEmployeeId;
        restTemplate.delete(deleteUrl);
    }

    @Then("the employee should be deleted successfully")
    public void the_employee_should_be_deleted_successfully() {
        String getUrl = baseUrl + "/" + deleteEmployeeId;
        assertThrows(HttpClientErrorException.NotFound.class, () -> {
            restTemplate.getForEntity(getUrl, String.class);
        });
    }

}
