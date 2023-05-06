package com.diploma.supplyapp.entities

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
class Supply (
        var price: Double,
        var quantity: Long,
        var groupId: Long,
        var deliveryTime: LocalDateTime,
        @ManyToOne
        var fromStorageItem: StorageItem,
        @ManyToOne
        var toStorageItem: StorageItem,
        @OneToMany(mappedBy = "supply", cascade = [CascadeType.ALL])
        var supplyStateHistory: List<SupplyStateHistory>
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    @CreationTimestamp
    var created: LocalDateTime = LocalDateTime.now()
}