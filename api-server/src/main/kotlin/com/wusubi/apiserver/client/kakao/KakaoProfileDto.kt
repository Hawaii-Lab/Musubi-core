package com.wusubi.apiserver.client.kakao

data class KakaoProfile(
    val properties: Properties,
    val kakao_account: KakaoAccount
)

data class Properties(
    val nickname: String
)

data class KakaoAccount(
    val email: String
)
