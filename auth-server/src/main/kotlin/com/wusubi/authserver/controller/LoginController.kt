package com.wusubi.authserver.controller

import com.wusubi.authserver.auth.model.AuthType
import com.wusubi.authserver.service.LoginHandler
import com.wusubi.authserver.service.LoginService
import com.wusubi.dto.LoginRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/login")
class LoginController(
    private val loginService: LoginService
) {

    @PostMapping("/{authType}")
    fun login(
        @PathVariable("authType") authType: AuthType,
        @RequestBody @Valid request: LoginRequest
    ) =
        ResponseEntity.ok(
            loginService.login(authType, request)
        )


}
