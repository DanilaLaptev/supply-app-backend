package com.diploma.supplyapp.controllers

import com.diploma.supplyapp.dto.FilterDto
import com.diploma.supplyapp.dto.OrganizationDto
import com.diploma.supplyapp.dto.PageDto
import com.diploma.supplyapp.services.OrganizationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/organization")
class OrganizationController (val organizationService: OrganizationService) {

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