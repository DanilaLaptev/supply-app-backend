package com.diploma.supplyapp.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

@Entity
class OrganizationBranch (
        var addressName: String?,
        var latitude: Double?,
        var longitude: Double?,
        @ManyToOne(targetEntity = Organization::class)
        @JoinColumn(name = "organization_id")
        var organization: Organization?,
        @OneToMany(targetEntity = ContactPerson::class, mappedBy = "organizationBranch")
        var contactPersons: List<ContactPerson>,
        @OneToMany(mappedBy = "organizationBranch")
        var storageItems: List<StorageItem>
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
}