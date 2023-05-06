package com.diploma.supplyapp.services.impl

import com.diploma.supplyapp.dto.ContactPersonDto
import com.diploma.supplyapp.entities.ContactPerson
import com.diploma.supplyapp.repositories.ContactPersonRepository
import com.diploma.supplyapp.repositories.OrganizationBranchRepository
import com.diploma.supplyapp.services.ContactPersonService
import org.springframework.stereotype.Service

@Service
class ContactPersonServiceImpl (
        val contactPersonRepository: ContactPersonRepository,
        val organizationBranchRepository: OrganizationBranchRepository): ContactPersonService {
    override fun createContactPerson(organizationId: Long, branchId: Long, contactPersonDto: ContactPersonDto) {
        val organizationBranch = organizationBranchRepository.findByIdAndOrganizationId(branchId, organizationId).orElseThrow()
        val contactPerson = ContactPerson(
                fullName = contactPersonDto.fullName,
                email = contactPersonDto.email,
                phone = contactPersonDto.phone,
                organizationBranch = organizationBranch
        )
        contactPersonRepository.save(contactPerson)
    }
}