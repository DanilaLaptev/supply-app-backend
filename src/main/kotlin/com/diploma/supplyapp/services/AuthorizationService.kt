package com.diploma.supplyapp.services

import com.diploma.supplyapp.dto.AuthorizationDto

interface AuthorizationService {

    fun existById(id: Long): Boolean

    fun check(organizationId: Long) : AuthorizationDto

    fun login(authorizationDto: AuthorizationDto) : AuthorizationDto

    fun register(authorizationDto: AuthorizationDto) : AuthorizationDto

}