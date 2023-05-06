package com.diploma.supplyapp.services.impl

import com.diploma.supplyapp.dto.FilterDto
import com.diploma.supplyapp.dto.OrganizationDto
import com.diploma.supplyapp.dto.PageDto
import com.diploma.supplyapp.entities.Organization
import com.diploma.supplyapp.enums.OrganizationRole
import com.diploma.supplyapp.enums.ProductType
import com.diploma.supplyapp.repositories.OrganizationBranchRepository
import com.diploma.supplyapp.repositories.OrganizationRepository
import com.diploma.supplyapp.services.OrganizationService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class OrganizationServiceImpl(
        val organizationRepository: OrganizationRepository,
        val organizationBranchRepository: OrganizationBranchRepository
): OrganizationService {

    override fun updateOrganization(organizationDto: OrganizationDto, id: Long) {
        val organization = organizationRepository.findById(id).orElseThrow()
        organization.title = organizationDto.title ?: organization.title
        organization.role = organizationDto.role ?: organization.role
        organization.approved = organizationDto.approved ?: organization.approved
        organization.image = organizationDto.image ?: organization.image
        organizationRepository.save(organization)
    }

    override fun getOrganizations(filterDto: FilterDto) : PageDto<OrganizationDto>{
        val titleReq = "%" + (filterDto.title ?:"") + "%"
        val pageReq = PageRequest.of((filterDto.page ?: 0).toInt(), (filterDto.perPage ?: 20).toInt())
        val roles = filterDto.role?.let {
            listOf(it)
        } ?: OrganizationRole.values().toList()
        val types = filterDto.productType ?: ProductType.values().toList()
        val organizations = organizationRepository.findAllOrganization(roles, titleReq, types, pageReq)
        return PageDto(
                items = organizations.map { OrganizationDto.fromEntity(it) }.toList(),
                total = organizations.totalElements
        )
    }

    override fun getOrganization(id: Long) : OrganizationDto {
        val organization = organizationRepository.findOrganizationWithBranches(id).orElseThrow()
        organization.branches = organizationBranchRepository.fetchAllContactPersons(organization.branches)
        return OrganizationDto.fromEntity(organization)
    }
}