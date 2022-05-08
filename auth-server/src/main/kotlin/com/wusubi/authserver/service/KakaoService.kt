package com.wusubi.authserver.service

import com.wusubi.domain.model.Member
import com.wusubi.dto.LoginRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

@FeignClient(name = "kakao-api", url = "https://kapi.kakao.com/v2/user/me")
interface KakaoService {
    fun getUserInfo(loginRequest: LoginRequest): Member
}


//class KakaoService {
//    fun getUserInfo(request: LoginRequest): Member {
//
//        val reqURL = "https://kapi.kakao.com/v2/user/me"
//        val url = URL(reqURL)
//        val con = url.openConnection() as HttpURLConnection
//
//        con.setRequestProperty(HttpHeaders.AUTHORIZATION, "Bearer ${request.accessToken}")
//
//        val responseCode: Int = con.responseCode
//        if (responseCode != HttpStatus.OK.value())
//            throw IllegalStateException()
//
//        val br = BufferedReader(InputStreamReader(con.inputStream))
//        val content = br.readText()
//        br.close()
//
//        return content
//
//
//    }
//}
