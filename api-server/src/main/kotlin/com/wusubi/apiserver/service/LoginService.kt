package com.wusubi.apiserver.service

import com.wusubi.domain.model.Member
import com.wusubi.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val userRepository: UserRepository
) {

    fun signUp(member: Member) {
        userRepository.save(member)
    }

    fun findMembers(): MutableList<Member> {
        return userRepository.findAll()
    }
}
