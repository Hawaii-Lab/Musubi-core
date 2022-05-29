package com.wusubi.apiserver.client.naver

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "naver-client", url = "https://openapi.naver.com")
interface NaverClient {

    @GetMapping("/v1/nid/me")
    fun getUserInfo(@RequestHeader(value = "Authorization", required = true) authorizationCode: String): NaverProfile
}
