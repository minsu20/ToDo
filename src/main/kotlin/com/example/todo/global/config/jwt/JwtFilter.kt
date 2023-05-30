package com.example.todo.global.config.jwt

import com.example.todo.global.config.jwt.exception.JWTExceptionList
import com.example.todo.global.config.jwt.exception.MalformedException
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.UnsupportedJwtException
import mu.KotlinLogging
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


private val logger = KotlinLogging.logger {}

class JwtFilter(private val tokenProvider: TokenProvider) : OncePerRequestFilter() {

    companion object {
        private const val AUTHORIZATION_HEADER = "Authorization"
    }

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val jwt = resolveToken(request)
        val requestURI = request.requestURI

        try {
            if (jwt.isNotNullOrBlank() && tokenProvider.validateToken(jwt)) {
                val authentication = tokenProvider.getAuthentication(jwt)
                SecurityContextHolder.getContext().authentication = authentication
                logger.debug { "Security Context에 '${authentication.name}' 인증 정보를 저장했습니다, uri: $requestURI" }
            }
        } catch (e: MalformedException) {
            request.setAttribute("exception", JWTExceptionList.MAL_FORMED_TOKEN.errorCode)
        } catch (e: ExpiredJwtException) {
            request.setAttribute("exception", JWTExceptionList.EXPIRED_TOKEN.errorCode)
        } catch (e: UnsupportedJwtException) {
            request.setAttribute("exception", JWTExceptionList.UNSUPPORTED_TOKEN.errorCode)
        } catch (e: IllegalArgumentException) {
            request.setAttribute("exception", JWTExceptionList.ILLEGAL_TOKEN.errorCode)
        } catch (e: Exception) {
            logger.error("""
            =================================================
            JwtFilter - doFilterInternal() 오류발생
            token : $jwt
            Exception Message : ${e.message}
            Exception StackTrace : 
            ${e.stackTrace.joinToString("\n")}
            =================================================
            """.trimIndent())
            request.setAttribute("exception", JWTExceptionList.UNKNOWN_ERROR.errorCode)
        }

        filterChain.doFilter(request, response)
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(AUTHORIZATION_HEADER)
        return if (bearerToken.isNotNullOrBlank() && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7)
        } else {
            null
        }
    }

    private fun String?.isNotNullOrBlank(): Boolean {
        return this != null && this.isNotBlank()
    }
}
