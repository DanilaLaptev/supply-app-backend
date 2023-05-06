package com.diploma.supplyapp.controllers

import com.diploma.supplyapp.dto.AuthorizationDto
import com.diploma.supplyapp.services.AuthorizationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthorizationController (val authorizationService: AuthorizationService) {

    @GetMapping("/check")
    fun check(@RequestAttribute("id") organizationId: Long) : ResponseEntity<AuthorizationDto>? {
        return ResponseEntity.ok(authorizationService.check(organizationId))
    }

    @PostMapping("/register")
    fun register(@RequestBody authorizationDto: AuthorizationDto) : ResponseEntity<AuthorizationDto>? {
        return ResponseEntity.ok(authorizationService.register(authorizationDto))
    }

    @PostMapping("/login")
    fun login(@RequestBody authorizationDto: AuthorizationDto) : ResponseEntity<AuthorizationDto>? {
        return ResponseEntity.ok(authorizationService.login(authorizationDto))
    }

}