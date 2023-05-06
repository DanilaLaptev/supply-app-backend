package com.diploma.supplyapp.dto

import com.diploma.supplyapp.entities.StorageItem

data class StorageItemDto (
        var id: Long?,
        var price: Double?,
        var description: String?,
        var quantity: Long?,
        var isHidden: Boolean?,
        var product: ProductDto?
) {
    companion object {

        fun fromEntity(storageItem: StorageItem) :StorageItemDto {
            val product = if (storageItem.product == null) null else ProductDto.fromEntity(storageItem.product!!)
            return StorageItemDto(
                    id = storageItem.id,
                    price = storageItem.price,
                    description = storageItem.description,
                    quantity = storageItem.quantity,
                    isHidden = storageItem.isHidden,
                    product = product
            )
        }

    }
}