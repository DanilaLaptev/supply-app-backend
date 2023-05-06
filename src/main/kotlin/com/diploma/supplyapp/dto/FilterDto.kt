package com.diploma.supplyapp.dto

import com.diploma.supplyapp.enums.OrganizationRole
import com.diploma.supplyapp.enums.ProductType

data class FilterDto (
        var hidden: Boolean?,
        var role: OrganizationRole?,
        var title: String?,
        var productType: List<ProductType>?,
        var page: Long?,
        var perPage: Long?
)