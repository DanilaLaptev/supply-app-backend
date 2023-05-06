package com.diploma.supplyapp.dto

import java.time.LocalDateTime

class SupplyDto (
        var fromBranch: Long?,
        var toBranch: Long?,
        var deliveryTime: LocalDateTime?,
        var groupId: Long?,
        var items: List<StorageItemDto>?,
        var statuses: List<StatusDto>?,
        var created: LocalDateTime?,
)