package com.wusubi.domain.model

import com.wusubi.dto.AuthType
import com.wusubi.dto.GenderType
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Member(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Enumerated(EnumType.STRING)
    var authType: AuthType,

    var name: String? = null,
    var email: String,
    var isActive: Boolean = true,
    var nickname: String? = null,

    @Enumerated(EnumType.STRING)
    var gender: GenderType = GenderType.U,

    @OneToMany(fetch = FetchType.LAZY)
    var category: List<Category>? = null
)
