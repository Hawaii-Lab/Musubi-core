package com.wusubi.dto

import javax.validation.constraints.NotEmpty

data class TokenRequest(
    @field:NotEmpty
    val refreshToken: String?
)

data class TokenDto(
    val accessToken: String,
    val refreshToken: String
)
