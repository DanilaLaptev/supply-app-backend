package com.diploma.supplyapp.services.impl

import com.diploma.supplyapp.dto.FilterDto
import com.diploma.supplyapp.dto.OrganizationBranchDto
import com.diploma.supplyapp.dto.PageDto
import com.diploma.supplyapp.dto.StorageItemDto
import com.diploma.supplyapp.entities.OrganizationBranch
import com.diploma.supplyapp.entities.StorageItem
import com.diploma.supplyapp.enums.ProductType
import com.diploma.supplyapp.repositories.OrganizationBranchRepository
import com.diploma.supplyapp.repositories.OrganizationRepository
import com.diploma.supplyapp.repositories.ProductRepository
import com.diploma.supplyapp.repositories.StorageItemsRepository
import com.diploma.supplyapp.services.OrganizationBranchService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class OrganizationBranchServiceImpl (
        val productRepository: ProductRepository,
        val storageItemsRepository: StorageItemsRepository,
        val organizationRepository: OrganizationRepository,
        val organizationBranchRepository: OrganizationBranchRepository
): OrganizationBranchService {

    override fun createBranch(organizationId: Long, organizationBranchDto: OrganizationBranchDto): OrganizationBranchDto {
        val organization = organizationRepository.findById(organizationId).orElseThrow()
        val organizationBranch = OrganizationBranch(
                addressName = organizationBranchDto.addressName,
                contactPeople = ArrayList(),
                latitude = organizationBranchDto.latitude,
                longitude = organizationBranchDto.longitude,
                organization = organization,
                storageItems = ArrayList()
        )
        val createdOrganization = organizationBranchRepository.save(organizationBranch)
        return OrganizationBranchDto.fromEntity(createdOrganization)
    }

    override fun updateBranch(organizationBranchId: Long, organizationBranchDto: OrganizationBranchDto, organizationId: Long): OrganizationBranchDto {
        val organizationBranch = organizationBranchRepository.findByIdAndOrganizationId(organizationBranchId, organizationId).orElseThrow()
        organizationBranch.addressName = organizationBranchDto.addressName ?: organizationBranch.addressName
        organizationBranch.latitude = organizationBranchDto.latitude ?: organizationBranch.latitude
        organizationBranch.longitude = organizationBranchDto.longitude ?: organizationBranch.longitude

        val updatedOrganization = organizationBranchRepository.save(organizationBranch)
        return OrganizationBranchDto.fromEntity(updatedOrganization)
    }

    override fun getStorageItems(organizationBranchId: Long, filterDto: FilterDto): PageDto<StorageItemDto> {
        val result = organizationBranchRepository.findAllStorageItems(organizationBranchId, filterDto.productType ?: ProductType.values().toList(), filterDto.hidden ?: false, PageRequest.of((filterDto.page ?: 0).toInt(), (filterDto.perPage ?: 20).toInt()))
        return PageDto(
                result.map { StorageItemDto.fromEntity(it) }.toList(),
                result.totalElements
        )
    }

    override fun addStorageItems(branchId: Long, organizationId: Long, items: List<StorageItemDto>) {
        val organizationBranch = organizationBranchRepository.findByIdAndOrganizationId(branchId, organizationId).orElseThrow()
        items.forEach {
            val product = storageItemsRepository.findStorageItemByOrganizationBranchIdAndProductId(branchId, it.id!!).orElse(
                    StorageItem(
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            ArrayList(),
                            ArrayList(),
                    )
            )
            product.description = it.description ?: product.description
            product.organizationBranch = organizationBranch
            product.product = productRepository.getReferenceById(it.id!!)
            product.price = it.price ?: product.price
            product.isHidden = it.isHidden ?: product.isHidden
            product.quantity = it.quantity ?: product.quantity
            storageItemsRepository.save(product)
        }
    }

    override fun updateStorageItem(branchId: Long, organizationId: Long, item: StorageItemDto): StorageItemDto {
        val organizationBranch = organizationBranchRepository.findByIdAndOrganizationId(branchId, organizationId).orElseThrow()
        val product = storageItemsRepository.findStorageItemByOrganizationBranchIdAndId(branchId, item.id!!).orElseThrow()

        product?.description = item.description ?: product.description
        product.price = item.price ?: product.price
        product.isHidden = item.isHidden ?: product.isHidden
        val storageItem = storageItemsRepository.save(product)
        return StorageItemDto.fromEntity(storageItem)
    }
}