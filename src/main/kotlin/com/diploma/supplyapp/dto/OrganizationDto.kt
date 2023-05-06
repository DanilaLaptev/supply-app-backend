package com.diploma.supplyapp.dto

import com.diploma.supplyapp.entities.Organization
import com.diploma.supplyapp.enums.OrganizationRole

data class OrganizationDto (
        var id: Long?,
        var role: OrganizationRole?,
        var title: String?,
        var approved: Boolean?,
        var branches: List<OrganizationBranchDto>,
        var image: String?
) {
    companion object {
        fun fromEntity(organization: Organization) : OrganizationDto {
            return OrganizationDto(
                    id = organization.id,
                    role = organization.role,
                    title = organization.title,
                    approved = organization.approved,
                    branches = organization.branches.map (OrganizationBranchDto::fromEntity ),
                    image = organization.image

            )
        }
    }
}
