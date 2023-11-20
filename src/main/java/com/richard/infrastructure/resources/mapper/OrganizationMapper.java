package com.richard.infrastructure.resources.mapper;

import com.richard.infrastructure.persistence.entities.OrganizationEntity;
import com.richard.infrastructure.resources.response.OrganizationResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrganizationMapper {
    OrganizationResponse toResponse(OrganizationEntity entity);

    Iterable<OrganizationResponse> map(Iterable<OrganizationEntity> organizations);

}
