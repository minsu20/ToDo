package com.example.todo.global.config.jwt.exception

import com.example.todo.global.config.jwt.exception.JWTExceptionList.EXPIRED_TOKEN

class ExpiredException: JwtException(
        EXPIRED_TOKEN.errorCode,
        EXPIRED_TOKEN.httpStatus,
        EXPIRED_TOKEN.message
)