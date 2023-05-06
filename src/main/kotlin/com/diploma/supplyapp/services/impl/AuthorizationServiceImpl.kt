package com.diploma.supplyapp.services.impl

import com.diploma.supplyapp.configurations.security.JwtProvider
import com.diploma.supplyapp.dto.AuthorizationDto
import com.diploma.supplyapp.entities.Organization
import com.diploma.supplyapp.repositories.OrganizationRepository
import com.diploma.supplyapp.services.AuthorizationService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthorizationServiceImpl (
        val organizationRepository: OrganizationRepository,
        val passwordEncoder: PasswordEncoder,
        val jwtProvider: JwtProvider
): AuthorizationService {
    override fun existById(id: Long): Boolean {
        return organizationRepository.existsById(id)
    }

    override fun check(organizationId: Long): AuthorizationDto {
        val organization = organizationRepository.findById(organizationId).orElseThrow { IllegalArgumentException("User not found") }
        return AuthorizationDto(
                organizationId = organization.id,
                mainBranchId = if (organization.branches.isEmpty())  null else organization.branches.last().id,
                token = null,
                title = organization.title,
                password = null,
                email = organization.email,
                role = organization.role
        )
    }

    override fun login(authorizationDto: AuthorizationDto): AuthorizationDto {
        val organization = organizationRepository.findByEmail(authorizationDto.email!!).orElseThrow { IllegalArgumentException("User not found") }
        if(!passwordEncoder.matches(authorizationDto.password, organization.password)) {
            throw IllegalArgumentException("Wrong password")
        }
        return AuthorizationDto(
                organizationId = organization.id,
                mainBranchId = if (organization.branches.isEmpty())  null else organization.branches.last().id,
                token = jwtProvider.generateToken(organization),
                title = organization.title,
                password = null,
                email = organization.email,
                role = organization.role
        )
    }

    override fun register(authorizationDto: AuthorizationDto): AuthorizationDto {
        var organization = Organization(
                role = authorizationDto.role,
                title = authorizationDto.title,
                email = authorizationDto.email,
                password = passwordEncoder.encode(authorizationDto.password),
                approved = false,
                null,
                branches = ArrayList(),
        )
        organization = organizationRepository.save(organization)
        return AuthorizationDto(
                organizationId = organization.id,
                mainBranchId = null,
                token = jwtProvider.generateToken(organization),
                title = organization.title,
                password = null,
                email = organization.email,
                role = organization.role
        )
    }
}