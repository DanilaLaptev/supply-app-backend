package com.diploma.supplyapp.repositories

import com.diploma.supplyapp.entities.StorageItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface StorageItemsRepository : JpaRepository<StorageItem, Long> {

    fun findStorageItemByOrganizationBranchIdAndProductId(organizationBranchId: Long, productId:Long): Optional<StorageItem>

    fun findStorageItemByOrganizationBranchIdAndId(organizationBranchId: Long, storageItemId:Long): Optional<StorageItem>

}