package com.diploma.supplyapp.services

import com.diploma.supplyapp.dto.ContactPersonDto

interface ContactPersonService {

    fun createContactPerson(organizationId: Long, branchId: Long, contactPersonDto: ContactPersonDto)

}