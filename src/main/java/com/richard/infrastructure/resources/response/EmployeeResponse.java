package com.richard.infrastructure.resources.response;

public record EmployeeResponse(Integer id, String firstName, String lastName, String position, int salary, int age,
                               DepartmentResponse department, OrganizationResponse organization) {}
