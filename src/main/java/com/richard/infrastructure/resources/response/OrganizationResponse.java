package com.richard.infrastructure.resources.response;

import java.util.Set;

public record OrganizationResponse(Integer id, String name,
                                   Set<DepartmentResponse> departments,
                                   Set<EmployeeResponse> employees) {
}
