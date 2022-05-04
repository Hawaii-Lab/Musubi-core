package com.wusubi.authserver.controller

import com.wusubi.authserver.service.OauthService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/oauth")
class OauthController(private val oauthService: OauthService) {

    // access token 받아오기
    @GetMapping("/kakao")
    fun kakaoLoginTest(@RequestParam("code") code: String) {
        val accessToken: String = oauthService.getKakaoAccessToken(code)
        println("access_Token : $accessToken")
        val content: String = oauthService.getKakaoUserInfo(accessToken)
        println("content : $content")
    }

    // access token으로 사용자 정보 받아오기
//    @PostMapping("/signUp")
//    fun signUp(@RequestBody signUpRequest: SignUpRequest) {
//        oauthService.signUp(signUpRequest)
//    }
}
