package com.diploma.supplyapp.configurations.security

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class JwtInterceptor (val jwtProvider: JwtProvider): HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val jwtToken = SecurityContextHolder.getContext().authentication.credentials as String
        if("" != jwtToken && jwtProvider.validateToken(jwtToken)) {
            val claims = jwtProvider.getTokenClaims(jwtToken)
            claims.forEach { key, value ->  request.setAttribute(key, value)}
        }
        return super.preHandle(request, response, handler)
    }
}