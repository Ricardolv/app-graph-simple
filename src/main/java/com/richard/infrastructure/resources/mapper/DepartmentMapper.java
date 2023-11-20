package com.richard.infrastructure.resources.mapper;

import com.richard.infrastructure.persistence.entities.DepartmentEntity;
import com.richard.infrastructure.resources.response.DepartmentResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DepartmentMapper {

    DepartmentResponse toResponse(DepartmentEntity entity);

    Iterable<DepartmentResponse> map(Iterable<DepartmentEntity> departments);
}
