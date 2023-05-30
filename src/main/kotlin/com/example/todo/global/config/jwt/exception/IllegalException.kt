package com.example.todo.global.config.jwt.exception

import com.example.todo.global.config.jwt.exception.JWTExceptionList.ILLEGAL_TOKEN

class IllegalException : JwtException(
        ILLEGAL_TOKEN.errorCode,
        ILLEGAL_TOKEN.httpStatus,
        ILLEGAL_TOKEN.message
)