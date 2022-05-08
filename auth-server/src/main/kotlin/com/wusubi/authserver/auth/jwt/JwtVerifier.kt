package com.wusubi.authserver.auth.jwt

import com.wusubi.authserver.auth.model.AccessTokenClaims
import com.wusubi.authserver.auth.model.AuthType
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.security.Key

@Component
class JwtVerifier(
    private val secretKey: Key
) {

    fun verify(token: String): AccessTokenClaims =
        try {
            Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .body
                .let {
                    AccessTokenClaims(
                        issuer = it.issuer,
                        authType = AuthType.valueOf(it["authType"].toString()),
                        userId = it.subject
                    )
                }
        } catch (e: JwtException) {
            throw JwtException("Invalid token")
        }
}
