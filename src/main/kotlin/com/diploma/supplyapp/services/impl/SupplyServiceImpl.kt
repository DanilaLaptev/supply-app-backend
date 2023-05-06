package com.diploma.supplyapp.services.impl

import com.diploma.supplyapp.dto.ProductDto
import com.diploma.supplyapp.dto.StatusDto
import com.diploma.supplyapp.dto.StorageItemDto
import com.diploma.supplyapp.dto.SupplyDto
import com.diploma.supplyapp.entities.StorageItem
import com.diploma.supplyapp.entities.Supply
import com.diploma.supplyapp.entities.SupplyStateHistory
import com.diploma.supplyapp.enums.SupplyStatus
import com.diploma.supplyapp.repositories.OrganizationBranchRepository
import com.diploma.supplyapp.repositories.StorageItemsRepository
import com.diploma.supplyapp.repositories.SupplyRepository
import com.diploma.supplyapp.services.SupplyService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class SupplyServiceImpl (
        val supplyRepository: SupplyRepository,
        val storageItemsRepository: StorageItemsRepository,
        val organizationBranchRepository: OrganizationBranchRepository
): SupplyService{

    override fun createSupply(dto: SupplyDto, id: Long) {
        val organizationBranch = organizationBranchRepository.findByIdAndOrganizationId(dto.toBranch!!, id).orElseThrow()
        val groupId = supplyRepository.generateGroupId() + 1
        dto.items?.forEach{
            val fromStorageItem = storageItemsRepository.findById(it.id!!).get()
            val toStorageItem = storageItemsRepository
                    .findStorageItemByOrganizationBranchIdAndProductId(organizationBranch.id, fromStorageItem.product!!.id)
                    .orElseGet{storageItemsRepository.save(StorageItem(true, fromStorageItem.price, fromStorageItem.description, 0, organizationBranch, fromStorageItem.product, listOf(), listOf()))}
            val supplyHistory = SupplyStateHistory(SupplyStatus.PENDING, null)
            val supply = Supply(
                    fromStorageItem.price!!,
                    it.quantity!!,
                    groupId,
                    dto.deliveryTime!!,
                    fromStorageItem,
                    toStorageItem,
                    listOf(supplyHistory))
            supplyHistory.supply = supply
            supplyRepository.save(supply)
        }
    }

    override fun getSupply(
            startDate: LocalDateTime?,
            endDate: LocalDateTime?,
            outgoingSupply: Boolean?,
            incomingSupply: Boolean?,
            branchId: Long
    ): List<SupplyDto> {
        val startDate = startDate ?: LocalDateTime.of(1990, 1,1,0,0)
        val endDate = endDate ?: LocalDateTime.now().plusYears(1000)
        val outgoingSupply = outgoingSupply ?: true
        val incomingSupply = incomingSupply ?: true
        val result = supplyRepository.findAllWithFilters(
                startDate,
                endDate,
                outgoingSupply,
                incomingSupply,
                branchId
        )
        return result.groupBy ({it.groupId}, {it})
                .map {
                    SupplyDto(
                            it.value[0].fromStorageItem.organizationBranch!!.id,
                            it.value[0].toStorageItem.organizationBranch!!.id,
                            it.value[0].deliveryTime,
                            it.key,
                            it.value.map { it2 -> StorageItemDto(
                                    it2.toStorageItem.product!!.id,
                                    it2.price,
                                    null,
                                    it2.quantity,
                                    null,
                                    ProductDto.fromEntity(it2.fromStorageItem.product!!)) }.toList(),
                            it.value[0].supplyStateHistory.map { it2 -> StatusDto(it2.status.name, it2.time) },
                            it.value[0].created
                    )
                }
    }

    override fun declineSupply(organizationId: Long, id: Long, organizationBranchId: Long) {
        val organizationBranch = organizationBranchRepository.findByIdAndOrganizationId(organizationBranchId, organizationId).orElseThrow()
        val supply = supplyRepository.findSupplyByIdAndOrganizationBranchId(organizationBranchId, id)
        val status = supply.supplyStateHistory.stream().sorted { o1, o2 -> o2.time.compareTo(o1.time) }.findFirst().get().status
        if (status == SupplyStatus.PENDING) {
            val supplyHistory = SupplyStateHistory(SupplyStatus.DENIED, null)
            supply.supplyStateHistory = supply.supplyStateHistory.plus(supplyHistory)
            supplyHistory.supply = supply
        }
        supplyRepository.save(supply)
    }

    override fun acceptSupply(organizationId: Long, id: Long, organizationBranchId: Long) {
        val organizationBranch = organizationBranchRepository.findByIdAndOrganizationId(organizationBranchId, organizationId).orElseThrow()
        val supply = supplyRepository.findSupplyByIdAndOrganizationBranchId(organizationBranchId, id)
        val status = supply.supplyStateHistory.stream().sorted { o1, o2 -> o2.time.compareTo(o1.time) }.findFirst().get().status
        if (status == SupplyStatus.PENDING) {
            val supplyHistory = SupplyStateHistory(SupplyStatus.APPROVED, null)
            supply.supplyStateHistory = supply.supplyStateHistory.plus(supplyHistory)
            supplyHistory.supply = supply
        }
        else if(status == SupplyStatus.DELIVERED) {
            val supplyHistory = SupplyStateHistory(SupplyStatus.SUPPLY_ACCEPTED, null)
            supply.supplyStateHistory = supply.supplyStateHistory.plus(supplyHistory)
            supplyHistory.supply = supply
            supply.toStorageItem.quantity = supply.toStorageItem.quantity!! + supply.quantity
            storageItemsRepository.save(supply.toStorageItem)
        }
        supplyRepository.save(supply)
    }

    @Scheduled(cron = "*/15 * * * * *")
    fun changeStatuses() {
        val supplies = supplyRepository.findAllFetch()
        supplies.forEach {
            when(it.supplyStateHistory.stream().sorted { o1, o2 -> o2.time.compareTo(o1.time) }.findFirst().get().status) {
                SupplyStatus.APPROVED ->{
                    val supplyHistory = SupplyStateHistory(SupplyStatus.COLLECTED, null)
                    it.supplyStateHistory = it.supplyStateHistory.plus(supplyHistory)
                    supplyHistory.supply = it
                }
                SupplyStatus.COLLECTED -> {
                    val supplyHistory = SupplyStateHistory(SupplyStatus.IN_TRANSIT, null)
                    it.supplyStateHistory = it.supplyStateHistory.plus(supplyHistory)
                    supplyHistory.supply = it
                }
                SupplyStatus.IN_TRANSIT -> {
                    val supplyHistory = SupplyStateHistory(SupplyStatus.DELIVERED, null)
                    it.supplyStateHistory = it.supplyStateHistory.plus(supplyHistory)
                    supplyHistory.supply = it
                }
                else -> {}
            }
            supplyRepository.save(it)
        }
    }

}