package com.diploma.supplyapp.configurations.security

import com.diploma.supplyapp.services.AuthorizationService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilter (
        val jwtProvider: JwtProvider,
        val authorizationService: AuthorizationService
): OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val header = request.getHeader("Authorization")
        if(header == null || header.length <= 7) {
            filterChain.doFilter(request, response)
            return
        }
        val jwtToken = header.substring(7)
        if(!jwtProvider.validateToken(jwtToken)) {
            filterChain.doFilter(request, response)
            return
        }
        val id = jwtProvider.getTokenClaims(jwtToken)["id"].toString().toLong()
        if(!authorizationService.existById(id)) {
            filterChain.doFilter(request, response)
            return
        }
        val securityToken = JwtSecurityToken(jwtToken)
        securityToken.isAuthenticated = true
        SecurityContextHolder.getContext().authentication = securityToken
        filterChain.doFilter(request, response)
    }
}