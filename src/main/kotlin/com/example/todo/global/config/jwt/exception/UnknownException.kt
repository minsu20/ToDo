package com.example.todo.global.config.jwt.exception

class UnknownException  : JwtException(
        JWTExceptionList.UNKNOWN_ERROR.errorCode,
        JWTExceptionList.UNKNOWN_ERROR.httpStatus,
        JWTExceptionList.UNKNOWN_ERROR.message
)