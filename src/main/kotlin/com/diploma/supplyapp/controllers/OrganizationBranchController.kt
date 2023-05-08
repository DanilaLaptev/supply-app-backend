package com.diploma.supplyapp.controllers

import com.diploma.supplyapp.dto.*
import com.diploma.supplyapp.services.ContactPersonService
import com.diploma.supplyapp.services.OrganizationBranchService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/organization/branch")
class OrganizationBranchController (
        val organizationBranchService: OrganizationBranchService,
        val contactPersonService: ContactPersonService
) {
    @PostMapping("/")
    fun createOrganizationBranch(@RequestBody organizationBranchDto: OrganizationBranchDto, @RequestAttribute("id") organizationId: Long) : ResponseEntity<OrganizationBranchDto> {
        return ResponseEntity.ok(organizationBranchService.createBranch(organizationId, organizationBranchDto))
    }

    @PostMapping("/{branchId}/")
    fun updateOrganizationBranch(@PathVariable branchId: Long, @RequestBody organizationBranchDto: OrganizationBranchDto, @RequestAttribute("id") organizationId: Long) : ResponseEntity<OrganizationBranchDto> {
        return ResponseEntity.ok(organizationBranchService.updateBranch(branchId, organizationBranchDto, organizationId))
    }

    @PostMapping("/{branchId}/contactPerson/")
    fun addContactPerson(@PathVariable branchId: Long, @RequestBody contactPersonDto: ContactPersonDto, @RequestAttribute("id") organizationId: Long): ResponseEntity<Void> {
        contactPersonService.createContactPerson(organizationId, branchId, contactPersonDto)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/{branchId}/products/")
    fun getStorageItems(@RequestBody filterDto: FilterDto, @PathVariable branchId: Long) : ResponseEntity<PageDto<StorageItemDto>> {
        return ResponseEntity.ok(organizationBranchService.getStorageItems(branchId, filterDto))
    }

    @PutMapping("/{branchId}/products/")
    fun addStorageItems(@PathVariable branchId: Long, @RequestAttribute("id") organizationId: Long, @RequestBody items: List<StorageItemDto>): ResponseEntity<Void> {
        organizationBranchService.addStorageItems(branchId, organizationId, items)
        return ResponseEntity.ok().build()
    }

    @PatchMapping("/{branchId}/products/")
    fun updateStorageItem(@PathVariable branchId: Long, @RequestAttribute("id") organizationId: Long, @RequestBody item: StorageItemDto): ResponseEntity<StorageItemDto> {
        return ResponseEntity.ok(organizationBranchService.updateStorageItem(branchId, organizationId, item))
    }
}