package com.wusubi.apiserver.selector

import com.wusubi.apiserver.client.kakao.KakaoLoginHandler
import com.wusubi.apiserver.client.naver.NaverLoginHandler
import com.wusubi.apiserver.handler.LoginHandler
import com.wusubi.dto.AuthType
import org.springframework.stereotype.Component

@Component
class LoginHandlerSelector(
    private val kakaoLoginHandler: KakaoLoginHandler,
    private val naverLoginHandler: NaverLoginHandler,
) {
    fun select(authType: AuthType): LoginHandler? =
        when (authType) {
            AuthType.KAKAO -> kakaoLoginHandler
            AuthType.NAVER -> naverLoginHandler
            else -> null
        }
}
