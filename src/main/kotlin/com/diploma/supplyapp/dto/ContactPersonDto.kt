package com.diploma.supplyapp.dto

import com.diploma.supplyapp.entities.ContactPerson

data class ContactPersonDto (
        var id: Long,
        var fullName: String?,
        var email: String?,
        var phone: String?
) {
    companion object {
        fun fromEntity(contactPerson: ContactPerson): ContactPersonDto {
            return ContactPersonDto(
                id = contactPerson.id,
                email = contactPerson.email,
                phone = contactPerson.phone,
                fullName = contactPerson.fullName
            )
        }
    }
}