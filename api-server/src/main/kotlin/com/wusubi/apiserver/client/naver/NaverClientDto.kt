package com.wusubi.apiserver.client.naver

data class NaverProfile(
    val response : Response
)

data class Response(
    val nickname: String,
    val name : String,
    val email: String
)
