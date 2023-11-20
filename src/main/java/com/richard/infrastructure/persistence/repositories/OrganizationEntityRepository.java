package com.richard.infrastructure.persistence.repositories;

import com.richard.infrastructure.persistence.entities.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface OrganizationEntityRepository extends JpaRepository<OrganizationEntity, Integer>,
        JpaSpecificationExecutor<OrganizationEntity> {
    Optional<OrganizationEntity> findByName(String name);

    Optional<OrganizationEntity> findByNameIsNot(String name);
}
