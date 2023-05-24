package com.diploma.supplyapp.dto

import com.diploma.supplyapp.entities.OrganizationBranch

data class OrganizationBranchDto (
        var id: Long?,
        var addressName: String?,
        var longitude: Double?,
        var latitude: Double?,
        var contactPeople: List<ContactPersonDto>
) {
    companion object {
        fun fromEntity(organizationBranch: OrganizationBranch) : OrganizationBranchDto {
            return OrganizationBranchDto(
                    id = organizationBranch.id,
                    addressName = organizationBranch.addressName,
                    longitude = organizationBranch.longitude,
                    latitude = organizationBranch.latitude,
                    contactPeople = organizationBranch.contactPeople.map { ContactPersonDto.fromEntity(it) }
            )
        }
    }
}