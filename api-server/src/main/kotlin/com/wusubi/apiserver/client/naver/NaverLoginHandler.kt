package com.wusubi.apiserver.client.naver

import com.wusubi.apiserver.KSLF4J
import com.wusubi.apiserver.handler.LoginHandler
import org.springframework.stereotype.Component

@Component
class NaverLoginHandler(
    private val naverClient: NaverClient
) : LoginHandler {
    companion object : KSLF4J()

    override fun handle(authorizationCode: String, identityCode: String?) {
        val member = naverClient.getUserInfo("Bearer $authorizationCode")
        log.info("$member")


    }
}
