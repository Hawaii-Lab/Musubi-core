package com.wusubi.authserver.auth.model

data class AccessTokenClaims(
    val authType: AuthType,
    val issuer: String,
    val userId: String
)
