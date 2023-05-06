package com.diploma.supplyapp.configurations.security

import com.diploma.supplyapp.entities.Organization
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.lang.IllegalArgumentException
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Component
class JwtProvider (
        @Value("\${jwt.secret}")
        var secret: String
) {
    fun generateToken(organization: Organization) : String{
        val now = Date()
        val expires = Date.from(LocalDateTime
                .now()
                .plusHours(1)
                .atZone(ZoneId.systemDefault())
                .toInstant())
        return Jwts.builder()
                .setNotBefore(now)
                .setIssuedAt(now)
                .setExpiration(expires)
                .signWith(SignatureAlgorithm.HS512, secret)
                .claim("id", organization.id.toString())
                .compact()
    }

    fun validateToken(token: String) : Boolean {
        try {
            Jwts
                    .parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
            return true
        }
        catch (ex: JwtException) {
            return false
        }
        catch (ex: IllegalArgumentException) {
            return false
        }
    }

    fun getTokenClaims(token: String): Claims {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .body
    }
}