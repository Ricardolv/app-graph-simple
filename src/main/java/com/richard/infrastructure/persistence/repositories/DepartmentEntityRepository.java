package com.richard.infrastructure.persistence.repositories;

import com.richard.infrastructure.persistence.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DepartmentEntityRepository extends JpaRepository<DepartmentEntity, Integer>,
        JpaSpecificationExecutor<DepartmentEntity> {
}
