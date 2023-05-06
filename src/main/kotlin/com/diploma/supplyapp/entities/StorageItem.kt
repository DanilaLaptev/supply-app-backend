package com.diploma.supplyapp.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

@Entity
class StorageItem (
        var isHidden: Boolean?,
        var price: Double?,
        var description: String?,
        var quantity: Long?,
        @ManyToOne
        @JoinColumn(name = "organization_branch_id")
        var organizationBranch: OrganizationBranch?,
        @ManyToOne
        @JoinColumn(name = "product_id")
        var product: Product?,
        @OneToMany(mappedBy = "fromStorageItem")
        var sentSupply: List<Supply>,
        @OneToMany(mappedBy = "toStorageItem")
        var receivedSupply: List<Supply>
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
}