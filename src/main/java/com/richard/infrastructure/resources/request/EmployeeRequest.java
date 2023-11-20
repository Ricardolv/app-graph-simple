package com.richard.infrastructure.resources.request;


public record EmployeeRequest(String firstName, String lastName, String position,
                                int salary, int age, Integer departmentId, Integer organizationId) {
}
