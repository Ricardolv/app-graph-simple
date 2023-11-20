package com.richard.infrastructure.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "organization")
public class OrganizationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "organization")
    private Set<DepartmentEntity> departments;
    @OneToMany(mappedBy = "organization")
    private Set<EmployeeEntity> employees;

    public OrganizationEntity() {}

    public OrganizationEntity(Long id, String name, Set<DepartmentEntity> departments, Set<EmployeeEntity> employees) {
        this.id = id;
        this.name = name;
        this.departments = departments;
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Set<DepartmentEntity> getDepartments() {
        return departments;
    }

    public Set<EmployeeEntity> getEmployees() {
        return employees;
    }
}
