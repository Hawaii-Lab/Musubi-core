package com.wusubi.apiserver.client.kakao

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "kakao-client", url = "https://kapi.kakao.com")
interface KakaoClient {

    @GetMapping("/v2/user/me")
    fun getUserInfo(@RequestHeader(value = "Authorization", required = true) authorizationCode: String): KakaoProfile
}
