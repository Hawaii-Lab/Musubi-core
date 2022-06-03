package com.wusubi.apiserver.handler

import com.wusubi.domain.model.Member

interface LoginHandler {
    fun handle(authorizationCode: String, identityCode: String? = null): Member?
}
