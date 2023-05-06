package com.diploma.supplyapp.entities

import com.diploma.supplyapp.enums.ProductType
import jakarta.persistence.*

@Entity
class Product (
        @Enumerated(EnumType.STRING)
        var productType: ProductType?,
        var name: String?,
        var approved: Boolean?,
        @OneToMany(mappedBy = "product")
        var storageItems: List<StorageItem>,
        var image: String?
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
}