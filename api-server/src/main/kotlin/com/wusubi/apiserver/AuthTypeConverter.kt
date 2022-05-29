package com.wusubi.apiserver

import com.wusubi.dto.AuthType
import org.springframework.core.convert.converter.Converter

class AuthTypeConverter : Converter<String, AuthType> {

    override fun convert(source: String): AuthType? =
        AuthType.valueOf(source.uppercase())
}
