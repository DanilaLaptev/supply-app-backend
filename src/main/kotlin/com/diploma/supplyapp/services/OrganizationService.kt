package com.diploma.supplyapp.services

import com.diploma.supplyapp.dto.FilterDto
import com.diploma.supplyapp.dto.OrganizationDto
import com.diploma.supplyapp.dto.PageDto

interface OrganizationService {

    fun updateOrganization(organizationDto: OrganizationDto, id: Long)

    fun getOrganizations(filterDto: FilterDto): PageDto<OrganizationDto>

    fun getOrganization(id: Long): OrganizationDto

}