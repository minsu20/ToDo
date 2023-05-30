package com.example.todo.global.config.jwt.exception

class ExpiredException: JwtException(
        JWTExceptionList.EXPIRED_TOKEN.errorCode,
        JWTExceptionList.EXPIRED_TOKEN.httpStatus,
        JWTExceptionList.EXPIRED_TOKEN.message
)