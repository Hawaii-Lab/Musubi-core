package com.wusubi.apiserver.client.naver

import com.wusubi.apiserver.KSLF4J
import com.wusubi.apiserver.handler.LoginHandler
import com.wusubi.apiserver.service.LoginService
import com.wusubi.domain.model.Member
import com.wusubi.dto.AuthType
import org.springframework.stereotype.Component

@Component
class NaverLoginHandler(
    private val naverClient: NaverClient,
    private val loginService: LoginService
) : LoginHandler {
    companion object : KSLF4J()

    override fun handle(authorizationCode: String, identityCode: String?): Member? {
        val NaverMember: NaverProfile = naverClient.getUserInfo("Bearer $authorizationCode")

        val member = Member(
            email = NaverMember.response.email,
            name = NaverMember.response.name,
            nickname = NaverMember.response.nickname,
            authType = AuthType.NAVER
        )

        loginService.signUp(member)
        return member
    }
}
