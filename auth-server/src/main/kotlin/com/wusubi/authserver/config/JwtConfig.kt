package com.wusubi.authserver.config

import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.security.Key

@Configuration
class JwtConfig {

    @Bean
    fun secretKey(): Key =
        Keys.secretKeyFor(SignatureAlgorithm.HS256)
}
