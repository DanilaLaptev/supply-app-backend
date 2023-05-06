package com.diploma.supplyapp.controllers

import com.diploma.supplyapp.dto.FilterDto
import com.diploma.supplyapp.dto.SupplyDto
import com.diploma.supplyapp.services.SupplyService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/supplies")
class SupplyController(
        val supplyService: SupplyService
) {

    @PostMapping("/")
    fun createSupply(@RequestBody dto: SupplyDto, @RequestAttribute("id") id: Long) : ResponseEntity<Void> {
        supplyService.createSupply(dto, id)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/")
    fun getSupply(
            @RequestParam(required = false) startDate: LocalDateTime?,
            @RequestParam(required = false) endDate: LocalDateTime?,
            @RequestParam(required = false) outgoingSupply: Boolean?,
            @RequestParam(required = false) incomingSupply: Boolean?,
            @RequestParam branchId: Long): ResponseEntity<List<SupplyDto>> {
        return ResponseEntity.ok(supplyService.getSupply(startDate, endDate, outgoingSupply, incomingSupply, branchId))
    }

    @PostMapping("/{id}/decline")
    fun declineSupply(@RequestAttribute("id") organizationId: Long, @PathVariable id: Long, @RequestParam organizationBranch: Long): ResponseEntity<Void>{
        supplyService.declineSupply(organizationId, id, organizationBranch)
        return ResponseEntity.ok().build()
    }

    @PostMapping("{id}/accept")
    fun acceptSupply(@RequestAttribute("id") organizationId: Long, @PathVariable id: Long, @RequestParam organizationBranch: Long): ResponseEntity<Void>{
        supplyService.acceptSupply(organizationId, id, organizationBranch)
        return ResponseEntity.ok().build()
    }
}