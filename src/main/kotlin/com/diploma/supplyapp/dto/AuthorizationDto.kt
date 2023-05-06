package com.diploma.supplyapp.dto

import com.diploma.supplyapp.enums.OrganizationRole
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class AuthorizationDto (
        var organizationId : Long?,
        var mainBranchId : Long?,
        var role: OrganizationRole?,
        var title: String?,
        var email: String?,
        var password: String?,
        var token: String?
)