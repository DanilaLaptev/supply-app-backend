package com.diploma.supplyapp.entities

import jakarta.persistence.*

@Entity
class OrganizationBranch (
        var addressName: String?,
        var latitude: Double?,
        var longitude: Double?,
        @ManyToOne(targetEntity = Organization::class)
        @JoinColumn(name = "organization_id")
        var organization: Organization?,
        @OneToMany(targetEntity = ContactPerson::class, mappedBy = "organizationBranch")
        var contactPeople: List<ContactPerson>,
        @OneToMany(mappedBy = "organizationBranch")
        var storageItems: List<StorageItem>
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
}