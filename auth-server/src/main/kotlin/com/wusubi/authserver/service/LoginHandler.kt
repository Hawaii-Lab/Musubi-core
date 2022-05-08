package com.wusubi.authserver.service

import com.wusubi.authserver.auth.model.AuthType
import com.wusubi.dto.LoginRequest


class LoginHandler(
    private val kakaoService: KakaoService
) {

    fun handle(authType: AuthType, request: LoginRequest) {
        val member = when (authType) {
            AuthType.Kakao -> {
                kakaoService.getUserInfo(request)
            }
            else -> null
        }
    }
}
