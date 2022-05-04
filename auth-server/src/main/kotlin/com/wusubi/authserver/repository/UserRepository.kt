package com.wusubi.authserver.repository

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<Member, Long>
