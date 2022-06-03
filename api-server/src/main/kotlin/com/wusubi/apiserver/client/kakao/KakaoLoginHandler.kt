package com.wusubi.apiserver.client.kakao

import com.wusubi.apiserver.KSLF4J
import com.wusubi.apiserver.handler.LoginHandler
import com.wusubi.apiserver.service.LoginService
import com.wusubi.domain.model.Member
import com.wusubi.dto.AuthType
import org.springframework.stereotype.Component

@Component
class KakaoLoginHandler(
    private val kakaoClient: KakaoClient,
    private val loginService: LoginService,
) : LoginHandler {

    companion object : KSLF4J()

    override fun handle(authorizationCode: String, identityCode: String?): Member? {
        val kakaoMember: KakaoProfile = kakaoClient.getUserInfo("Bearer $authorizationCode")

        if (kakaoMember == null) {
            throw IllegalArgumentException("kakaoMember is null")
        }
        if (kakaoMember.kakao_account.email == null) {
            throw IllegalArgumentException("kakaoMember email is null")
        }

        val member = Member(
            email = kakaoMember.kakao_account.email,
            name = kakaoMember.properties.nickname,
            nickname = kakaoMember.properties.nickname,
            authType = AuthType.KAKAO,
        )
        loginService.signUp(member)
        return member
    }
}
