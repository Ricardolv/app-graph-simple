package com.richard.infrastructure.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "department")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "department")
    private Set<EmployeeEntity> employees;
    @ManyToOne(fetch = FetchType.LAZY)
    private OrganizationEntity organization;

    public DepartmentEntity() {}

    public DepartmentEntity(Integer id, String name, OrganizationEntity organization) {
        this.id = id;
        this.name = name;
        this.organization = organization;
    }

    public DepartmentEntity(Integer id, String name, Set<EmployeeEntity> employees, OrganizationEntity organization) {
        this.id = id;
        this.name = name;
        this.employees = employees;
        this.organization = organization;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Set<EmployeeEntity> getEmployees() {
        return employees;
    }

    public OrganizationEntity getOrganization() {
        return organization;
    }
}
