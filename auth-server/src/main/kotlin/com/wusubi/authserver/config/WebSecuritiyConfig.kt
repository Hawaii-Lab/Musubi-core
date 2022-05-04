package com.wusubi.authserver.config.jwt

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
class WebSecurityConfig(private val jwtProvider: JwtProvider) : WebSecurityConfigurerAdapter() {

    // 필터를 안 타는 url들 설정
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers(
            "/h2-console/**",
            "/signUp",
            "/signIn"
        )
    }

    // http 관련 설정
    override fun configure(http: HttpSecurity) {
        http.httpBasic().disable()
        http.cors().disable()
        http.csrf().disable()
        http.formLogin().disable()
        http.addFilterBefore(JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter::class.java)
    }
}
