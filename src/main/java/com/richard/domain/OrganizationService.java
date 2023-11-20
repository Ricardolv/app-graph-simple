package com.richard.domain;

import com.richard.infrastructure.persistence.entities.DepartmentEntity;
import com.richard.infrastructure.persistence.entities.EmployeeEntity;
import com.richard.infrastructure.persistence.entities.OrganizationEntity;
import com.richard.infrastructure.persistence.repositories.OrganizationEntityRepository;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrganizationService {
    private final OrganizationEntityRepository organizationEntityRepository;

    public OrganizationService(OrganizationEntityRepository organizationEntityRepository) {
        this.organizationEntityRepository = organizationEntityRepository;
    }

    public Optional<OrganizationEntity> findById(Integer id) {
        return organizationEntityRepository.findById(id);
    }

    public OrganizationEntity save(OrganizationEntity entity) {

        organizationEntityRepository.findByNameIsNot(entity.getName())
                .orElseThrow(NoSuchElementException::new);

        return organizationEntityRepository.save(entity);
    }

    public Iterable<OrganizationEntity> findAll() {
        return organizationEntityRepository.findAll();
    }

    public OrganizationEntity findOne(Integer id, DataFetchingEnvironment environment) {
        Specification<OrganizationEntity> spec = byId(id);
        DataFetchingFieldSelectionSet selectionSet = environment.getSelectionSet();
        if (selectionSet.contains("employees"))
            spec = spec.and(fetchEmployees());
        if (selectionSet.contains("departments"))
            spec = spec.and(fetchDepartments());
        return organizationEntityRepository.findOne(spec).orElseThrow();
    }

    private Specification<OrganizationEntity> fetchDepartments() {
        return (root, query, builder) -> {
            Fetch<OrganizationEntity, DepartmentEntity> f = root
                    .fetch("departments", JoinType.LEFT);
            Join<OrganizationEntity, DepartmentEntity> join = (Join<OrganizationEntity, DepartmentEntity>) f;
            return join.getOn();
        };
    }

    private Specification<OrganizationEntity> fetchEmployees() {
        return (root, query, builder) -> {
            Fetch<OrganizationEntity, EmployeeEntity> f = root
                    .fetch("employees", JoinType.LEFT);
            Join<OrganizationEntity, EmployeeEntity> join = (Join<OrganizationEntity, EmployeeEntity>) f;
            return join.getOn();
        };
    }

    private Specification<OrganizationEntity> byId(Integer id) {
        return (root, query, builder) -> builder.equal(root.get("id"), id);
    }


}
