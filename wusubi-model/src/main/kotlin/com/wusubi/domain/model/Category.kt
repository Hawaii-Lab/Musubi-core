package com.wusubi.domain.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Category(

    @Id
    @GeneratedValue
    var id: Long? = null,
    var categoryCode: String? = null,
    var name: String? = null,
    @ManyToOne
    var member: Member? = null,
)
