package com.richard.infrastructure.resources;

import com.richard.domain.OrganizationService;
import com.richard.infrastructure.persistence.entities.OrganizationEntity;
import com.richard.infrastructure.resources.mapper.OrganizationMapper;
import com.richard.infrastructure.resources.request.OrganizationRequest;
import com.richard.infrastructure.resources.response.OrganizationResponse;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class OrganizationResource {
    private final OrganizationService organizationService;
    private final OrganizationMapper organizationMapper;

    public OrganizationResource(OrganizationService organizationService, OrganizationMapper organizationMapper) {
        this.organizationService = organizationService;
        this.organizationMapper = organizationMapper;
    }

    @MutationMapping
    public OrganizationResponse newOrganization(@Argument OrganizationRequest organization) {
        return organizationMapper.toResponse(organizationService.save(
                new OrganizationEntity(null, organization.name(), null, null)));
    }

    @QueryMapping
    public Iterable<OrganizationResponse> organizations() {
        return organizationMapper.map(organizationService.findAll());
    }

    @QueryMapping
    public OrganizationResponse organization(@Argument Integer id, DataFetchingEnvironment environment) {
       return organizationMapper.toResponse(organizationService.findOne(id, environment));
    }


}
