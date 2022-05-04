package com.wusubi.authserver.service

import com.wusubi.authserver.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class AuthService(private val userRepository: UserRepository) {
    fun loadUserByEmail(authenticationUserId: String): UserDetails? {
    }
}
