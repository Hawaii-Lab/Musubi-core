package com.wusubi.apiserver.error

import org.springframework.core.convert.ConversionFailedException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalControllerExceptionHandler {

    @ExceptionHandler(ConversionFailedException::class)
    fun handleAuthType(ex: Exception) =
        ResponseEntity.badRequest()
}
