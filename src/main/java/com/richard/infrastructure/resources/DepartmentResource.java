package com.richard.infrastructure.resources;

import com.richard.domain.DepatmentService;
import com.richard.domain.OrganizationService;
import com.richard.infrastructure.persistence.entities.DepartmentEntity;
import com.richard.infrastructure.persistence.entities.OrganizationEntity;
import com.richard.infrastructure.resources.mapper.DepartmentMapper;
import com.richard.infrastructure.resources.request.DepartmentRequest;
import com.richard.infrastructure.resources.response.DepartmentResponse;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class DepartmentResource {
    private final DepatmentService depatmentService;
    private  final OrganizationService organizationService;
    private final DepartmentMapper departmentMapper;

    public DepartmentResource(DepatmentService depatmentService, OrganizationService organizationService,
                              DepartmentMapper departmentMapper) {
        this.depatmentService = depatmentService;
        this.organizationService = organizationService;
        this.departmentMapper = departmentMapper;
    }

    @MutationMapping
    public DepartmentResponse newDepartment(@Argument DepartmentRequest department) {
        OrganizationEntity organization = organizationService
                .findById(department.organizationId()).get();
        return departmentMapper.toResponse(
                depatmentService.save(new DepartmentEntity(null, department.name(), null, organization)));
    }

    @QueryMapping
    public Iterable<DepartmentResponse> departments(DataFetchingEnvironment environment) {
      return departmentMapper.map(depatmentService.findAll(environment));
    }

    @QueryMapping
    public DepartmentResponse department(@Argument Integer id, DataFetchingEnvironment environment) {
       return departmentMapper.toResponse(depatmentService.findOnde(id, environment));
    }

}
