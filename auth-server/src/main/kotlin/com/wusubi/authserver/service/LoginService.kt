package com.wusubi.authserver.service

import com.wusubi.authserver.auth.model.AuthType
import com.wusubi.domain.repository.UserRepository
import com.wusubi.dto.LoginRequest
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val userRepository: UserRepository,
    private val loginHandler: LoginHandler
) {

    fun login(authType: AuthType, request: LoginRequest): String {
        // (1) TODO: select a handler for each auth type
        loginHandler.handle(authType, request)
        // (2) TODO: Get user info from each vendor API

        // (3) TODO: Check a user whether sign up

        // (4) TODO: Issue access and refresh token to client
    }
}
