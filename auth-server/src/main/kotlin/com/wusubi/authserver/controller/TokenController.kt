package com.wusubi.authserver.controller

import com.wusubi.authserver.service.TokenService
import com.wusubi.dto.TokenDto
import com.wusubi.dto.TokenRequest
import org.springframework.context.annotation.Description
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/token")
class TokenController(
    private val tokenService: TokenService
) {

    @PostMapping("/refresh")
    @Description("Refresh access token")
    fun refreshAccessToken(@RequestBody @Valid request: TokenRequest) =
        ResponseEntity.ok(
            TokenDto(
                accessToken = tokenService.refreshAccessToken(request.refreshToken!!),
                refreshToken = request.refreshToken!!
            )
        )
}
