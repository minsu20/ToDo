package com.example.todo.global.config.jwt.exception

import com.example.todo.global.config.jwt.exception.JWTExceptionList.UNSUPPORTED_TOKEN

class UnsupportedException : JwtException(
        UNSUPPORTED_TOKEN.errorCode,
        UNSUPPORTED_TOKEN.httpStatus,
        UNSUPPORTED_TOKEN.message
)