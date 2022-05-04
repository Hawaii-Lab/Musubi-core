package com.wusubi.authserver.config.jwt

import com.wusubi.authserver.config.error.ErrorCode
import com.wusubi.authserver.config.error.ErrorResponse
import com.wusubi.authserver.util.HandlerResponseUtil
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(
    private val jwtProvider: JwtProvider
) : GenericFilterBean() {

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        try {
            val token: String = jwtProvider.getTokenFromHeader(request as HttpServletRequest, response as HttpServletResponse)
            jwtProvider.decodeToken(token)
        } catch (e: ExpiredJwtException) {
            jwtFailureTask(response as HttpServletResponse, ErrorCode.EXPIRED_TOKEN_ERROR)
            return
        } catch (e: NullPointerException) {
            jwtFailureTask(response as HttpServletResponse, ErrorCode.NO_TOKEN_ERROR)
            return
        } catch (e: Exception) {
            jwtFailureTask(response as HttpServletResponse, ErrorCode.INVALID_TOKEN_ERROR)
            return
        }

        chain!!.doFilter(request, response)
    }

    // 토큰의 유효성 검증을 통과하지 못할 경우 에러 코드 반환
    fun jwtFailureTask(
        response: HttpServletResponse,
        e: ErrorCode
    ) {
        HandlerResponseUtil.doResponse(response, ErrorResponse.error(e), e.status)
    }
}
