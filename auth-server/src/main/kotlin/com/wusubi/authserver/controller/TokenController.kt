package com.wusubi.authserver.controller

import org.springframework.context.annotation.Description
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/token")
class TokenController(
    private val tokenService: TokenService
) {

    @PostMapping
    @Description("")
    fun issueAccessToken()

    @PostMapping
    @Description("")
    fun refreshAccessToken()
}