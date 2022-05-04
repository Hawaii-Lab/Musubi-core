package com.wusubi.authserver.service

import org.json.JSONObject
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

@Service
class OauthService {
    fun getKakaoAccessToken(code: String): String {
        val reqURL = "https://kauth.kakao.com/oauth/token"
        val url = URL(reqURL)
        val con: HttpURLConnection = url.openConnection() as HttpURLConnection

        con.requestMethod = "POST"
        con.doOutput = true

        val bw = BufferedWriter(OutputStreamWriter(con.outputStream))
        val sb = StringBuilder()
        sb.append("grant_type=authorization_code")
        sb.append("&client_id=83c7c68ec936f2864e78f3648fb6d7c7")
        sb.append("&redirect_uri=http://localhost:8080/oauth/kakao")
        sb.append("&code=$code")
        bw.write(sb.toString())
        bw.flush()

        val responseCode: Int = con.responseCode
        if (responseCode != HttpStatus.OK.value())
            throw IllegalStateException()

        val br = BufferedReader(InputStreamReader(con.inputStream))
        val content = br.readText()
        val obj = JSONObject(content)

        val accessToken: String = obj.optString("access_token")

        return accessToken
    }

    fun getKakaoUserInfo(kakaoAccessToken: String): String {
        val reqURL = "https://kapi.kakao.com/v2/user/me"
        val url = URL(reqURL)
        val con = url.openConnection() as HttpURLConnection

        con.setRequestProperty(HttpHeaders.AUTHORIZATION, "Bearer $kakaoAccessToken")

        val responseCode: Int = con.responseCode
        if (responseCode != HttpStatus.OK.value())
            throw IllegalStateException()

        val br = BufferedReader(InputStreamReader(con.inputStream))
        val content = br.readText()
        br.close()

        return content
    }
}
