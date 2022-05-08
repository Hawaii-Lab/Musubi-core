package com.wusubi.authserver.auth.jwt

import com.wusubi.authserver.auth.model.AccessTokenClaims
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.security.Key
import java.time.Instant
import java.util.*

@Component
class JwtProvider(
    private val secretKey: Key
) {

    fun provide(accessTokenClaims: AccessTokenClaims): String =
        Jwts.builder()
            .setIssuer(accessTokenClaims.issuer)
            .setSubject(accessTokenClaims.userId)
            .setIssuedAt(Date())
            .setExpiration(Date.from(Instant.now().plusSeconds(15 * 60)))
            .claim("authType", accessTokenClaims.authType)
            .signWith(secretKey)
            .compact()
}
