package com.richard.infrastructure.resources;

import com.richard.infrastructure.persistence.entities.EmployeeEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureGraphQlTester
class EmployeeResourceTest {

    @Autowired
    private GraphQlTester tester;

    @Test
    void addEmployee() {
        String query = "mutation { newEmployee(employee: { firstName: \"John\" lastName: \"Wick\" position: \"developer\" salary: 10000 age: 20 departmentId: 1 organizationId: 1}) { id } }";
        EmployeeEntity employee = tester.document(query)
                .execute()
                .path("data.newEmployee")
                .entity(EmployeeEntity.class)
                .get();

        Assertions.assertNotNull(employee);
        Assertions.assertNotNull(employee.getId());
    }

    @Test
    void findAll() {
        String query = "{ employees { id firstName lastName salary } }";
        List<EmployeeEntity> employees = tester.document(query)
                .execute()
                .path("data.employees[*]")
                .entityList(EmployeeEntity.class)
                .get();
        Assertions.assertTrue(employees.size() > 0);
        Assertions.assertNotNull(employees.get(0).getId());
        Assertions.assertNotNull(employees.get(0).getFirstName());
    }

    @Test
    void findById() {
        String query = "{ employee(id: 1) { id firstName lastName salary } }";
        EmployeeEntity employee = tester.document(query)
                .execute()
                .path("data.employee")
                .entity(EmployeeEntity.class)
                .get();
        Assertions.assertNotNull(employee);
        Assertions.assertNotNull(employee.getId());
        Assertions.assertNotNull(employee.getFirstName());
    }

    @Test
    void findWithFilter() {
        String query = "{ employeesWithFilter(filter: { salary: { operator: \"gt\" value: \"12000\" } }) { id firstName lastName salary } }";
        List<EmployeeEntity> employees = tester.document(query)
                .execute()
                .path("data.employeesWithFilter[*]")
                .entityList(EmployeeEntity.class)
                .get();
        Assertions.assertTrue(employees.size() > 0);
        Assertions.assertNotNull(employees.get(0).getId());
        Assertions.assertNotNull(employees.get(0).getFirstName());
    }

}