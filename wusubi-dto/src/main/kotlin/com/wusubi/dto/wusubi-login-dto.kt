package com.wusubi.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.validation.constraints.NotEmpty

@JsonIgnoreProperties(ignoreUnknown = true)
data class LoginRequest(
    @field:NotEmpty
    val authorizationCode: String,
    val identityToken: String? = null
)

data class LoginDto(
    val accessToken: String?,
    val refreshToken: String?
)

//data class KakaoProfile(
//    val id: Long,
//    val properties: Properties,
//    val kakao_account: KakaoAccount
//)
//
//data class Properties(
//    val nickname: String
//)
//
//data class KakaoAccount(
//    val email: String,
//    val gender: String
//)
