package com.diploma.supplyapp.entities

import com.diploma.supplyapp.enums.OrganizationRole
import jakarta.persistence.*

@Entity
data class Organization (
        @Enumerated(EnumType.STRING)
        @Column(name = "organization_role")
        var role: OrganizationRole?,
        var title : String?,
        var email: String?,
        var password: String?,
        var approved: Boolean?,
        var image: String?,

        @OneToMany(targetEntity = OrganizationBranch::class, mappedBy = "organization")
        var branches: List<OrganizationBranch>
) {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0
}