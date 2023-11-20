package com.richard.infrastructure.resources.mapper;

import com.richard.infrastructure.persistence.entities.EmployeeEntity;
import com.richard.infrastructure.resources.response.EmployeeResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper {

    EmployeeResponse toResponse(EmployeeEntity entity);

    Iterable<EmployeeResponse> map(Iterable<EmployeeEntity> employees);
}
