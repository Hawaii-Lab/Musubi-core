package com.musubi.model.domain.model

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany


@Entity
class Member(
    @Id
    @GeneratedValue
    var id: Long? = null,
    var name: String? = null,
    var email: String? = null,
    var isActive: Boolean? = null,
    var nickname: String? = null,
    var gender: String? = null,

    @OneToMany(fetch = FetchType.LAZY)
    var category: List<Category>? = null
)
