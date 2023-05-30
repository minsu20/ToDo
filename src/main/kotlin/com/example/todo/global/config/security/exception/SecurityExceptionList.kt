package com.example.todo.global.config.security.exception

import org.springframework.http.HttpStatus

enum class SecurityExceptionList(
        val errorCode: String,
        val httpStatus: HttpStatus,
        val message: String) {

    NO_AUTHENTICATION_FOUND("S0001", HttpStatus.UNAUTHORIZED, "보안 컨텍스트에서 인증 정보를 찾을 수 없습니다."),
    NOT_AUTH_WITH_OAUTH2("S0002", HttpStatus.UNAUTHORIZED, "현재 사용자는 OAuth2를 사용하여 인증되지 않았습니다."),
    LOGGED_IN_NOT_FOUND("S0003", HttpStatus.UNAUTHORIZED, "로그인한 사용자를 찾을 수 없습니다."),
    REQUIRED_LOGGED_IN("SS0001", HttpStatus.UNAUTHORIZED, "해당 서비스는 로그인이 필요한 서비스입니다.");
}






