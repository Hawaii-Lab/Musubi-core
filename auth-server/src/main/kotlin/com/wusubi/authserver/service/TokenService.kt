package com.wusubi.authserver.service

import com.wusubi.authserver.auth.jwt.JwtProvider
import com.wusubi.authserver.auth.jwt.JwtVerifier
import com.wusubi.authserver.auth.model.AccessTokenClaims
import com.wusubi.dto.AuthType
import org.springframework.stereotype.Service

@Service
class TokenService(
    private val jwtProvider: JwtProvider,
    private val jwtVerifier: JwtVerifier
) {

    fun issueAccessToken(email: String, authType: AuthType): String =
        jwtProvider.provide(
            AccessTokenClaims(
                issuer = "wusubi-auth",
                authType = authType,
                userId = email
            )
        )

    fun refreshAccessToken(refreshToken: String): String =
        verifyRefreshToken(refreshToken).let {
            jwtProvider.provide(it)
        }

    fun verifyRefreshToken(refreshToken: String): AccessTokenClaims =
        jwtVerifier.verify(refreshToken)
}
