package com.diploma.supplyapp.repositories

import com.diploma.supplyapp.entities.OrganizationBranch
import com.diploma.supplyapp.entities.StorageItem
import com.diploma.supplyapp.enums.ProductType
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrganizationBranchRepository : JpaRepository<OrganizationBranch, Long> {

    @Query("select ob from OrganizationBranch ob join fetch ob.contactPeople where ob in :branches")
    fun fetchAllContactPersons(branches: List<OrganizationBranch>) : List<OrganizationBranch>

    fun findByIdAndOrganizationId(id: Long, organizationId: Long) :Optional<OrganizationBranch>

    @Query("select si " +
            "from StorageItem si " +
            "join si.product p " +
            "where si.organizationBranch.id = :organizationBranchId " +
            "and p.productType in :types " +
            "and (si.isHidden = false or si.isHidden = :hidden)")
    fun findAllStorageItems(organizationBranchId: Long, types: List<ProductType>, hidden: Boolean, pageable: Pageable): Page<StorageItem>
}