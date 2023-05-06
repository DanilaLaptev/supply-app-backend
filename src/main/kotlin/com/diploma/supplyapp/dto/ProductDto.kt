package com.diploma.supplyapp.dto

import com.diploma.supplyapp.entities.Product
import com.diploma.supplyapp.enums.ProductType

data class ProductDto(
        var id: Long?,
        var productType: ProductType?,
        var name: String?,
        var approved: Boolean?,
        var image: String?
) {
    companion object {

        fun fromEntity(product: Product) : ProductDto{
            return ProductDto(
                    product.id,
                    product.productType,
                    product.name,
                    product.approved,
                    product.image
            )
        }

    }
}