package com.diploma.supplyapp.services

import com.diploma.supplyapp.dto.FilterDto
import com.diploma.supplyapp.dto.OrganizationBranchDto
import com.diploma.supplyapp.dto.PageDto
import com.diploma.supplyapp.dto.StorageItemDto

interface OrganizationBranchService {
    fun createBranch(organizationId:Long, organizationBranchDto: OrganizationBranchDto): OrganizationBranchDto

    fun updateBranch(organizationBranchId:Long, organizationBranchDto: OrganizationBranchDto, organizationId: Long): OrganizationBranchDto

    fun getStorageItems(organizationBranchId: Long, filterDto: FilterDto): PageDto<StorageItemDto>

    fun addStorageItems(branchId: Long, organizationId: Long, items: List<StorageItemDto>)

    fun updateStorageItem(branchId: Long, organizationId: Long, item: StorageItemDto): StorageItemDto
}