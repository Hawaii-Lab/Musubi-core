package com.wusubi.domain.model

import com.wusubi.dto.AuthType
import com.wusubi.dto.GenderType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Member(
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    var id: Long,
    var authType: AuthType ,
    var name: String? = null,
    var email: String,
    var isActive: Boolean = true,
    var nickname: String? = null,
    var gender: GenderType,

    @OneToMany(fetch = FetchType.LAZY)
    var category: List<Category>? = null
)
