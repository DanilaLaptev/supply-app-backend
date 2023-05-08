package com.diploma.supplyapp.repositories

import com.diploma.supplyapp.entities.Supply
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface SupplyRepository : JpaRepository<Supply, Long> {
    @Query("select coalesce(max(s.groupId), 0) from Supply s")
    fun generateGroupId(): Long

    @Query("select s " +
            "from Supply s " +
                "join s.toStorageItem tos " +
                "join s.fromStorageItem froms " +
                "join fetch s.supplyStateHistory " +
            "where (s.deliveryTime between :startDate and :endDate) " +
            "and ((:incomingSupply = true and tos.organizationBranch.id = :branchId) " +
            "or (:outgoingSupply = true and froms.organizationBranch.id = :branchId))")
    fun findAllWithFilters(startDate: LocalDateTime,
                           endDate: LocalDateTime,
                           outgoingSupply: Boolean,
                           incomingSupply: Boolean,
                           branchId: Long): List<Supply>

    @Query("select s from Supply s where s.id = :id and (s.fromStorageItem.organizationBranch.id = :organizationBranch or s.toStorageItem.organizationBranch.id = :organizationBranch)")
    fun findSupplyByIdAndOrganizationBranchId(organizationBranch: Long, id: Long) : Supply

    @Query("select s from Supply s where s.groupId = :groupId and (s.fromStorageItem.organizationBranch.id = :organizationBranch or s.toStorageItem.organizationBranch.id = :organizationBranch)")
    fun findSuppliesByGroupIdAndOrganizationBranchId(organizationBranch: Long, groupId: Long) : List<Supply>

    @Query("select s from Supply s join fetch s.supplyStateHistory")
    fun findAllFetch(): List<Supply>
}