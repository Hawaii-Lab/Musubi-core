package com.wusubi.authserver.config.error

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val code: String,
    val message: String
) {
    // Token
    NO_TOKEN_ERROR(HttpStatus.FORBIDDEN, "T001", "No Token Error"),
    INVALID_TOKEN_ERROR(HttpStatus.FORBIDDEN, "T002", "Invalid Token Error"),
    EXPIRED_TOKEN_ERROR(HttpStatus.FORBIDDEN, "T003", "Expired Token Error"),
}
