package com.richard.infrastructure.persistence.repositories;

import com.richard.infrastructure.persistence.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity, Integer>,
                                                    JpaSpecificationExecutor<EmployeeEntity> {
}
