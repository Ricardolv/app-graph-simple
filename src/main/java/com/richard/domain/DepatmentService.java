package com.richard.domain;

import com.richard.infrastructure.persistence.entities.DepartmentEntity;
import com.richard.infrastructure.persistence.entities.EmployeeEntity;
import com.richard.infrastructure.persistence.entities.OrganizationEntity;
import com.richard.infrastructure.persistence.repositories.DepartmentEntityRepository;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class DepatmentService {
    private final DepartmentEntityRepository departmentEntityRepository;

    public DepatmentService(DepartmentEntityRepository departmentEntityRepository) {
        this.departmentEntityRepository = departmentEntityRepository;
    }

    public Optional<DepartmentEntity> findById(Integer id) {
        return departmentEntityRepository.findById(id);
    }

    public DepartmentEntity save(DepartmentEntity entity) {
        return departmentEntityRepository.save(entity);
    }

    public Iterable<DepartmentEntity> findAll(DataFetchingEnvironment environment) {
        DataFetchingFieldSelectionSet s = environment.getSelectionSet();
        if (s.contains("employees") && !s.contains("organization"))
            return departmentEntityRepository.findAll(fetchEmployees());
        else if (!s.contains("employees") && s.contains("organization"))
            return departmentEntityRepository.findAll(fetchOrganization());
        else if (s.contains("employees") && s.contains("organization"))
            return departmentEntityRepository.findAll(fetchEmployees().and(fetchOrganization()));
        else
            return departmentEntityRepository.findAll();
    }

    public DepartmentEntity findOnde(Integer id, DataFetchingEnvironment environment) {
        Specification<DepartmentEntity> spec = byId(id);
        DataFetchingFieldSelectionSet selectionSet = environment
                .getSelectionSet();
        if (selectionSet.contains("employees"))
            spec = spec.and(fetchEmployees());
        if (selectionSet.contains("organization"))
            spec = spec.and(fetchOrganization());
        return departmentEntityRepository.findOne(spec).orElseThrow(NoSuchElementException::new);
    }

    private Specification<DepartmentEntity> fetchOrganization() {
        return (root, query, builder) -> {
            Fetch<DepartmentEntity, OrganizationEntity> f = root
                    .fetch("organization", JoinType.LEFT);
            Join<DepartmentEntity, OrganizationEntity> join = (Join<DepartmentEntity, OrganizationEntity>) f;
            return join.getOn();
        };
    }

    private Specification<DepartmentEntity> fetchEmployees() {
        return (root, query, builder) -> {
            Fetch<DepartmentEntity, EmployeeEntity> f = root
                    .fetch("employees", JoinType.LEFT);
            Join<DepartmentEntity, EmployeeEntity> join = (Join<DepartmentEntity, EmployeeEntity>) f;
            return join.getOn();
        };
    }

    private Specification<DepartmentEntity> byId(Integer id) {
        return (root, query, builder) -> builder.equal(root.get("id"), id);
    }

}
