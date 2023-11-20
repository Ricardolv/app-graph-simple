package com.richard.domain;

import com.richard.domain.filter.EmployeeFilter;
import com.richard.domain.filter.FilterField;
import com.richard.infrastructure.persistence.entities.EmployeeEntity;
import com.richard.infrastructure.persistence.repositories.EmployeeEntityRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeEntityRepository employeeEntityRepository;

    public EmployeeService(EmployeeEntityRepository employeeEntityRepository) {
        this.employeeEntityRepository = employeeEntityRepository;
    }

    public Iterable<EmployeeEntity> findAll() {
        return employeeEntityRepository.findAll();
    }

    public Iterable<EmployeeEntity> findAll(Specification<EmployeeEntity> spec) {
        return employeeEntityRepository.findAll(spec);
    }

    public EmployeeEntity findById(Integer id) {
        return employeeEntityRepository.findById(id).orElseThrow();
    }

    public Iterable<EmployeeEntity> findAll(EmployeeFilter filter) {
        Specification<EmployeeEntity> spec = null;
        if (filter.getSalary() != null)
            spec = bySalary(filter.getSalary());
        if (filter.getAge() != null)
            spec = (spec == null ? byAge(filter.getAge()) : spec.and(byAge(filter.getAge())));
        if (filter.getPosition() != null)
            spec = (spec == null ? byPosition(filter.getPosition()) :
                    spec.and(byPosition(filter.getPosition())));
        if (spec != null)
            return employeeEntityRepository.findAll(spec);
        else
            return employeeEntityRepository.findAll();
    }

    public EmployeeEntity save(EmployeeEntity employeeEntity) {
        return employeeEntityRepository.save(employeeEntity);
    }

    private Specification<EmployeeEntity> bySalary(FilterField filterField) {
        return (root, query, builder) -> filterField
                .generateCriteria(builder, root.get("salary"));
    }

    private Specification<EmployeeEntity> byAge(FilterField filterField) {
        return (root, query, builder) -> filterField
                .generateCriteria(builder, root.get("age"));
    }

    private Specification<EmployeeEntity> byPosition(FilterField filterField) {
        return (root, query, builder) -> filterField
                .generateCriteria(builder, root.get("position"));
    }

}
