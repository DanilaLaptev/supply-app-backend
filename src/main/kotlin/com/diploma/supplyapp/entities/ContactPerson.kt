package com.diploma.supplyapp.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class ContactPerson (
    var fullName: String?,
    var email: String?,
    var phone: String?,
    @ManyToOne(targetEntity = OrganizationBranch::class)
    @JoinColumn(name = "organization_branch_id")
    var organizationBranch: OrganizationBranch
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
}