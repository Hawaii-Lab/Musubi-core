package com.wusubi.apiserver.handler

interface LoginHandler {
    fun handle(authorizationCode: String, identityCode: String? = null)
}
