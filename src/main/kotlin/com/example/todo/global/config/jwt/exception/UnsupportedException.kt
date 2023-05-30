package com.example.todo.global.config.jwt.exception

class UnsupportedException : JwtException(
        JWTExceptionList.UNSUPPORTED_TOKEN.errorCode,
        JWTExceptionList.UNSUPPORTED_TOKEN.httpStatus,
        JWTExceptionList.UNSUPPORTED_TOKEN.message
)