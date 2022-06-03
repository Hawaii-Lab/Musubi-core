package com.wusubi.apiserver.controller

import com.wusubi.apiserver.selector.LoginHandlerSelector
import com.wusubi.apiserver.service.LoginService
import com.wusubi.domain.model.Member
import com.wusubi.dto.AuthType
import com.wusubi.dto.LoginRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/login")
class LoginController(
    private val loginHandlerSelector: LoginHandlerSelector,
    private val loginService: LoginService,
) {

    @PostMapping("/{authType}")
    fun login(
        @PathVariable("authType") authType: AuthType,
        @RequestBody @Valid request: LoginRequest
    ): ResponseEntity<Member> =
        loginHandlerSelector
            .select(authType)
            ?.handle(request.authorizationCode, request.identityToken)
            .let {
                ResponseEntity.ok().build()
            }

    @GetMapping("/members")
    fun members(): MutableList<Member> =
        loginService.findMembers()
}
