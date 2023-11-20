package com.richard.infrastructure.resources.response;

import java.util.Set;

public record DepartmentResponse(Integer id, String name, Set<EmployeeResponse> employees, OrganizationResponse organization) {}
