package com.wusubi.apiserver.client.kakao

import com.wusubi.apiserver.KSLF4J
import com.wusubi.apiserver.handler.LoginHandler
import com.wusubi.domain.repository.UserRepository
import com.wusubi.dto.AuthType
import org.springframework.stereotype.Component

@Component
class KakaoLoginHandler(
    private val kakaoClient: KakaoClient,
  //  private val userRepository: UserRepository
) : LoginHandler {

    companion object : KSLF4J()

    override fun handle(authorizationCode: String, identityCode: String?) {
        val member = kakaoClient.getUserInfo("Bearer $authorizationCode")
        log.info("$member")

        // email 없는 경우 -> 종료
        if (member.kakao_account.email == null) {
            log.error("email is null")
            return
        }
//        else if(userRepository.findByEmailAndAuthType(member.kakao_account.email, AuthType.KAKAO).isPresent()) {
//            log.error("email is already exist")
//            return
//        }

    }
}
