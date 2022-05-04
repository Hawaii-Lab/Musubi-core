package com.wusubi.authserver.config.jwt

import com.wusubi.authserver.service.AuthService
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtProvider(
    private val authService: AuthService
) {
    private val secretKey: String = Base64.getEncoder().encodeToString("secretKey".encodeToByteArray())

    private fun getAuthenticationUserId(token: String?): String {
        return Jwts.parser().setSigningKey(secretKey)
            .parseClaimsJws(token).body.subject
    }

    fun getAuthentication(token: String?): Authentication {
        val user: UserDetails? = authService.loadUserByEmail(getAuthenticationUserId(token))
        return UsernamePasswordAuthenticationToken(user, "", null)
    }

    fun getTokenFromHeader(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse): String {
    }

    fun decodeToken(token: String) {
    }
}
