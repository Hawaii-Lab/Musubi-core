package com.wusubi.domain.repository

import com.wusubi.domain.model.Member
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<Member, Long>
