package com.example.todo.global.config.jwt.exception

class IllegalException : JwtException(
        JWTExceptionList.ILLEGAL_TOKEN.errorCode,
        JWTExceptionList.ILLEGAL_TOKEN.httpStatus,
        JWTExceptionList.ILLEGAL_TOKEN.message
)