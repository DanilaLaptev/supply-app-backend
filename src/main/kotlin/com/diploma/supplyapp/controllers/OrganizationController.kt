package com.diploma.supplyapp.controllers

import com.diploma.supplyapp.dto.FilterDto
import com.diploma.supplyapp.dto.OrganizationDto
import com.diploma.supplyapp.dto.PageDto
import com.diploma.supplyapp.services.OrganizationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/organization")
class OrganizationController (
        val organizationService: OrganizationService
) {
    @PutMapping("/")
    fun updateOrganization(@RequestAttribute("id") id: Long, @RequestBody organizationDto: OrganizationDto): ResponseEntity<Void> {
        organizationService.updateOrganization(organizationDto, id)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/")
    fun getOrganizations(@RequestBody filter: FilterDto) : ResponseEntity<PageDto<OrganizationDto>> {
        return ResponseEntity.ok(organizationService.getOrganizations(filter))
    }

    @GetMapping("/{id}")
    fun getOrganization(@PathVariable id: Long): ResponseEntity<OrganizationDto> {
        return ResponseEntity.ok(organizationService.getOrganization(id))
    }
}