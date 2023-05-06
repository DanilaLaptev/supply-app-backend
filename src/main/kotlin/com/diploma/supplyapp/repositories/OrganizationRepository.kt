package com.diploma.supplyapp.repositories

import com.diploma.supplyapp.entities.Organization
import com.diploma.supplyapp.enums.OrganizationRole
import com.diploma.supplyapp.enums.ProductType
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrganizationRepository : JpaRepository<Organization, Long> {

    fun findByEmail(email: String): Optional<Organization>

    @Query("select o " +
            "from Organization o " +
            "where o.role in :roles " +
            "and o.title like :title " +
            "and exists(" +
            "select st " +
            "from StorageItem st " +
            "join st.product p " +
            "join st.organizationBranch ob1 " +
            "join ob1.organization o1 " +
            "where p.productType in :productTypes " +
            "and o1.id = o.id)")
    fun findAllOrganization(roles: List<OrganizationRole>, title: String, productTypes: List<ProductType>, pageable: Pageable): Page<Organization>

    @Query("select o from Organization o join fetch o.branches where o.id = :id")
    fun findOrganizationWithBranches(id: Long) : Optional<Organization>

}