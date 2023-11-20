package com.richard.infrastructure.resources;

import com.richard.domain.DepatmentService;
import com.richard.domain.EmployeeService;
import com.richard.domain.OrganizationService;
import com.richard.domain.filter.EmployeeFilter;
import com.richard.infrastructure.persistence.entities.DepartmentEntity;
import com.richard.infrastructure.persistence.entities.EmployeeEntity;
import com.richard.infrastructure.persistence.entities.OrganizationEntity;
import com.richard.infrastructure.resources.mapper.EmployeeMapper;
import com.richard.infrastructure.resources.request.EmployeeRequest;
import com.richard.infrastructure.resources.response.EmployeeResponse;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeResource {
    private final EmployeeService employeeService;
    private final DepatmentService depatmentService;
    private  final OrganizationService organizationService;
    private final EmployeeMapper employeeMapper;
    public EmployeeResource(EmployeeService employeeService, DepatmentService depatmentService, OrganizationService organizationService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.depatmentService = depatmentService;
        this.organizationService = organizationService;
        this.employeeMapper = employeeMapper;
    }

    @QueryMapping
    public Iterable<EmployeeResponse> employees() {
        return employeeMapper.map(employeeService.findAll());
    }

    @QueryMapping
    public EmployeeResponse employee(@Argument Integer id) {
        return employeeMapper.toResponse(employeeService.findById(id));
    }

    @MutationMapping
    public EmployeeResponse newEmployee(@Argument EmployeeRequest employee) {

        DepartmentEntity departmentEntity =
                depatmentService.findById(employee.departmentId()).get();

        OrganizationEntity organizationEntity =
                organizationService.findById(employee.departmentId()).get();

        EmployeeEntity employeeEntity = new EmployeeEntity(null, employee.firstName(), employee.lastName(),
                employee.position(), employee.age(), employee.salary(), departmentEntity, organizationEntity);

        return employeeMapper.toResponse(employeeService.save(employeeEntity));
    }

    @QueryMapping
    public Iterable<EmployeeResponse> employeesWithFilter(@Argument EmployeeFilter filter) {
       return employeeMapper.map(employeeService.findAll(filter));
    }


}
