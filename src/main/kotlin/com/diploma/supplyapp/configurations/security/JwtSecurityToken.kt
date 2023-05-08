package com.diploma.supplyapp.configurations.security

import org.springframework.security.authentication.AbstractAuthenticationToken

class JwtSecurityToken (
        private val token: String
) : AbstractAuthenticationToken(null) {
    override fun getCredentials(): Any {
        return token
    }

    override fun getPrincipal(): Any {
        return token
    }
}