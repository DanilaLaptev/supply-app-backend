package com.diploma.supplyapp.services

import com.diploma.supplyapp.dto.FilterDto
import com.diploma.supplyapp.dto.OrganizationBranchDto
import com.diploma.supplyapp.dto.PageDto
import com.diploma.supplyapp.dto.StorageItemDto
import com.diploma.supplyapp.entities.OrganizationBranch
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody

interface OrganizationBranchService {

    fun createBranch(organizationId:Long, organizationBranchDto: OrganizationBranchDto): OrganizationBranchDto

    fun updateBranch(organizationBranchId:Long, organizationBranchDto: OrganizationBranchDto, organizationId: Long): OrganizationBranchDto

    fun getStorageItems(organizationBranchId: Long, filterDto: FilterDto): PageDto<StorageItemDto>

    fun addStorageItems(branchId: Long, organizationId: Long, items: List<StorageItemDto>)
}