package com.diploma.supplyapp.services

import com.diploma.supplyapp.dto.SupplyDto
import java.time.LocalDateTime

interface SupplyService {
    fun createSupply(dto: SupplyDto, id: Long)

    fun sellSupply(dto: SupplyDto, id: Long)

    fun getSupply(startDate: LocalDateTime?,
                  endDate: LocalDateTime?,
                  outgoingSupply: Boolean?,
                  incomingSupply: Boolean?,
                  branchId: Long): List<SupplyDto>

    fun declineSupply(organizationId: Long, id: Long, organizationBranch: Long)

    fun declineSuppliesGroup(organizationId: Long, id: Long, organizationBranch: Long)

    fun acceptSupply(organizationId: Long, id: Long, organizationBranchId: Long)

    fun acceptSuppliesGroup(organizationId: Long, groupId: Long, organizationBranchId: Long)
}