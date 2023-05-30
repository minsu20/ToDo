package com.example.todo.global.config.jwt

import com.example.todo.global.config.jwt.exception.JWTExceptionList
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {

    @Throws(IOException::class)
    override fun commence(
            request: HttpServletRequest,
            response: HttpServletResponse,
            authException: AuthenticationException
    ) {
        val exception = request.getAttribute("exception").toString()

        when (exception) {
            JWTExceptionList.UNKNOWN_ERROR.errorCode -> setResponse(response, JWTExceptionList.UNKNOWN_ERROR)
            JWTExceptionList.MAL_FORMED_TOKEN.errorCode -> setResponse(response, JWTExceptionList.MAL_FORMED_TOKEN)
            JWTExceptionList.ILLEGAL_TOKEN.errorCode -> setResponse(response, JWTExceptionList.ILLEGAL_TOKEN)
            JWTExceptionList.EXPIRED_TOKEN.errorCode -> setResponse(response, JWTExceptionList.EXPIRED_TOKEN)
            JWTExceptionList.UNSUPPORTED_TOKEN.errorCode -> setResponse(response, JWTExceptionList.UNSUPPORTED_TOKEN)
            else -> setResponse(response, JWTExceptionList.ACCESS_DENIED)
        }
    }

    @Throws(IOException::class)

    private fun setResponse(response: HttpServletResponse, exceptionCode: JWTExceptionList) {
        response.contentType = "application/json;charset=UTF-8"
        response.status = HttpServletResponse.SC_UNAUTHORIZED

        val responseJson = ObjectMapper().createObjectNode()
        responseJson.put("timestamp", LocalDateTime.now().withNano(0).toString())
        responseJson.put("message", exceptionCode.message)
        responseJson.put("errorCode", exceptionCode.errorCode)

        response.writer.print(responseJson.toString())
    }
}