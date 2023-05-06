package com.diploma.supplyapp.entities

import com.diploma.supplyapp.enums.SupplyStatus
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
class SupplyStateHistory(
        @Enumerated(EnumType.STRING)
        var status: SupplyStatus,
        @ManyToOne(cascade = [CascadeType.ALL])
        var supply: Supply?
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    @CreationTimestamp
    var time:LocalDateTime = LocalDateTime.now()
}