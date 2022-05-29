package com.wusubi.authserver.auth.model

import com.wusubi.dto.AuthType

data class AccessTokenClaims(
    val authType: AuthType,
    val issuer: String,
    val userId: String
)
