package com.wusubi.domain.repository

import com.wusubi.domain.model.Member
import com.wusubi.dto.AuthType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<Member, Long> {

    fun findByEmailAndAuthType(email: String, authType: AuthType): Member?
    fun findByEmail(email: String): Member
}
